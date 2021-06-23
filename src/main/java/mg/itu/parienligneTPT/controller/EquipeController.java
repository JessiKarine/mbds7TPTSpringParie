package mg.itu.parienligneTPT.controller;

import mg.itu.parienligneTPT.dao.EquipeDao;
import mg.itu.parienligneTPT.exception.ExceptionService;
import mg.itu.parienligneTPT.model.Equipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class EquipeController {
    @Autowired
    private EquipeDao equipeDao;

    @GetMapping(value = "/Equipes")
    public List<Equipe> listEquipe(){
        return equipeDao.findAll();
    }

    @PostMapping(value = "/Equipes")
    public ResponseEntity<Void> addEquipe(@RequestBody Equipe equipe){
        Equipe equipeAdded = equipeDao.save(equipe);
        if(equipeAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(equipeAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/Equipes/{id}")
    public Equipe equipeById(@PathVariable int id){
        Equipe equipe = equipeDao.findById(id);
        if(equipe == null)
            throw new ExceptionService("L'équipe id: " + id + "n'existe pas");
        return equipe;
    }

    @PutMapping(value = "/Equipes")
    public void updateEquipe(@RequestBody Equipe equipe){
        equipeDao.save(equipe);
    }

    @DeleteMapping(value = "/Equipes")
    public void deleteEquipe(@RequestBody Equipe equipe){
        equipeDao.delete(equipe);
    }
}
