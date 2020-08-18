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

public class Osluskivaci {

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
  getColorText();
  loadHTMLView();
 
}


void setHTMLText() {

   
    
Platform.runLater(new Runnable() {
@Override
public void run() {


         texta.setHtmlText(html);

}});
}



public void readComponentFile()
{ html = (new FileURL()).readFile(kon.ComponentFileNamePath);
  Text t1 = new Text(html);
  kon.pan.ComponentText.getChildren().clear();
  kon.pan.ComponentText.getChildren().add(t1);
}






public void loadHTMLView()
{ Text t1 = new Text(html);
  kon.pan.HtmlView.getChildren().clear();
  kon.pan.HtmlView.getChildren().add(t1);
}


public String getColorText () 
{              try {
    HTMLEditorSkin skin = (HTMLEditorSkin) texta.getSkin();
    Field f = skin.getClass().getDeclaredField("webView");
    f.setAccessible(true);
    WebView wv = (WebView) f.get(skin);
    
     html = texta.getHtmlText(); // Bez obzira sto sam sacuvao ' kao #39;, ova metoda ga je konvertovala u '
     //i to mi pravi problem kada ga obradjuje Java script ... zato sam stavio html = html.replace("'","?????");. 
     html = html.replace("&lt;","!lt;");
     html = html.replace("&gt;","!gt;");
     //html = html.replace("\\","!!!!!");
     html = html.replace("'","?????");
     
     
     texta.setHtmlText(html);
    
     Thread.sleep(500);
    
    Platform.runLater(() -> {
        wv.requestFocus();
        wv.getEngine().executeScript("document.body.focus()");
        
        Integer pcount = (Integer) wv.getEngine().executeScript("document.getElementsByTagName('p').length");       
        System.out.println("PCount: " + pcount); 
        String word;
        for(int i =0; i< pcount; i++)
         { word =  (String) wv.getEngine().executeScript("document.getElementsByTagName('p')[" + i + "].textContent");       
            
           //Ovo radi!!!wv.getEngine().executeScript("document.getElementsByTagName('p')[" + i + "].style.color = '" + returnElementColor(word) +"' ");
           wv.getEngine().executeScript("document.getElementsByTagName('p')[" + i + "].textContent = '" + changeElementsColor(word) +"'");
             
         }
         
         html = texta.getHtmlText();
         html = html.replace("&lt;","<");
         html = html.replace("&gt;",">");
         
         html = html.replace("!lt;","&lt;");
         html = html.replace("!gt;","&gt;");
         //html = html.replace("!!!!!","\\");
         html = html.replace("?????","'"); // Ovo je nestabilan deo koda!!!
         
         setHTMLText();
         //texta.setHtmlText(html); 
         
    
    });
                
      
    // var TextInsideLi = ctrl.getElementsByTagName('p')[0].innerHTML;    
        

    
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(Osluskivaci.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(Osluskivaci.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Osluskivaci.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Osluskivaci.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Osluskivaci.class.getName()).log(Level.SEVERE, null, ex);
        }
   return "";
}  


String changeElementsColor(String plainText)
{
  //html = plainText.replace("at","<font id='fnt' size='3' face='Arial' color='red'>at</font>");
  // prop,expr
    if (plainText.equals("")) return "<br>";
  
    String firstTwoChars = "";
    
    if (plainText.length()>1)
         { firstTwoChars = plainText.substring(0,2);
          if ((firstTwoChars.equals("#1")==false) && (firstTwoChars.equals("#*"))==false) return plainText;  
         }
   
   if (kon==null) return plainText;   
    
  if (kon.expr!=null)
  {  
      for(Expr e:kon.expr)  
           {   if ((e.name.equals("#1")) || (e.name.equals("#*"))) 
                  plainText = plainText.replace(e.name,"<span style=\"color:red;font-weight:bold\">" + e.name + "</span>"); 
               else   
                 plainText = plainText.replace(e.name,"<span style=\"color:red;font-weight:bold\">" + e.name + "</span>"); 
           }
  }   
  
  
  if (kon.prop==null) return plainText;  
  
  for(Kontroler4.TypePropNameProp p:kon.prop)  
       { 
         if (p.getTypeProp().equals("rootprop"))
             plainText = plainText.replace(p.getNameProp(),"<span style=\"color:green;font-weight:bold\">" + p.getNameProp() + "</span>"); 
           if (p.getTypeProp().equals("aprop"))
             plainText = plainText.replace(p.getNameProp(),"<span style=\"color:Navy;font-weight:bold\">" + p.getNameProp() + "</span>"); 
        }
  
    
  return plainText;  
}


public String convertPlainToHtmlText (String plainText)
    {  String value = "";
       if (plainText.equals("")) return "<p><br></p>";
       
       plainText = plainText.replace(" ","&nbsp;");
       plainText = plainText.replace("<","&lt;");
       plainText = plainText.replace(">","&gt;");
       plainText = plainText.replace("'","&#39;");
       plainText = plainText.replace("\\","&#92;");
        
       
       String rows[] = plainText.split("\n");   
       
       for (int i = 0; i<rows.length; i++)
       { if (rows[i].equals(""))  
            rows[i] = "<p><br></p>"; 
         else
            rows[i] = "<p>" + rows[i] + "</p>";
         value = value + rows[i];
       } 
     
      return value;
    }

public void readTemplateFile()
{ String plainText = (new FileURL()).readFile(kon.TemplateFileNamePath);
  html = convertPlainToHtmlText(plainText); 
  html = "<font face='Segoe UI' color='black'>" + html + "</font>";
  html = "<html><head></head><body onLoad='document.body.focus();' contenteditable='true'>" + html +  "</body></html>"; 
  //getColorText();
  texta.setHtmlText(html); 
}

public void saveTemplate()
{
        try {
            html = texta.getHtmlText();
            String plainText = extractTextFromHTML(html);
            new FileURL().loadLStringFile(plainText, kon.TemplateFileNamePath);
        } catch (IOException ex) {
            Logger.getLogger(Osluskivaci.class.getName()).log(Level.SEVERE, null, ex);
        }
}



String extractTextFromHTML(String html)
{  String state = "ready"; // "noready"
   int counter = 0;
   
   
   html = html.replace("</p>","\n");
   html = html.replace("&nbsp;"," ");
   
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
            { result = result + c;
            }  
      }
   
   result = result.replace("&lt;","<");
   result = result.replace("&gt;",">");
   result = result.replace("&#39;","'");
   result = result.replace("&#92;","\\"); 
   
   
   
   return result;
}


}}
