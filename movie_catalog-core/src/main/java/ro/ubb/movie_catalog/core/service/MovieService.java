package ro.ubb.movie_catalog.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.movie_catalog.core.domain.entities.Movie;
import ro.ubb.movie_catalog.core.domain.validators.ValidatorException;

import ro.ubb.movie_catalog.core.repository.DB.DBRepositoryMovie;
import ro.ubb.movie_catalog.core.service.interfaces.IMovieService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MovieService implements IMovieService {
    public static final Logger log = LoggerFactory.getLogger(MovieService.class);

    @Autowired
    private DBRepositoryMovie repository;

    public Movie addMovie(Movie movie) throws ValidatorException {
        log.trace("addMovie - method entered: movie={}", movie);

        log.debug("addMovie - added movie: movie={}", movie);
        log.trace("addMovie - method finished");
        return repository.save(movie);
    }

    @Override
    public List<Movie> getAllMovies(){
        log.trace("getAllMovies - method entered");
//        Sort sort = new Sort("id");
//        List<Movie> movies =  StreamSupport.stream(repository.findAll(sort).spliterator(), false).collect(Collectors.toList());
        List<Movie> movies = repository.findAll();
        log.trace("getAllMovies - method finished");
        return movies;
    }

    @Override
    public Set<Movie> filterMoviesByDirector(String director){
        log.trace("filterMoviesByDirector - method entered: director={}", director);
        Iterable<Movie> movies = this.repository.findAll();
        Set<Movie> filteredMovies = new HashSet<>();
        movies.forEach(filteredMovies::add);
        filteredMovies.removeIf(movie-> !movie.getDirector().equals(director));
        log.debug("filterMoviesByDirector - filtered movies with director={}", director);
        log.trace("filterMoviesByDirector - method finished");
        return filteredMovies;
    }

    @Override
    public Movie getById(Long id) {
        log.trace("getById - method entered: id = {}", id);
        Movie c = this.repository.findById(id).get();
        log.debug("getById - got the client with id={}, client={}", id, c);
        log.trace("getById - method finished");
        return c;
    }

    @Override
    public void deleteMovie(Long id){
        log.trace("deleteMovie - method entered: id = {}", id);
        this.repository.deleteById(id);
        log.debug("deleteMovie - deleted the movie with id={}", id);
        log.trace("deleteMovie - method finished");
    }

    @Override
    @Transactional
    public Movie updateMovie(Long id, Movie movie) throws ValidatorException {
        log.trace("updateMovie - method entered: movie = {}", movie);
        Movie updatedmovie = repository.findById(movie.getId()).orElse(movie);
        updatedmovie.setName(movie.getName());
        updatedmovie.setDirector(movie.getDirector());
        log.trace("updateMovie - method finished");
        return updatedmovie;
    }
}
