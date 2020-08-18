/* Autor programa:  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */

package GUI;

import GUI.Kontroler4.Expr;
import Structure.FileURL;
import com.sun.javafx.scene.web.skin.HTMLEditorSkin;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;

public class Osluskivaci1 {

public class OsluskivacComponentCB implements EventHandler
{   Kontroler4 kon;
 
    public OsluskivacComponentCB(Kontroler4 kon1) {kon = kon1;}
    
    @Override
    public void handle(javafx.event.Event e) {
        kon.changeStrAndTempField();
        kon.prop = kon.setStructuredTree();
        if (kon.ote!=null)
          { kon.ote.readComponentFile();
            kon.ote.readTemplateFile();
          }  
    }
}


public class OsluskivacTextEditor implements EventHandler<KeyEvent> {
 
    HTMLEditor texta;
    String innerhtml;
    String html;
    Kontroler4 kon;
    int rowCount;
    
 
    public OsluskivacTextEditor(Kontroler4 kon1) throws InterruptedException 
      {  kon = kon1;
         
         
         Platform.runLater(new Runnable() {
            @Override
            public void run() {

            //html = (new FileURL()).readFile(kon.TemplateFileNamePath);
            //setHTMLText(); 
            texta = kon.pan.TemplateText;
            readTemplateFile();
            readComponentFile();

            // disable tab key
            texta.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.TAB) {
                System.out.println("TAB!!!");
                event.consume();
            }
           });
            
            
            
            }});
         
         
         
      }      
           
        @Override
        public void handle(KeyEvent ke) 
            {  
               if (ke.getCode() == KeyCode.F1) 
                {  InterColorText();
                    System.out.println("HTML");
                }
               
               if (ke.getCode() == KeyCode.F2) 
                {  readTemplateFile();
                }
               
            }
      
    
    

void InterColorText() 
{ html = texta.getHtmlText();
    System.out.println("HTML:" + html);
  loadHTMLView();
  //String plainText = extractTextFromHTML(html);
  //loadHTMLView();
  //html = importNBSPInHTML(plainText);
  getColorText1();
  //html = "<font size='3' face='Arial' color='black'>" + html + "</font>";
  //html = "<html><head></head><body onLoad='document.body.focus();' contenteditable='true'>" + html +  "</body></html>"; 
  //System.out.println("HTML text1:" + html);
  //texta.setHtmlText(""); 
  //setHTMLText();
  

  
  
}


void setHTMLText() {

   
    
Platform.runLater(new Runnable() {
@Override
public void run() {

texta.setHtmlText(html); 

}});
}


public String getColorText (String plainText)
    {  String value = "";
       String rows[] = plainText.split("\n");   
       
       for (int i = 0; i<rows.length; i++)
       { // onclick='myfunction(this)'
         //rows[i] = "<luca id='" + "l" + i + "'><font  size='3' face='Arial' color='green'>" + rows[i] +  "</font></luca>";
           rows[i] = rows[i] + "<p>";
           if (rows[i].length()>1)
            {if ((rows[i].substring(0, 2).equals("#1")) || (rows[i].substring(0, 2).equals("#*")))
               rows[i] = "<luca id='" + "l" + i + "'>" + changeElelementsColor(rows[i]) +  "</luca>";
             else
               rows[i] = "<luca id='" + "l" + i + "'>" + rows[i] +  "</luca>"; 
            }
           else   
              rows[i] = "<luca id='" + "l" + i + "'>" + rows[i] +  "</luca>";
         //if (i + 1 < rows.length)
           // rows[i] = rows[i] + "<p>";
           rows[i] = rows[i] + "</p>";
         value = value + rows[i];
         
       } // <textarea id="ta" rows="4" cols="50">
       
       
              
       
      rowCount = rows.length;          
      return value;
    }

     
 
String changeElelementsColor(String plainText)
{
  //html = plainText.replace("at","<font id='fnt' size='3' face='Arial' color='red'>at</font>");
  // prop,expr
  if (kon.expr!=null)
  {  
      for(Expr e:kon.expr)  
           {   if ((e.name.equals("#1")) || (e.name.equals("#*"))) 
                  plainText = plainText.replace(e.name,"<font size='3' face='Arial' color='red'><b>" + e.name + "</b></font>"); 
               else   
                 plainText = plainText.replace(e.name + " ","<font size='3' face='Arial' color='red'><b>" + e.name + " " + "</b></font>"); 
           }
  }   
  
  if (kon==null) return plainText;  
  if (kon.prop==null) return plainText;  
  
  for(Kontroler4.TypePropNameProp p:kon.prop)  
       { 
         if (p.getTypeProp().equals("rootprop"))
             plainText = plainText.replace(p.getNameProp(),"<font size='3' face='Arial' color='green'><b>" + p.getNameProp() + "</b></font>"); 
         //if (p.getTypeProp().equals("pprop"))
            //plainText = plainText.replace(p.getNameProp(),"<font id='fnt1' size='3' face='Arial' color='blue'><b>" + p.getNameProp() + "</b></font>"); 
         if (p.getTypeProp().equals("aprop"))
             plainText = plainText.replace(p.getNameProp(),"<font size='3' face='Arial' color='Navy'><b>" + p.getNameProp() + "</b></font>"); 
         //if (p.getTypeProp().equals("aderprop"))
           //  plainText = plainText.replace(p.getNameProp(),"<font id='fnt3' size='3' face='Arial' color='SkyBlue'><b>" + p.getNameProp() + "</b></font>"); 
       }
  
    
  return plainText;  
}



public void readTemplateFile()
{ String plainText = (new FileURL()).readFile(kon.TemplateFileNamePath);
html = plainText;
  //html = importNBSPInHTML(plainText);
  //html = getColorText(html);  
  html = "<font size='3' face='Arial' color='black'>" + html + "</font>";
  html = "<html><head></head><body onLoad='document.body.focus();' contenteditable='true'>" + html +  "</body></html>"; 

  texta.setHtmlText(html); 
}

public void saveTemplate()
{
        try {
            html = texta.getHtmlText();
            String plainText = extractTextFromHTML(html);
            new FileURL().loadLStringFile(plainText, kon.TemplateFileNamePath);
        } catch (IOException ex) {
            Logger.getLogger(Osluskivaci1.class.getName()).log(Level.SEVERE, null, ex);
        }
}


public void readComponentFile()
{ html = (new FileURL()).readFile(kon.ComponentFileNamePath);
  Text t1 = new Text(html);
  kon.pan.ComponentText.getChildren().clear();
  kon.pan.ComponentText.getChildren().add(t1);
}


String extractTextFromHTML(String html)
{  String state = "ready"; // "noready"
   int counter = 0;
   
   html = html.replace("<br>","\n");
   html = html.replace("<p>","\n");
   html = html.replace("&nbsp;"," ");
   
   //html = html.replace("\t","       ");
   String result = "";
   String chars[] = html.split(""); 
   for(String c:chars)
      { if (c.equals("<") == true)
         { counter ++;
           state = "noready"; 
         } 
        if (c.equals(">") == true)
         { counter --;
           if (counter==0)
               state = "ready"; 
         }   
        else
          if (state.equals("ready"))
            {
              result = result + c;
            }  
      }
   
    

   
   return result;
}



String importNBSPInHTML(String html)
{  //int counterSpace = 0;
   String result = "";
   String chars[] = html.split(""); 
   for(String c:chars)
      { if (c.equals(" "))  
          { result = result + "&nbsp";
            /*if (counterSpace>0)
                result = result + "&nbsp";
            else
             { result = result + c;
               counterSpace++;
             }*/
          }
        else
         { result = result + c;  
           //counterSpace = 0;
         }
      }
   return result;
}


public void loadHTMLView()
{ Text t1 = new Text(html);
  kon.pan.HtmlView.getChildren().clear();
  kon.pan.HtmlView.getChildren().add(t1);
}


public String getColorText1 () 
{       try {
    HTMLEditorSkin skin = (HTMLEditorSkin) texta.getSkin();
    Field f = skin.getClass().getDeclaredField("webView");
    f.setAccessible(true);
    WebView wv = (WebView) f.get(skin);
    
    Platform.runLater(() -> {
        wv.requestFocus();
        wv.getEngine().executeScript("document.body.focus()");
        
          /*String skript = "function p1() {" +
          " var paragraphs = document.getElementsByTagName('p');" +
          " var myArray = java.lang.reflect.Array.newInstance(java.lang.String, paragraphs.length);" +
          " for(i=0;i<paragraphs.length;i++)  " +
          " { myArray[i]=paragraphs[i].textContent;}return myArray;}"; */
          
    //String skript = "function makeArray() { var myArray = new Array(4); myArray[0] = 'A'; myArray[1] = 'B';  myArray[2] = 'C'; return myArray[0];}";

    String skript = "function vratiString(){ return document.getElementsByTagName('p')[0].textContent;}";
          
          
    
    
           //NativeArray na =  (NativeArray) wv.getEngine().executeScript(skript);
           
           //Object [] selection = new Object[(int) na.getLength()];
           //selection = na.getArray().asObjectArray();
           
           
           Integer pcount = (Integer) wv.getEngine().executeScript("document.getElementsByTagName('p').length");       
           System.out.println("PCount: " + pcount); 
           
           String na;
           for(int i =0; i< pcount; i++)
             {na =  (String) wv.getEngine().executeScript("document.getElementsByTagName('p')[" + i + "].textContent");       
              System.out.println("SELECTION: " + na);
              
             }
          
          
    
    });
                
      
    // var TextInsideLi = ctrl.getElementsByTagName('p')[0].innerHTML;    
        

    
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(Osluskivaci1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(Osluskivaci1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Osluskivaci1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Osluskivaci1.class.getName()).log(Level.SEVERE, null, ex);
        }
   return "";
}  


}}
