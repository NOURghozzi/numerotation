package numerotation.counter_service.Entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Counter")
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Counter {
    @Id
    private String id ;

    private int value;

    private String prefix;
    private String suffix;
    private int padding;
    private int initialValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public int getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(int initialValue) {
        this.initialValue = initialValue;
    }
}