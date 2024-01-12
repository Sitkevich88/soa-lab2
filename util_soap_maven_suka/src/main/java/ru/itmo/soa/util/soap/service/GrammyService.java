package ru.itmo.soa.util.soap.service;

import io.spring.guides.gs_producing_web_service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.itmo.soa.util.soap.dto.MusicBandDto;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class GrammyService {

    @Value("${bands.api.base}")
    private String BASE_URL;

    private final RestTemplate restTemplate;
    
    private final ModelMapper mapper;

    public GrammyService(RestTemplate restTemplate, ModelMapper mapper) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
    }

    public MusicBandDtoParticipants addParticipant(AddParticipantRequest request) throws URISyntaxException {
        var bandId = request.getBandId();
        //get it
        var url = BASE_URL + "/musicbands/" + bandId;
        var band = restTemplate.getForEntity(url, MusicBandDto.class).getBody();

        //update it
        var changes = new NumberOfParticipantsDto();
        changes.setNumberOfParticipants(band.getNumberOfParticipants() + 1);

        band = restTemplate.exchange(new URI(url),
                HttpMethod.PATCH,
                new HttpEntity<>(changes),
                MusicBandDto.class).getBody();
        
        return mapper.map(band, MusicBandDtoParticipants.class);
    }

    public MusicBandDtoSingle addSingle(AddSingeRequest request) throws URISyntaxException {
        var bandId = request.getBandId();
        var album = request.getAlbum();
        var url = BASE_URL + "/musicbands/" + bandId;
        var changes = new AlbumDto2();
        changes.setBestAlbum(album);

        var band = restTemplate.exchange(new URI(url),
                HttpMethod.PATCH,
                new HttpEntity<>(changes),
                MusicBandDtoSingle.class).getBody();
        
        return mapper.map(band, MusicBandDtoSingle.class);
    }
}