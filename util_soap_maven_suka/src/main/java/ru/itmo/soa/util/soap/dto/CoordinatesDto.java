package ru.itmo.soa.util.soap.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "x",
        "y"
})
@XmlRootElement(name = "coordinatesDto")
@Data
public class CoordinatesDto {
    private Long x; //Поле не может быть null
    private Double y; //Максимальное значение поля: 967, Поле не может быть null
}