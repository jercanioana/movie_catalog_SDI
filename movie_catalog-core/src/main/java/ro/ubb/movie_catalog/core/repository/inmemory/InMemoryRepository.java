//package ro.ubb.movie_catalog.core.repository.inmemory;
//
//
//import jdk.nashorn.internal.ir.annotations.Ignore;
//import ro.ubb.movie_catalog.core.domain.entities.BaseEntity;
//import ro.ubb.movie_catalog.core.domain.validators.Validator;
//import ro.ubb.movie_catalog.core.domain.validators.ValidatorException;
//import ro.ubb.movie_catalog.core.repository.Repository;
//
//
//import javax.xml.parsers.ParserConfigurationException;
//import java.io.Serializable;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//import java.util.Set;
//import java.util.stream.Collector;
//import java.util.stream.Collectors;
//
//
//public class InMemoryRepository<ID extends Serializable, T extends BaseEntity<ID>> implements Repository<ID, T> {
//    private Map<ID, T> entities;
//    private Validator<T> validator;
//
//    public InMemoryRepository(Validator<T> validator) {
//        this.validator = validator;
//        this.entities = new HashMap<>();
//    }
//
//    @Override
//    public Optional<T> findOne(ID id) {
//        if (id == null) {
//            throw new IllegalArgumentException("id must not be null.");
//        }
//        return Optional.ofNullable(entities.get(id));
//    }
//
//    @Override
//    public Iterable<T> findAll() {
//        Set<T> allEntities = entities.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toSet());
//        return allEntities;
//    }
//
//    @Override
//    public Optional<T> save(T entity) throws ValidatorException {
//        if (entity == null) {
//            throw new IllegalArgumentException("Entity cannot be null.");
//        }
//        validator.validate(entity);
//        return Optional.ofNullable(entities.putIfAbsent(entity.getId(), entity));
//
//    }
//
//    @Override
//    public Optional<T> delete(ID id) {
//        if (id == null) {
//            throw new IllegalArgumentException("id must not be null.");
//        }
//        return Optional.ofNullable(entities.remove(id));
//    }
//
//    @Override
//    public Optional<T> update(T entity) throws ValidatorException {
//        if (entity == null) {
//            throw new IllegalArgumentException("Entity cannot be null.");
//        }
//        validator.validate(entity);
//        return Optional.ofNullable(entities.computeIfPresent(entity.getId(), (k, v) -> entity));
//    }
//}
