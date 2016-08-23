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
import java.io.OutputStreamWriter;
import java.util.Arrays;



public class B2BAnalyser {
	
	public static void main(String[] args) throws Exception {

		    MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        
	        File destinationFile = new File("/home/vpashinin/b2borders.csv");	   	
	        FileOutputStream fos = new FileOutputStream(destinationFile);
	    	
	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
	        
	        
			
			   			    	         	    
			
	     	         	               	                  
	         
	         DB db = mongoClient.getDB( "som" );
	         System.out.println("Connect to database successfully");
				
	           
				
	         DBCollection coll = db.getCollection("order");
	         System.out.println("Collection mycol selected successfully");

   		     bw.write("vatId");
   		     bw.newLine();
	         
   		     
   		     DBCursor cursor = coll.find();
	         
	         while (cursor.hasNext()) { 
	        	 DBObject dbObject = cursor.next();
	        	
	        	 String vatId = (String)dbObject.get("vatId");	        	 
	        	 if (vatId != null){
	        		 bw.write(vatId);
	        
	        		  bw.newLine();
	        	
	        		   
	        		  
	        	 }	 
	        	
	         }
	         bw.flush();
	         bw.close();

	   }
	
	}	
		

		
		
		
	