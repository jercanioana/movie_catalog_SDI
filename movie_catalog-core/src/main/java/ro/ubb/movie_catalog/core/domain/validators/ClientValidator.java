package ro.ubb.movie_catalog.core.domain.validators;

import ro.ubb.movie_catalog.core.domain.entities.Client;


public class ClientValidator implements Validator<Client> {
    @Override
    public void validate(Client entity) throws ValidatorException {
        if(entity.getId() < 0 )
            throw new ValidatorException("ID cannot be less than 0.");
        if(entity.getName() == null)
            throw new ValidatorException("Name cannot be null.");
        if(entity.getAge() <= 0)
            throw new ValidatorException("Age cannot be less than 0.");

    }
}
