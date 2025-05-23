package numerotation.generator_service.DTO;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CounterDto {

    @NotBlank(message = "Type is mandatory")
    private String type;

    @Min(value = 0, message = "Initial value must be greater than or equal to 0")
    private int initialValue;

    @Min(value = 1, message = "Step must be at least 1")
    private int step = 1;

    @Min(value = 1, message = "Length must be at least 1")
    private int length = 5;

    private String prefix = "";
    private String suffix = "";
}

