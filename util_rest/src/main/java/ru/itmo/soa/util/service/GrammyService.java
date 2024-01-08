package ru.itmo.soa.util.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.itmo.soa.util.dto.AlbumDTO;
import ru.itmo.soa.util.dto.AlbumDTO2;
import ru.itmo.soa.util.dto.MusicBandDTO;
import ru.itmo.soa.util.dto.NumberOfParticipantsDTO;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

@Service
public class GrammyService {

    @Value("${bands.api.base}")
    private String BASE_URL;

    private final RestTemplate restTemplate;

    public GrammyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public MusicBandDTO addParticipant(long bandId) throws URISyntaxException {
        //get it
        var url = BASE_URL + "/musicbands/" + bandId;


        var bandEnt = restTemplate.getForEntity(url, MusicBandDTO.class);
        var band = bandEnt.getBody();

        //update it
        var changes = new NumberOfParticipantsDTO();
        changes.setNumberOfParticipants(band.getNumberOfParticipants() + 1);

//        return restTemplate.patchForObject(url, changes, MusicBandDTO.class);

        return restTemplate.exchange(new URI(url),
                HttpMethod.PATCH,
                new HttpEntity<>(changes),
                MusicBandDTO.class).getBody();
    }

    public MusicBandDTO addSingle(long bandId, AlbumDTO album) throws URISyntaxException {
        var url = BASE_URL + "/musicbands/" + bandId;
        var changes = new AlbumDTO2();
        changes.setBestAlbum(album);

        return restTemplate.exchange(new URI(url),
                HttpMethod.PATCH,
                new HttpEntity<>(changes),
                MusicBandDTO.class).getBody();
    }
}