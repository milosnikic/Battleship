/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures;

import Structure.FileURL;
import Structure.Property;
import Structure.Structure;
import Structure.Template;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author milos
 */
public class MainMenu extends BaseStructure {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        new GlavniMeni().create();
    }

    public MainMenu() {
        super();
    }

    public MainMenu(Property[] prop) {
        super(prop);
    }

    @Override
    public void create() throws FileNotFoundException, IOException {
        String structureName = getStructureName();

        s = new Structure(structureName, this.prop);
        s.addProperty("menuBar", "MainMenu");

        Structure at = new Structure("Menu 1", true);
        at.addProperty("menu1", "game");
        at.addProperty("text1", "Igra");
        Template.menuJavaFx(at);
        s.addStructure(at);

        Structure at1 = new Structure("Menu item 11");
        at1.addProperty("menuItem1", "newGame");
        at1.addProperty("onAction1", "#newGame");
        at1.addProperty("text2", "New game");
        Template.menuItemJavaFx(at1);
        at.addStructure(at1);

        Structure at3 = new Structure("Menu 2", true);
        at3.addProperty("menu1", "gameRules");
        at3.addProperty("text1", "Pravila igre");
        Template.menuJavaFx(at3);
        s.addStructure(at3);

        Structure at8 = new Structure("Menu item 23");
        at8.addProperty("menuItem1", "rules");
        at8.addProperty("onAction1", "#showRules");
        at8.addProperty("text2", "Prikazi pravila");
        Template.menuItemJavaFx(at8);
        at3.addStructure(at8);

        Structure at4 = new Structure("Menu item 2");
        at4.addProperty("menuItem1", "authorInformation");
        at4.addProperty("onAction1", "#showAuthorInformation");
        at4.addProperty("text2", "Autor programa");
        Template.menuItemJavaFx(at4);
        at3.addStructure(at4);

        Structure at7 = new Structure("Menu item 22");
        at7.addProperty("menuItem1", "scoreboard");
        at7.addProperty("onAction1", "#showScoreboard");
        at7.addProperty("text2", "Rang lista");
        Template.menuItemJavaFx(at7);
        at3.addStructure(at7);

        Structure at5 = new Structure("Menu 3", true);
        at5.addProperty("menu1", "exitGame");
        at5.addProperty("text1", "Izlaz iz programa");
        Template.menuJavaFx(at5);
        s.addStructure(at5);

        Structure at6 = new Structure("Menu item 3");
        at6.addProperty("menuItem1", "exit");
        at6.addProperty("onAction1", "#exitGame");
        at6.addProperty("text2", "Izlaz");
        Template.menuItemJavaFx(at6);
        at5.addStructure(at6);

        saveStructureToLCSTFile();
    }
}
