package ro.ubb.movie_catalog.core.service.interfaces;

import ro.ubb.movie_catalog.core.domain.entities.Movie;
import ro.ubb.movie_catalog.core.domain.validators.ValidatorException;

import java.util.List;
import java.util.Set;

public interface IMovieService {
    List<Movie> getAllMovies();
    Movie addMovie(Movie movie) throws ValidatorException;
    Set<Movie> filterMoviesByDirector(String director);
    void deleteMovie(Long id);
    Movie updateMovie(Long id, Movie movie) throws ValidatorException;
    Movie getById(Long id);
}
