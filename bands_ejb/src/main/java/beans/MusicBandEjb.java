package beans;

import entity.MusicBand;
import interfaces.MusicBandBean;
import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Data
@Remote(MusicBandBean.class)
@Stateless(name = "MusicBandEjb")
public class MusicBandEjb implements MusicBandBean {
    @PersistenceContext(unitName="db_unit")
    private EntityManager entityManager;

    @Override
    public MusicBand save(MusicBand musicBand) {
        entityManager.persist(musicBand);
        return musicBand;
    }

    @Override
    public Optional<MusicBand> findById(long id) {
        final MusicBand musicBand = entityManager.find(MusicBand.class, id);

        return Optional.of(musicBand);
    }

    @Override
    public boolean existsById(long id) {
        final MusicBand musicBand = entityManager.find(MusicBand.class, id);

        return musicBand != null;
    }

    @Override
    public void deleteById(long id) {
        Query query = entityManager.createQuery("DELETE FROM MusicBand s WHERE s.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Double findAverageNumberOfParticipants() {
        return (Double) entityManager.createQuery("SELECT AVG(mb.numberOfParticipants) FROM MusicBand mb").getSingleResult();
    }

    @Override
    public boolean existsByEstablishmentDate(LocalDate localDate) {
        Query query = entityManager.createQuery("SELECT mb FROM MusicBand mb WHERE mb.establishmentDate = :date");
        query.setParameter("date", localDate);
        List<MusicBand> musicBands = query.getResultList();

        return !musicBands.isEmpty();
    }

    @Override
    public void deleteByEstablishmentDate(LocalDate localDate) {
        Query query = entityManager.createQuery("DELETE FROM MusicBand s WHERE s.establishmentDate = :date");

        query.setParameter("date", localDate);
        query.executeUpdate();
    }
}

