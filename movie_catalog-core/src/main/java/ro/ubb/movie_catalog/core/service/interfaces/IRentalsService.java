package ro.ubb.movie_catalog.core.service.interfaces;

import ro.ubb.movie_catalog.core.domain.entities.Rentals;
import ro.ubb.movie_catalog.core.domain.validators.ValidatorException;

import java.util.List;

public interface IRentalsService {

    List<Rentals> getAllRentals();
    Rentals addRental(Rentals rental) throws ValidatorException;
    void deleteRental(Long Id);
    Rentals updateRental(Long id, Rentals rental) throws ValidatorException;
    Long getMostRentedMovie();
    Long getMostFrequentClient();

}
