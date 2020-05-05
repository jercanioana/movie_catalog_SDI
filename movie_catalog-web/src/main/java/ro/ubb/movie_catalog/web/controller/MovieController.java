package ro.ubb.movie_catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.movie_catalog.core.domain.validators.ValidatorException;
import ro.ubb.movie_catalog.core.service.MovieService;
import ro.ubb.movie_catalog.core.service.interfaces.IMovieService;
import ro.ubb.movie_catalog.web.converter.MovieConverter;

import ro.ubb.movie_catalog.web.dto.MovieDTO;
import ro.ubb.movie_catalog.web.dto.MoviesDTO;

@RestController
public class MovieController {
    public static final Logger log = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private IMovieService movieService;

    @Autowired
    private MovieConverter movieConverter;

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    MoviesDTO getMovies(){
        log.trace("getAllMovies - method entered");
        log.trace("getAllMovies - method finished");
        return new MoviesDTO(movieConverter.convertModelsToDTOs(movieService.getAllMovies()));
    }

    @RequestMapping(value = "/movies", method = RequestMethod.POST)
    MovieDTO saveMovie(@RequestBody MovieDTO movieDTO) throws ValidatorException {
        log.trace("addMovie - method entered: movie={}", movieDTO);

        log.debug("addMovie - added movie: movie={}", movieDTO);
        log.trace("addMovie - method finished");
        return movieConverter.convertModelToDto(movieService.addMovie(movieConverter.convertDtoToModel(movieDTO)));
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.PUT)
    MovieDTO updateMovie(@PathVariable Long id, @RequestBody MovieDTO movieDTO) throws ValidatorException {
        log.trace("updateMovie - method entered: id = {}", id);

        log.trace("updateMovie - method finished");
        return movieConverter.convertModelToDto(movieService.updateMovie(id, movieConverter.convertDtoToModel((movieDTO))));
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteMovie(@PathVariable Long id){
        log.trace("deleteMovie - method entered: id = {}",id);
        movieService.deleteMovie(id);
        log.debug("deleteMovie - deleted the movie with id={}", id);
        log.trace("deleteMovie - method finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
