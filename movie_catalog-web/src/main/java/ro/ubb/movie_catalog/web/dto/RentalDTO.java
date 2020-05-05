package ro.ubb.movie_catalog.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class RentalDTO extends BaseDTO {
    private Long movieID;
    private Long clientID;
    private int numberOfDays;
}
