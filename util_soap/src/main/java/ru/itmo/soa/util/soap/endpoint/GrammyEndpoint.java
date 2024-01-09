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
    private static final String NAMESPACE_URI = "http://localhost:7011";
    private final GrammyService grammyService;

    public GrammyEndpoint(GrammyService grammyService) {
        this.grammyService = grammyService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AddSingeRequest")
    @ResponsePayload
    public MusicBandDTO addSingle(@RequestPayload AddSingeRequest addSingeRequest) throws URISyntaxException {
        return grammyService.addSingle(addSingeRequest);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AddParticipantRequest")
    @ResponsePayload
    public MusicBandDTO addParticipant(@RequestPayload AddParticipantRequest addParticipantRequest) throws URISyntaxException {
        return grammyService.addParticipant(addParticipantRequest);
    }
}