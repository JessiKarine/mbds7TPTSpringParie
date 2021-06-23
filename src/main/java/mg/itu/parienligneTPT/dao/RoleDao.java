package mg.itu.parienligneTPT.dao;

import mg.itu.parienligneTPT.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Integer> {
    Role findById(int id);
}
