package numerotation.generator_service.Map;

import numerotation.generator_service.DTO.InscritDto;
import numerotation.generator_service.Entity.NumeroInscrit;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class InscritMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public InscritMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public NumeroInscrit dtoToEntity(InscritDto dto) {
        if (dto == null) return null;
        return modelMapper.map(dto, NumeroInscrit.class);
    }

    public InscritDto entityToDto(NumeroInscrit entity) {
        if (entity == null) return null;
        return modelMapper.map(entity, InscritDto.class);
    }
}
