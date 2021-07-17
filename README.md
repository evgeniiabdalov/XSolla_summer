
Prototype web app, that represents e-shop.

It has been created as a test task.   


Project is written as Java Web Application, it has been tested on Linux OS.


Linux: To build the project insert 'mvn clean install' in your command line


To run the project insert 'mvn tomcat7:run' in your command line, application should be running on your localhost on port 9090.



REST API calls:

+ GET: localhost:9090/services/Item/all
   
        returns list of all items

+ GET: localhost:9090/services/Item/all/{page}

        returns a certain amount of items out of the list, pagination

+ GET: localhost:9090/services/Item/all/type/{type}

       returns list of items of a certain type

+ GET: localhost:9090/services/Item/all/price/bigger/{price}

       returns list of items with a price bigger than the parameter

+ GET: localhost:9090/services/Item/all/price/smaller/{price}

       
       returns list of items with a price smaller than the parameter
  
+ GET: localhost:9090/services/Item/all/price/equals/{price}

       returns list of items with a price equal to the parameter  
      
+ GET: localhost:9090/services/Item/all/price/bigger/equals/{price}

       returns list of items with a price bigger/equal than/to the parameter

+ GET: localhost:9090/services/Item/all/price/smaller/equals/{price}

       returns list of items with a price smaller/equal than/to the parameter

+ GET: localhost:9090/services/Item/ID/{ID}

       returns an item with the parameter ID  

+ GET: localhost:9090/services/Item/SKU/{SKU}

        
       returns an item with the parameter SKU  
 
+ DELETE: localhost:9090/services/Item/ID/{ID}

       deletes an item with the parameter ID

+ DELETE: localhost:9090/services/Item/SKU/{SKU}

       deletes an item with the parameter SKU 

+ POST: localhost:9090/services/Item

       creates an item with a certain attributes

       

+ PUT: localhost:9090/services/Item/ID/{ID}


         updates an item with a parameter ID with a certain parameters
