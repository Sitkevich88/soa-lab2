package ru.itmo.soa.util.soap.service;

import io.spring.guides.gs_producing_web_service.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class GrammyService {

    @Value("${bands.api.base}")
    private String BASE_URL;

    private final RestTemplate restTemplate;

    public GrammyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public MusicBandDtoParticipants addParticipant(AddParticipantRequest request) throws URISyntaxException {
        var bandId = request.getBandId();
        //get it
        var url = BASE_URL + "/musicbands/" + bandId;
        var band = restTemplate.getForEntity(url, MusicBandDtoParticipants.class).getBody();

        //update it
        var changes = new NumberOfParticipantsDto();
        changes.setNumberOfParticipants(band.getNumberOfParticipants() + 1);

        return restTemplate.exchange(new URI(url),
                HttpMethod.PATCH,
                new HttpEntity<>(changes),
                MusicBandDtoParticipants.class).getBody();
    }

    public MusicBandDtoSingle addSingle(AddSingeRequest request) throws URISyntaxException {
        var bandId = request.getBandId();
        var album = request.getAlbum();
        var url = BASE_URL + "/musicbands/" + bandId;
        var changes = new AlbumDto2();
        changes.setBestAlbum(album);

        return restTemplate.exchange(new URI(url),
                HttpMethod.PATCH,
                new HttpEntity<>(changes),
                MusicBandDtoSingle.class).getBody();
    }
}