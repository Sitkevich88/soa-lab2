package beans;

import dto.MusicBandDTO;
import entity.MusicBand;
import interfaces.MusicBandBean;
import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.Data;
import mapper.MusicBandMapper;
import org.jboss.ejb3.annotation.Pool;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Data
@Remote(MusicBandBean.class)
@Stateless(name = "MusicBandEjb")
@Pool("slsb-strict-max-pool")
public class MusicBandEjb implements MusicBandBean {
    @PersistenceContext(unitName = "db_unit")
    private EntityManager entityManager;

    @Override
    public List<MusicBand> findAll(int page, int size, String sort, String fieldName, String sign, String value) {
        String baseRequest = "SELECT mb FROM MusicBand mb ";

        if (!fieldName.equals("") && !sign.equals("")) {
            baseRequest += "WHERE :filter_field :sign :filter_value ";
        }

        if (!sort.equals("")) {
            baseRequest += "ORDER BY :sort_field ";
        }

        Query query = entityManager.createQuery(baseRequest);

        if (!fieldName.equals("") && !sign.equals("")) {
            query.setParameter("filter_field", "mb." + fieldName);
            query.setParameter("sign", sign);
            query.setParameter("filter_value", value);        }

        if (!sort.equals("")) {
            query.setParameter("sort_field", "mb." + sort);
        }

        query.setMaxResults(size);
        query.setFirstResult(page * size);

        return query.getResultList();
    }

    @Override
    public MusicBand save(MusicBand musicBand) {
        entityManager.persist(musicBand);
        return musicBand;
    }

    @Override
    public MusicBand findById(long id) {
        Query query = entityManager.createQuery("SELECT mb FROM MusicBand mb where mb.id = :id");
        query.setParameter("id", id);
        List<MusicBand> musicBands = query.getResultList();

        if (musicBands.isEmpty()) {
            return null;
        } else {
            return musicBands.get(0);
        }
    }

    @Override
    public boolean existsById(long id) {
        final MusicBand musicBand = entityManager.find(MusicBand.class, id);

        return musicBand != null;
    }

    @Override
    public void deleteById(long id) {
        Query query = entityManager.createQuery("UPDATE Album a SET a.musicBand.id = NULL WHERE a.musicBand.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();

        query = entityManager.createQuery("DELETE FROM MusicBand s WHERE s.id = :id");
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
        Query query = entityManager.createQuery("SELECT id FROM MusicBand s WHERE s.establishmentDate = :date");
        query.setParameter("date", localDate);
        List<Long> musicBandsIds = query.getResultList();

        query = entityManager.createQuery("UPDATE Album a SET a.musicBand.id = NULL WHERE a.musicBand.id in :ids");
        query.setParameter("ids", musicBandsIds);
        query.executeUpdate();

        query = entityManager.createQuery("DELETE FROM MusicBand s WHERE s.id in :ids");

        query.setParameter("ids", musicBandsIds);
        query.executeUpdate();
    }

    @Override
    public void update(MusicBandDTO source, MusicBand target) {
        MusicBandMapper.updateMusicBandFromDto(source, target);
        entityManager.merge(target);
    }
}

