package net.datasa.web4.repository;

import net.datasa.web4.domain.entity.GuestbookRecommendEntity;
import net.datasa.web4.domain.entity.GuestbookRecommendKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestbookRecommendRepository extends JpaRepository<GuestbookRecommendEntity, GuestbookRecommendKey> {
}
