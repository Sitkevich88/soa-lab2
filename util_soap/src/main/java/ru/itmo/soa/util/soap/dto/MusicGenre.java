package ru.itmo.soa.util.soap.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum MusicGenre {
    BLUES,
    PUNK_ROCK,
    POST_PUNK;
}