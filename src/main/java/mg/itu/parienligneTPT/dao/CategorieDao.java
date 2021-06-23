package mg.itu.parienligneTPT.dao;

import mg.itu.parienligneTPT.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieDao extends JpaRepository<Categorie, Integer> {
    Categorie findById(int id);
}
