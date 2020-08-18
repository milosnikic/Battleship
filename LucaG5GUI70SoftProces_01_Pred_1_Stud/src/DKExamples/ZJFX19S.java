package DKExamples;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.FileNotFoundException;
import java.io.IOException;
import Structure.*;
import Structures.Glumac;



/**
 *
 * @author Sinisa Vlajic
 */

// Example DKExample1: Generating DKExample1 java program.



public class ZJFX19S {
    
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException, InterruptedException {
       
        String projectName = "ZJFX19S";
        ProjectFileResourceSS1 pfr = new ProjectFileResourceSS1(projectName);
        Project pr = new Project(pfr);
        LucaEngine lengine = new LucaEngine(pr);
        
        String clientPath = "C:/Predmeti/Original/SoftverskiProces/2020/Predavac/ResenjeZadataka/ZJFX19K/src/"; // uneti putanju do vaseg klijentskog projekta
        String serverPath = "C:/Predmeti/Original/SoftverskiProces/2020/Predavac/ResenjeZadataka/ZJFX19S/src/java/"; // uneti putanju do vaseg serverskog projekta
        
        lengine.connectSTC(new Glumac(),"DKObject2.lctm", "Glumac.java", serverPath + "DomenskeKlase/");
        lengine.connectSTC(new Glumac(),"TransferObjekat.lctm", "TransferObjekat.java", serverPath + "TransferObjekat/");
        lengine.connectSTC(new Glumac(),"SpecificniKontrolerGUI.lctm", "SpecificniKontrolerGUI.java", clientPath + "GUIKorisnik/");
        lengine.connectSTC(new Glumac(),"JFX01.lctm", "JFX01.java", clientPath + "GUIKorisnik/");
        
        lengine.connectSTC(new Glumac(),"MySQLTableTemplate.lctm", "Glumac.sql", pfr.domainClassesFolderPath);
        lengine.connectSTC(new Glumac(),"MySQLCounterTableTemplate.lctm", "CounterGlumac.sql", pfr.domainClassesFolderPath); 

        
        lengine.connectSTC(new Glumac(),"FXMLDocument.lctm", "FXMLDocument.fxml", clientPath + "GUIKorisnik/");
        lengine.connectSTC(new Glumac(),"FXMLDocumentController.lctm", "FXMLDocumentController.java", clientPath + "GUIKorisnik/");
        
        
        lengine.showJavaFXForm();
        lengine.runGenerator();
        
    } 
    
   
    
}
