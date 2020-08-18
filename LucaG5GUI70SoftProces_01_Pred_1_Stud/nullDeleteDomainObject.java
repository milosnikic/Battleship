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
public class DeleteDomainObject extends GeneralSO{

    public DeleteDomainObject(TransferObject to) {
          generalExecutionSO(to);
    }
    
    
  @Override
  boolean executionSO(TransferObject to)
    {  boolean signal = dbbr.deleteRecord(to.getDomainObject());
       return signal;
    }
  
    @Override
 String messageSuccess(TransferObject to){return to.getDomainObject().messageDeleteSuccess(); }
    @Override
 String messageFailure(TransferObject to){return to.getDomainObject().messageDeleteFailure();}   
    
}
