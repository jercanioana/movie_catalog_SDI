package ro.ubb.movie_catalog.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.movie_catalog.core.domain.entities.Rentals;
import ro.ubb.movie_catalog.web.dto.RentalDTO;

@Component
public class RentalsConverter extends BaseConverter<Rentals, RentalDTO> {
    @Override
    public Rentals convertDtoToModel(RentalDTO dto) {
        Rentals rentals = Rentals.builder().clientID(dto.getClientID())
                .movieID(dto.getMovieID()).numberOfDays(dto.getNumberOfDays())
                .build();
        rentals.setId(dto.getId());
        return rentals;
    }

    @Override
    public RentalDTO convertModelToDto(Rentals rentals) {
        RentalDTO rentalDTO = RentalDTO.builder().movieID(rentals.getMovieID())
                .clientID(rentals.getClientID()).numberOfDays(rentals.getNumberOfDays())
                .build();
        rentalDTO.setId(rentals.getId());
        return rentalDTO;
    }
}
