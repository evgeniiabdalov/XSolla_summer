
package Business;

import Entities.Item;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author abdalevg
 */
public class ItemController {
 
    
    private List<Item> itemList;
    
    public ItemController(){
        itemList = new ArrayList<>();
    }
    
    public boolean addItem(Item item){
      
        System.out.println("adding item"); 
         
        for( Item tmpItem : this.itemList ){
        
            if( item.getSKU().equals(tmpItem.getSKU()) )
                return false;
        
        }
        
        //return this.itemList.add(item);
       
        boolean res = this.itemList.add(item);
        
        System.out.println("size " + this.itemList.size());
        
        return res;
        
    }
    
    public boolean deleteItemByID(int ID){
        
        for(Item item : itemList){
            
            if(item.getID() == ID){
             
                itemList.remove(item);
                
                
                return true;
                  
            }    
            
            
        }
        
        return false;
        
    }
    
    public boolean deleteItemBySKU(String SKU){
        
        for(Item item : itemList){
            
            if(item.getSKU().equals(SKU)){
             
                itemList.remove(item);
                
                
                return true;
                  
            }    
            
            
        }
        
        return false;
        
    }
    
    
    public Item getItemByID(int ID){
    
        for( Item item : itemList ){
            
            if( item.getID() == ID )
                return item;
            
        }       
        
        return null;
    }
    
    
    public Item getItemBySKU(String SKU){
        
        for( Item item : itemList ){
            
            if( item.getSKU().equals(SKU) )
                return item;
            
        }        
        
        return null;
        
    }
    
    
    public List<Item> getAllItems(){
                
        return itemList;
                
    }
    
    
    
    
    
}
