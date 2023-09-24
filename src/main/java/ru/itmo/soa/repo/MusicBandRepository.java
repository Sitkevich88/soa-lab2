package ru.itmo.soa.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.itmo.soa.entity.MusicBand;

public interface MusicBandRepository extends PagingAndSortingRepository<MusicBand, Long> {
    //todo validators: https://www.baeldung.com/spring-data-rest-validators
}