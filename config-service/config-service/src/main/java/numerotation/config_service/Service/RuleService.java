package numerotation.config_service.Service;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import numerotation.config_service.DTO.RuleDto;
import numerotation.config_service.Entity.Rule;
import numerotation.config_service.Map.RuleMapper;
import numerotation.config_service.Repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RuleService {
    @Autowired
    private  RuleRepository ruleRepository;
   private  RuleMapper ruleMapper;

    public List<RuleDto> createAll(List<RuleDto> dtos) {

        List<Rule> rules = dtos.stream()
                .map(RuleMapper::dtoToModel)
                .collect(Collectors.toList());

        List<Rule> savedRules = ruleRepository.saveAll(rules);

        return savedRules.stream()
                .map(RuleMapper::modelToDto)
                .collect(Collectors.toList());
    }

    public  List<RuleDto> getAllRules(String id) {
        List<Rule> rules = ruleRepository.findAllByConfigid( id);
        for (Rule rule : rules) {
            if (rule.getField() == null) {
                System.out.println("Rule with null field detected: " + rule);
            }
        }
        return rules.stream()
                .map(RuleMapper::modelToDto)
                .collect(Collectors.toList());
    }
}


