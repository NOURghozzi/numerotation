package numerotation.config_service.Map;

import numerotation.config_service.DTO.RuleDto;
import numerotation.config_service.Entity.Rule;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RuleMapper {

    private static  ModelMapper modelMapper ;
    @Autowired
    public RuleMapper(ModelMapper modelMapper) {
        RuleMapper.modelMapper = modelMapper;
    }

    public static Rule dtoToModel(RuleDto ruleDto) {
        return modelMapper.map(ruleDto, Rule.class);
    }

    public static RuleDto modelToDto(Rule rule) {
        return modelMapper.map(rule, RuleDto.class);
    }
}
