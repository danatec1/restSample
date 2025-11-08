package io.reflectoring.demo.repository;

import io.reflectoring.demo.entity.WitakByungwonHyunhwang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WitakByungwonHyunhwangRepository extends JpaRepository<WitakByungwonHyunhwang, Integer> {
}
