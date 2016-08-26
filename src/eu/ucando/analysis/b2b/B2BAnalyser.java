package eu.ucando.analysis.b2b;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;



public class B2BAnalyser {
	
	private final DB mongoDb;
	
	public B2BAnalyser(DB mongoDb) {
		this.mongoDb = mongoDb;
	}
	
	
		
	public void export(Writer ordersWriter, Writer salesWriter) throws IOException {
		DBCollection coll = mongoDb.getCollection("order");
        System.out.println("Collection mycol selected successfully");

        ordersWriter.write("vatId;customer name;company name; date\n");
        salesWriter.write("Order; sku; vatId; quantity; singlePriceInSubunit; month; day\n");
        
	     /*
	      * deliveryAddress.name 
	      */
	     DBCursor cursor = coll.find();
        
         while (cursor.hasNext()) { 
        	 DBObject order = cursor.next();
        	 DBObject deliveryAddress = (DBObject)order.get("deliveryAddress");       	
        	 String vatId = (String)order.get("vatId");	        	 
        	 if (vatId != null){	        
        		 writeOrder(deliveryAddress, vatId, ordersWriter);				      
			     writeSales(order, vatId, salesWriter);
        	 }
          }
	}
	
	
	
	private void writeOrder(DBObject deliveryAddress, String vatId, Writer b2bOrdersWriter) throws IOException {
		b2bOrdersWriter.write(vatId);	        		
		b2bOrdersWriter.write(";");
						  	   
		String deliveryAddressname = (String)deliveryAddress.get("name");	         	 
		if (deliveryAddressname != null){ 
			 b2bOrdersWriter.write(deliveryAddressname);
			 System.out.println(deliveryAddress);
		 }
		 b2bOrdersWriter.write(";"); 
			 
		 String companyname = (String)deliveryAddress.get("company");	         	 
		 if (companyname != null){ 
			 b2bOrdersWriter.write(companyname);
			 System.out.println(deliveryAddress); 
		  }
		  b2bOrdersWriter.write(";");	
		  
		  
		  
		 String data = (String)deliveryAddress.get("");	         	 
		 if (data != null){ 
			b2bOrdersWriter.write(data);
			System.out.println(deliveryAddress);  
		 }
		 b2bOrdersWriter.write(";");
		 b2bOrdersWriter.write("\n");
	}	
	
	

	private void writeSales(DBObject order, String vatId, Writer b2bSalesWriter) throws IOException {
		Collection<DBObject> orderEntries = (Collection<DBObject>)order.get("entries");
		
		String entriescode = (String)order.get("code");	         	  
		for (DBObject entry : orderEntries) {
			b2bSalesWriter.write(entriescode);
			System.out.println(entriescode); 
			b2bSalesWriter.write(";");	
		  			  			  
		    
			String entriessku = (String)entry.get("sku");
		    b2bSalesWriter.write(entriessku); 
		    System.out.println(entriessku);	    
		    b2bSalesWriter.write(";");		
			
						
			String entriesvatId = (String)order.get("vatId");	         	  
			b2bSalesWriter.write(entriesvatId);
			System.out.println(entriesvatId); 
			b2bSalesWriter.write(";");	
			
						  			  				
													    
		    String entriesquantity  = entry.get("quantity").toString();  
		    b2bSalesWriter.write(entriesquantity);   
		    b2bSalesWriter.write(";");  
	
		  
		    /*
		     * singlePriceInSubunit / 100
		     */
		    		    
		    Long singlePriceInSubunit  = (Long)entry.get("singlePriceInSubunit"); 
		    
		    double singlePriceInMainUnit = singlePriceInSubunit/100d;
		    		    		    
		    b2bSalesWriter.write(Double.toString(singlePriceInMainUnit));
		    b2bSalesWriter.write(";");		    		  
		     
		    
		    
		    Date executionTime = (Date)order.get("executionTime");
		    SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
		    String month = dateFormat.format(executionTime);		    
			b2bSalesWriter.write(month);
			b2bSalesWriter.write(";");
			
			SimpleDateFormat dateDay = new SimpleDateFormat("yyyy-MM-dd");
			String day = dateDay.format(executionTime);
			b2bSalesWriter.write(day);
			// 2016-08-25
						 
			b2bSalesWriter.write(";\n"); 
		    
	   }
   }
}
	
	  
	



	        	 
	         
  
	


	


		


		
		
		
	