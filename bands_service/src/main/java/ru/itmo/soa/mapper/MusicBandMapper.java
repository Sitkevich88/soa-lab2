package ru.itmo.soa.mapper;

import dto.MusicBandDTO;
import entity.MusicBand;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MusicBandMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMusicBandFromDto(@MappingTarget MusicBand entity, MusicBandDTO dto);
}