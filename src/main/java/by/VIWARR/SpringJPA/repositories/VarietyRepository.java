package by.VIWARR.SpringJPA.repositories;

import by.VIWARR.SpringJPA.models.Variety;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VarietyRepository extends JpaRepository<Variety, Integer> {
}
