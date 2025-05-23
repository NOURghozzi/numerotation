package numerotation.config_service.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import numerotation.config_service.DTO.RuleDto;
import numerotation.config_service.Service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
@RequiredArgsConstructor
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class RuleController {

    @Autowired
    private RuleService ruleService;


    @PostMapping
    public ResponseEntity<List<RuleDto>>  createRules(@RequestBody List<RuleDto> dtos){
      //  log.info("Attempting to save Team");
        return new ResponseEntity<>(ruleService.createAll(dtos), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public List<RuleDto> getRules(@PathVariable String id) {
        return ruleService.getAllRules(id);
    }
}

