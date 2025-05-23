package numerotation.generator_service.DTO;


import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
@Data
public class InscritDto {
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    @Min(0)
    private Integer counter;

}