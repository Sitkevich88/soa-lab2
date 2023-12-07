package ru.itmo.soa.mapper;

import entity.MusicBand;
import org.mapstruct.*;
import ru.itmo.soa.dto.MusicBandDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MusicBandMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMusicBandFromDto(@MappingTarget MusicBand entity, MusicBandDTO dto);
}