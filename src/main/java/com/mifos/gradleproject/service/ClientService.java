package com.mifos.gradleproject.service;

import com.mifos.gradleproject.entity.Client;
import com.mifos.gradleproject.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepo;

    public Client createClient(Client client) {
        return clientRepo.save(client);
    }

    public List<Client> getAllClients() {
        return clientRepo.findAll();
    }
}
