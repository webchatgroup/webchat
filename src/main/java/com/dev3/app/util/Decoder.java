package com.dev3.app.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.thoughtworks.xstream.core.util.Base64Encoder;

/**
 * 
 * @author RL
 *
 */
public class Decoder {
	
	public static String decode(String origin){
		
		String encoded = origin;
		
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			
			Base64Encoder base64en = new Base64Encoder();

			encoded = base64en.encode(digest.digest(origin.getBytes("utf-8")));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return encoded;
		
	}
	
	public static void main(String[] args){
		
		String origin = "123456";
		
		System.out.println(decode(origin)); //4QrcOUm6Wau+VuBX8g+IPg==
		
	}
	
}
