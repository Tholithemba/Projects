/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdphouses;

/**
 *
 * @author tholithemba
 */
interface IUpdates {
    
    String setValues();
    boolean validateInput();
    boolean outputStatement(String message);
    void selectedField();
    void clearCells();
    void warningTextInitialised();
    boolean modifiedTextFieldNotEmpty();
    String queryStatement();
    
}
