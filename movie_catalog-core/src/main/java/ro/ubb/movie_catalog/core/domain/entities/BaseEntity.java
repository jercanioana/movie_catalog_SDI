package ro.ubb.movie_catalog.core.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BaseEntity<ID extends Serializable> {

    @Id
    @GeneratedValue
    private ID id;

    @Override
    public String toString() {
        return "BaseEntity{" + "id = " + id + "}";
    }
}
