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

public class LucaGComponents {
      
    List<Structure> forEnd; 
    
    public boolean loadLucaGComponents(Structure st,String projectName)
    { forEnd = new ArrayList<>();  
      st.addProperty(new PropMethod("dte",new Method("getDate"))); 
      //st.addProperty(new Property("dbname",projectName)); 
      st.addProperty(new Property("newL","\n")); 
      st.addProperty(new Property("projectName",projectName));
      Template.createTemplate();
      loadLucaGComponents(st,true);          
      
      for(Structure fe:forEnd)
        {loadLucaGComponents(fe,false);} 
      
      return true;   
       
    }
    
    public boolean loadLucaGComponents(Structure st, boolean firstLoop)
    { 
      String typeValue = null;
      for(Property pv1:st.pv)
         {   if (st.forEnd && firstLoop) 
              { forEnd.add(st);
         
              }
             else
              {
             
             String value = Generator.generateValue(pv1.value,st);
             pv1.setValue(value);
             pv1.setLoad(true);
             if (pv1.name.equals("type"))
                { typeValue = pv1.value;
                 
                }
               }
                   
         }
      if (typeValue!=null) 
           Template.createdDerivedProperty(typeValue, st);
      
      
      
      
       for(Structure st1:st.st)
         { loadLucaGComponents(st1,firstLoop);
         }
         
       return true;   
       
    }
    
   
      
    
}
