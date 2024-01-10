package ru.itmo.soa.util.soap.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.itmo.soa.util.soap.dto.MusicBandDto;
import ru.itmo.soa.util.soap.service.GrammyService;
import ru.itmo.soa.util.soap.soap_dto.request.*;

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
    public MusicBandDto addSingle(@RequestPayload AddSingeRequest addSingeRequest) throws URISyntaxException {
        var res = new MusicBandDto();
        res.setId(addSingeRequest.getBandId());
        return res;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addParticipantRequest")
    @ResponsePayload
    public MusicBandDto addParticipant(@RequestPayload AddParticipantRequest addParticipantRequest) throws URISyntaxException {
        var res = new MusicBandDto();
        res.setId(addParticipantRequest.getBandId());
        return res;
    }
}