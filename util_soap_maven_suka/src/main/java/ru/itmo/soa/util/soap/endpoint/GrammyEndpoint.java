package ru.itmo.soa.util.soap.endpoint;

import io.spring.guides.gs_producing_web_service.AddParticipantRequest;
import io.spring.guides.gs_producing_web_service.AddSingeRequest;
import io.spring.guides.gs_producing_web_service.MusicBandDtoParticipants;
import io.spring.guides.gs_producing_web_service.MusicBandDtoSingle;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.itmo.soa.util.soap.service.GrammyService;

import java.net.URISyntaxException;

@Endpoint
public class GrammyEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";
    private final GrammyService grammyService;

    public GrammyEndpoint(GrammyService grammyService) {
        this.grammyService = grammyService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addSingeRequest")
    @ResponsePayload
    public MusicBandDtoSingle addSingle(@RequestPayload AddSingeRequest addSingeRequest) throws URISyntaxException {
        var res = new MusicBandDtoSingle();
        res.setId(addSingeRequest.getBandId());
        return res;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addParticipantRequest")
    @ResponsePayload
    public MusicBandDtoParticipants addParticipant(@RequestPayload AddParticipantRequest addParticipantRequest) throws URISyntaxException, InterruptedException {
        var res = new MusicBandDtoParticipants();
        res.setId(addParticipantRequest.getBandId());
        return res;
    }
}