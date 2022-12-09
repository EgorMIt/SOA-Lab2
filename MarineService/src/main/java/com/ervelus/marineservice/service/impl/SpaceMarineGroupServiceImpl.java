package com.ervelus.marineservice.service.impl;

import com.ervelus.marineservice.repository.SpaceMarineRepository;
import com.ervelus.marineservice.service.SpaceMarineGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egormit.library.MeleeWeaponGroupResponse;
import ru.egormit.library.enums.MeleeWeapon;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class SpaceMarineGroupServiceImpl implements SpaceMarineGroupService {
    private final SpaceMarineRepository repository;

    @Override
    public MeleeWeaponGroupResponse groupSpaceMarinesByMeleeWeaponAndCount() {
        Map<MeleeWeapon, Long> meleeToAmount = repository.groupByMeleeAndCount();
        return MeleeWeaponGroupResponse.of(
                meleeToAmount.get(MeleeWeapon.CHAIN_AXE),
                meleeToAmount.get(MeleeWeapon.CHAIN_SWORD),
                meleeToAmount.get(MeleeWeapon.POWER_FIST));
    }
}
