package ro.ubb.movie_catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.movie_catalog.core.domain.entities.Client;
import ro.ubb.movie_catalog.core.domain.validators.ValidatorException;
import ro.ubb.movie_catalog.core.service.ClientService;
import ro.ubb.movie_catalog.core.service.interfaces.IClientService;
import ro.ubb.movie_catalog.web.converter.ClientConverter;
import ro.ubb.movie_catalog.web.dto.ClientDTO;
import ro.ubb.movie_catalog.web.dto.ClientsDTO;


@RestController
public class ClientController {
    public static final Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private IClientService clientService;

    @Autowired
    private ClientConverter clientConverter;



    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    ClientsDTO getClients(){
        log.trace("getAllClients - method entered");
        log.trace("getAllClients - method finished");
        return new ClientsDTO(clientConverter.convertModelsToDTOs(clientService.getAllClients()));
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    ClientDTO saveClient(@RequestBody ClientDTO clientDTO) throws ValidatorException {
        log.trace("addClient - method entered: client={}", clientDTO);
        log.debug("addClient - added client: client={}", clientDTO);

        ClientDTO clientDTO1 =  clientConverter.convertModelToDto(clientService.addClient(clientConverter.convertDtoToModel(clientDTO)));
        log.trace("addClient - method finished");
        return  clientDTO1;
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.PUT)
    ClientDTO updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) throws ValidatorException {
        log.trace("updateClient - method entered: client = {}", clientDTO);
        ClientDTO updatedclient =  clientConverter.convertModelToDto(clientService.updateClient(id, clientConverter.convertDtoToModel((clientDTO))));
        log.debug("updateClient - updated: c={}", updatedclient);
        log.trace("updateClient - method finished");
        return updatedclient;
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteClient(@PathVariable Long id){
        log.trace("deleteClient - method entered: id = {}", id);
        clientService.deleteClient(id);
        log.debug("deleteClient - deleted the client with id={}", id);
        log.trace("deleteClient - method finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/clients'/{name}", method = RequestMethod.GET)
    ClientsDTO filterClients(@PathVariable String name){
        log.trace("getAllClients - method entered");
        log.trace("getAllClients - method finished");
        return new ClientsDTO(clientConverter.convertModelsToDTOs(clientService.filterClientsByName(name)));
    }

}
