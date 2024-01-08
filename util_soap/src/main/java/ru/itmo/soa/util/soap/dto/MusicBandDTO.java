package ru.itmo.soa.util.soap.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.time.LocalDateTime;

@XmlRootElement
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MusicBandDTO {
    private long id;
    private String name; //Поле не может быть null, Строка не может быть пустой

    private CoordinatesDTO coordinates; //Поле не может быть null
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer numberOfParticipants; //Значение поля должно быть больше 0
    private String description; //Поле может быть null
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate establishmentDate; //Поле не может быть null
    private MusicGenre genre; //Поле не может быть null
    private AlbumDTO bestAlbum; //Поле не может быть null
}