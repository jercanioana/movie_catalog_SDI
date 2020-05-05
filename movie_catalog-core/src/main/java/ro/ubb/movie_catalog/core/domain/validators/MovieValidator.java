package ro.ubb.movie_catalog.core.domain.validators;

import ro.ubb.movie_catalog.core.domain.entities.Movie;


public class MovieValidator implements Validator<Movie> {

    @Override
    public void validate(Movie entity) throws ValidatorException {
        if(entity.getId() < 0 )
            throw new ValidatorException("ID cannot be less than 0.");
        if(entity.getName() == null || entity.getDirector() == null)
            throw new ValidatorException("Attributes cannot be null.");
    }
}
