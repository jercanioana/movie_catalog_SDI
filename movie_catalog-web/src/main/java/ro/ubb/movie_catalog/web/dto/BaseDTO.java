package ro.ubb.movie_catalog.web.dto;

import lombok.*;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BaseDTO implements Serializable {
    private Long id;
}
