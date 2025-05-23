package numerotation.counter_service.DTO;


import lombok.Data;

@Data
public class CounterDto {
    private String id ;
    private String prefix;
    private String suffix;
    private int padding;
    private int initialValue;

    public int getInitialValue() {
        return initialValue;
    }
}
