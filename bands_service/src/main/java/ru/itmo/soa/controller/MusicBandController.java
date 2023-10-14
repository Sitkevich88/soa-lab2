package ru.itmo.soa.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.soa.dto.MusicBandDTO;
import ru.itmo.soa.entity.MusicBand;
import ru.itmo.soa.service.MusicBandService;

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
            @RequestParam(value = "search", defaultValue = "") String search
    ) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(sort));
        return musicBandService.getAllMusicBands(pageable, search);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<MusicBand> getMusicBand(@PathVariable long id) {
        return musicBandService.getMusicBand(id);
    }

    @PostMapping
    public ResponseEntity<MusicBand> createMusicBand(@RequestBody MusicBandDTO musicBandDTO) {
        return musicBandService.createMusicBand(musicBandDTO);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<MusicBand> updateMusicBand(@PathVariable long id, @RequestBody MusicBandDTO musicBandDTO) {
        return musicBandService.updateMusicBand(id, musicBandDTO);
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