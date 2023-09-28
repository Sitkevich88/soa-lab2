package ru.itmo.soa.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.soa.entity.MusicGenre;
import ru.itmo.soa.service.MusicBandService;

import java.util.List;

@RestController
@CrossOrigin("*") //todo remove it
@RequestMapping("musicbands-util")
public class MusicBandUtilController {
    private final MusicBandService musicBandService;

    public MusicBandUtilController(MusicBandService musicBandService) {
        this.musicBandService = musicBandService;
    }

    @GetMapping("genres-unique")
    public List<MusicGenre> getAllGenres() {
        return musicBandService.getAllGenres();
    }

    @PostMapping("average-number-of-participants")
    public Double getAverageNumberOfParticipants() {
        return musicBandService.getAverageNumberOfParticipants();
    }

    @DeleteMapping("/bands")
    public ResponseEntity<?> deleteMusicBandByEstablishmentDate(
            @RequestParam(name = "establishmentDate") String establishmentDate
    ) {
        return musicBandService.deleteMusicBandByEstablishmentDate(establishmentDate);
    }
}