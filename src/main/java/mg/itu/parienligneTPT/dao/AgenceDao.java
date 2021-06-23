package mg.itu.parienligneTPT.dao;

import mg.itu.parienligneTPT.model.Agence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgenceDao extends JpaRepository<Agence, Integer> {
}
