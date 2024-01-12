package ru.itmo.soa.util.soap.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "numberOfParticipants"
})
@XmlRootElement(name = "numberOfParticipantsDto")
@Data
public class NumberOfParticipantsDto {
    private int numberOfParticipants;
}