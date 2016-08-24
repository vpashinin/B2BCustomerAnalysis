package eu.ucando.analysis.b2b;

//import VehicleSearchesAnalyser;

import com.mongodb.MongoClient;
import com.mongodb.MongoException; 
import com.mongodb.WriteConcern;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;

import com.mongodb.ServerAddress;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.Arrays;



public class B2BAnalyser {
	
	/*
	 * /home/vpashinin/b2bsales.csv
	 * 
	 * order.get("code");
	 */
	
	public static void main(String[] args) throws Exception {

		    MongoClient mongoClient = new MongoClient( "localhost" , 27017 );        
	        BufferedWriter b2bOrdersWriter = new BufferedWriter(new FileWriter("/home/vpashinin/b2borders.csv"));
	        BufferedWriter b2bSalesWriter = new BufferedWriter(new FileWriter("/home/vpashinin/b2bsales.csv"));	         	 			 			    	         	    
				     	         	               	                  	          
	         DB db = mongoClient.getDB( "som" ); 
	         System.out.println("Connect to database successfully");					           
				
	         DBCollection coll = db.getCollection("order");
	         System.out.println("Collection mycol selected successfully");

   		     b2bOrdersWriter.write("vatId;customer name;company name");
   		     b2bOrdersWriter.newLine();
   		     
   		     b2bSalesWriter.write("Order; Order line item; Month; Day; NIP");
             b2bSalesWriter.newLine(); 
	         
   		     /*
   		      * deliveryAddress.name 
   		      */
   		     DBCursor cursor = coll.find();
	         
	         while (cursor.hasNext()) { 
	        	 DBObject order = cursor.next();
	        	 DBObject deliveryAddress = (DBObject)order.get("deliveryAddress");	        	 
	        	
	        	 String vatId = (String)order.get("vatId");	        	 
	        	 if (vatId != null){	        
	        		b2bOrdersWriter.write(vatId);	        		
	        	 
		        	 b2bOrdersWriter.write(";");
		     	   
		        	 String deliveryAddressname = (String)deliveryAddress.get("name");	         	 
			         if (deliveryAddressname != null){ 
		        		 b2bOrdersWriter.write(deliveryAddressname);
		        		 System.out.println(deliveryAddressname);
			         }
			         b2bOrdersWriter.write(";");
		        		
			          String companyname = (String)deliveryAddress.get("company");	         	 
				      if (companyname != null){ 
		        		 b2bOrdersWriter.write(companyname);
				      }
				      b2bOrdersWriter.write(";");	
				      b2bOrdersWriter.newLine();				      
				      
				      
				      String code = (String)order.get("code");	         	  
				      if (code != null){ 
		        		 b2bSalesWriter.write(code);
				         System.out.println(code);
				      }
				      b2bSalesWriter.write(";");
//				      b2bSalesWriter.newLine();
				      
				      
				      String Orderlineitem = (String)order.get("line item");	         	  
				      if (Orderlineitem != null){ 
		        		 b2bSalesWriter.write(Orderlineitem);
				         System.out.println(Orderlineitem);
				      }
				      b2bSalesWriter.write(";");  				      			      
				      
				      
				      String ordermonth = (String)order.get("month");	         	  
				      if (ordermonth != null){ 
		        		 b2bSalesWriter.write(ordermonth);
				         System.out.println(ordermonth);
				      }
				      b2bSalesWriter.write(";"); 
				      
				      
				      String orderday = (String)order.get("day");	         	  
				      if (orderday != null){ 
		        		 b2bSalesWriter.write(orderday);
				         System.out.println(orderday);
				      }
				      b2bSalesWriter.write(";");
				      
				      String orderNIP = (String)order.get("NIP");	         	  
				      if (orderNIP != null){ 
		        		 b2bSalesWriter.write(orderNIP);
				         System.out.println(orderNIP);
				      }
				      
				      b2bSalesWriter.write(";");
				      b2bSalesWriter.newLine();				      			      
				      				      
	        	 }
	        	
	            }
	           b2bOrdersWriter.flush();
	           b2bOrdersWriter.close();
	           b2bSalesWriter.flush();
	           b2bSalesWriter.close();
	           
	           mongoClient.close();

	           }
	
	    
	        }

       

	
	



	        	 
	         
  
	


	


		


		
		
		
	