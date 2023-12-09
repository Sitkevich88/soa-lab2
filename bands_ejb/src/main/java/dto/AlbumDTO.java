package dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@XmlRootElement
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumDTO implements Serializable {
    @NotBlank
    private String name; //Поле не может быть null, Строка не может быть пустой
    
    @Positive
    private Long tracks; //Поле может быть null, Значение поля должно быть больше 0
    
    @Positive
    private Long length; //Поле может быть null, Значение поля должно быть больше 0
    
    @Positive
    private Integer sales; //Значение поля должно быть больше 0
}