/* Autor programa:  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */

package Structure;

import java.util.ArrayList;
import java.util.List;

public class Template {
    String type;
    String property;
    String value;
    
    public Template(String type,String property,String value)
    { this.type = type;
      this.property = property;
      this.value = value;
    }
    
    
    static List<Template> temps = new ArrayList<>();
    
    public static void createTemplate()
    {  temps = new ArrayList<>(); 
       
       //gtp**********************************************
       Template temp = new Template("boolean","gtp","getBoolean"); 
       temps.add(temp);
       temp = new Template("byte","gtp","getByte"); 
       temps.add(temp);
       temp = new Template("Date","gtp","getDate"); 
       temps.add(temp);
       temp = new Template("double","gtp","getDouble"); 
       temps.add(temp);
       temp = new Template("int","gtp","getInt"); 
       temps.add(temp);
       temp = new Template("long","gtp","getLong"); 
       temps.add(temp);
       temp = new Template("String","gtp","getString"); 
       temps.add(temp);
       //avr**********************************************
       temp = new Template("boolean","avr","#1 ~f at"); 
       temps.add(temp);
       temp = new Template("byte","avr","#1 ~f at"); 
       temps.add(temp);
       temp = new Template("Date","avr","#1 ~f \"'\" + at + \"'\""); 
       temps.add(temp);
       temp = new Template("double","avr","#1 ~f at"); 
       temps.add(temp);
       temp = new Template("int","avr","#1 ~f at"); 
       temps.add(temp);
       temp = new Template("long","avr","#1 ~f at"); 
       temps.add(temp);
       temp = new Template("String","avr","#1 ~f (at == null ? null : \"'\" + at + \"'\")"); 
       temps.add(temp);
       //av1**********************************************
       temp = new Template("boolean","av1","#1 ~f at"); 
       temps.add(temp);
       temp = new Template("byte","av1","#1 ~f at"); 
       temps.add(temp);
       temp = new Template("Date","av1","#1 ~f \"'\" + getat1(at) + \"'\""); 
       temps.add(temp);
       temp = new Template("double","av1","#1 ~f at"); 
       temps.add(temp);
       temp = new Template("int","av1","#1 ~f at"); 
       temps.add(temp);
       temp = new Template("long","av1","#1 ~f at"); 
       temps.add(temp);
       temp = new Template("String","av1","#1 ~f (at == null ? null : \"'\" + at + \"'\")"); 
       temps.add(temp);
       //defaultValueDK**********************************************
       temp = new Template("boolean","defaultValueDC","#1 ~f at=true;"); 
       temps.add(temp);
       temp = new Template("byte","defaultValueDC","#1 ~f at=0;"); 
       temps.add(temp);
       String dt =  "SimpleDateFormat sm = new SimpleDateFormat(\"yyyy-MM-dd\");\n" +
"#1  ~f at~ = new Date();\n" +
"#1  ~f at~ = java.sql.Date.valueOf(sm.format(~f at)); \n"; 

       temp = new Template("Date","defaultValueDC",dt); 
       temps.add(temp);
       temp = new Template("double","defaultValueDC","#1 ~f at=0;"); 
       temps.add(temp);
       temp = new Template("int","defaultValueDC","#1 ~f at=0;"); 
       temps.add(temp);
       temp = new Template("long","defaultValueDC","#1 ~f at=0;"); 
       temps.add(temp);
       temp = new Template("String","defaultValueDC","#1 ~f at=\"\";"); 
       temps.add(temp);
       //setAtValueSc**********************************************
        temp = new Template("boolean","setAtValueSc","#1 ~f obj.setat(Boolean.parseBoolean(sc.nextLine()));"); 
       temps.add(temp);
       temp = new Template("byte","setAtValueSc","1 ~f obj.setat(Byte.parseByte(sc.nextLine()));"); 
       temps.add(temp);
       String dt1 =  "String SDatumOsnivanja = sc.nextLine();\n" +
"SimpleDateFormat sm = new SimpleDateFormat(\"yyyy-MM-dd\");\n" +
"#1  Date ~f at = sm.parse(Sat);\n" +
"#1 ~f at~ = java.sql.Date.valueOf(sm.format(~f at)); \n" +
"#1 ~f obj.setat(at);"; 

       temp = new Template("Date","setAtValueSc",dt1); 
       temps.add(temp);
       temp = new Template("double","setAtValueSc","#1 ~f obj.setat(Double.parseDouble(sc.nextLine()));"); 
       temps.add(temp);
       temp = new Template("int","setAtValueSc","#1 ~f obj.setat(Integer.parseInt(sc.nextLine()));"); 
       temps.add(temp);
       temp = new Template("long","setAtValueSc","#1 ~f obj.setat(Long.parseLong(sc.nextLine()));"); 
       temps.add(temp);
       temp = new Template("String","setAtValueSc","#1 ~f obj.setat(sc.nextLine());"); 
       temps.add(temp);
       //setAtJSON**********************************************
        temp = new Template("boolean","setAtJSON","#1 ~f obj.setat(jsobj.getBoolean(\"at\"));"); 
       temps.add(temp);
       temp = new Template("byte","setAtJSON","1 ~f obj.setat(jsobj.getInt(\"at\"));"); 
       temps.add(temp);
       dt1 =  "SimpleDateFormat sm = new SimpleDateFormat(\"yyyy-MM-dd\");\n" +
"#1  Date ~f at~ = sm.parse((String)jsobj.get(\"~f at\"));\n" +
"#1 ~f at~ = java.sql.Date.valueOf(sm.format(~f at));  \n" +
"#1 ~f obj.setat(at);"; 

       temp = new Template("Date","setAtJSON",dt1); 
       temps.add(temp);
       temp = new Template("double","setAtJSON","#1 ~f obj.setat(jsobj.getDouble(\"at\"));");
       temps.add(temp);
       temp = new Template("int","setAtJSON","#1 ~f obj.setat(jsobj.getInt(\"at\"));");
       temps.add(temp);
       temp = new Template("long","setAtJSON","#1 ~f obj.setat(jsobj.getLong(\"at\"));");
       temps.add(temp);
       temp = new Template("String","setAtJSON","#1 ~f obj.setat(jsobj.getString(\"at\"));");
       temps.add(temp);
       //criteriaQuery**********************************************
       temp = new Template("boolean","criteriaQuery","#1 ~f case \"at\": query = query + \" Where at = \" + criteria; break;");
       temps.add(temp);
       temp = new Template("byte","criteriaQuery","#1 ~f case \"at\": query = query + \" Where at = \" + criteria; break;"); 
       temps.add(temp);
       temp = new Template("Date","criteriaQuery","#1 ~f case \"at\": query = query + \" Where at = \" + criteria; break;"); 
       temps.add(temp);
       temp = new Template("double","criteriaQuery","#1 ~f case \"at\": query = query + \" Where at = \" + criteria; break;");
       temps.add(temp);
       temp = new Template("int","criteriaQuery","#1 ~f case \"at\": query = query + \" Where at = \" + criteria; break;");
       temps.add(temp);
       temp = new Template("long","criteriaQuery","#1 ~f case \"at\": query = query + \" Where at = \" + criteria; break;");
       temps.add(temp);
       temp = new Template("String","criteriaQuery","#1 ~f case \"at\": query = query + \" Where at like '%\" + criteria + \"%'\"; break;");
       temps.add(temp);
       //setDomainObject**********************************************
       temp = new Template("boolean","setDomainObject","#1 ~f case \"at\": \n" +
"                  o = t.getNewValue();\n" +
"#1 ~f                 ((dk) t.getTableView().getItems().get(t.getTablePosition().getRow())).setat((Boolean) o); break;");
       temps.add(temp);
       temp = new Template("byte","setDomainObject","#1 ~f case \"at\": \n" +
"                  o = t.getNewValue();\n" +
"#1 ~f                  ((dk) t.getTableView().getItems().get(t.getTablePosition().getRow())).setat((Byte)o); break;");
       temps.add(temp);
       temp = new Template("Date","setDomainObject","#1 ~f  case \"at\": \n" +
"                   o = (Object)t.getNewValue();\n" +
"                   SimpleDateFormat sm = new SimpleDateFormat(\"yyyy-MM-dd\");\n" +
"#1                    Date ~f at~ = (Date)o;\n" +
"#1 ~f                   at~ = java.sql.Date.valueOf(sm.format(~f at));\n" +
"#1 ~f                   if (at!=null)            \n" +
"#1 ~f                      ((dk) t.getTableView().getItems().get(t.getTablePosition().getRow())).setat(at); \n" +
"                   break;"); 
       temps.add(temp);
       temp = new Template("double","setDomainObject","#1 ~f case \"at\": \n" +
"                  o = t.getNewValue();\n" +
"#1 ~f                  ((dk) t.getTableView().getItems().get(t.getTablePosition().getRow())).setat((Double)o); break;");
       temps.add(temp);
       temp = new Template("int","setDomainObject","#1 ~f case \"at\": \n" +
"                  o = t.getNewValue();\n" +
"#1 ~f                  ((dk) t.getTableView().getItems().get(t.getTablePosition().getRow())).setat((Integer)o); break;");
       temps.add(temp);
       temp = new Template("long","setDomainObject","#1 ~f case \"at\": \n" +
"                  o = t.getNewValue();\n" +
"#1 ~f                  ((dk) t.getTableView().getItems().get(t.getTablePosition().getRow())).setat((Long)o); break;");
       temps.add(temp);
       temp = new Template("String","setDomainObject","#1 ~f case \"at\": \n" +
"#1 ~f                  ((dk) t.getTableView().getItems().get(t.getTablePosition().getRow())).setat(t.getNewValue()); break;");
       temps.add(temp);
       //classType**********************************************
       temp = new Template("boolean","classType","Boolean"); 
       temps.add(temp);
       temp = new Template("byte","classType","Byte"); 
       temps.add(temp);
       temp = new Template("Date","classType","Date"); 
       temps.add(temp);
       temp = new Template("double","classType","Double"); 
       temps.add(temp);
       temp = new Template("int","classType","Integer"); 
       temps.add(temp);
       temp = new Template("long","classType","Long"); 
       temps.add(temp);
       temp = new Template("String","classType","String"); 
       temps.add(temp);
    
    }
    
 public static void createdDerivedProperty(String type,Structure st)
 { for(Template temp:temps)
     { if(temp.type.equals(type) )
         {  String temp1 = Generator.generateValue(temp.value,st);
            Property pv = new Property(temp.property,temp1);
            if (st.addProperty(pv)==true)
                pv.setLoad(true);
            
         }
     }
   
 }
 
 public static void criteriaQuery(Structure at)
 {
   at.addProperty("criteriaQuery","#1 ~f case \"at\": query = \"Select * from dk,fkTable Where dk.at = fkTable.fkAttribute and fkName like '%\" + criteria + \"%'\";break;");
 }
 
 public static void getValueCombo(Structure at)
 {  at.addProperty("getValueCombo","#1 ~f        if (cisk.getTable().equals(\"fkTable\"))\n" +
"#1 ~f            { fkTable item = (fkTable) item1;\n" +
"#1 ~f              return item.getfkName();\n" +
"            }");
 }  
 
 public static void sqlExpression(Structure at)
 { at.addProperty("sqlExpression","#1 ~f        if (cisk.getTable().equals(\"fkTable\"))\n" +
"#1 ~f          return \"Select fkAttribute, fkName From fkTable\";");
 
 }
 
  public static void convertJSONObjectToDomainObject(Structure at)
 { at.addProperty("convertJSONObjectToDomainObject","#1 ~f        if (cisk.getTable().equals(\"fkTable\"))\n" +
"          {    \n" +
"            try {\n" +
"#1 ~f            fkTable ob = new fkTable();\n" +
"#1 ~f            ob.setSifraGrada(jsobj.getInt(\"fkAttribute\"));\n" +
"#1 ~f            ob.setNazivGrada(jsobj.getString(\"fkName\")); \n" +
"            return ob;\n" +
"                } catch (JSONException ex) {\n" +
"                    Logger.getLogger(GUIControllerPoslovniPartner.class.getName()).log(Level.SEVERE, null, ex);\n" +
"                }\n" +
"          }");  
 
 }
 
 public static void addColumn(Structure at)
 {
   at.addProperty("addColumn","SET _count = (  SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS \n" +
"#1 WHERE   TABLE_SCHEMA = '~f dbname' AND TABLE_NAME = '~f tl' AND COLUMN_NAME = 'at'); \n" +
"#1 IF _count = 0 THEN  ALTER TABLE ~f tl ADD COLUMN  ~f `at` tpMySQL sizeSQL Null defaultMySQL; END IF; \n");
 }
 
 public static void formTable(Structure at)
 {
   at.addProperty("formTable","@FXML\n" +
"#1 void Form~f tl(ActionEvent event) {\n" +
"#1      if (form~f tl == null)\n" +
"#1         form~f tl = createtl(\"Paneltl.fxml\",\"lablstr\",new tl()); \n" +
"      else\n" +
"#1         form~f tl.run();\n" +
"    }");
 }
 
 
  public static void menuApl(Structure at)
 { String menuApl = "";
      
         for(Structure s:at.st)         
           { for(Property p:s.pv)
               { if (p.name.equals("mnu"))
                  { menuApl = menuApl + "<Menu fx:id=\"" + p.value + "\" mnemonicParsing=\"false\" text=\"" + p.value + "\">\n";
                    menuApl = menuApl + "              <items>\n"; 
                    for(Structure s1:s.st)
                     {  
                        for(Property p1:s1.pv)
                              {
                               if (p1.name.equals("mnItem"))
                                { menuApl = menuApl + "                <MenuItem fx:id=\"" + p1.value + "\" mnemonicParsing=\"false\" onAction=\"#Form" + p1.value + "\" text=\"" + p1.value + "\" />\n";
                                }
                              }
                        
                     } 
                    menuApl = menuApl + "              </items>\n"; 
                    menuApl = menuApl + "            </Menu>";
                   }
               }
           } 
   at.addProperty("menuApl",menuApl);
 }
 
 public static void fxLbTxtFld(Structure at, int layoutY)
   {
     at.addProperty("fxLbTxtFld","#1 <Label layoutX=\"45.0\" layoutY=\"" + layoutY + "\" text=\"~f lab\" textFill=\"#f8f4f4\">\n" +
"          <font>\n" +
"             <Font name=\"System Bold\" size=\"11.0\" />\n" +
"          </font>\n" +
"      </Label>\n" +
"#1      <TextField fx:id=\"~f at\" layoutX=\"45.0\" layoutY=\"" + (layoutY+20) + "\" prefHeight=\"25.0\" prefWidth=\"111.0\" />");
   }    
public static void fxLblDtPicker(Structure at,int layoutY)
   {
     at.addProperty("fxLblDtPicker","#1 <Label layoutX=\"45.0\" layoutY=\"" + layoutY + "\" text=\"~f lab\" textFill=\"#f8f4f4\">\n" +
"          <font>\n" +
"             <Font name=\"System Bold\" size=\"11.0\" />\n" +
"          </font>\n" +
"      </Label>\n" +
"#1      <DatePicker fx:id=\"~f at\" layoutX=\"45.0\" layoutY=\"" + (layoutY+20) + "\" />");
   }    

public static void menuJavaFx(Structure at)
{ at.addProperty("menuJavaFx","#1 <Menu fx:id=\"~f menu1~\" mnemonicParsing=\"false\" text=\"~f text1~ \">\n" +
"\n" +
"               <items>\n" +
"\n" +
"#* ~a menuItemJavaFx \n" + 
"               </items>\n" +
"\n" +
"          </Menu>");   

}


public static void menuItemJavaFx(Structure at)
{ at.addProperty("menuItemJavaFx","#1 ~f           <MenuItem fx:id=\"menuItem1\" mnemonicParsing=\"false\" onAction=\"onAction1\" text=\"text2\" />");   
}

}