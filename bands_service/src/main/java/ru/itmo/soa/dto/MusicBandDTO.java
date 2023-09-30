package ru.itmo.soa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itmo.soa.entity.MusicGenre;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MusicBandDTO {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private CoordinatesDTO coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer numberOfParticipants; //Значение поля должно быть больше 0
    private String description; //Поле может быть null
    private java.time.LocalDate establishmentDate; //Поле не может быть null
    private MusicGenre genre; //Поле не может быть null
    private AlbumDTO bestAlbum; //Поле не может быть null
}