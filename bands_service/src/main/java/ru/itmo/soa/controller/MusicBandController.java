package ru.itmo.soa.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.itmo.soa.dto.MusicBandDTO;
import ru.itmo.soa.entity.MusicBand;
import ru.itmo.soa.service.MusicBandService;

import javax.validation.Valid;


@RestController
@CrossOrigin("*") //todo remove it
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
        
        PageRequest pageable = PageRequest.of(page, size, Sort.by(sort));
        return musicBandService.getAllMusicBands(pageable, filter);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<MusicBand> getMusicBand(@PathVariable long id) {
        return musicBandService.getMusicBand(id);
    }

    @PostMapping
    public ResponseEntity<MusicBand> createMusicBand(@Valid @RequestBody MusicBandDTO musicBandDTO) {
        return musicBandService.createMusicBand(musicBandDTO);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<MusicBand> updateMusicBand(@PathVariable long id, @Valid @RequestBody MusicBandDTO musicBandDTO) {
        return musicBandService.updateMusicBand(id, musicBandDTO);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<MusicBand> patchMusicBand(@PathVariable long id, @Valid @RequestBody MusicBandDTO musicBandDTO) {
        return musicBandService.patchMusicBand(id, musicBandDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteMusicBand(@PathVariable long id) {
        return musicBandService.deleteMusicBand(id);
    }
}