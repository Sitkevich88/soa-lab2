package ru.itmo.soa.util.soap.endpoint;

import io.spring.guides.gs_producing_web_service.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.itmo.soa.util.soap.service.GrammyService;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

@Endpoint
public class GrammyEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";
    private final GrammyService grammyService;

    public GrammyEndpoint(GrammyService grammyService) {
        this.grammyService = grammyService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addSingeRequest")
    @ResponsePayload
    public MusicBandDtoSingle addSingle(@RequestPayload AddSingeRequest addSingeRequest) throws URISyntaxException, DatatypeConfigurationException {
//        var res = new MusicBandDtoSingle();
//        res.setId(addSingeRequest.getBandId());
//        var albumdto = new AlbumDto();
//        albumdto.setLength(10);
//        albumdto.setName("album name");
//        albumdto.setTracks(244);
//        albumdto.setSales(90);
//        res.setBestAlbum(albumdto);
//        var coord = new CoordinatesDto();
//        coord.setX(10);
//        coord.setY(55.55);
//        res.setCoordinates(coord);
//        res.setDescription("DES");
//        var crLocal = LocalDateTime.now();
//        String iso = crLocal.toString();
//        if (crLocal.getSecond() == 0 && crLocal.getNano() == 0) {
//            iso += ":00"; // necessary hack because the second part is not optional in XML
//        }
//        res.setCreationDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(iso));
//        res.setGenre(MusicGenre.BLUES);
//        return res;
        var res = grammyService.addSingle(addSingeRequest);
        return res;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addParticipantRequest")
    @ResponsePayload
    public MusicBandDtoParticipants addParticipant(@RequestPayload AddParticipantRequest addParticipantRequest) throws URISyntaxException, InterruptedException {
//        var res = new MusicBandDtoParticipants();
//        res.setId(addParticipantRequest.getBandId());
//        return res;
        var res = grammyService.addParticipant(addParticipantRequest);
        return res;
    }
}