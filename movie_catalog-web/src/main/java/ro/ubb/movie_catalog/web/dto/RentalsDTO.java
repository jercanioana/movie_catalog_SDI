package ro.ubb.movie_catalog.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.ubb.movie_catalog.core.domain.entities.Client;
import ro.ubb.movie_catalog.core.domain.entities.Rentals;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RentalsDTO {
    private Set<RentalDTO> rentals;
}