package ro.ubb.movie_catalog.core.domain.entities;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Movie extends BaseEntity<Long>{
    private String name;
    private String director;


}
