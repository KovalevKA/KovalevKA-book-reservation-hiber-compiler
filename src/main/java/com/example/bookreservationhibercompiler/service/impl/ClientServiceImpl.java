package com.example.bookreservationhibercompiler.service.impl;

import com.example.bookreservationhibercompiler.dto.ClientDTO;
import com.example.bookreservationhibercompiler.entity.Client;
import com.example.bookreservationhibercompiler.service.ClientService;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl
	extends AbstractHibernateService<Client, ClientDTO>
	implements ClientService {

	public ClientServiceImpl() {
		super(Client.class, ClientDTO.class);
	}
}
