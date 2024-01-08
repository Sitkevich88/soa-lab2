package ru.itmo.soa.util.soap.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoordinatesDTO {
    private Long x; //Поле не может быть null
    private Double y; //Максимальное значение поля: 967, Поле не может быть null
}