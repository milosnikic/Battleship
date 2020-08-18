/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DKExamples;

import Structure.LucaEngine;
import Structure.Project;
import Structure.ProjectFileResourceSS1;
import Structures.Game;
import Structures.Korisnik;
import Structures.MainMenu;
import Structures.User;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author milos
 */
public class BattleshipGame {

    public static void main(String[] args) throws IOException, FileNotFoundException, FileNotFoundException, ClassNotFoundException{
        String projectName = "BattleshipGame";
        ProjectFileResourceSS1 pfr = new ProjectFileResourceSS1(projectName);
        Project pr = new Project(pfr);
        LucaEngine lengine = new LucaEngine(pr);

        lengine.connectSTC(new User(), "DKObject.lctm", "User.java", pfr.domainClassesFolderPath);
        lengine.connectSTC(new Game(), "DKObject.lctm", "Game.java", pfr.domainClassesFolderPath);
        lengine.connectSTC(new MainMenu(), "FXMLDocument1.lctm", "FXMLDocument.fxml", pfr.domainClassesFolderPath);

        lengine.showJavaFXForm();

        lengine.runGenerator();
    }
}
