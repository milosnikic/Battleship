package Structures;
/* Autor programa:  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */

import Structure.*;
import java.io.FileNotFoundException;

import java.io.IOException;

public class Korisnik extends BaseStructure {
     
    public static void main(String[] args) throws FileNotFoundException, IOException {
    new Korisnik().create();
    }
    
    
    @Override
    public void create () throws FileNotFoundException, IOException {
        String structureName = getStructureName();
        
        s = new Structure(structureName);
        s.addProperty("Autor:", "dr. Sini?a Vlaji?, red. prof.");
        
        s.addProperty("dk",structureName);
        s.addProperty("poruka1","Problem oko brojaca korisnika.");
        s.addProperty("poruka2","Ne moze da se poveca brojac korisnika.");
        s.addProperty("poruka3","Korisnik je kreiran.");
        s.addProperty("poruka4","Nije mogao da bude kreiran korisnik.");
        s.addProperty("poruka5","Korisnik je obrisan.");
        s.addProperty("poruka6","Nije mogao da se obrise korisnik.");
        s.addProperty("poruka7","Ne moze se obrisati korisnik jer ne postoji.");
        s.addProperty("poruka8","Korisnik je promenjen.");
        s.addProperty("poruka9","Nije mogao da se promeni korisnik.");
        s.addProperty("porukaX0","Ne moze se promeniti korisnik jer ne postoji.");
        
        
        
        Structure at = new Structure("ID User");
        at.addProperty("at", "idUser");
        at.addProperty("aPK", "idUser");
        at.addProperty("tPK", "int");
        at.addProperty("type", "int");
        at.addProperty("access", "private");
        s.addStructure(at);
        
        at = new Structure("Username");        
        at.addProperty("at", "username");
        at.addProperty("type", "String");
        at.addProperty("access", "private");
        s.addStructure(at);
        
        at = new Structure("Password");        
        at.addProperty("at", "password");
        at.addProperty("type", "String");
        at.addProperty("access", "private");
        s.addStructure(at);
        
        at = new Structure("Firstname");        
        at.addProperty("at", "firstname");
        at.addProperty("type", "String");
        at.addProperty("access", "private");
        s.addStructure(at);
        
        at = new Structure("Lastname");        
        at.addProperty("at", "lastname");
        at.addProperty("type", "String");
        at.addProperty("access", "private");
        s.addStructure(at);
        
        saveStructureToLCSTFile();
        
        
    }
   
   
}



