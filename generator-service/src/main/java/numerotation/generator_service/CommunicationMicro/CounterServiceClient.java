package numerotation.generator_service.CommunicationMicro;

import numerotation.generator_service.DTO.CounterDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "counter-service", url = "http://localhost:8082")
public interface CounterServiceClient {

    @GetMapping("/api/counter/next")
    String getNextCounter();

    @PostMapping("/api/counter/init")
    String initCounter(@RequestBody CounterDto dto);
    @PostMapping("/api/counter/{type}/increment")
    int incrementCounter(@PathVariable("type") String type);
}

