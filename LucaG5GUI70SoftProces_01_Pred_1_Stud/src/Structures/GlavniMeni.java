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
 * @author Administrator
 */
public class GlavniMeni extends BaseStructure {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        new GlavniMeni().create();
    }

    public GlavniMeni() {
        super();
    }

    public GlavniMeni(Property[] prop) {
        super(prop);
    }

    @Override
    public void create() throws FileNotFoundException, IOException {
        String structureName = getStructureName();
        String userReqFileFolderPath = getUserRequirements();
        s = new Structure(structureName, this.prop);

        FileURL fu = new FileURL(projectName, ".txt", userReqFileFolderPath);
        s.addProperty("menuBar", "GlavniMeni");

        Structure at = new Structure("Menu 1", true);
        at.addProperty("menu1", "prikazImena");
        at.addProperty("text1", "Prikaz imena");
        Template.menuJavaFx(at);
        s.addStructure(at);

        Structure at1 = new Structure("Menu item 11");
        at1.addProperty("menuItem1", "prikaziIme");
        at1.addProperty("onAction1", "#prikazatiIme");
        at1.addProperty("text2", "Prikazi ime");
        Template.menuItemJavaFx(at1);
        at.addStructure(at1);

  
        Structure at3 = new Structure("Menu 2", true);
        at3.addProperty("menu1", "prikazAdrese");
        at3.addProperty("text1", "Prikaz adrese");
        Template.menuJavaFx(at3);
        s.addStructure(at3);

        Structure at4 = new Structure("Menu item 2");
        at4.addProperty("menuItem1", "prikaziAdresu");
        at4.addProperty("onAction1", "#prikazatiAdresu");
        at4.addProperty("text2", "Autor programa");
        Template.menuItemJavaFx(at4);
        at3.addStructure(at4);

        Structure at5 = new Structure("Menu 3", true);
        at5.addProperty("menu1", "izlazIzPrograma");
        at5.addProperty("text1", "Izlaz iz programa");
        Template.menuJavaFx(at5);
        s.addStructure(at5);

        Structure at6 = new Structure("Menu item 3");
        at6.addProperty("menuItem1", "Izlaz");
        at6.addProperty("onAction1", "#izlazakIzPrograma");
        at6.addProperty("text2", "Izlaz");
        Template.menuItemJavaFx(at6);
        at5.addStructure(at6);

        saveStructureToLCSTFile();

    }

}
