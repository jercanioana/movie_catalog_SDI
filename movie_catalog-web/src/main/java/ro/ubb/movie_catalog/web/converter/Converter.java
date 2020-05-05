package ro.ubb.movie_catalog.web.converter;

import ro.ubb.movie_catalog.core.domain.entities.BaseEntity;
import ro.ubb.movie_catalog.web.dto.BaseDTO;

public interface Converter<Model extends BaseEntity<Long>, DTO extends BaseDTO> {
    Model convertDtoToModel(DTO dto);

    DTO convertModelToDto(Model model);
}
