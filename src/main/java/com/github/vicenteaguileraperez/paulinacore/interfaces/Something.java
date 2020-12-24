package com.github.vicenteaguileraperez.paulinacore.interfaces;
import com.github.vicenteaguileraperez.paulinacore.enums.Options;
import java.util.ArrayList;



/**
 * @author Vicente Aguilera Pérez
 * @version 1.0.0
 * Date December 18th, 2020
 */

public interface Something
{
    /**
     * <code>getSomething</code> is a default method where you can receive
     * the object added in the database
     * @param fromJson  is the object returned to cast our class
     */
    default void getSomething(Object fromJson)
    {

    }
    /**
     * <code>status</code> is a default method where you can receive
     * the action done in the database. These actions are <code>ADD, Update and Delete</code>
     * @param option return the action done  <code>ADD, Update and Delete</code>
     * @param isOk  return <code>true</code> if the action is success, <code>false</code> otherwise.
     */
    default void status(Options option,boolean isOk)
    {
        
    }
    /**
     * <code>getAll(ArrayList Object  values)</code> is a default method where you can receive all the documents stored 
     * in the database in Firebase
     * @param values is the collection of information getting
     */
    default void  getAll( ArrayList<Object> values)
    {
        
    }

    
}
