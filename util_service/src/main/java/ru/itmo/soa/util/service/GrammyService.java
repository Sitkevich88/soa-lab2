package ru.itmo.soa.util.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.stereotype.Service;
import ru.itmo.soa.util.dto.AlbumDTO;
import ru.itmo.soa.util.dto.MusicBandDTO;
import ru.itmo.soa.util.dto.NumberOfParticipantsDTO;

import java.io.IOException;

@Service
public class GrammyService {
    public static final String BASE_URL = "http://localhost:7008";
    public static final MediaType XML
            = MediaType.get("application/xml; charset=utf-8");
    public final ObjectMapper mapper;
    public final OkHttpClient client = new OkHttpClient();

    public GrammyService(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public MusicBandDTO addParticipant(long bandId) throws IOException {
        //get it
        Request request = new Request.Builder()
                .url(BASE_URL + "/musicbands/" + bandId)
                .addHeader("Content-Type", "application/xml")
                .addHeader("Accept", "application/xml")
                .get()
                .build();
        Response response = client.newCall(request).execute();
        MusicBandDTO band = mapper.readValue(response.body().string(), MusicBandDTO.class);
        
        //update it
        var bandChanges = new NumberOfParticipantsDTO();
        bandChanges.setNumberOfParticipants(band.getNumberOfParticipants() + 1);
        var body = RequestBody.create(
                mapper.writeValueAsString(bandChanges), 
                MediaType.parse("application/xml; charset=utf-8")
        );
        request = new Request.Builder()
                .url(BASE_URL + "/musicbands/" + bandId)
                .addHeader("Content-Type", "application/xml")
                .addHeader("Accept", "application/xml")
                .patch(body)
                .build();
        response = client.newCall(request).execute();
        band = mapper.readValue(response.body().string(), MusicBandDTO.class);
        
        return band;
    }

    public MusicBandDTO addSingle(long bandId, AlbumDTO album) throws IOException {
        var body = RequestBody.create(
                mapper.writeValueAsString(album), 
                MediaType.parse("application/xml; charset=utf-8")
        );
        var request = new Request.Builder()
                .url(BASE_URL + "/musicbands/" + bandId)
                .addHeader("Content-Type", "application/xml")
                .addHeader("Accept", "application/xml")
                .patch(body)
                .build();
        var response = client.newCall(request).execute();
        var band = mapper.readValue(response.body().string(), MusicBandDTO.class);

        return band;
    }
}