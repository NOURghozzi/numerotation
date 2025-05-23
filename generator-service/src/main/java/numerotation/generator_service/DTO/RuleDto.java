package numerotation.generator_service.DTO;


import lombok.Data;
@Data
public class RuleDto {
    private String field;
    private Integer length;
    private String prefix;
    private String suffix;
    private String dateFormat;
    private Integer orderIndex;
    private String  configid;


}

