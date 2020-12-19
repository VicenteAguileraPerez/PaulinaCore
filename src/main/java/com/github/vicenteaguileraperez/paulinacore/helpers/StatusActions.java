/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.vicenteaguileraperez.paulinacore.helpers;

import com.github.vicenteaguileraperez.paulinacore.enums.Options;


/**
 * @author Vicente Aguilera Pérez
 * @version 1.0.0
 * Date December 18th, 2020
 */
public class StatusActions 
{
    /**
     * 
     * @param option is a value of the enum <code>Options</code> ADD,UPDATE,DELTE
     * @param isOk  is the status of the action  
     * 
     * @return if and only if  the action is success return the next message
     * ADDING IS SUCCESS or UPDATING IS SUCCESS or DELETING IS SUCCESS.
     * ADDING IS NOT SUCCESS or UPDATING IS NOT SUCCESS or DELETING IS NOT SUCCESS.
     * otherwise
     */
    public static String statusConfirm(Options option,boolean isOk)
    {
        String message="";
        switch(option)
        {
            case ADD:
                message =  isOk?"ADDING IS SUCCESS":"ADDING IS NOT SUCCESS";
                break;
            case UPDATE:
                message = isOk?"UPDATING IS SUCCESS":"ADDING IS NOT SUCCESS";
                break;
            case DELETE:
                message = isOk?"DELETING IS SUCCESS":"ADDING IS NOT SUCCESS";
                break;
        }
        return message;
    }
    
}
