package ru.itmo.soa.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.itmo.soa.entity.MusicBand;

import javax.transaction.Transactional;
import java.time.LocalDate;

//todo validators: https://www.baeldung.com/spring-data-rest-validators
public interface MusicBandRepository extends PagingAndSortingRepository<MusicBand, Long> {
    @Query("SELECT AVG(e.numberOfParticipants) FROM MusicBand e")
    Double findAverageNumberOfParticipants();

    boolean existsByEstablishmentDate(LocalDate establishmentDate);

    @Transactional
    void deleteByEstablishmentDate(LocalDate establishmentDate);
}