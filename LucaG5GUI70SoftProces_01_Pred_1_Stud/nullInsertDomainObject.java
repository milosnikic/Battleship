/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLBehaviour;

import TransferObject.TransferObject;

/**
 *
 * @author Sinisa
 */
public class InsertDomainObject extends GeneralSO{

    public InsertDomainObject(TransferObject to) {
          generalExecutionSO(to);
    }
    
    
  @Override
  boolean executionSO(TransferObject to)
    {  boolean signal = dbbr.insertRecord(to.getDomainObject());
       return signal;
    }
  
  @Override
 String messageSuccess(TransferObject to){return to.getDomainObject().messageInsertSuccess(); }
    @Override
 String messageFailure(TransferObject to){return to.getDomainObject().messageInsertFailure();}   
    
}
