package ro.ubb.movie_catalog.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.movie_catalog.core.domain.entities.Client;
import ro.ubb.movie_catalog.core.domain.entities.Movie;
import ro.ubb.movie_catalog.web.dto.ClientDTO;
import ro.ubb.movie_catalog.web.dto.MovieDTO;

@Component
public class ClientConverter extends BaseConverter<Client, ClientDTO> {
    @Override
    public Client convertDtoToModel(ClientDTO dto) {
        Client client = Client.builder().name(dto.getName()).age(dto.getAge()).build();
        client.setId(dto.getId());
        return client;

    }

    @Override
    public ClientDTO convertModelToDto(Client client) {
        ClientDTO clientDTO = ClientDTO.builder().name(client.getName()).age(client.getAge()).build();
        clientDTO.setId(client.getId());
        return clientDTO;
    }

}
