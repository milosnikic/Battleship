package DKExamples;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.FileNotFoundException;
import java.io.IOException;
import Structure.*;
import Structures.Film;



/**
 *
 * @author Sinisa Vlajic
 */

// Example DKExample1: Generating DKExample1 java program.



public class RZJFX16S {
    
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException, InterruptedException {
       
        String projectName = "RZJFX16S";
        ProjectFileResourceSS1 pfr = new ProjectFileResourceSS1(projectName);
        Project pr = new Project(pfr);
        LucaEngine lengine = new LucaEngine(pr);
        
        lengine.connectSTC(new Film(),"DKObject1.lctm", "Film.java", pfr.domainClassesFolderPath);
        lengine.connectSTC(new Film(),"TransferObjekat.lctm", "TransferObjekat.java", pfr.domainClassesFolderPath);
        lengine.connectSTC(new Film(),"SpecificniKontrolerGUI.lctm", "SpecificniKontrolerGUI.java", pfr.domainClassesFolderPath);
        lengine.connectSTC(new Film(),"JFX01.lctm", "JFX01.java", pfr.domainClassesFolderPath);
        
        lengine.connectSTC(new Film(),"MySQLTableTemplate.lctm", "Film.sql", pfr.domainClassesFolderPath);
        lengine.connectSTC(new Film(),"MySQLCounterTableTemplate.lctm", "CounterFilm.sql", pfr.domainClassesFolderPath); 

        
        // Dodati programski kod
        
        
        lengine.showJavaFXForm();
        lengine.runGenerator();
        
    } 
    
   
    
}
