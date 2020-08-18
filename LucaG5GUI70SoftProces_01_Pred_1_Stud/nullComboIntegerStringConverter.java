/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerGeneral;

import javafx.util.converter.IntegerStringConverter;



/**
 *
 * @author Sinisa
 */
public class ComboIntegerStringConverter extends IntegerStringConverter{
   String key;
   String name;
   String table;
   public ComboIntegerStringConverter(String key, String name, String table) {this.key = key; this.name = name; this.table = table;}
   public String getKey(){return key;}
   public String getName(){return name;}
   public String getTable(){return table;}
    
}
