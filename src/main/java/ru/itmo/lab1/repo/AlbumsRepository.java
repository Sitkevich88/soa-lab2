package ru.itmo.lab1.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.validation.annotation.Validated;
import ru.itmo.lab1.entity.Album;


@RepositoryRestResource(collectionResourceRel = "albums", path = "albums")
@Validated
public interface AlbumsRepository extends PagingAndSortingRepository<Album, Integer> {
    //validators: https://www.baeldung.com/spring-data-rest-validators

/*    @Query("select e from Album e " 
            + "where (:name='' or e.name=:name) "
            + "and (:name='' or e.sales=:sales) "
    )
    
    Page<Album> advancedSearch(@Param("name") String name, @Param("sales") String sales);*/ //todo
}