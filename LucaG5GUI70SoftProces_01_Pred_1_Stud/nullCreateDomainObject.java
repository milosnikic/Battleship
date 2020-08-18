/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLBehaviour;


import TransferObject.TransferObject;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Sinisa
 */
public class CreateDomainObject extends GeneralSO{

    public CreateDomainObject(TransferObject to) {
         generalExecutionSO(to);
    }
    
    
  @Override
  boolean executionSO(TransferObject to)
    {  boolean signal = true;
       AtomicInteger counter = new AtomicInteger(0);
    
       if (!dbbr.getCounter(to.getDomainObject(),counter)) {signal = false;} 
       if (!dbbr.increaseCounter(to.getDomainObject(),counter)) { signal = false;}
       if (signal == true)
         { to.getDomainObject().setPrimaryKey(counter.get());
           signal = dbbr.insertRecord(to.getDomainObject());
         }  
       return signal;
    }
    
    @Override
  String messageSuccess(TransferObject to){return to.getDomainObject().messageCreateSuccess(); }
    @Override
  String messageFailure(TransferObject to){return to.getDomainObject().messageCreateFailure();}
    
}
