package mg.itu.parienligneTPT.controller;

import mg.itu.parienligneTPT.dao.CategorieDao;
import mg.itu.parienligneTPT.exception.ExceptionService;
import mg.itu.parienligneTPT.model.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
public class CatgorieController {
    @Autowired
    private CategorieDao categorieDao;

    @GetMapping(value = "/Categories")
    public List<Categorie> listCategorie(){
        return categorieDao.findAll();
    }

    @PostMapping(value = "/Categories")
    public ResponseEntity<Void> addCategorie(@RequestBody Categorie categorie){
        Categorie categorieAdded = categorieDao.save(categorie);
        if(categorieAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categorieAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/Categories/{id}")
    public Categorie categorieById(@PathVariable int id){
        Categorie categorie = categorieDao.findById(id);
        if(categorie == null)
            throw new ExceptionService("Catégorie id: " + id + "n'existe pas");
        return categorie;
    }

    @PutMapping(value = "/Categories")
    public void updateCategorie(@RequestBody Categorie categorie){
        categorieDao.save(categorie);
    }

    @DeleteMapping(value = "/Categories")
    public void deleteCategorie(@RequestBody Categorie categorie){
        categorieDao.delete(categorie);
    }
}
