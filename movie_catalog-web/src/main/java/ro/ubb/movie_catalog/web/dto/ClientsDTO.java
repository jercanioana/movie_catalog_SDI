package ro.ubb.movie_catalog.web.dto;
import lombok.*;
import ro.ubb.movie_catalog.core.domain.entities.Client;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientsDTO {
    private Set<ClientDTO> clients;
}
