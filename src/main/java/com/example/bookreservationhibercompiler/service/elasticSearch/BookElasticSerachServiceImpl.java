package com.example.bookreservationhibercompiler.service.elasticSearch;

import com.example.bookreservationhibercompiler.dto.BookDTO;
import com.example.bookreservationhibercompiler.entity.Book;
import com.example.bookreservationhibercompiler.mapper.CommonMapper;
import com.example.bookreservationhibercompiler.service.CommonElasticSearchService;
import org.hibernate.SessionFactory;
import org.hibernate.search.backend.elasticsearch.ElasticsearchExtension;
import org.hibernate.search.backend.elasticsearch.search.query.ElasticsearchSearchQuery;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookElasticSerachServiceImpl implements CommonElasticSearchService<BookDTO> {

    @Autowired(required = false)
    private CommonMapper<Book, BookDTO> mapper;

    private final SessionFactory sessionFactory;

    @Autowired
    public BookElasticSerachServiceImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        try {
            SearchSession searchSession = Search.session(sessionFactory.getCurrentSession());
            MassIndexer indexer = searchSession.massIndexer(Book.class)
                    .threadsToLoadObjects(7);
            indexer.startAndWait();
        } catch (InterruptedException e) {
            throw new IllegalArgumentException("Cant create indexer");
        }

    }

    @Override
    public List<BookDTO> search(String keyWords) {
        SearchSession searchSession = Search.session(sessionFactory.getCurrentSession());
        ElasticsearchSearchQuery<Book> query = searchSession.search(Book.class)
                .extension(ElasticsearchExtension.get())
                .where(f -> f.fromJson(keyWords))
                .toQuery();
        ElasticsearchSearchQuery<Book> searchQuery = query.extension(ElasticsearchExtension.get());
        return mapper.toDTOs(searchQuery.fetchAll().hits(), BookDTO.class);
    }

}
