package ru.itmo.soa.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.itmo.soa.dto.MusicBandDTO;
import ru.itmo.soa.entity.MusicBand;
import ru.itmo.soa.entity.MusicGenre;
import ru.itmo.soa.repo.MusicBandRepository;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MusicBandService {
    private static final Logger logger = LoggerFactory.getLogger(MusicBandService.class);
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

        try {
            musicBand = musicBandRepository.save(musicBand);
            return ResponseEntity.status(HttpStatus.CREATED).body(musicBand);
        } catch (Throwable e) {
            logger.warn("Cannot create musicBand", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    public ResponseEntity<MusicBand> getMusicBand(long id) {
        final var musicBand = musicBandRepository.findById(id);

        return musicBand
                .map(ResponseEntity::ok)
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                );
    }

    public ResponseEntity<?> deleteMusicBand(long id) {
        if (musicBandRepository.existsById(id)) {
            musicBandRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<MusicBand> updateMusicBand(long id, MusicBandDTO musicBandDTO) {
        if (!musicBandRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        MusicBand musicBand = modelMapper.map(musicBandDTO, MusicBand.class);
        musicBand.setId(id);
        musicBand.setCreationDate(LocalDateTime.now());
        musicBand.getBestAlbum().setMusicBand(musicBand);

        try {
            musicBand = musicBandRepository.save(musicBand);
            return ResponseEntity.ok(musicBand);
        } catch (Throwable e) {
            logger.warn("Cannot update musicBand", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    public Double getAverageNumberOfParticipants() {
        return musicBandRepository.findAverageNumberOfParticipants();
    }

    public List<MusicGenre> getAllGenres() {
        return List.of(MusicGenre.values());
    }

    public ResponseEntity<?> deleteMusicBandByEstablishmentDate(String establishmentDateString) {
        LocalDate establishmentDate;
        try {
            establishmentDate = LocalDate.parse(establishmentDateString);
        } catch (DateTimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (musicBandRepository.existsByEstablishmentDate(establishmentDate)) {
            musicBandRepository.deleteByEstablishmentDate(establishmentDate);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}