package mg.itu.parienligneTPT.controller;

import mg.itu.parienligneTPT.dao.AgenceDao;
import mg.itu.parienligneTPT.exception.ExceptionService;
import mg.itu.parienligneTPT.model.Agence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class AgenceController {

    @Autowired
    private AgenceDao agenceDao;

    @GetMapping(value = "/Agences")
    public List<Agence> listAgence(){
        return agenceDao.findAll();
    }

    @PostMapping(value = "/Agences")
    public ResponseEntity<Void> addAgence(@RequestBody Agence agence){
         Agence agenceAdded = agenceDao.save(agence);
         if(agenceAdded == null)
             return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(agenceAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/Agences/{id}")
    public Agence getAgenceById(@PathVariable int id){
        Agence agence = agenceDao.findById(id);
        if(agence == null)
            throw new ExceptionService("L'agence id: " + id + "n'existe pas");
        return agence;
    }

    @PutMapping(value = "/Agences")
    public void updateAgence(@RequestBody Agence agence){
        agenceDao.save(agence);
    }

    @DeleteMapping(value = "/Agences")
    public void deleteAgence(@RequestBody Agence agence){
        agenceDao.delete(agence);
    }
}
