package ro.ubb.movie_catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.movie_catalog.core.domain.validators.ValidatorException;
import ro.ubb.movie_catalog.core.service.RentalsService;
import ro.ubb.movie_catalog.core.service.interfaces.IRentalsService;
import ro.ubb.movie_catalog.web.converter.RentalsConverter;
import ro.ubb.movie_catalog.web.dto.RentalDTO;
import ro.ubb.movie_catalog.web.dto.RentalsDTO;

@RestController
public class RentalController {
    public static final Logger log = LoggerFactory.getLogger(RentalController.class);

    @Autowired
    private IRentalsService rentalsService;

    @Autowired
    private RentalsConverter rentalsConverter;

    @RequestMapping(value = "/rentals", method = RequestMethod.GET)
    RentalsDTO getRentals(){
        log.trace("getAllRentals - method entered");
        log.trace("getAllRentals - method finished");
        return new RentalsDTO(rentalsConverter.convertModelsToDTOs(rentalsService.getAllRentals()));
    }

    @RequestMapping(value = "/rentals", method = RequestMethod.POST)
    RentalDTO saveRental(@RequestBody RentalDTO rentalDTO) throws ValidatorException {
        log.trace("addRental - method entered: rental={}", rentalDTO);
        log.debug("addRental - added rental: rental={}", rentalDTO);
        log.trace("addRental - method finished");
        return rentalsConverter.convertModelToDto(rentalsService.addRental(rentalsConverter.convertDtoToModel(rentalDTO)));
    }

    @RequestMapping(value = "/rentals/{id}", method = RequestMethod.PUT)
    RentalDTO updateRental(@PathVariable Long id, @RequestBody RentalDTO rentalDTO) throws ValidatorException {
        log.trace("updateRental - method entered: rental = {}", rentalDTO);
        log.trace("updateRental - method finished");
        return rentalsConverter.convertModelToDto(rentalsService.updateRental(id, rentalsConverter.convertDtoToModel((rentalDTO))));

    }

    @RequestMapping(value = "/rentals/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteRental(@PathVariable Long id){
        log.trace("deleteRental - method entered: id = {}", id);
        rentalsService.deleteRental(id);
        log.debug("deleteRental - deleted the rental with id={}", id);
        log.trace("deleteRental - method finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
