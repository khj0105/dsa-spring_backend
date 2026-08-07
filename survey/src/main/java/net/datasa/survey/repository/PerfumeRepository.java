package net.datasa.survey.repository;

import net.datasa.survey.domain.entity.PerfumeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
	Repository
 */
@Repository
public interface PerfumeRepository extends JpaRepository<PerfumeEntity, Integer>{

}
