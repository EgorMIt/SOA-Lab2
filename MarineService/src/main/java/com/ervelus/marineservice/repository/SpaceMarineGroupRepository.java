package com.ervelus.marineservice.repository;

import ru.egormit.library.SpaceMarine;
import ru.egormit.library.enums.MeleeWeapon;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.util.HashMap;
import java.util.Map;

@Stateless
public class SpaceMarineGroupRepository {
    @Inject
    private EntityManagerProvider entityManagerProvider;

    public Map<MeleeWeapon, Long> groupByMeleeAndCount(){
        CriteriaBuilder builder = entityManagerProvider.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Tuple> query = builder.createQuery(Tuple.class);
        Root<SpaceMarine> root = query.from(SpaceMarine.class);
        query.groupBy(root.get("melee_weapon"));
        query.multiselect(root.get("melee_weapon"), builder.count(root));
        Map<MeleeWeapon, Long> result = new HashMap<>();
        entityManagerProvider.getEntityManager().createQuery(query).getResultStream().forEach((item) ->
                result.put((MeleeWeapon) item.get(0), (Long) item.get(1)));
        return result;
    }
}
