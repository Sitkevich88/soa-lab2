package ru.itmo.soa.util.soap.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDate;
import java.time.LocalDateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "id",
        "name",
        "coordinates",
        "creationDate",
        "numberOfParticipants",
        "description",
        "establishmentDate",
        "genre",
        "bestAlbum"
})
@XmlRootElement(name = "musicBandDto")
public class MusicBandDto {
    private long id;
    private String name; //Поле не может быть null, Строка не может быть пустой

    private CoordinatesDto coordinates; //Поле не может быть null
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer numberOfParticipants; //Значение поля должно быть больше 0
    private String description; //Поле может быть null
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate establishmentDate; //Поле не может быть null
    private MusicGenre genre; //Поле не может быть null
    private AlbumDTO bestAlbum; //Поле не может быть null

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CoordinatesDto getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(CoordinatesDto coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(Integer numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getEstablishmentDate() {
        return establishmentDate;
    }

    public void setEstablishmentDate(LocalDate establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }

    public AlbumDTO getBestAlbum() {
        return bestAlbum;
    }

    public void setBestAlbum(AlbumDTO bestAlbum) {
        this.bestAlbum = bestAlbum;
    }
}