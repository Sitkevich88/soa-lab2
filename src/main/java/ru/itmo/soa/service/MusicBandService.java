package ru.itmo.soa.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.itmo.soa.dto.MusicBandDTO;
import ru.itmo.soa.entity.MusicBand;
import ru.itmo.soa.repo.MusicBandRepository;

import java.time.LocalDateTime;

@Service
public class MusicBandService {
    private final MusicBandRepository musicBandRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MusicBandService(MusicBandRepository musicBandRepository, ModelMapper modelMapper) {
        this.musicBandRepository = musicBandRepository;
        this.modelMapper = modelMapper;
    }

    public Page<MusicBand> getAllMusicBands(Pageable pageable) {
        return musicBandRepository.findAll(pageable);
    }

    public ResponseEntity<MusicBand> createMusicBand(MusicBandDTO musicBandDTO) {
        MusicBand musicBand = modelMapper.map(musicBandDTO, MusicBand.class);
        
        musicBand.setCreationDate(LocalDateTime.now());
        musicBand.getBestAlbum().setMusicBand(musicBand);
        
        musicBand = musicBandRepository.save(musicBand);
        return ResponseEntity.status(HttpStatus.CREATED).body(musicBand);
    }
}