package ru.itmo.soa.service;

import dto.MusicBandDTO;
import entity.MusicBand;
import entity.MusicGenre;
import interfaces.MusicBandBean;
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

import ru.itmo.soa.mapper.MusicBandMapper;
import ru.itmo.soa.spec.MusicBandSpecification;
import ru.itmo.soa.spec.SearchCriteria;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MusicBandService {
    private static final Logger logger = LoggerFactory.getLogger(MusicBandService.class);
    private final ModelMapper modelMapper;
    private final MusicBandMapper patcher;
    private final MusicBandBean musicBandBean;

    @Autowired
    public MusicBandService(ModelMapper modelMapper, MusicBandMapper patcher, MusicBandBean musicBandBean) {
        this.modelMapper = modelMapper;
        this.patcher = patcher;
        this.musicBandBean = musicBandBean;
    }

    public List<MusicBand> getAllMusicBands(int page, int size, String sort, String filter) {
        StringBuilder name = new StringBuilder();
        StringBuilder sign = new StringBuilder();
        StringBuilder value = new StringBuilder();

        for (char c : filter.toCharArray()) {
            if (!sign.toString().equals("")) {
                value.append(c);
            } else {
                if (c == '>' || c == '<' || c == '=') {
                    sign.append(c);
                } else {
                    name.append(c);
                }
            }
        }

        return musicBandBean.findAll(page, size, sort, name.toString(), sign.toString(), value.toString());
    }

    public ResponseEntity<MusicBand> createMusicBand(MusicBandDTO musicBandDTO) {
        MusicBand musicBand = modelMapper.map(musicBandDTO, MusicBand.class);
        musicBand.setCreationDate(LocalDateTime.now());
        musicBand.getBestAlbum().setMusicBand(musicBand);

        try {
            musicBand = musicBandBean.save(musicBand);
            return ResponseEntity.status(HttpStatus.CREATED).body(musicBand);
        } catch (Throwable e) {
            logger.warn("Cannot create musicBand", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "MusicBand is invalid");
        }
    }

    public ResponseEntity<MusicBand> getMusicBand(long id) {
        final var musicBand = musicBandBean.findById(id);

        if (musicBand == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "MusicBand is not found");
        }
        return ResponseEntity.ok(musicBand);
    }

    public ResponseEntity<?> deleteMusicBand(long id) {
        if (musicBandBean.existsById(id)) {
            musicBandBean.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "MusicBand is not found");
        }
    }

    public ResponseEntity<MusicBand> updateMusicBand(long id, MusicBandDTO musicBandDTO) {
        var bandOptional = musicBandBean.findById(id);
        if (bandOptional == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "MusicBand is not found");
        }

        MusicBand musicBand = modelMapper.map(musicBandDTO, MusicBand.class);
        musicBand.setId(id);
        musicBand.setCreationDate(LocalDateTime.now());
        musicBand.getBestAlbum().setMusicBand(musicBand);

        try {
            musicBand = musicBandBean.save(musicBand);
            return ResponseEntity.ok(musicBand);
        } catch (Throwable e) {
            logger.warn("Cannot update musicBand", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "MusicBand is invalid");
        }
    }

    public Double getAverageNumberOfParticipants() {
        return musicBandBean.findAverageNumberOfParticipants();
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

        if (musicBandBean.existsByEstablishmentDate(establishmentDate)) {
            musicBandBean.deleteByEstablishmentDate(establishmentDate);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "MusicBand is not found");
        }
    }

    public ResponseEntity<MusicBand> patchMusicBand(long id, MusicBandDTO musicBandDTO) {
        var bandOptional = musicBandBean.findById(id);
        if (bandOptional == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "MusicBand is not found");
        }
        var musicBand = bandOptional;
//        patcher.updateMusicBandFromDto(musicBand, musicBandDTO);

        try {
            musicBandBean.update(musicBandDTO, musicBand);
            return ResponseEntity.ok(musicBand);
        } catch (Throwable e) {
            logger.warn("Cannot patch musicBand", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "MusicBand is invalid");
        }
    }
}