package ru.itmo.soa.spec;

import entity.MusicBand;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class MusicBandSpecification implements Specification<MusicBand> {

    private final SearchCriteria criteria;

    public MusicBandSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<MusicBand> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getOperation().equals(">")) {
            return builder.greaterThan(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equals("<")) {
            return builder.lessThan(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equals(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like( 
                        builder.lower(
                                root.get(criteria.getKey())
                        ), "%" + criteria.getValue() + "%"
                );
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }
}