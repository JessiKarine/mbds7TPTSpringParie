package mg.itu.parienligneTPT.dao;

import mg.itu.parienligneTPT.model.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipeDao extends JpaRepository<Equipe, Integer> {
    Equipe findById(int id);
}
