package ru.itmo.soa.util.controller;

import org.springframework.web.bind.annotation.*;
import ru.itmo.soa.util.dto.AlbumDTO;
import ru.itmo.soa.util.dto.MusicBandDTO;
import ru.itmo.soa.util.service.GrammyService;


@RestController
@CrossOrigin(origins = "http://localhost:443")
@RequestMapping("band")
public class BandController {
    private final GrammyService grammyService;

    public BandController(GrammyService grammyService) {
        this.grammyService = grammyService;
    }
    
    @PostMapping(path = "/{id}/singles/add")
    public MusicBandDTO addSingle(@PathVariable long id, @RequestBody AlbumDTO album) {
        return grammyService.addSingle(id, album);
    }

    @PostMapping(path = "/{id}/participants/add")
    public MusicBandDTO addParticipant(@PathVariable long id) {
        return grammyService.addParticipant(id);
    }
}