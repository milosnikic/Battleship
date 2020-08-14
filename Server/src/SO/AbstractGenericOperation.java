/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SO;

import DatabaseBroker.DatabaseBroker;
import DatabaseBroker.IDatabaseBroker;
import domain.GeneralDObject;

/**
 *
 * @author milos
 */
public abstract class AbstractGenericOperation {

    static public IDatabaseBroker bbp = new DatabaseBroker();
    GeneralDObject gdo;

    synchronized public boolean abstractExecuteSO() {
        bbp.makeConnection();
        boolean signal = executeSO();
        if (signal == true) {
            bbp.commitTransation();
        } else {
            bbp.rollbackTransation();
        }
        bbp.closeConnection();
        return signal;
    }

    abstract public boolean executeSO();
}
