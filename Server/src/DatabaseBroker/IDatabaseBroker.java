/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseBroker;

import domain.GeneralDObject;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author milos
 */
public interface IDatabaseBroker {

    public abstract boolean makeConnection();

    public abstract boolean insertRecord(GeneralDObject odo);

    public abstract boolean updateRecord(GeneralDObject odo, GeneralDObject odoold);

    public abstract boolean updateRecord(GeneralDObject odo);

    public abstract boolean deleteRecord(GeneralDObject odo);

    public abstract boolean deleteRecords(GeneralDObject odo, String where);

    public abstract GeneralDObject findRecord(GeneralDObject odo);

    public abstract List<GeneralDObject> findRecords(GeneralDObject odo, String where);

    public abstract boolean commitTransation();

    public abstract boolean rollbackTransation();

    public abstract boolean increaseCounter(GeneralDObject odo, AtomicInteger counter);

    public abstract void closeConnection();

    public abstract GeneralDObject getRecord(GeneralDObject odo, int index);

    public abstract int getRecordsNumber(GeneralDObject odo);
}
