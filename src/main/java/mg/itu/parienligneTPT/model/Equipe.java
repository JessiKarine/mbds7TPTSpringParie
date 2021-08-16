package mg.itu.parienligneTPT.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.awt.*;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Equipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nom;
    private String image;

    public Equipe() {
    }

    public Equipe(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Equipe{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public static String uploadImage(MultipartFile file)throws Exception{
        Image image=null;
        List<String> extensionImage=new ArrayList<String>();
        String path="E:/fianarana/tpt/mbdsp7tptBackPari/src/assets/images/";
        extensionImage.add("jpg");
        extensionImage.add("jpeg");
        extensionImage.add("png");
        extensionImage.add("gif");
        String[] filextension=file.getOriginalFilename().split("\\.");
        try {

            if(!extensionImage.contains(filextension[filextension.length-1])){
                throw new Exception("Nous ne prenons pas en compte ce type de fichiers");
            }
            File check=new File("/images/importe/"+file.getOriginalFilename());

            File dir = new File(path);
            if(!dir.exists()) {
                dir.mkdir();
            }
            byte barr[] = file.getBytes();
            System.out.println("DIR NAME="+dir.getAbsolutePath());
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(dir.getAbsolutePath() + "/" + file.getOriginalFilename()));
            out.write(barr);
            out.flush();
            out.close();
            return file.getOriginalFilename();
        }catch (Exception e){
            throw e;
        }
    }
}
