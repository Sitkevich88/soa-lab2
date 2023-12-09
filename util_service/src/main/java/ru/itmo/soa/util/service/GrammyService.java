package ru.itmo.soa.util.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.itmo.soa.util.dto.AlbumDTO;
import ru.itmo.soa.util.dto.AlbumDTO2;
import ru.itmo.soa.util.dto.MusicBandDTO;
import ru.itmo.soa.util.dto.NumberOfParticipantsDTO;

@Service
public class GrammyService {

    @Value("${bands.api.base}")
    private String BASE_URL;

    private final RestTemplate restTemplate;

    public GrammyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public MusicBandDTO addParticipant(long bandId) {
        //get it
        var url = BASE_URL + "/musicbands/" + bandId;
        MusicBandDTO band = restTemplate.getForEntity(url, MusicBandDTO.class).getBody();

        //update it
        var changes = new NumberOfParticipantsDTO();
        changes.setNumberOfParticipants(band.getNumberOfParticipants() + 1);

        return restTemplate.patchForObject(url, changes, MusicBandDTO.class);
    }

    public MusicBandDTO addSingle(long bandId, AlbumDTO album) {
        var url = BASE_URL + "/musicbands/" + bandId;
        var changes = new AlbumDTO2();
        changes.setBestAlbum(album);

        return restTemplate.patchForObject(url, changes, MusicBandDTO.class);
    }
}