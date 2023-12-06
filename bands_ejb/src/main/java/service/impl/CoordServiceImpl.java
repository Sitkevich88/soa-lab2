package service.impl;

import entity.Coordinates;
import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.jboss.ejb3.annotation.Pool;
import service.CoordService;

@Remote(CoordService.class)
@Stateless(name = "CoordServiceImpl")
public class CoordServiceImpl implements CoordService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Coordinates getCoord(String id) {
        return em.find(Coordinates.class, id);
    }

    @Override
    public String helloFromEjb() {
        return "Hello From Ejb!";
    }
}
