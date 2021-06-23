package mg.itu.parienligneTPT.controller;

import mg.itu.parienligneTPT.dao.AgenceDao;
import mg.itu.parienligneTPT.model.Agence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AgenceController {

    @Autowired
    private AgenceDao agenceDao;

    @GetMapping(value = "/Agences")
    public List<Agence> listeAgence(){
        return agenceDao.findAll();
    }
    @PostMapping(value = "/Agences")
    public String ajouterAgence(@RequestBody Agence agence){
         agenceDao.save(agence);
        return "agenceAdded";
    }
}
