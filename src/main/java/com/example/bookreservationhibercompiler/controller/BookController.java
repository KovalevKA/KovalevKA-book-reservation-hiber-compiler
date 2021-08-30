package com.example.bookreservationhibercompiler.controller;

import com.example.bookreservationhibercompiler.dto.BookDTO;
import com.example.bookreservationhibercompiler.entity.Book;
import com.example.bookreservationhibercompiler.service.BookService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.example.bookreservationhibercompiler.service.CommonElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
@Path("books")
@Consumes(MediaType.APPLICATION_JSON_VALUE)
@Produces(MediaType.APPLICATION_JSON_VALUE)
public class BookController {

	@Autowired
	private BookService bookService;
	@Autowired
	private CommonElasticSearchService<BookDTO> elasticSearchService;

	@GET
	public List<BookDTO> getAll() {
		return bookService.findAll();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	public BookDTO addBook(BookDTO data) {
		return (BookDTO) bookService.create(data);
	}

	@POST
	@Path("search")
	public List<BookDTO> search(String keyWords) throws Exception {
		return elasticSearchService.search(keyWords);
	}

	@GET
	@Path("{id}")
	public BookDTO getInfoAboutBookByBookId(@PathParam("id") Long id) {
		return (BookDTO) bookService.findOne(id);
	}

	@POST
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	public BookDTO editBook(@PathParam("id") Long id, BookDTO data) {
		return (BookDTO) bookService.update(id, data);
	}

	@DELETE
	@Path("{id}")
	public void deleteBook(
		@PathParam("id") Long id) {
		bookService.deleteById(id);
	}

}
