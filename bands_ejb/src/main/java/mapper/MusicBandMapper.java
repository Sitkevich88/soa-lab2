package mapper;

import dto.AlbumDTO;
import dto.CoordinatesDTO;
import dto.MusicBandDTO;
import entity.Album;
import entity.Coordinates;
import entity.MusicBand;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

public class MusicBandMapper {
    public static void updateMusicBandFromDto(MusicBandDTO dto, MusicBand entity) {
        if ( dto == null ) {
            return;
        }

        if (dto.getName()!= null) {
            entity.setName( dto.getName() );
        }


        if ( dto.getCoordinates() != null ) {
            if ( entity.getCoordinates() == null ) {
                entity.setCoordinates( new Coordinates() );
            }
            coordinatesDTOToCoordinates( dto.getCoordinates(), entity.getCoordinates() );
        }


        if ( dto.getNumberOfParticipants() != null ) {
            entity.setNumberOfParticipants( dto.getNumberOfParticipants() );
        }

        if (dto.getDescription()!= null) {
            entity.setDescription( dto.getDescription() );

        }

        if (dto.getEstablishmentDate()!= null) {
            entity.setEstablishmentDate( dto.getEstablishmentDate() );
        }

        if (dto.getGenre()!= null) {
            entity.setGenre( dto.getGenre() );
        }


        if ( dto.getBestAlbum() != null ) {
            if ( entity.getBestAlbum() == null ) {
                entity.setBestAlbum( new Album() );
            }
            albumDTOToAlbum( dto.getBestAlbum(), entity.getBestAlbum() );
        }


    }

    private static void coordinatesDTOToCoordinates(CoordinatesDTO coordinatesDTO, Coordinates mappingTarget) {
        if ( coordinatesDTO == null ) {
            return;
        }

        mappingTarget.setX( coordinatesDTO.getX() );
        mappingTarget.setY( coordinatesDTO.getY() );
    }

    private static void albumDTOToAlbum(AlbumDTO albumDTO, Album mappingTarget) {
        if ( albumDTO == null ) {
            return;
        }

        mappingTarget.setName( albumDTO.getName() );
        mappingTarget.setTracks( albumDTO.getTracks() );
        mappingTarget.setLength( albumDTO.getLength() );
        mappingTarget.setSales( albumDTO.getSales() );
    }
}
