package ro.ubb.movie_catalog.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.movie_catalog.core.service.interfaces.IClientService;
import ro.ubb.movie_catalog.core.domain.entities.Client;
import ro.ubb.movie_catalog.core.domain.validators.ValidatorException;
import ro.ubb.movie_catalog.core.repository.DB.DBRepositoryClient;


import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class ClientService implements IClientService {
    public static final Logger log = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    private DBRepositoryClient repository;


    @Override
    public Client addClient(Client client) throws ValidatorException {
        log.trace("addClient - method entered: client={}", client);
        log.debug("addClient - added client: client={}", client);
        log.trace("addClient - method finished");
        return repository.save(client);

    }

    @Override
    public List<Client> getAllClients() {
        log.trace("getAllClients - method entered");

        Sort sort = new Sort(Sort.DEFAULT_DIRECTION, "name");

        List<Client> clients = repository.findAll(sort);
        log.trace("getAllClients - method finished");
        return clients;
    }

    @Override
    public Set<Client> filterClientsByName(String name){
        log.trace("filterClientsByName - method entered: name={}", name);
        Iterable<Client> clients = repository.findAll();
        Set<Client> filteredClients = new HashSet<>();
        clients.forEach(filteredClients::add);
        filteredClients.removeIf(c -> !c.getName().contains(name));
        log.debug("filterClientsByName - filtered clients with name={}", name);
        log.trace("filterClientsByName - method finished");
        return filteredClients;
    }
    @Override
    public void deleteClient(Long id) {
        log.trace("deleteClient - method entered: id = {}", id);
        this.repository.deleteById(id);
        log.debug("deleteClient - deleted the client with id={}", id);
        log.trace("deleteClient - method finished");
    }


    @Override
    public Client getById(Long id) {
        log.trace("getById - method entered: id = {}", id);
        Client c = this.repository.findById(id).get();
        log.debug("getById - got the client with id={}, client={}", id, c);
        log.trace("getById - method finished");
        return c;
    }

    @Override
    @Transactional
    public Client updateClient(Long id, Client client) throws ValidatorException {
        log.trace("updateClient - method entered: client = {}", client);
        Client updatedClient = repository.findById(id).orElse(client);
        updatedClient.setAge(client.getAge());
        updatedClient.setName(client.getName());
        repository.save(updatedClient);
        log.debug("updateClient - updated: c={}", updatedClient);
        log.trace("updateClient - method finished");
        return updatedClient;
    }
}

