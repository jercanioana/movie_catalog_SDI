package ro.ubb.movie_catalog.core.domain.entities;


import lombok.*;

import javax.persistence.Entity;


@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@Entity
public class Rentals extends BaseEntity<Long> {

    private Long clientID;
    private Long movieID;
    private int numberOfDays;


}
