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
public class Film extends BaseStructure {
     
    public static void main(String[] args) throws FileNotFoundException, IOException {
    new Film().create();
    }
    
    public Film(){super();}   
    public Film(Property[] prop) {super(prop);}
    
    
    @Override
    public void create () throws FileNotFoundException, IOException {
        String structureName = getStructureName();
        String userReqFileFolderPath = getUserRequirements();
        s = new Structure(structureName,this.prop);
        s.addProperty("author", "dr. Sinisa Vlajic, red. prof.");
        FileURL fu = new FileURL(projectName,".txt",userReqFileFolderPath);
        s.addProperty(new PropFile("userreq",fu) );
        s.addProperty("dbname","skakacevskok");
        s.addProperty("dk","Film");
        s.addProperty("tl",structureName);
        s.addProperty("poruka1","Problem oko brojaca filma.");
        s.addProperty("poruka2","Ne moze da se poveca brojac filma.");
        s.addProperty("poruka3","Film je kreiran.");
        s.addProperty("poruka4","Nije mogao da bude kreiran film.");
        s.addProperty("poruka5","Film je obrisan.");
        s.addProperty("poruka6","Nije mogao da se obrise film.");
        s.addProperty("poruka7","Ne moze se obrisati film jer ne postoji.");
        s.addProperty("poruka8","Film je promenjeno.");
        s.addProperty("poruka9","Nije mogao da se promeni film.");
        s.addProperty("porukaX0","Ne moze se promeniti film jer ne postoji.");
        
        s.addProperty("kreiraj", "Kreiraj film");
        s.addProperty("promeni", "Promeni film");
        s.addProperty("obrisi", "Obrisi film");
        
        
                     
        Structure at = new Structure("Sifra filma");
        at.addProperty("at", "sifraFilma");
        at.addProperty("aPK", "sifraFilma");
        at.addProperty("lab","Sifra filma:");
        at.addProperty("tPK", "int");
        at.addProperty("type", "int");
        at.addProperty("access", "public");
        at.addProperty("tpMySQL", "int");
        at.addProperty("sizeSQL", "(11)");
        at.addProperty("Null", "NOT NULL");
        at.addProperty("defaultMySQL", "");
        Template.addColumn(at);
        Template.fxLbTxtFld(at,56);
        at.addProperty("fxtp", "TextField");
        s.addStructure(at);
        
        at = new Structure("Naziv filma");        
        // Dodati programski kod
        
        at = new Structure("Ime rezisera");        
        // Dodati programski kod
        
        at = new Structure("Datum nabavke");        
        at.addProperty("at", "datumNabavke");
        at.addProperty("lab","Datum nabavke:");
        at.addProperty("type", "Date");
        at.addProperty("access", "public");
        at.addProperty("getDt","#1 public java.util.Date get~f at~1(java.util.Date ~f at)\n" +
"      {SimpleDateFormat sm = new SimpleDateFormat(\"yyyy-MM-dd\");\n" +
"#1       this.~f at~ = java.sql.Date.valueOf(sm.format(~f at)); \n" +
"#1       return this.~f at;\n" +
"      }");
        at.addProperty("tpMySQL", "date");
        at.addProperty("sizeSQL", "");
        at.addProperty("Null", "");
        at.addProperty("defaultMySQL", "DEFAULT NULL");
        Template.addColumn(at);
        Template.fxLblDtPicker(at,206);
        at.addProperty("fxtp", "DatePicker");
        s.addStructure(at);
        
        
        saveStructureToLCSTFile();
        
        
    }
   
   
}