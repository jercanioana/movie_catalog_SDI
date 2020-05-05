package ro.ubb.movie_catalog.web.converter;

import ro.ubb.movie_catalog.core.domain.entities.BaseEntity;
import ro.ubb.movie_catalog.web.dto.BaseDTO;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class BaseConverter<Model extends BaseEntity<Long>, DTO extends BaseDTO> implements Converter<Model, DTO>{

    public Set<Long> convertModelsToIDSs(Set<Model> models){
        return models.stream()
                .map(model->model.getId())
                .collect(Collectors.toSet());
    }

    public Set<Long> convertDTOsToIDs(Set<DTO> dtos){
        return dtos.stream()
                .map(dto -> dto.getId())
                .collect(Collectors.toSet());
    }

    public Set<DTO> convertModelsToDTOs(Collection<Model> models){
        return models.stream()
                .map(model -> convertModelToDto(model))
                .collect(Collectors.toSet());
    }
}
