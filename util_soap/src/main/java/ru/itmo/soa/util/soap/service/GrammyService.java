package ru.itmo.soa.util.soap.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.itmo.soa.util.soap.dto.*;
import ru.itmo.soa.util.soap.soap_dto.request.*;

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

    public MusicBandDTO addParticipant(AddParticipantRequest request) throws URISyntaxException {
        var bandId = request.getBandId();
        //get it
        var url = BASE_URL + "/musicbands/" + bandId;
        var band = restTemplate.getForEntity(url, MusicBandDTO.class).getBody();

        //update it
        var changes = new NumberOfParticipantsDTO();
        changes.setNumberOfParticipants(band.getNumberOfParticipants() + 1);

        return restTemplate.exchange(new URI(url),
                HttpMethod.PATCH,
                new HttpEntity<>(changes),
                MusicBandDTO.class).getBody();
    }

    public MusicBandDTO addSingle(AddSingeRequest request) throws URISyntaxException {
        var bandId = request.getBandId();
        var album = request.getAlbum();
        var url = BASE_URL + "/musicbands/" + bandId;
        var changes = new AlbumDTO2();
        changes.setBestAlbum(album);

        return restTemplate.exchange(new URI(url),
                HttpMethod.PATCH,
                new HttpEntity<>(changes),
                MusicBandDTO.class).getBody();
    }
}