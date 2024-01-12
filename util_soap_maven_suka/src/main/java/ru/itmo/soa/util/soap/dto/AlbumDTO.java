package ru.itmo.soa.util.soap.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@XmlRootElement
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumDTO {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private long tracks; //Поле может быть null, Значение поля должно быть больше 0
    private long length; //Поле может быть null, Значение поля должно быть больше 0
    private int sales; //Значение поля должно быть больше 0
}