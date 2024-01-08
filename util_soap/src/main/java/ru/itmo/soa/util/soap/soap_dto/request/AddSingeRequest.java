package ru.itmo.soa.util.soap.soap_dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itmo.soa.util.soap.dto.AlbumDTO;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddSingeRequest {
    private Long bandId;
    private AlbumDTO album;
}