package com.example.bookreservationhibercompiler.service;

import com.example.bookreservationhibercompiler.dto.ClientDTO;
import com.example.bookreservationhibercompiler.entity.Client;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends AbstractHibernateService<Client, ClientDTO> {
    public ClientService() {
        super(Client.class, ClientDTO.class);
    }
}
