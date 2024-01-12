package ru.itmo.soa.util.soap.dto;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AlbumDTO {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Long tracks; //Поле может быть null, Значение поля должно быть больше 0
    private Long length; //Поле может быть null, Значение поля должно быть больше 0
    private Integer sales; //Значение поля должно быть больше 0
}