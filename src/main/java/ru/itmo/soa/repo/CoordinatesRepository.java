package ru.itmo.soa.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.itmo.soa.entity.Coordinates;

public interface CoordinatesRepository extends PagingAndSortingRepository<Coordinates, Integer> {
    //todo validators: https://www.baeldung.com/spring-data-rest-validators
}