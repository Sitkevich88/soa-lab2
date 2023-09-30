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
import org.springframework.web.server.ResponseStatusException;
import ru.itmo.soa.dto.MusicBandDTO;
import ru.itmo.soa.entity.MusicBand;
import ru.itmo.soa.entity.MusicGenre;
import ru.itmo.soa.mapper.MusicBandMapper;
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
    private final MusicBandMapper patcher;

    @Autowired
    public MusicBandService(MusicBandRepository musicBandRepository, ModelMapper modelMapper, MusicBandMapper patcher) {
        this.musicBandRepository = musicBandRepository;
        this.modelMapper = modelMapper;
        this.patcher = patcher;
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "MusicBand is invalid");
        }
    }

    public ResponseEntity<MusicBand> getMusicBand(long id) {
        final var musicBand = musicBandRepository.findById(id);

        return musicBand
                .map(ResponseEntity::ok)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "MusicBand is not found")
                );
    }

    public ResponseEntity<?> deleteMusicBand(long id) {
        if (musicBandRepository.existsById(id)) {
            musicBandRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "MusicBand is not found");
        }
    }

    public ResponseEntity<MusicBand> updateMusicBand(long id, MusicBandDTO musicBandDTO) {
        if (!musicBandRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "MusicBand is not found");
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "MusicBand is invalid");
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Establishment Date is invalid");
        }

        if (musicBandRepository.existsByEstablishmentDate(establishmentDate)) {
            musicBandRepository.deleteByEstablishmentDate(establishmentDate);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "MusicBand is not found");
        }
    }

    public ResponseEntity<MusicBand> patchMusicBand(long id, MusicBandDTO musicBandDTO) {
        var bandOptional = musicBandRepository.findById(id);
        if (bandOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "MusicBand is not found");
        }
        var musicBand = bandOptional.get();
        patcher.updateMusicBandFromDto(musicBand, musicBandDTO);

        try {
            musicBand = musicBandRepository.save(musicBand);
            return ResponseEntity.ok(musicBand);
        } catch (Throwable e) {
            logger.warn("Cannot patch musicBand", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "MusicBand is invalid");
        }
    }
}