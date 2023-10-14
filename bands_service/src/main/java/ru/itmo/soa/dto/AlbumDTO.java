package ru.itmo.soa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumDTO {
    @NotBlank
    private String name; //Поле не может быть null, Строка не может быть пустой
    
    @Positive
    private Long tracks; //Поле может быть null, Значение поля должно быть больше 0
    
    @Positive
    private Long length; //Поле может быть null, Значение поля должно быть больше 0
    
    @Positive
    private Integer sales; //Значение поля должно быть больше 0
}