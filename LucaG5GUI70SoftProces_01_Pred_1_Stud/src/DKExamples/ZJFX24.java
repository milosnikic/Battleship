package DKExamples;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.FileNotFoundException;
import java.io.IOException;
import Structure.*;
import Structures.GlavniMeni;



/**
 *
 * @author Sinisa Vlajic
 */

// Example DKExample1: Generating DKExample1 java program.



public class ZJFX24 {
    
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException, InterruptedException {
       
        String projectName = "RZJFX24";
        ProjectFileResourceSS1 pfr = new ProjectFileResourceSS1(projectName);
        Project pr = new Project(pfr);
        LucaEngine lengine = new LucaEngine(pr);
        
        
        lengine.connectSTC(new GlavniMeni(),"FXMLDocument1.lctm", "FXMLDocument.fxml", pfr.domainClassesFolderPath);
        
        lengine.showJavaFXForm();
        lengine.runGenerator();
        
    } 
    
   
    
}