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
public class UpdateDomainObject extends GeneralSO{

    public UpdateDomainObject(TransferObject to) {
       generalExecutionSO(to);
    }
    
    
  @Override
  boolean executionSO(TransferObject to)
    {  boolean signal = dbbr.updateRecord(to.getDomainObject());
       return signal;
    }
  
@Override
 String messageSuccess(TransferObject to){return to.getDomainObject().messageUpdateSuccess(); }
    @Override
 String messageFailure(TransferObject to){return to.getDomainObject().messageUpdateFailure();}   
    
}
