/* Autor programa:  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */

package GUI;




import GUI.Osluskivaci.OsluskivacTextEditor;


import Structure.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;





public final class Kontroler4 extends Kontroler{
   
    Panel pan;
    Project pr;
    ComboBox ComponentCB;
    TextFlow LucaExpressionText; 
    TreeView StructureText;
    //HTMLEditor TemplateText;
    public List <TypePropNameProp> prop;
        
    
 
    String StructureFileName;
    String TemplateFileName;
    String ComponentFileName;
        
    String StructureFileNamePath;
    String TemplateFileNamePath;
    String ComponentFileNamePath;
    Expr[] expr;
    OsluskivacTextEditor ote;
    
    public Kontroler4(IEkranskaForma ef1,Project pr) throws IOException, FileNotFoundException, ClassNotFoundException, InterruptedException {
     ef=ef1;this.pr = pr;   
     EkranskaForma3 ef3 =  (EkranskaForma3)ef;
     pan = (Panel) ef3.getPanel();
     
     
      //while(pan.ProjectName==null || pan.ComponentName==null || pan.LucaExpressionText==null || pan.StructureText==null || pan.TemplateText==null) {try {Thread.sleep(10);} catch (InterruptedException ex) {
       //         Logger.getLogger(Kontroler4.class.getName()).log(Level.SEVERE, null, ex);
        //    }}
     
     TextField ProjectName = pan.ProjectName;
     ProjectName.setText(pr.getName());
     
    
    //pan.GenerateComponent.setOnMouseClicked((e)->{generateComponent();});
    pan.SaveTemplate.setOnMouseClicked((e)->{saveTemplate();}); 
     
     ComponentCB = pan.ComponentName;
     String Components[] = new String[pr.lf.size()];
    
     int i=0;
     for(LucaFile generatedFile:pr.lf) 
     { Components[i] = generatedFile.getFileNameExt();
      i++;
     }
     
     ObservableList<String> list = FXCollections.observableArrayList();
     list.addAll(Components);
     
     
     Platform.runLater(new Runnable() {
            @Override
            public void run() {

            ComponentCB.setItems(list);
            ComponentCB.getSelectionModel().selectFirst();

            }});
        
     
     
     Platform.runLater(()-> {changeStrAndTempField();});
     
     this.LucaExpressionText = pan.LucaExpressionText;
     
     Platform.runLater(()->{expr = setLucaExpressionText();});
     
     
     this.StructureText=pan.StructureText;
     Platform.runLater(()->{prop = setStructuredTree();});
     //Platform.runLater(()->{treeCellFactory();});
     
     Osluskivaci os = new Osluskivaci();
     ComponentCB.setOnAction( os.new OsluskivacComponentCB(this));
     
     System.out.println("Ekranska forma ... this:" + pan.ef);
     
     //this.TemplateText = pan.TemplateText;
     //this.TemplateText.requestFocus();
     Platform.runLater(()->
     {   try {
         ote = os.new OsluskivacTextEditor(this);
         pan.TemplateText.setOnKeyReleased(ote);
         } catch (InterruptedException ex) {
             Logger.getLogger(Kontroler4.class.getName()).log(Level.SEVERE, null, ex);
         }
     });
     
    
     
     }
    
    void changeStrAndTempField()
    { 
      ComponentFileName =(String)ComponentCB.getValue();  
      
      ComponentFileNamePath = pr.getGeneratedFilePathNameExt(ComponentFileName);
      
      TextField StructureName = pan.StructureName;
      StructureFileName = pr.getStructureFileNameExt((String)ComponentCB.getValue());
      StructureFileNamePath = pr.getStructureFilePathNameExt((String)ComponentCB.getValue());
      StructureName.setText(StructureFileName);
     
      TextField TemplateName = pan.TemplateName;
      TemplateFileName = pr.getTemplateFileNameExt((String)ComponentCB.getValue());
      TemplateFileNamePath = pr.getTemplateFilePathNameExt((String)ComponentCB.getValue());
      TemplateName.setText(TemplateFileName);
    
    }
    
    class Expr
    { String name;
      String description;
      public Expr(String name, String description) {this.name = name; this.description = description;}
    }
    
    Expr[] setLucaExpressionText()
    { Expr expr[] = {
           new Expr("#1","Shows the text in one line."),
            new Expr("#*","Shows the text in more lines."),
            new Expr("~a","Shows all structures."),
            new Expr("~a-f","Shows all stuctures except the first."),
            new Expr("~a-l","Shows all structurs except the last."),
            new Expr("~a-f-l","Shows all structures except the first and the last."),
            new Expr("~f","Shows the first structure."),
            new Expr("~l","Shows the last structure.")};
          
        
      for (int i=0; i<expr.length; i++)
       {
         Text t1 = new Text(expr[i].name);
         t1.setFont(Font.font("Arial", 14));
         t1.setFill(Color.RED);
         LucaExpressionText.getChildren().add(t1);
         
         t1 = new Text("---");
         t1.setFont(Font.font("Arial", 12));
         t1.setFill(Color.BLACK);
         LucaExpressionText.getChildren().add(t1);
         
         
         t1 = new Text(expr[i].description + "\n");
         t1.setFont(Font.font("Arial", 12));
         t1.setFill(Color.BLACK);
         LucaExpressionText.getChildren().add(t1);
         
       }
      return expr;
    }
  
    
    class TypePropNameProp
     { String typeProp;
       String nameProp;
       public TypePropNameProp(String typeProp,String nameProp) {this.typeProp = typeProp; this.nameProp = nameProp;}
       public String getTypeProp(){return typeProp;}
       public String getNameProp(){return nameProp;}
     }
    
    List <TypePropNameProp> setStructuredTree() 
   { 
       FileInputStream fins = null;
        try {
            List <TypePropNameProp> col = new ArrayList<TypePropNameProp>();
            Image rootIcon = new Image(getClass().getResourceAsStream("resource/Luca6.png"));
            String structPathNameExt = pr.getStructureFilePathNameExt((String)ComponentCB.getValue());
            fins = new FileInputStream(structPathNameExt);
            ObjectInputStream ois = new ObjectInputStream(fins);
            Structure st = (Structure) ois.readObject();
            TreeItem<String> rootNode = new TreeItem<>("Root");
            rootNode.setExpanded(true);
                       
            TreeItem<String> stNode = new TreeItem<>(st.name);
            rootNode.getChildren().add(stNode);
            for(Property pv1:st.pv)
            {  TreeItem<String> propNode = new TreeItem<>(pv1.name,new ImageView(rootIcon));
               stNode.getChildren().add(propNode);
              
               
                TreeItem<String> valueNode = new TreeItem<>(pv1.value);
               propNode.getChildren().add(valueNode);
               
               
               col.add(new TypePropNameProp("rootprop",pv1.name));
            }  
            
           setStructuredTree(st,stNode,col,rootIcon) ;
           
           
           //String threeStyle = getClass().getResource("three.css").toExternalForm();
           
           //StructureText.getStyleClass().add("myTree");  
           
           StructureText.setRoot(rootNode);
           StructureText.setEditable(true);
           ois.close();
           fins.close();
           
            return col;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Kontroler4.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Kontroler4.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Kontroler4.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fins.close();
            } catch (IOException ex) {
                Logger.getLogger(Kontroler4.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
     return null; 
   }
    
    
    void setStructuredTree(Structure st,TreeItem<String> stNode, List <TypePropNameProp> col,Image rootIcon) 
    {       //TreeItem<String> stNode1 = new TreeItem<>("***",new ImageView(rootIcon));
            //stNode.getChildren().add(stNode1);
            for(Structure st_: st.st)
            {  TreeItem<String> stNodeChild = new TreeItem<>(st_.name);
               stNode.getChildren().add(stNodeChild);
               for(Property pv: st_.pv)
                 {  TreeItem<String> propNode = new TreeItem<>(pv.name,new ImageView(rootIcon));
                    stNodeChild.getChildren().add(propNode);
                    
                    
                    TreeItem<String> valueNode = new TreeItem<>(pv.value);
                    propNode.getChildren().add(valueNode);
                    
                    col.add(new TypePropNameProp("aprop",pv.name));
                 }   
               setStructuredTree(st_,stNodeChild,col,rootIcon);
            }
    }
    
    
public String[] getGlobal()
    { String [] gb = {"dt","dbname","newL","projectName"};
      return gb;
    }    

public String[] getDerivedProp()
   { String derivedProp[] = {"addColumn","gtp","avr","defaultValueDC"};
     return derivedProp;
    }

void generateComponent()
{
        try {
            LucaFileGenerator.LucaFileGenerator(pr);
            ote.readComponentFile();
        } catch (IOException ex) {
            Logger.getLogger(Kontroler4.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Kontroler4.class.getName()).log(Level.SEVERE, null, ex);
        }
}

void saveTemplate()
{
  ote.saveTemplate();
  generateComponent();
 ote.readTemplateFile();
}



/*void treeCellFactory()
{
    StructureText.setCellFactory(tv -> 
        new TreeCellImpl(prop)    
    ); 

}*/

   /* private class TreeCellImpl extends TreeCell<String> {
        public List <TypePropNameProp> prop;
        public TreeCellImpl(List <TypePropNameProp> prop) {this.prop = prop;}
        

        @Override
        protected void updateItem(String item, boolean empty)
        {   //final Label label = new Label();
            //final Label anotherLabel = new Label("Item:");
            //label.getStyleClass().add("tree-cell");
            //anotherLabel.getStyleClass().add("tree-cell"); 
            //final HBox hbox = new HBox(5, anotherLabel, label);
                       
            
            super.updateItem(item, empty);  
                    if (empty) {
                        setGraphic(null);
                    } else { //setStyle("-fx-background-color: red;");
                            //setGraphic(hbox);
                    }
            
            
            //this.setStyle("-fx-background-color: red;");
            
            
            //if (this.prop == null || item == null) return;
            //for(TypePropNameProp prop:this.prop)  
            //{   
                
              //  if ((prop.typeProp.equals("rootprop")) && (item.equals(prop.nameProp)))
                  {
                    //this.setStyle("-fx-background-color: green;");
                  }
                if (empty || item == null)
                {   setText("");
                //setStyle("-fx-background-color: black;");
                } else
                {   setText(item);
                setStyle(null);
                }
            //}
        }
    }*/
}


