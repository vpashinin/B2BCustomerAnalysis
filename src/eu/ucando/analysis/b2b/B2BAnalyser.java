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
	 */
	
	public static void main(String[] args) throws Exception {

		    MongoClient mongoClient = new MongoClient( "localhost" , 27017 );        
	        BufferedWriter b2bOrdersWriter = new BufferedWriter(new FileWriter("/home/vpashinin/b2borders.csv"));
	        	        				 			    	         	    
				     	         	               	                  	         
	         DB db = mongoClient.getDB( "som" );
	         System.out.println("Connect to database successfully");					           
				
	         DBCollection coll = db.getCollection("order");
	         System.out.println("Collection mycol selected successfully");

   		     b2bOrdersWriter.write("vatId;customer name;company name");
   		     b2bOrdersWriter.newLine();
	         
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
	        	 }
			        		 
	        		 
	        		 
	        		 /*
	        		  * vatId;customer name
	        		  */	        		      		  	     	
	        		   
	        		  
	        	  	 
	        	
	            }
	           b2bOrdersWriter.flush();
	           b2bOrdersWriter.close();
	           
	           mongoClient.close();

	           }
	
	    
	        }
	  
       

	
	



	        	 
	         
  
	


	


		


		
		
		
	