package numerotation.counter_service.Service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import numerotation.counter_service.DTO.CounterDto;
import numerotation.counter_service.Entity.Counter;
import numerotation.counter_service.Map.CounterMapper;
import numerotation.counter_service.Repository.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class CounterService {

   @Autowired
    private  CounterRepository counterRepository;
    @Autowired private CounterMapper counterMapper;
    private final Map<String, Integer> counters = new HashMap<>();

    public int incrementAndGet(String type) {
        int current = counters.getOrDefault(type, 0);
        int next = current + 1;
        counters.put(type, next);
        return next;
    }
    @Transactional
    public String getNextFormattedCounter() {
        Counter counter = counterRepository.findById("default")
                .orElseThrow(() -> new RuntimeException("Counter not initialized"));
        counter.setValue(counter.getValue() + 1);
        counterRepository.save(counter);
        return format(counter);
    }

    public CounterDto init(CounterDto dto) {
        var model = CounterMapper.dtoToModel(dto);
        model.setId("default");
        return CounterMapper.modelToDto(counterRepository.save(model));
    }

    private String format(Counter counter) {
        String formatted = String.format(" %02d "+ counter.getPadding() + " d ", counter.getValue());
        return counter.getPrefix() + formatted + counter.getSuffix();
    }
}


