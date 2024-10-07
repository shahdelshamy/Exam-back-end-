package com.global.classes;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.entities.StudentIntity;
import com.global.entities.TeacherIntity;
import com.google.gson.Gson;

public class Authentication {

	
	private int key=3;
	
	public String encript(Object object) throws JsonProcessingException{
		
		ObjectMapper objectToString=new ObjectMapper();       //Jackson
		String value=objectToString.writeValueAsString(object);
		
//        String textEncripted="";
//        for(int i=0;i<value.length();i++){
//            textEncripted+=(char)(value.codePointAt(i)+key);
//        }
		
       String base64Text= Base64.getEncoder().encodeToString(value.getBytes());
        
        return base64Text;
    }
    
   public StudentIntity decriptStudent(String text){
	   
	   byte[] bytesText= Base64.getDecoder().decode(text);
		String textDecodedBase=new String(bytesText);
	   
//        String textdecripted="";
//        for(int i=0;i<textDecodedBase.length();i++){
//        	textdecripted+=(char)(textDecodedBase.codePointAt(i)-key);
//        }
//        System.out.println(textdecripted); 
        
        ObjectMapper objectToString=new ObjectMapper();       //Jackson
		StudentIntity value = null;
		try {
			value = objectToString.readValue(textDecodedBase, StudentIntity.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return  value ;
    }
   
   public TeacherIntity decriptTeacher(String text) {
	  
	   byte[] bytesText = Base64.getDecoder().decode(text);
       String textDecodedBase = new String(bytesText);
	 
//       String textdecripted="";
//       for(int i=0;i<textDecodedBase.length();i++){
//           textdecripted+=(char)(textDecodedBase.codePointAt(i)-key);
//       }
//		System.out.println(textdecripted);

		 ObjectMapper objectToString=new ObjectMapper();       //Jackson
		 TeacherIntity value = null;
		try {
			value = objectToString.readValue(textDecodedBase, TeacherIntity.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	     return  value ;
       
   }
  
   public boolean checkEmailAndPhone(String email,String phone) {
	    String regexEmail="^[\\w]+@(gmail|outlook)\\.com$";
		String regexPhone="^\\d{11}$";
		Matcher patternEmail=Pattern.compile(regexEmail).matcher(email);
		Matcher patternPhone=Pattern.compile(regexPhone).matcher(phone);
		return patternEmail.find() && patternPhone.find();
   }
	
	
}
