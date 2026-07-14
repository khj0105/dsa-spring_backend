package net.datasa.ex4.repository;

import net.datasa.ex4.domain.entity.ChickenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChickenRepository extends JpaRepository<ChickenEntity, Integer> {

}
