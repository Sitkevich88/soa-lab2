package ru.itmo.soa.util.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.soa.util.dto.AlbumDTO;
import ru.itmo.soa.util.service.GrammyService;

@RestController
@CrossOrigin("*") //todo remove it
@RequestMapping("band")
public class BandController {
    private final GrammyService grammyService;

    public BandController(GrammyService grammyService) {
        this.grammyService = grammyService;
    }
    
    @PostMapping(path = "/{id}/singles/add")
    public ResponseEntity<?> addSingle(@PathVariable long id, AlbumDTO album){
        return grammyService.addSingle(album);
    }

    @PostMapping(path = "/{id}/participants/add")
    public ResponseEntity<?> addParticipant(@PathVariable long id){
        return grammyService.addParticipant(id);
    }
}