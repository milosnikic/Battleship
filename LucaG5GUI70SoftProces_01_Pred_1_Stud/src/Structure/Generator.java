/* Autor programa:  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */

package Structure;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Generator {
    
    public static void generate(LucaFile templateFile1,LucaFile generatedFile1,Structure root,boolean append)
    {     
        DataOutputStream outs;
        List <String> rowsTemplate = new ArrayList<>();
        List <String> rowsForGeneratedFile = new ArrayList<>();
      
        File templateFile = new File(templateFile1.getPathNameExtension()); 
        try 
        {             
           File generatedFile = new File(generatedFile1.getPathNameExtension()); 
           generatedFile.createNewFile();
           
           if(templateFile.exists())
            {
                
                
                if (templateFile1.extension.equals("lctm")!=true)
                 { // Prosto kopiranje sadrzaja ne lctm datoteke u generisanu datoteku.
                   
                   FileChannel source = new FileInputStream(templateFile).getChannel();
  	           FileChannel destination = new FileOutputStream(generatedFile,append).getChannel();
  		   if (destination != null && source != null) 
                     { destination.transferFrom(source, destination.size(), source.size());
  	   	     }
                   
                   source.close();
                   destination.close();
                   return;
                 } 
                
                BufferedReader br;
                br = new BufferedReader(new FileReader(templateFile));
                String line;
                while ((line = br.readLine()) != null) 
                      { 
                        if (line.isEmpty()) 
                            { rowsTemplate.add( " ");
                            }
                        else
                            {  rowsTemplate.add(line);
                            }
                      }
                br.close();
            }
           else
            { System.out.println("File: " + templateFile1.getPathNameExtension() + " doesn't exist!");
              return;
            }
         
        
         
         outs = new DataOutputStream(new FileOutputStream(generatedFile,append));
         
         if (generateRowsForGeneratedFile(rowsTemplate,root,rowsForGeneratedFile))
         { 
           for (int i = 0; i < rowsForGeneratedFile.size(); i++) 
               {  if (rowsForGeneratedFile.size() == 1)
                      outs.writeBytes(rowsForGeneratedFile.get(i));
                  else
                    { if (rowsForGeneratedFile.size()-i > 1)
                           outs.writeBytes(rowsForGeneratedFile.get(i) + "\n");
                      else
                           outs.writeBytes(rowsForGeneratedFile.get(i));
                    }
               }  
          }   
        outs.close();
        
         } catch (Exception ex) {
            Logger.getLogger(Generator.class.getName()).log(Level.SEVERE, null, ex);
            }  
    }
    
    
    
     public static String generateValue(String templateValue, Structure root)
    {   List <String> rowsTemplate = new ArrayList<>();
        List <String> rowsForGeneratedFile = new ArrayList<>();
        String value = "";
        String temp = "";
        String templateValueRow[] = templateValue.split("\n");
        for (String line:templateValueRow)
          { if (line.isEmpty()) 
                { rowsTemplate.add("  ");
                }
            else
                {  rowsTemplate.add(line);
                }
          }
          
        if (generateRowsForGeneratedFile(rowsTemplate,root,rowsForGeneratedFile))
         {    for (int i = 0; i < rowsForGeneratedFile.size(); i++) 
               {  if (rowsForGeneratedFile.size() == 1)
                      value = value + rowsForGeneratedFile.get(i);
                  else
                    { if (rowsForGeneratedFile.size()-i > 1)
                           value = value + rowsForGeneratedFile.get(i) + "\n";
                      else
                           value = value + rowsForGeneratedFile.get(i);
                    }
               }
          
         }   
       return value; 
    }
    
    
    static boolean  generateRowsForGeneratedFile(List <String> rowsTemplate,Structure root, List <String> rowsForGeneratedFile)
    {   
        String rowGeneratedFile = "";
        String temp ="";
        int childType;
        int childNumber;
        List<Structure> s;
        int structureNumber;
        String WITH_DELIMITER = "(?<=%1$s)|(?=%1$s)";
        for (String rowTemplate : rowsTemplate) {
            try { String firstTwoChars ="";
                  if (rowTemplate.length()<2)
                      firstTwoChars = "";
                  else            
                      firstTwoChars = rowTemplate.substring(0,2);
                  
                  if (firstTwoChars.equals("#1") || firstTwoChars.equals("#*"))
                    { String spaceString ="";
                      int countSpace=0;
                    
                      String rowTemplate1 = rowTemplate.substring(2);
                      while(true)
                      {  String temp1 = rowTemplate1.substring(countSpace,countSpace+1);
                         if (temp1.equals(" "))
                            { spaceString = spaceString + " ";
                              countSpace++;
                            }
                         else
                             break;
                      }
                      String[] rowItem = rowTemplate.split("~"); 
                      for(int i=0;i<rowItem.length;i++)
                        {  //System.out.println("rowItem[" + i + "]: " +  rowItem[i]);
                          String[] rowItemItem = rowItem[i].split(" ");
                          if (rowItemItem.length == 0 )
                            {}
                          else
                           {
                          switch (rowItemItem[0])
                              {     case "a": 
                                       rowItem[i] = rowItem[i].substring(2);
                                       rowItemItem = rowItem[i].split(String.format(WITH_DELIMITER, " "));
                                       temp ="";
                                           s = new ArrayList();
                                           structureNumber = 0;
                                           for(String rowItemItemC : rowItemItem)
                                            { root.findStructures(rowItemItemC,s,true);
                                              structureNumber = s.size();
                                              if (structureNumber > 0)
                                                break;  
                                            } 
                                          
                                      for (int j=0;j<structureNumber;j++)
                                        {  
                                           for(String rowItemItemC : rowItemItem)
                                            {                     
                                                temp = temp + s.get(j).getPropertyValue(rowItemItemC);
                                            }
                                           temp = temp + " ";
                                           if (firstTwoChars.equals("#*") && j < structureNumber-1)
                                                temp = temp + "\n" + spaceString;
                                        }
                                           
                                       rowItem[i]= temp;
                                       rowGeneratedFile = rowGeneratedFile + rowItem[i];
                                       break;
                                       
                                  case "a-l": 
                                       rowItem[i] = rowItem[i].substring(4);
                                       rowItemItem = rowItem[i].split(String.format(WITH_DELIMITER, " "));
                                       temp ="";
                                           s = new ArrayList();
                                           structureNumber = 0;
                                           for(String rowItemItemC : rowItemItem)
                                            { root.findStructures(rowItemItemC,s,true);
                                              structureNumber = s.size();
                                              if (structureNumber > 0)
                                                break;  
                                            } 
                                          
                                      for (int j=0;j<structureNumber-1;j++)
                                        {  
                                           for(String rowItemItemC : rowItemItem)
                                            {                     
                                                temp = temp + s.get(j).getPropertyValue(rowItemItemC);
                                            }
                                           temp = temp + " ";
                                           if (firstTwoChars.equals("#*") && j < structureNumber-1)
                                                temp = temp + "\n" + spaceString;
                                        }
                                           
                                       rowItem[i]= temp;
                                       rowGeneratedFile = rowGeneratedFile + rowItem[i];
                                       break;
                                      
                                  case "a-f":
                                       rowItem[i] = rowItem[i].substring(4);
                                       rowItemItem = rowItem[i].split(String.format(WITH_DELIMITER, " "));
                                       temp ="";
                                           s = new ArrayList();
                                           structureNumber = 0;
                                           for(String rowItemItemC : rowItemItem)
                                            { root.findStructures(rowItemItemC,s,true);
                                              structureNumber = s.size();
                                              if (structureNumber > 0)
                                                break;  
                                            } 
                                          
                                      for (int j=1;j<structureNumber;j++)
                                        {  
                                           for(String rowItemItemC : rowItemItem)
                                            {                     
                                                temp = temp + s.get(j).getPropertyValue(rowItemItemC);
                                            }
                                           temp = temp + " ";
                                           if (firstTwoChars.equals("#*") && j < structureNumber-1)
                                                temp = temp + "\n" + spaceString;
                                        }
                                           
                                       rowItem[i]= temp;
                                       rowGeneratedFile = rowGeneratedFile + rowItem[i];
                                       break;
                                  case "a-f-l":
                                       rowItem[i] = rowItem[i].substring(6);
                                       rowItemItem = rowItem[i].split(String.format(WITH_DELIMITER, " "));
                                       temp ="";
                                           s = new ArrayList();
                                           structureNumber = 0;
                                           for(String rowItemItemC : rowItemItem)
                                            { root.findStructures(rowItemItemC,s,true);
                                              structureNumber = s.size();
                                              if (structureNumber > 0)
                                                break;  
                                            } 
                                          
                                      for (int j=1;j<structureNumber-1;j++)
                                        {  
                                           for(String rowItemItemC : rowItemItem)
                                            {                     
                                                temp = temp + s.get(j).getPropertyValue(rowItemItemC);
                                            }
                                           temp = temp + " ";
                                           if (firstTwoChars.equals("#*") && j < structureNumber-1)
                                                temp = temp + "\n" + spaceString;
                                        }
                                           
                                       rowItem[i]= temp;
                                       rowGeneratedFile = rowGeneratedFile + rowItem[i];
                                       break;     
                                  case "f": 
                                       rowItem[i] = rowItem[i].substring(2);
                                       rowItemItem = rowItem[i].split(String.format(WITH_DELIMITER, " "));
                                       temp ="";
                                           s = new ArrayList();
                                           structureNumber = 0;
                                           for(String rowItemItemC : rowItemItem)
                                            { root.findStructures(rowItemItemC,s,true);
                                              structureNumber = s.size();
                                              if (structureNumber > 0)
                                                break;  
                                            } 
                                          
                                      
                                           for(String rowItemItemC : rowItemItem)
                                            {                     
                                                temp = temp + s.get(0).getPropertyValue(rowItemItemC);
                                            }
                                          //temp = temp + " ";
                                      
                                           
                                       rowItem[i]= temp;
                                       rowGeneratedFile = rowGeneratedFile + rowItem[i];
                                       break;
                                  
                                  case "l":
                                       rowItem[i] = rowItem[i].substring(2);
                                       rowItemItem = rowItem[i].split(String.format(WITH_DELIMITER, " "));
                                       temp ="";
                                           s = new ArrayList();
                                           structureNumber = 0;
                                           for(String rowItemItemC : rowItemItem)
                                            { root.findStructures(rowItemItemC,s,true);
                                              structureNumber = s.size();
                                              if (structureNumber > 0)
                                                break;  
                                            } 
                                          
                                      
                                           for(String rowItemItemC : rowItemItem)
                                            {                     
                                                temp = temp + s.get(structureNumber-1).getPropertyValue(rowItemItemC);
                                            }
                                          
                                      //temp = temp + " ";
                                           
                                       rowItem[i]= temp;
                                       rowGeneratedFile = rowGeneratedFile + rowItem[i];
                                       break;
                                  case "a-i": 
                                       rowItem[i] = rowItem[i].substring(4);
                                       rowItemItem = rowItem[i].split(String.format(WITH_DELIMITER, " "));
                                       temp ="";
                                           s = new ArrayList();
                                           structureNumber = 0;
                                           for(String rowItemItemC : rowItemItem)
                                            { root.findStructures(rowItemItemC,s,false);
                                              structureNumber = s.size();
                                              if (structureNumber > 0)
                                                break;  
                                            } 
                                          
                                      for (int j=0;j<structureNumber;j++)
                                        {  
                                           for(String rowItemItemC : rowItemItem)
                                            {                     
                                                temp = temp + s.get(j).getPropertyValue(rowItemItemC);
                                            }
                                           temp = temp + " ";
                                           if (firstTwoChars.equals("#*") && j < structureNumber-1)
                                                temp = temp + "\n" + spaceString;
                                        }
                                           
                                       rowItem[i]= temp;
                                       rowGeneratedFile = rowGeneratedFile + rowItem[i];
                                       break;
                                       
                                  case "a-i-l": 
                                       rowItem[i] = rowItem[i].substring(6);
                                       rowItemItem = rowItem[i].split(String.format(WITH_DELIMITER, " "));
                                       temp ="";
                                           s = new ArrayList();
                                           structureNumber = 0;
                                           for(String rowItemItemC : rowItemItem)
                                            { root.findStructures(rowItemItemC,s,false);
                                              structureNumber = s.size();
                                              if (structureNumber > 0)
                                                break;  
                                            } 
                                          
                                      for (int j=0;j<structureNumber-1;j++)
                                        {  
                                           for(String rowItemItemC : rowItemItem)
                                            {                     
                                                temp = temp + s.get(j).getPropertyValue(rowItemItemC);
                                            }
                                           temp = temp + " ";
                                           if (firstTwoChars.equals("#*") && j < structureNumber-1)
                                                temp = temp + "\n" + spaceString;
                                        }
                                           
                                       rowItem[i]= temp;
                                       rowGeneratedFile = rowGeneratedFile + rowItem[i];
                                       break;
                                      
                                  case "a-i-f":
                                       rowItem[i] = rowItem[i].substring(6);
                                       rowItemItem = rowItem[i].split(String.format(WITH_DELIMITER, " "));
                                       temp ="";
                                           s = new ArrayList();
                                           structureNumber = 0;
                                           for(String rowItemItemC : rowItemItem)
                                            { root.findStructures(rowItemItemC,s,false);
                                              structureNumber = s.size();
                                              if (structureNumber > 0)
                                                break;  
                                            } 
                                          
                                      for (int j=1;j<structureNumber;j++)
                                        {  
                                           for(String rowItemItemC : rowItemItem)
                                            {                     
                                                temp = temp + s.get(j).getPropertyValue(rowItemItemC);
                                            }
                                           temp = temp + " ";
                                           if (firstTwoChars.equals("#*") && j < structureNumber-1)
                                                temp = temp + "\n" + spaceString;
                                        }
                                           
                                       rowItem[i]= temp;
                                       rowGeneratedFile = rowGeneratedFile + rowItem[i];
                                       break;
                                  case "a-i-f-l":
                                       rowItem[i] = rowItem[i].substring(8);
                                       rowItemItem = rowItem[i].split(String.format(WITH_DELIMITER, " "));
                                       temp ="";
                                           s = new ArrayList();
                                           structureNumber = 0;
                                           for(String rowItemItemC : rowItemItem)
                                            { root.findStructures(rowItemItemC,s,false);
                                              structureNumber = s.size();
                                              if (structureNumber > 0)
                                                break;  
                                            } 
                                          
                                      for (int j=1;j<structureNumber-1;j++)
                                        {  
                                           for(String rowItemItemC : rowItemItem)
                                            {                     
                                                temp = temp + s.get(j).getPropertyValue(rowItemItemC);
                                            }
                                           temp = temp + " ";
                                           if (firstTwoChars.equals("#*") && j < structureNumber-1)
                                                temp = temp + "\n" + spaceString;
                                        }
                                           
                                       rowItem[i]= temp;
                                       rowGeneratedFile = rowGeneratedFile + rowItem[i];
                                       break;     
                                  case "f-i": 
                                       rowItem[i] = rowItem[i].substring(4);
                                       rowItemItem = rowItem[i].split(String.format(WITH_DELIMITER, " "));
                                       temp ="";
                                           s = new ArrayList();
                                           structureNumber = 0;
                                           for(String rowItemItemC : rowItemItem)
                                            { root.findStructures(rowItemItemC,s,false);
                                              structureNumber = s.size();
                                              if (structureNumber > 0)
                                                break;  
                                            } 
                                          
                                      
                                           for(String rowItemItemC : rowItemItem)
                                            {                     
                                                temp = temp + s.get(0).getPropertyValue(rowItemItemC);
                                            }
                                          //temp = temp + " ";
                                      
                                           
                                       rowItem[i]= temp;
                                       rowGeneratedFile = rowGeneratedFile + rowItem[i];
                                       break;
                                  
                                  case "l-i":
                                       rowItem[i] = rowItem[i].substring(4);
                                       rowItemItem = rowItem[i].split(String.format(WITH_DELIMITER, " "));
                                       temp ="";
                                           s = new ArrayList();
                                           structureNumber = 0;
                                           for(String rowItemItemC : rowItemItem)
                                            { root.findStructures(rowItemItemC,s,false);
                                              structureNumber = s.size();
                                              if (structureNumber > 0)
                                                break;  
                                            } 
                                          
                                      
                                           for(String rowItemItemC : rowItemItem)
                                            {                     
                                                temp = temp + s.get(structureNumber-1).getPropertyValue(rowItemItemC);
                                            }
                                          
                                      //temp = temp + " ";
                                           
                                       rowItem[i]= temp;
                                       rowGeneratedFile = rowGeneratedFile + rowItem[i];
                                       break;     
                                  /*case "an": 
                                       rowItem[i] = rowItem[i].substring(rowItemItem[0].length()+1);
                                       rowItemItem = rowItem[i].split(String.format(WITH_DELIMITER, " "));
                                       temp ="";
                                           s = new ArrayList();
                                           structureNumber = 0;
                                           for(String rowItemItemC : rowItemItem)
                                            { root.findStructures(rowItemItemC,s,false,true);
                                              structureNumber = s.size();
                                              if (structureNumber > 0)
                                                break;  
                                            } 
                                          
                                      for (int j=0;j<structureNumber;j++)
                                        {  
                                           for(String rowItemItemC : rowItemItem)
                                            {                     
                                                temp = temp + s.get(j).getPropertyValue1(rowItemItemC);
                                                List<Structure> s1 = new ArrayList();
                                                s.get(j).findStructures(temp,s1,false,true);
                                                int structureNumber1 = s1.size();
                                                for (int j1=0;j1<structureNumber1;j1++)
                                                  { temp = temp + s1.get(j1).getPropertyValue1(temp);
                                                  }
                                            }
                                           temp = temp + " ";
                                           if (firstTwoChars.equals("#*") && j < structureNumber-1)
                                                temp = temp + "\n" + spaceString;
                                        }
                                           
                                       rowItem[i]= temp;
                                       rowGeneratedFile = rowGeneratedFile + rowItem[i];
                                       break;*/
                                      
                                       
                                  default: 
                                      rowItem[i] = rowItem[i].replace("#1","");
                                      rowItem[i] = rowItem[i].replace("#*","");
                                      rowGeneratedFile = rowGeneratedFile + rowItem[i];
                                      break;                          
                              }
                           }
                        }
                      System.out.println("rowGeneratedFile: " + rowGeneratedFile);
                      rowsForGeneratedFile.add(rowGeneratedFile);
                      rowGeneratedFile = "";
                      
	            }
                  else
                    {   System.out.println("Unchanged: " + rowTemplate);
                        rowsForGeneratedFile.add(rowTemplate);
                    }
               } catch (Exception e) { 
                   
            }
          
            
        }
        
        
        return true;
    }
    
}
