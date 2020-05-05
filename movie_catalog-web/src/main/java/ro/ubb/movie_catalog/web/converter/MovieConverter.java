package ro.ubb.movie_catalog.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.movie_catalog.core.domain.entities.Movie;
import ro.ubb.movie_catalog.web.dto.MovieDTO;

@Component
public class MovieConverter extends BaseConverter<Movie, MovieDTO>{
    @Override
    public Movie convertDtoToModel(MovieDTO dto) {
        Movie movie = Movie.builder().name(dto.getName()).director(dto.getDirector()).build();
        movie.setId(dto.getId());
        return movie;
    }

    @Override
    public MovieDTO convertModelToDto(Movie movie) {
        MovieDTO movieDTO = MovieDTO.builder().name(movie.getName()).director(movie.getDirector()).build();
        movieDTO.setId(movie.getId());
        return movieDTO;
    }
}
