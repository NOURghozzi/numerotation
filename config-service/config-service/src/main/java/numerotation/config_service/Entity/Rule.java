package numerotation.config_service.Entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "Rules"
)
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String field;
    private Integer length;
    private String prefix;
    private String suffix;
    private String dateFormat;
    private int orderIndex;
    private String  configid;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
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

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public String getConfigId() {
        return configid;
    }

    public void setConfigId(String configId) {
        this.configid = configId;
    }

    public String getField() {
        return field;
    }
}

