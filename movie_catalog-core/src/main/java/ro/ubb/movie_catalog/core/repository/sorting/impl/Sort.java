package ro.ubb.movie_catalog.core.repository.sorting.impl;



import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Sort   {
    public enum Direction {
        ASC,
        DESC
    }

    private List<Pair<String, Direction>> fields;

    public Sort(Direction direction, String... fileds) {
        this.fields = new ArrayList<>();
        for(String field: fileds) {
            this.fields.add(new Pair<>(field, direction));
        }
    }

    public Sort(String... fields) {
        this(Direction.ASC, fields);
    }

    public List<Pair<String, Direction>> getFields() {
        return fields;
    }

    public void setFields(List<Pair<String, Direction>> fields) {
        this.fields = fields;
    }

    public Sort and(Sort other) {
        fields.addAll(other.getFields());
        return this;
    }
}
