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

	@GET
	@Path("")
	public List<BookDTO> getAll() {
		return bookService.findAll();
	}

	@POST
	@Path("")
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	public BookDTO addBook(BookDTO data) {
		return (BookDTO) bookService.create(data);
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
