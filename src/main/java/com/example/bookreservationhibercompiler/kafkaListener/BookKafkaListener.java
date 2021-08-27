package com.example.bookreservationhibercompiler.kafkaListener;

import com.example.bookreservationhibercompiler.dto.BookDTO;
import com.example.bookreservationhibercompiler.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@EnableKafka
//@Service
public class BookKafkaListener {

    @Autowired
    private BookService bookService;
    ObjectMapper mapper = new ObjectMapper();

    @KafkaListener(topics = {"book-add-topic"})
    public void getTopics(@RequestBody String data) throws Exception {
        BookDTO bookDTO = mapper.readValue(data, BookDTO.class);
        Long id = bookService.create(bookDTO).getId();
        bookDTO.setId(id);
        System.out.println(bookDTO);
    }
}
