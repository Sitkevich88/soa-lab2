package ru.itmo.lab1.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.itmo.lab1.entity.Album;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "albums", path = "albums")
public interface AlbumsRepository extends PagingAndSortingRepository<Album, Integer> {

/*    @Query("select e from Album e " 
            + "where (:name='' or e.name=:name) "
            + "and (:name='' or e.sales=:sales) "
    )
    
    Page<Album> advancedSearch(@Param("name") String name, @Param("sales") String sales);*/ //todo
}