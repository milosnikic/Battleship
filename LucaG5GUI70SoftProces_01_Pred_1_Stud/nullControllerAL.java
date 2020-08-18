package ControllerAL;

/*
 * ControlerAL.java
 *
 * 02.05.2011
 *
 * @autor  Dr Sinisa Vlajic
 *
 * Katedra za softversko inzenjerstvo
 *
 * Laboratorija za softversko inzenjerstvo
 *
 * Fakultet organizacionih nauka - Beograd
 *
 */

import BLBehaviour.CreateDomainObject;
import BLBehaviour.DeleteDomainObject;
import BLBehaviour.GeneralSO;
import BLBehaviour.InsertDomainObject;
import BLBehaviour.UpdateDomainObject;
import TransferObject.TransferObject;
import java.net.*;
import java.io.*;


class ControllerAL // Kontroler aplikacione logike
  {   static ServerSocket ss;
      static Klijent kl[];

      public static void main(String[] args) throws Exception
	     {   kl = new Klijent[10];
	         ss = new ServerSocket(8189);

                System.out.println("Podignut je serverski program:");
	        for (int brojKlijenta = 0;brojKlijenta < 10;brojKlijenta ++)
		         { Socket soketS = ss.accept();
		           System.out.println("Klijent " + (brojKlijenta+1));
		           kl[brojKlijenta] = new Klijent(soketS,brojKlijenta+1);
		         }
	     }
}


class Klijent extends Thread
	{ public Klijent(Socket soketS1,int brojKlijenta1)
	   { soketS = soketS1; brojKlijenta=brojKlijenta1;
	     System.out.println("Konstruktor");
	     start();
	   }

          @Override
	  public void run()
	   { try { String signal = "";
		       out = new ObjectOutputStream(soketS.getOutputStream());
		       in = new ObjectInputStream(soketS.getInputStream());

                   System.out.println("run:");
	           while (true)
	             { // Citanje naziva operacije i racuna
	               
                       TransferObject to = (TransferObject) in.readObject();
                       if (to.getDomainObject()!=null)
                          to.getDomainObject().println();
                       if (to.getSOName().equals("createDomainObject")==true) { new CreateDomainObject(to);}
                       if (to.getSOName().equals("insertDomainObject")==true) { new InsertDomainObject(to);}
                       if (to.getSOName().equals("updateDomainObject")==true) { new UpdateDomainObject(to);}
                       if (to.getSOName().equals("deleteDomainObject")==true) { new DeleteDomainObject(to);}
                       if (to.getSOName().equals("findDomainObject")==true) { (new GeneralSO()).findDomainObject(to);}
                       if (to.getSOName().equals("getAllDomainObjects")==true) { (new GeneralSO()).getAllDomainObjects(to);}
                       if (to.getSOName().equals("getDomainObjects")==true) 
                       {  
                           (new GeneralSO()).getDomainObjects(to);
                       }
                   
                       out.writeObject(to);
                       out.reset();
                     }
				   
                 } catch (Exception e)  { System.out.println(e);  }
	   }

	  private
	  Socket soketS;
	  int brojKlijenta;
          ObjectOutputStream out;
          ObjectInputStream in;
}




