package ru.itmo.soa.mapper;

import org.mapstruct.*;
import ru.itmo.soa.dto.MusicBandDTO;
import ru.itmo.soa.entity.MusicBand;

@Mapper(componentModel = "spring")
public interface MusicBandMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMusicBandFromDto(@MappingTarget MusicBand entity, MusicBandDTO dto);
}