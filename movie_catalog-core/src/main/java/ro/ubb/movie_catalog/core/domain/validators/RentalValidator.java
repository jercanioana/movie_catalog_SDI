package ro.ubb.movie_catalog.core.domain.validators;

import ro.ubb.movie_catalog.core.domain.entities.Rentals;

public class RentalValidator implements Validator<Rentals> {

    public RentalValidator() { }

    @Override
    public void validate(Rentals entity) throws ValidatorException {
        if(entity.getId() < 0 )
            throw new ValidatorException("ID cannot be less than 0.");
        if(entity.getMovieID() < 0 )
            throw new ValidatorException("ID cannot be less than 0.");
        if(entity.getClientID() < 0 )
            throw new ValidatorException("ID cannot be less than 0.");
        if(entity.getNumberOfDays() < 0 )
            throw new ValidatorException("Number of days cannot be less than 0.");
    }
}
