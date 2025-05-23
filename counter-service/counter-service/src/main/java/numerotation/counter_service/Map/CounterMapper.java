package numerotation.counter_service.Map;

import numerotation.counter_service.DTO.CounterDto;
import numerotation.counter_service.Entity.Counter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CounterMapper {

    private static  ModelMapper modelMapper ;
    @Autowired
    public CounterMapper(ModelMapper modelMapper) {
        CounterMapper.modelMapper = modelMapper;
    }

    public static Counter dtoToModel(CounterDto counterDto) {
        return modelMapper.map(counterDto, Counter.class);
    }

    public static CounterDto modelToDto(Counter counter) {
        return modelMapper.map(counter, CounterDto.class);
    }
}
