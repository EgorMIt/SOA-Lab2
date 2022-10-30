package com.ervelus.marineservice.repository;

import ru.egormit.library.SpaceMarine;
import ru.egormit.library.enums.SortByType;
import ru.egormit.library.enums.SortOrder;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

@Stateless
public class SpaceMarineSearchRepository {
    @Inject
    private EntityManagerProvider entityManagerProvider;

    public List<SpaceMarine> getAllMatchingFieldsSortedPageable(
            Map<String, String> fieldToVal, SortByType type, SortOrder order, Integer page, Integer limit){
        CriteriaBuilder builder = entityManagerProvider.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<SpaceMarine> query = builder.createQuery(SpaceMarine.class);
        Root<SpaceMarine> root = query.from(SpaceMarine.class);
        query.select(root);
        if (order.equals(SortOrder.ASC)){
            query.orderBy(builder.asc(root.get(type.name())));
        } else query.orderBy(builder.desc(root.get(type.name())));
        for (Map.Entry<String, String> entry: fieldToVal.entrySet()) {
            query.where(builder.equal(root.get(entry.getKey()), entry.getValue()));
        }
        return entityManagerProvider.getEntityManager().createQuery(query)
                .setFirstResult(page*limit)
                .setMaxResults(limit)
                .getResultList();
    }

    public List<SpaceMarine> getAllMatchingFieldsPageable(Map<String, String> fieldToVal, Integer page, Integer limit){
        CriteriaBuilder builder = entityManagerProvider.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<SpaceMarine> query = builder.createQuery(SpaceMarine.class);
        Root<SpaceMarine> root = query.from(SpaceMarine.class);
        query.select(root);
        for (Map.Entry<String, String> entry: fieldToVal.entrySet()) {
            query.where(builder.equal(root.get(entry.getKey()), entry.getValue()));
        }
        return entityManagerProvider.getEntityManager().createQuery(query)
                .setFirstResult(page*limit)
                .setMaxResults(limit)
                .getResultList();
    }

    public List<SpaceMarine> getAllByNamePageable(String name, Integer page, Integer limit){
        CriteriaBuilder builder = entityManagerProvider.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<SpaceMarine> query = builder.createQuery(SpaceMarine.class);
        Root<SpaceMarine> root = query.from(SpaceMarine.class);
        query.where(builder.like(root.get("name"), "%"+name+"%"));
        return entityManagerProvider.getEntityManager().createQuery(query)
                .setFirstResult(page*limit)
                .setMaxResults(limit)
                .getResultList();
    }

    public List<SpaceMarine> getAllByHealthGreaterPageable(Long health, Integer page, Integer limit){
        CriteriaBuilder builder = entityManagerProvider.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<SpaceMarine> query = builder.createQuery(SpaceMarine.class);
        Root<SpaceMarine> root = query.from(SpaceMarine.class);
        query.where(builder.greaterThan(root.get("health"), health));
        return entityManagerProvider.getEntityManager().createQuery(query)
                .setFirstResult(page*limit)
                .setMaxResults(limit)
                .getResultList();
    }
}
