package com.global.classes;

import java.util.Base64;

import com.global.entities.StudentIntity;
import com.global.entities.TeacherIntity;
import com.google.gson.Gson;

public class Authentication {

	
	private int key=3;
	
	public String encript(String text){
        String textEncripted="";
        for(int i=0;i<text.length();i++){
            textEncripted+=(char)(text.codePointAt(i)+key);
        }
       String base64Text= Base64.getEncoder().encodeToString(textEncripted.getBytes());
        
        return base64Text;
    }
    
   public StudentIntity decriptStudent(String text){
	   
	   byte[] bytesText= Base64.getDecoder().decode(text);
		String textDecodedBase=new String(bytesText);
	   
        String textdecripted="";
        for(int i=0;i<textDecodedBase.length();i++){
        	textdecripted+=(char)(textDecodedBase.codePointAt(i)-key);
        }
        System.out.println(textdecripted); 
        
        Gson gson = new Gson();  
	
        StudentIntity object=gson.fromJson(textdecripted, StudentIntity.class);
		
        return  object ;
    }
   
   public TeacherIntity decriptTeacher(String text){
	   
	   byte[] bytesText = Base64.getDecoder().decode(text);
       String textDecodedBase = new String(bytesText);
	 
       String textdecripted="";
       for(int i=0;i<textDecodedBase.length();i++){
           textdecripted+=(char)(textDecodedBase.codePointAt(i)-key);
       }
		System.out.println(textdecripted);

       Gson gson = new Gson();   //Gson to convert the String into object
	
       TeacherIntity object=gson.fromJson(textdecripted, TeacherIntity.class);
		System.out.println(object.getId());
       return  object ;
   }
  
	
	
}
