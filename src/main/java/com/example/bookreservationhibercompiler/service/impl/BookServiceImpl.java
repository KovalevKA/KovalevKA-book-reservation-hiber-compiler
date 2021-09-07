package com.example.bookreservationhibercompiler.service.impl;

import com.example.bookreservationhibercompiler.dto.BookDTO;
import com.example.bookreservationhibercompiler.entity.Book;
import com.example.bookreservationhibercompiler.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl
        extends CommonServiceImpl<Book, BookDTO>
        implements BookService {

    public BookServiceImpl() {
        super(Book.class, BookDTO.class);
    }
}
