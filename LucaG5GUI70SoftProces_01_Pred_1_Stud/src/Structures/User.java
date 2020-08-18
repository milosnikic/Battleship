/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures;

import Structure.FileURL;
import Structure.PropFile;
import Structure.Structure;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author milos
 */
public class User extends BaseStructure {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        new Korisnik().create();
    }

    @Override
    public void create() throws FileNotFoundException, IOException {
        String structureName = getStructureName();

        s = new Structure(structureName);
        s.addProperty("Autor:", "Milo? Niki?");

        s.addProperty("dk", structureName);
        s.addProperty("poruka1", "Problem oko brojaca korisnika.");
        s.addProperty("poruka2", "Ne moze da se poveca brojac korisnika.");
        s.addProperty("poruka3", "Korisnik je kreiran.");
        s.addProperty("poruka4", "Nije mogao da bude kreiran korisnik.");
        s.addProperty("poruka5", "Korisnik je obrisan.");
        s.addProperty("poruka6", "Nije mogao da se obrise korisnik.");
        s.addProperty("poruka7", "Ne moze se obrisati korisnik jer ne postoji.");
        s.addProperty("poruka8", "Korisnik je promenjen.");
        s.addProperty("poruka9", "Nije mogao da se promeni korisnik.");
        s.addProperty("porukaX0", "Ne moze se promeniti korisnik jer ne postoji.");

        Structure at = new Structure("User ID");
        at.addProperty("at", "idUser");
        at.addProperty("aPK", "idUser");
        at.addProperty("tPK", "int");
        at.addProperty("type", "int");
        at.addProperty("access", "public");
        s.addStructure(at);

        at = new Structure("Username");
        at.addProperty("at", "username");
        at.addProperty("type", "String");
        at.addProperty("access", "public");
        s.addStructure(at);

        at = new Structure("password");
        at.addProperty("at", "password");
        at.addProperty("type", "String");
        at.addProperty("access", "public");
        s.addStructure(at);

        saveStructureToLCSTFile();

    }

}
