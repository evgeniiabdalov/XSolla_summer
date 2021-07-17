
package Service;

import Business.ItemController;
import Entities.Item;


import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author abdalevg
 */
@Stateless
@Path("/Item")
public class ItemFacade {

    private static ItemController itemController = new ItemController();
    
    private static int currentPagination = 0;
    
    
    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Item> getItems(){
        
        System.out.println(this.itemController.getAllItems().size());
        
        return this.itemController.getAllItems();
          
    }
    
    
    @GET
    @Path("/all/{page}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Item> getItems( @PathParam("page") int paginationIndex){
        
        List<Item> currentItemList = new ArrayList<>();
        
        List<Item> allItemList = this.itemController.getAllItems();
        
        int endOfPage = currentPagination + paginationIndex;
        
        for( int i = currentPagination; i < endOfPage; i++ ){
            
            if( i < allItemList.size() )
              currentItemList.add( allItemList.get(i) );
            else{
                
                currentPagination = 0;
               
                return currentItemList;
            
            }    
            
        }
        
        currentPagination = endOfPage;
        
        return currentItemList;
        
    }
    
    
    @GET
    @Path("/all/type/{type}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Item> getItems(@PathParam("type") String type){
        
        List<Item> currentItemList = new ArrayList<>();
        
        List<Item> allItemList = this.itemController.getAllItems();
        
        for( Item item : allItemList ){
            
            if( item.getType().equals(type) )
                currentItemList.add(item);
            
        }
        
        
        return currentItemList;
        
    }
    
    @GET
    @Path("/all/price/bigger/{price}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Item> getItemsByBiggerPrice(@PathParam("price") int price){
        
        List<Item> currentItemList = new ArrayList<>();
        
        List<Item> allItemList = this.itemController.getAllItems();
        
        for( Item item : allItemList ){
            
            if( item.getPrice() > price  )
                currentItemList.add(item);
            
        }
        
        
        return currentItemList;
        
    }
    
    @GET
    @Path("/all/price/smaller/{price}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Item> getItemsBySmallerPrice(@PathParam("price") int price){
        
        List<Item> currentItemList = new ArrayList<>();
        
        List<Item> allItemList = this.itemController.getAllItems();
        
        for( Item item : allItemList ){
            
            if( item.getPrice() < price  )
                currentItemList.add(item);
            
        }
        
        
        return currentItemList;
        
    }
    
    
    @GET
    @Path("/all/price/equals/{price}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Item> getItemsByEqualsPrice(@PathParam("price") int price){
        
        List<Item> currentItemList = new ArrayList<>();
        
        List<Item> allItemList = this.itemController.getAllItems();
        
        for( Item item : allItemList ){
            
            if( item.getPrice() == price  )
                currentItemList.add(item);
            
        }
        
        
        return currentItemList;
        
    }
    
    
    @GET
    @Path("/all/price/bigger/equals/{price}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Item> getItemsByBiggerEqualsPrice(@PathParam("price") int price){
        
        List<Item> currentItemList = new ArrayList<>();
        
        List<Item> allItemList = this.itemController.getAllItems();
        
        for( Item item : allItemList ){
            
            if( item.getPrice() >= price  )
                currentItemList.add(item);
            
        }
        
        
        return currentItemList;
        
    }
    
    
    @GET
    @Path("/all/price/smaller/equals/{price}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Item> getItemsBySmallerEqualsPrice(@PathParam("price") int price){
        
        List<Item> currentItemList = new ArrayList<>();
        
        List<Item> allItemList = this.itemController.getAllItems();
        
        for( Item item : allItemList ){
            
            if( item.getPrice() <= price  )
                currentItemList.add(item);
            
        }
        
        
        return currentItemList;
        
    }
    
    
    @GET
    @Path("/ID/{ID}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Item getByID( @PathParam("ID") int ID ){
        
        return itemController.getItemByID(ID);        
        
    }
    
    @GET
    @Path("/SKU/{SKU}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Item getBySKU( @PathParam("SKU") String SKU ){
        
        return this.itemController.getItemBySKU(SKU);    
        
    }
    
    
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String createItem( String itemXML ) throws ParserConfigurationException, SAXException, IOException{       
       
       Item item = new Item();
       
       int ID = this.itemController.getAllItems().size();
        
       item.setID( this.itemController.getAllItems().size() );
       
       
       //---------------------------------------------------------------- 
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = (Document) dBuilder.parse( new InputSource(new StringReader(itemXML)) );    
     
        NodeList nList = doc.getElementsByTagName("*");
            
        for (int i = 0; i < nList.getLength(); i++) {
        
            Node node = nList.item(i);

            Element element = (Element) node;
           
           
            String nodeName = element.getNodeName();
            String textContent = element.getTextContent();
            
            System.out.println("nodeName " + nodeName);
            System.out.println("textContent " + textContent);
            
            if( nodeName.equals("price") ){
               
                
                item.setPrice( Integer.parseInt( textContent ) );   
                System.out.println("getPrice " + item.getPrice() );
                
            }
            else if( nodeName.equals("SKU") ){
       
                item.setSKU( textContent );
                System.out.println("getSKU " + item.getSKU() );
            }
            else if( nodeName.equals("name") ){
               
                item.setName( textContent );                                 
                System.out.println("getName " + item.getName() );
            }
            else if( nodeName.equals("type") ){
               
               item.setType( textContent );
               System.out.println("getType " + item.getType() );       
            }
           
           
        }     
       
        
        //---------------------------------------------------------------- 
       
        
        
        if( itemController.addItem( item ) )     
              return ("new item is added ID: " + String.valueOf( ID ) + " SKU: " + item.getSKU());
        else
              return ("new item is not added SKU is not unique");
          
       
    }
    
    
    
    
    @DELETE
    @Path("/ID/{ID}")
    public String deleteByID( @PathParam("ID") int ID ){
                
       if( itemController.deleteItemByID(ID) ){
           
           return "Item with ID: " + String.valueOf(ID) + " is deleted";
           
       }
        

       return "Item is not found";         
        
    
    }


    @DELETE
    @Path("/SKU/{SKU}")
    public String deleteBySKU( @PathParam("SKU") String SKU ){
        
        if( itemController.deleteItemBySKU(SKU) ){
            
           return "Item with SKU: " + String.valueOf(SKU) + " is deleted";
            
            
            
        }
        
        
        return "Item is not found";
        
        
    }
    
    @PUT
    @Path("ID/{ID}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String updateByID( @PathParam("ID") int ID, String itemXML  ) throws ParserConfigurationException, SAXException, IOException{
        
        Item item = itemController.getItemByID(ID);
        
        if( item == null )
            return "Item is not found";
        
        //---------------------------------------------------------------- 
       
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = factory.newDocumentBuilder();
         Document doc = (Document) dBuilder.parse( new InputSource(new StringReader(itemXML)) );    
     
         NodeList nList = doc.getElementsByTagName("*");
            
         for (int i = 0; i < nList.getLength(); i++) {
        
            Node node = nList.item(i);

            Element element = (Element) node;
           
           
            String nodeName = element.getNodeName();
            String textContent = element.getTextContent();
            
            System.out.println("nodeName " + nodeName);
            System.out.println("textContent " + textContent);
            
            if( nodeName.equals("price") ){
               
                
                item.setPrice( Integer.parseInt( textContent ) );   
                System.out.println("getPrice " + item.getPrice() );
                
            }
            else if( nodeName.equals("SKU") ){
       
                item.setSKU( textContent );
                System.out.println("getSKU " + item.getSKU() );
            }
            else if( nodeName.equals("name") ){
               
                item.setName( textContent );                                 
                System.out.println("getName " + item.getName() );
            }
            else if( nodeName.equals("type") ){
               
               item.setType( textContent );
               System.out.println("getType " + item.getType() );       
            }
        
         
        }
        
        //---------------------------------------------------------------- 
       
       
        if( itemController.deleteItemByID(ID) ){       
        
            if( itemController.addItem(item) ){
                return ("Item " + String.valueOf( ID ) + " is updated ");
            }
            else 
                return "error SKU is not unique";           
        
        }
        
        return "";
        
    }
        
    
    
    
    
    
}
