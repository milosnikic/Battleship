/* Autor programa:  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */

package Structure;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Structure implements Serializable
{ public String name;
  public List<Structure> st;
  public Structure parent;
  public List<Property> pv;
  
  public boolean forEnd;
  
  public Structure(String name) 
   {this.name = name;
    this.pv = new ArrayList<>();
    this.st = new ArrayList<>();
    forEnd = false;
   }
  
  public Structure(String name,boolean forEnd) 
   {this.name = name;
    this.pv = new ArrayList<>();
    this.st = new ArrayList<>();
    this.forEnd = forEnd;
   }
  
  public Structure(String name,Property[] prop) 
   {this.name = name;
    this.pv = new ArrayList<>();
    this.st = new ArrayList<>();
    if (prop!=null)
        { for(Property p:prop)
             {addProperty(p);}  
        }  
    forEnd = false;
   }
  
  
  public boolean addProperty (String name, String value)
  {  if (PropertyName.addProperty(name)== null) return false;
     
     for(Property pv1:pv)    
       { if (pv1.name.equals(name))
           return false;
       }  
    
     this.pv.add(new Property(name,value));
     return true;
  }
  
   public boolean addProperty (Property pv)
  {  if (PropertyName.addProperty(pv.name)== null) return false;
     
     for(Property pv1:this.pv)    
       { if (pv1.name.equals(pv.name))
           return false;
       }  
  
     this.pv.add(pv);
     return true;
  }
   
   public void addStructure(Structure st)
   {this.st.add(st);
    st.addParent(this);
   }     
   
   public void addParent(Structure parent)
   {this.parent = parent;}
   
   
   public void findStructures(String rowItemItemC, List<Structure> s, boolean inheritProperty)
      { boolean signal = false;
        
        for(Property pv1: this.pv)
              {  if ( pv1.loaded == true)
                   { int index = rowItemItemC.indexOf(pv1.name);
                     if (index>-1)
                       { s.add(this); signal = true; break;}
                   }
              }
         if (signal == false && parent!=null)
           { signal = parent.findParent(rowItemItemC,s);
             if (signal){ s.add(this);}
           }
          findStructures1(rowItemItemC, s,signal,inheritProperty);
         
      }  
   
   
   public boolean findParent(String rowItemItemC, List<Structure> s)
      { boolean signal = false;
        
        for(Property pv1: this.pv)
              {  if ( pv1.loaded == true)
                   { int index = rowItemItemC.indexOf(pv1.name);
                     if (index>-1)
                       { signal = true; return signal;}
                   }
              }
         
           if (parent!=null)
             { signal = parent.findParent(rowItemItemC,s);
             }
           return signal;
      }  
   
   
   
   
   
   public void findStructures1(String rowItemItemC, List<Structure> s,boolean signal,boolean inheritProperty)
      { boolean signal1;
        
        for(Structure st1:this.st)
          { signal1 = false;  
            if (signal && inheritProperty)  
              {
                s.add(st1); 
                st1.findStructures1(rowItemItemC,s,true,inheritProperty);
              }
            else
            {  
                for(Property pv1: st1.pv)
                  {  if ( pv1.loaded == true)
                       { int index = rowItemItemC.indexOf(pv1.name);
                         if (index>-1)
                           { s.add(st1); signal1 = true; 
                             st1.findStructures1(pv1.name,s,true, inheritProperty); 
                             break;
                           }
                       }
                  }
                if (signal1 == false)
                  {st1.findStructures1(rowItemItemC,s,false, inheritProperty);}  
            } 
            
            
          }  
      }   
   
   
   String getPropertyValue(String rowItemItemC)
    { int index;
   
      for(Property pv1: this.pv)
              {  if ( pv1.loaded == true)
                   { index = rowItemItemC.indexOf(pv1.name);
                     if (index>-1)
                       {  rowItemItemC = rowItemItemC.replace(pv1.name,pv1.value);  
                       }
                   }
              } 
       if (parent != null)
          rowItemItemC = parent.getPropertyValue(rowItemItemC);
      
      return rowItemItemC;
    }
   
   
   
   /*String getPropertyValue1(String rowItemItemC)
    { int index;
      boolean signal = false;
      for(Property pv1: this.pv)
              {  if ( pv1.loaded == true)
                   { index = rowItemItemC.indexOf(pv1.name);
                     if (index>-1)
                       {  rowItemItemC = rowItemItemC.replace(pv1.name,pv1.value);  
                          signal = true;
                          break;
                       }
                   }
              } 
       if (parent != null && signal==false)
          rowItemItemC = parent.getPropertyValue(rowItemItemC);
      
      return rowItemItemC;
    }*/
   
   
  public String getName(){return name;} 
   
}

      


