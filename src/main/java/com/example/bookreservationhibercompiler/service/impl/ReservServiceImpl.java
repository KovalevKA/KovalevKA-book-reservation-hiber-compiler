package com.example.bookreservationhibercompiler.service.impl;

import com.example.bookreservationhibercompiler.dto.ReservDTO;
import com.example.bookreservationhibercompiler.dto.requestBodyParams.RequestParamForCancelReservation;
import com.example.bookreservationhibercompiler.dto.requestBodyParams.RequestParamForCheckReservedBooksByBookId;
import com.example.bookreservationhibercompiler.dto.requestBodyParams.RequestParamForMakeReservetion;
import com.example.bookreservationhibercompiler.entity.Book;
import com.example.bookreservationhibercompiler.entity.Client;
import com.example.bookreservationhibercompiler.entity.Reserv;
import com.example.bookreservationhibercompiler.mapper.CommonMapper;
import com.example.bookreservationhibercompiler.service.ReservService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ReservServiceImpl implements ReservService {

    @Autowired(required = false)
    private CommonMapper<Reserv, ReservDTO> mapper;

    @Autowired(required = false)
    private SessionFactory sessionFactory;

    public List<ReservDTO> getReservationClientListById(Long clientId) {
        return mapper.toDTOs(sessionFactory.getCurrentSession()
                .createQuery("FROM Reserv WHERE client.id = :id AND reservationDateCancel < NOW()")
                .setParameter("id", clientId)
                .getResultList(), ReservDTO.class);
    }

    public List<ReservDTO> checkReservedBooksByBookId(RequestParamForCheckReservedBooksByBookId param) {
        return mapper.toDTOs(sessionFactory.getCurrentSession()
                .createQuery("FROM Reserv WHERE book.id IN (:ids) AND reservationDateCancel > NOW()")
                .setParameter("ids", param.getListBooksId())
                .getResultList(), ReservDTO.class);
    }

    public List<ReservDTO> make(RequestParamForMakeReservetion param) {

        if (param.getListBooksId().isEmpty()) {
            throw new IllegalArgumentException("No book to add");
        }
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(param.getDateTo()));
        } catch (ParseException e) {
            throw new IllegalArgumentException("Date isn't correct. Incorrect input ");
        }
        if (date.compareTo(new Date()) <= 0) {
            throw new IllegalArgumentException("Date isn't correct");
        }

        Client client = sessionFactory.getCurrentSession().find(Client.class, param.getClientId());

        List<Book> books = sessionFactory.getCurrentSession()
                .createQuery("FROM Book WHERE id IN (:ids) AND Reserv.reservationDateCancel <= NOW()")
                .setParameter("ids", param.getListBooksId())
                .getResultList();
        if (books.size() != param.getListBooksId().size())
            throw new IllegalArgumentException("Some books is reserved");
        List<Reserv> reservs = new ArrayList<>();
        Date finalDate = date;
        books.forEach(book -> reservs.add(new Reserv(client, book, finalDate)));
        reservs.forEach(reserv -> sessionFactory.getCurrentSession().save(reserv));
        return null;
    }

    public Integer cancel(RequestParamForCancelReservation param) {
        List<Reserv> reservs = sessionFactory.getCurrentSession()
                .createQuery("FROM Reserv WHERE book.id in (:ids) AND reservationDateCancel > NOW()")
                .setParameter("ids", param.getListReservId())
                .getResultList();
        if (reservs.isEmpty()) {
            throw new IllegalArgumentException("No reservations found");
        }
        AtomicInteger count = new AtomicInteger();
        reservs.forEach(reserv -> {
            reserv.setReservationDateCancel(new Date());
            sessionFactory.getCurrentSession().saveOrUpdate(reserv);
            count.getAndIncrement();
        });
        return count.get();
    }
}
