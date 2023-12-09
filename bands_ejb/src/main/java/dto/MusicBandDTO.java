package dto;

import entity.MusicGenre;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@XmlRootElement
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MusicBandDTO implements Serializable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private CoordinatesDTO coordinates; //Поле не может быть null
    private Integer numberOfParticipants; //Значение поля должно быть больше 0
    private String description; //Поле может быть null
    private java.time.LocalDate establishmentDate; //Поле не может быть null
    private MusicGenre genre; //Поле не может быть null
    private AlbumDTO bestAlbum; //Поле не может быть null
}
