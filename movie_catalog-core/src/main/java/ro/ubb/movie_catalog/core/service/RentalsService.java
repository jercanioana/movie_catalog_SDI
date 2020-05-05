package ro.ubb.movie_catalog.core.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.movie_catalog.core.service.interfaces.IRentalsService;
import ro.ubb.movie_catalog.core.domain.entities.Rentals;
import ro.ubb.movie_catalog.core.domain.validators.ValidatorException;
import ro.ubb.movie_catalog.core.repository.DB.DBRepositoryClient;
import ro.ubb.movie_catalog.core.repository.DB.DBRepositoryMovie;
import ro.ubb.movie_catalog.core.repository.DB.DBRepositoryRental;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

@Service
public class RentalsService implements IRentalsService {
    public static final Logger log = LoggerFactory.getLogger(RentalsService.class);

    @Autowired
    private DBRepositoryRental repository;
    @Autowired
    private DBRepositoryClient clientRepository;
    @Autowired
    private DBRepositoryMovie movieRepository;

    public Rentals addRental(Rentals rental) throws ValidatorException {
        log.trace("addRental - method entered: rental={}", rental);
        if (this.clientRepository.findById(rental.getId()).isPresent() && this.movieRepository.findById(rental.getId()).isPresent())
            log.debug("addRental - added rental: rental={}", rental);
        log.trace("addRental - method finished");
        return this.repository.save(rental);


    }

    public void deleteRental(Long id){
        log.trace("deleteRental - method entered: id = {}", id);
        this.repository.deleteById(id);
        log.debug("deleteRental - deleted the rental with id={}", id);
        log.trace("deleteRental - method finished");
    }

    public Rentals updateRental(Long id, Rentals rental) throws ValidatorException {
        log.trace("updateRental - method entered: rental = {}", rental);
        Rentals updatedRental = repository.findById(rental.getId()).orElse(rental);
        updatedRental.setClientID(rental.getClientID());
        updatedRental.setMovieID(rental.getMovieID());
        updatedRental.setNumberOfDays(rental.getNumberOfDays());
        log.trace("updateRental - method finished");
        return updatedRental;
    }

    public List<Rentals> getAllRentals(){
        log.trace("getAllRentals - method entered");
//        Sort sort = new Sort("id");
//        List<Rentals> rentals =  StreamSupport.stream(repository.findAll(sort).spliterator(), false).collect(Collectors.toList());
        List<Rentals> rentals = repository.findAll();
        log.trace("getAllRentals - method finished");
        return rentals;
    }

    public Long getMostRentedMovie() {
        log.trace("getMostRentedMovie - method entered");
        Iterable<Rentals> allRentals = this.repository.findAll();
        Map<Long, Long> moviesID = StreamSupport.stream(allRentals.spliterator(), false).map(Rentals::getMovieID)
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));

        Long ID =  Collections.max(moviesID.entrySet(), (entry1, entry2) -> (int) (entry1.getValue() - entry2.getValue())).getKey();
        log.trace("getMostRentedMovie - method finished");
        return ID;
    }

    public Long getMostFrequentClient() {
        log.trace("getMostFrequentClient - method entered");
        Iterable<Rentals> allRentals = this.repository.findAll();
        Map<Long, Long> clientsID = StreamSupport.stream(allRentals.spliterator(), false).map(Rentals::getClientID)
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        Long ID = Collections.max(clientsID.entrySet(), (entry1, entry2) -> (int) (entry1.getValue() - entry2.getValue())).getKey();
        log.trace("getMostFrequentClient - method finished");
        return ID;

    }
}
