package numerotation.generator_service.CommunicationMicro;

import numerotation.generator_service.DTO.RuleDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "config-service", url = "http://localhost:8081")
public interface ConfigServiceClient {

    @GetMapping("/api/rules/{id}")
    List<RuleDto> getRules(@PathVariable("id") Long configId);

}

