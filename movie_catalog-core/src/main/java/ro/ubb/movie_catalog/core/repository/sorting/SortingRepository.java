package ro.ubb.movie_catalog.core.repository.sorting;

import ro.ubb.movie_catalog.core.domain.entities.BaseEntity;
import ro.ubb.movie_catalog.core.repository.Repository;

import java.io.Serializable;

public interface SortingRepository<ID extends Serializable,
        T extends BaseEntity<ID>>
        extends Repository<ID, T> {

//    Iterable<T> findAll(Sort sort);

}

