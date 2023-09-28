package ru.itmo.soa.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.itmo.soa.entity.Album;

//todo validators: https://www.baeldung.com/spring-data-rest-validators
public interface AlbumsRepository extends PagingAndSortingRepository<Album, Integer> {
}