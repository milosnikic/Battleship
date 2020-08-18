/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures;

import Structure.Structure;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author milos
 */
public class Game extends BaseStructure {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        new Game().create();
    }

    @Override
    public void create() throws FileNotFoundException, IOException, ClassNotFoundException {
        String structureName = getStructureName();

        s = new Structure(structureName);
        s.addProperty("Autor:", "Milos Nikic");

        s.addProperty("dk", structureName);
        
        Structure at = new Structure("Game ID");
        at.addProperty("at", "idGame");
        at.addProperty("aPK", "idGame");
        at.addProperty("tPK", "int");
        at.addProperty("type", "int");
        at.addProperty("access", "public");
        s.addStructure(at);

        at = new Structure("User ID");
        at.addProperty("at", "idUser");
        at.addProperty("aFK", "idUser");
        at.addProperty("tFK", "User");
        at.addProperty("type", "int");
        at.addProperty("access", "public");
        s.addStructure(at);

        at = new Structure("Winner ID");
        at.addProperty("at", "idWinner");
        at.addProperty("aFK", "idWinner");
        at.addProperty("tFK", "User");
        at.addProperty("type", "int");
        at.addProperty("access", "public");
        s.addStructure(at);
        
        at = new Structure("End");
        at.addProperty("at", "end");
        at.addProperty("type", "boolean");
        at.addProperty("access", "public");
        s.addStructure(at);
        
        at = new Structure("Number of fields left");
        at.addProperty("at", "numberOfFieldsLeft");
        at.addProperty("type", "int");
        at.addProperty("access", "public");
        s.addStructure(at);
        
        at = new Structure("Number of fields hit");
        at.addProperty("at", "numberOfFieldsHit");
        at.addProperty("type", "int");
        at.addProperty("access", "public");
        s.addStructure(at);
        
        at = new Structure("Score");
        at.addProperty("at", "score");
        at.addProperty("type", "int");
        at.addProperty("access", "public");
        s.addStructure(at);

        saveStructureToLCSTFile();
    }

}
