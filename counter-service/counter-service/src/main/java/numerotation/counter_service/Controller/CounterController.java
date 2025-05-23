package numerotation.counter_service.Controller;

import lombok.AllArgsConstructor;
import numerotation.counter_service.DTO.CounterDto;
import numerotation.counter_service.Service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/counter")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class CounterController {

    @Autowired
    private  CounterService counterService;


    @PostMapping("/init")
    public ResponseEntity<CounterDto> initCounter(@RequestBody CounterDto dto) {
        CounterDto savedDto = counterService.init(dto);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }
    @GetMapping("/next")
    public ResponseEntity<String> getNext() {
        return ResponseEntity.ok(counterService.getNextFormattedCounter());
    }

    @PostMapping("/{type}/increment")
    public ResponseEntity<Integer> incrementCounter(@PathVariable String type) {
        int newValue = counterService.incrementAndGet(type);
        return ResponseEntity.ok(newValue);
    }
}

