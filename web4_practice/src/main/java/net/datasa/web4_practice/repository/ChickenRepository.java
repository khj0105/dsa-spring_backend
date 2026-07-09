package net.datasa.web4_practice.repository;

import net.datasa.web4_practice.domain.entity.ChickenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChickenRepository extends JpaRepository<ChickenEntity, Integer> {
}
