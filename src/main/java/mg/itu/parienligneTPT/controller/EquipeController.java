package mg.itu.parienligneTPT.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mg.itu.parienligneTPT.dao.EquipeDao;
import mg.itu.parienligneTPT.exception.ExceptionService;
import mg.itu.parienligneTPT.model.Equipe;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.net.URI;
import java.util.List;
@CrossOrigin(origins = "*")
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

    @PostMapping(value = "/Equipes/Update")
    public void updateEquipeBack(@RequestParam("file") MultipartFile file , @RequestParam("equipe") String equipe){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Equipe equipeselected  = objectMapper.readValue(equipe, Equipe.class);
            String nomfile = Equipe.uploadImage(file);
            System.out.println("nom image : "+nomfile);
            equipeselected.setImage(nomfile);
            equipeDao.save(equipeselected);

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @DeleteMapping(value = "/Equipes")
    public void deleteEquipe(@RequestBody Equipe equipe){
        equipeDao.delete(equipe);
    }


}
