package ru.itmo.soa.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.itmo.soa.entity.Album;

public interface AlbumsRepository extends PagingAndSortingRepository<Album, Integer> {
    //todo validators: https://www.baeldung.com/spring-data-rest-validators
}