package mg.itu.parienligneTPT.controller;

import mg.itu.parienligneTPT.dao.AgenceDao;
import mg.itu.parienligneTPT.dao.RoleDao;
import mg.itu.parienligneTPT.exception.ExceptionService;
import mg.itu.parienligneTPT.model.Agence;
import mg.itu.parienligneTPT.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
public class RoleController {
    @Autowired
    private RoleDao roleDao;

    @GetMapping(value = "/Roles")
    public List<Role> listRole(){
        return roleDao.findAll();
    }

    @PostMapping(value = "/Roles")
    public ResponseEntity<Void> addRole(@RequestBody Role role){
        Role roleAdded = roleDao.save(role);
        if(role == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(roleAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/Roles/{id}")
    public Role roleById(@PathVariable int id){
        Role role = roleDao.findById(id);
        if(role == null)
            throw new ExceptionService("Le role id: " + id + "n'existe pas");
        return role;
    }

    @PutMapping(value = "/Roles")
    public void updateRole(@RequestBody Role role){
        roleDao.save(role);
    }

    @DeleteMapping(value = "/Roles")
    public void deleteRole(@RequestBody Role role){
        roleDao.delete(role);
    }

    @DeleteMapping(value = "/Roles/Delete/{id}")
    public void deleteRole(@PathVariable int id){
        roleDao.deleteById(id);
    }
}
