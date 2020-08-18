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

public class PropertyName {
      String name;
      static List <PropertyName> listProperties = new ArrayList<>();
    
    
    public PropertyName(String name) 
    { this.name = name;
    }
    
  public String getName()
     { return name;}
  
  public static PropertyName addProperty(String name)
  {      
     for(PropertyName prop:listProperties)
      {   if (name.equals(prop.name))
              { return prop;
              }
      }
       
      for(PropertyName prop:listProperties)
      {   if ((name.indexOf(prop.name)!= -1) || (prop.name.indexOf(name)!= -1))
              { System.out.println("New property is substring or superstring of existing properties!!!");
                return null;
              }
      }
     
     PropertyName newprop = new PropertyName(name); 
     listProperties.add(newprop);
     return newprop;
  }
    
 
}

