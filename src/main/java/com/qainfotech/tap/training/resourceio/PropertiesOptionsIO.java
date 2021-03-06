package com.qainfotech.tap.training.resourceio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class PropertiesOptionsIO{
	Properties props=new Properties();
	InputStream instream;
	OutputStream outstream;
	
    
    public Object getOptionValue(String optionKey) throws IOException {
    	String value=null;
    	/*instream=new FileInputStream("F:\\assignment-resource-io\\src\\test\\resources\\options.properties");
    	props.load(instream);*/
    	instream=this.getClass().getClassLoader().getResourceAsStream("options.properties");
    	props.load(instream);

    	value=props.getProperty(optionKey);
        /*ResourceBundle bundle=ResourceBundle.getBundle("test.resources.options");
        value=bundle.getString(optionKey);*/
    	System.out.println(value);
    	return value;
    	
        
    }

    public void addOption(String optionKey, Object optionValue) throws IOException {
    	try
    	{
        outstream=new FileOutputStream("F:\\assignment-resource-io\\src\\test\\resources\\options.properties",true);
        props.setProperty(optionKey, optionValue.toString());
        props.store(outstream, null);
    	}
    	catch(FileNotFoundException e){
    		e.printStackTrace();
    	}
    	finally{
    		if(outstream!=null){
    			outstream.close();
    		}
    	}
        
        
    }
}
