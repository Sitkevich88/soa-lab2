package ru.itmo.soa.util.soap.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.itmo.soa.util.soap.dto.MusicBandDTO;
import ru.itmo.soa.util.soap.service.GrammyService;
import ru.itmo.soa.util.soap.soap_dto.request.*;

import java.net.URISyntaxException;

@Endpoint
public class GrammyEndpoint {
    private final GrammyService grammyService;

    public GrammyEndpoint(GrammyService grammyService) {
        this.grammyService = grammyService;
    }

    @PayloadRoot(localPart = "addSingle")
    @ResponsePayload
    public MusicBandDTO addSingle(@RequestPayload AddSingeRequest request) throws URISyntaxException {
        return grammyService.addSingle(request);
    }

    @PayloadRoot(localPart = "addParticipant")
    @ResponsePayload
    public MusicBandDTO addParticipant(@RequestPayload AddParticipantRequest request) throws URISyntaxException {
        return grammyService.addParticipant(request);
    }
}