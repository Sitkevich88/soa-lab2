package interfaces;

import entity.MusicBand;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MusicBandBean {
    List<MusicBand> findAll(int page, int size, String sort, String fieldName, String sign, String value);

    MusicBand save(MusicBand musicBand);

    Optional<MusicBand> findById(long id);

    boolean existsById(long id);

    void deleteById(long id);

    Double findAverageNumberOfParticipants();

    boolean existsByEstablishmentDate(LocalDate localDate);

    void deleteByEstablishmentDate(LocalDate localDate);
}
