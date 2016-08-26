package eu.ucando.analysis.b2b;

import java.io.BufferedWriter;
import java.io.FileWriter;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class Starter {
	
	public static void main(String[] args) throws Exception {

	    MongoClient mongoClient = new MongoClient( "localhost" , 27017 );        
        BufferedWriter b2bOrdersWriter = new BufferedWriter(new FileWriter("/home/vpashinin/b2borders.csv"));
        BufferedWriter b2bSalesWriter = new BufferedWriter(new FileWriter("/home/vpashinin/b2bsales.csv"));	         	 			 			    	         	    
			     	         	               	                  	          
         DB db = mongoClient.getDB( "som" ); 
         
         B2BAnalyser b2bAnalyser = new B2BAnalyser(db);
         b2bAnalyser.export(b2bOrdersWriter, b2bSalesWriter);
         
		
         b2bOrdersWriter.flush();
         b2bOrdersWriter.close();
         b2bSalesWriter.flush(); 
         b2bSalesWriter.close(); 
       
         mongoClient.close();

}

}
