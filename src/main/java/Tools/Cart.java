/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Class.Item;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Djidjelly Siclait
 */
public class Cart {
    // Attributes
    private List<Item> content;
    
    // COnstructor
    public Cart(){
        content = new ArrayList<>();
    }
    
    public void setContent(List<Item> content){
        this.content = content;
    }
    
    public List<Item> getContent(){
        return this.content;
    }
}
