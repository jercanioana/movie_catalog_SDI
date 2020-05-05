package ro.ubb.movie_catalog.core.service.interfaces;

import ro.ubb.movie_catalog.core.domain.entities.Client;
import ro.ubb.movie_catalog.core.domain.validators.ValidatorException;

import java.util.List;
import java.util.Set;

public interface IClientService {

    List<Client> getAllClients();
    Client addClient(Client client) throws ValidatorException;
    Set<Client> filterClientsByName(String name);
    void deleteClient(Long id);
    Client updateClient(Long id,Client client) throws ValidatorException;
    Client getById(Long id);
}
