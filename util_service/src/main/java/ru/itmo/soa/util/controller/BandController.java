package ru.itmo.soa.util.controller;

import org.springframework.web.bind.annotation.*;
import ru.itmo.soa.util.dto.AlbumDTO;
import ru.itmo.soa.util.dto.MusicBandDTO;
import ru.itmo.soa.util.service.GrammyService;

import java.io.IOException;

@RestController
@CrossOrigin("*") //todo remove it
@RequestMapping("band")
public class BandController {
    private final GrammyService grammyService;

    public BandController(GrammyService grammyService) {
        this.grammyService = grammyService;
    }
    
    @PostMapping(path = "/{id}/singles/add")
    public MusicBandDTO addSingle(@PathVariable long id, AlbumDTO album) throws IOException {
        return grammyService.addSingle(id, album);
    }

    @PostMapping(path = "/{id}/participants/add")
    public MusicBandDTO addParticipant(@PathVariable long id) throws IOException {
        return grammyService.addParticipant(id);
    }
}