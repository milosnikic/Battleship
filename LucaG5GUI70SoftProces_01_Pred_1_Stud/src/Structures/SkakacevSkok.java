package Structures;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Structure.*;
import java.io.FileNotFoundException;

import java.io.IOException;

import Structure.Template;



/**
 *
 * @author Sinisa
 */
public class SkakacevSkok extends BaseStructure {
     
    public static void main(String[] args) throws FileNotFoundException, IOException {
    new SkakacevSkok().create();
    }
    
    public SkakacevSkok(){super();}   
    public SkakacevSkok(Property[] prop) {super(prop);}
    
    
    @Override
    public void create () throws FileNotFoundException, IOException {
        String structureName = getStructureName();
        String userReqFileFolderPath = getUserRequirements();
        s = new Structure(structureName,this.prop);
        s.addProperty("author", "dr. Sinisa Vlajic, red. prof.");
        FileURL fu = new FileURL(projectName,".txt",userReqFileFolderPath);
        s.addProperty("menuBar","GlavniMeni");
        
                     
        Structure at = new Structure("Menu 1",true);
        at.addProperty("menu1", "skakacevSkok");
        at.addProperty("text1", "Skakacev skok");
        Template.menuJavaFx(at);
        s.addStructure(at);                
        
        Structure at1 = new Structure("Menu item 11");
        at1.addProperty("menuItem1", "start");
        at1.addProperty("onAction1", "#pronaciPutOdGornjegLevogDoDonjegDesnogUgla");
        at1.addProperty("text2", "Start");
        Template.menuItemJavaFx(at1);
        at.addStructure(at1);
        
        Structure at2  = new Structure("Menu item 12");
        at2.addProperty("menuItem1", "start1");
        at2.addProperty("text2", "Start - test ");
        at2.addProperty("onAction1", "#pronaciPutOdGornjegLevogDoDonjegDesnogUgla");
        Template.menuItemJavaFx(at2);
        at.addStructure(at2);
        
        
        Structure at3 = new Structure("Menu 2",true);
        at3.addProperty("menu1", "informacijeOProgramu");
        at3.addProperty("text1", "Informacije o programu");
        Template.menuJavaFx(at3);
        s.addStructure(at3);
        
        Structure at4 = new Structure("Menu item 2");
        at4.addProperty("menuItem1", "autorPrograma");
        at4.addProperty("onAction1", "#autorPrograma");
        at4.addProperty("text2", "Autor programa");
        Template.menuItemJavaFx(at4); 
        at3.addStructure(at4);
        
        
        Structure at5 = new Structure("Menu 3",true);
        at5.addProperty("menu1", "izlazIzPrograma");
        at5.addProperty("text1", "Izlaz iz programa");
        Template.menuJavaFx(at5);
        s.addStructure(at5);
        
        Structure at6 = new Structure("Menu item 3");
        at6.addProperty("menuItem1", "Izlaz");
        at6.addProperty("onAction1", "#izlazIzPrograma");
        at6.addProperty("text2", "Izlaz");
        Template.menuItemJavaFx(at6); 
        at5.addStructure(at6);
        
        
        saveStructureToLCSTFile();
        
        
    }
   
   
}