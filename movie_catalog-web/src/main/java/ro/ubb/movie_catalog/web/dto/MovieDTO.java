package ro.ubb.movie_catalog.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class MovieDTO extends BaseDTO{
    private String name;
    private String director;
}
