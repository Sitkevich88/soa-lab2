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
            @RequestParam(name = "sort", defaultValue = "id") String sort){
        PageRequest pageable = PageRequest.of(page, size, Sort.by(sort));
        return musicBandService.getAllMusicBands(pageable);
    }
    
    @PostMapping
    public ResponseEntity<MusicBand> createMusicBand(@RequestBody MusicBandDTO musicBandDTO){
        return musicBandService.createMusicBand(musicBandDTO);
    }
}