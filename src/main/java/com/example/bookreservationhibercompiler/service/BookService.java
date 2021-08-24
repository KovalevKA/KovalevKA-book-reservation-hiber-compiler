package com.example.bookreservationhibercompiler.service;

import com.example.bookreservationhibercompiler.dto.BookDTO;
import com.example.bookreservationhibercompiler.entity.Book;
import org.springframework.stereotype.Service;

@Service
public class BookService extends AbstractHibernateService<Book, BookDTO> {

    public BookService() {
        super(Book.class, BookDTO.class);
    }
}
