package ru.itmo.soa.controller;

import dto.MusicBandDTO;
import entity.MusicBand;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import ru.itmo.soa.service.MusicBandService;

import java.util.List;


@RestController
@CrossOrigin(origins = {"http://localhost:443","http://localhost:7010"})
@RequestMapping("musicbands")
public class MusicBandController {
    private final MusicBandService musicBandService;

    public MusicBandController(MusicBandService musicBandService) {
        this.musicBandService = musicBandService;
    }

    @GetMapping
    public Page<MusicBand> getAllMusicBands(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sort", defaultValue = "id") String sort,
            @RequestParam(value = "search", defaultValue = "") String filter
    ) {
        if (page < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Page must be non-negative");
        }
        if (size < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Size must be positive");
        }
        if (!sort.equals("id") && !sort.equals("name") && !sort.equals("numberOfParticipants") && !sort.equals("establishmentDate") && !sort.equals("genre")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid sorting parameter");
        }

        List<MusicBand> musicBands = musicBandService.getAllMusicBands(page, size, sort, filter);
        PageRequest pageable = PageRequest.of(page, size, Sort.by(sort));

        return new PageImpl<>(musicBands, pageable, musicBands.size());
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<MusicBand> getMusicBand(@PathVariable long id) {
        return musicBandService.getMusicBand(id);
    }

    @PostMapping
    public ResponseEntity<MusicBand> createMusicBand(@Valid @RequestBody MusicBandDTO musicBandDTO) {
        return musicBandService.createMusicBand(musicBandDTO);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<MusicBand> updateMusicBand(@PathVariable long id, @Valid @RequestBody MusicBandDTO musicBandDTO) {
        return musicBandService.patchMusicBand(id, musicBandDTO);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<MusicBand> patchMusicBand(@PathVariable long id, @RequestBody MusicBandDTO musicBandDTO) {
        return musicBandService.patchMusicBand(id, musicBandDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteMusicBand(@PathVariable long id) {
        return musicBandService.deleteMusicBand(id);
    }
}