package org.iitwf.healthcare.mmppixel;

import java.security.SecureRandom;
import java.util.Random;

public class RandomUtils {
	
	public static String generateRandomString(int n)
	{
		String str="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		System.out.println("Length of the string"+ str.length());
		 
		StringBuilder randomString=new StringBuilder(n);
		int min=0,max=str.length()-1;
	 
		for(int i=0;i<10;i++)
		{
			int randomIndex= generateRandomInteger(min,max);
			char ch = str.charAt(randomIndex);
			randomString.append(ch+"");
			
		}
		System.out.println("Random String::" + randomString);
		return randomString.toString();
	}
	public static int generateRandomInteger(int min,int max)
	{
		Random rand = new Random();
		//LB+rand.nextInt(UB-LB+1)
		int value = min+rand.nextInt(max-min+1);
		//System.out.println(value);
		return value;
	}
	
	public static void main(String[] args) {

		//ASCII Table
		//65-A to  90-Z
		//97-a to 122-z
		
 String randomEmail = "AUTQA_"+generateRandomChars(65,90)+generateRandomChars(97,122)+generateRandomNumber(100,200)+"@gmail.com";
//		
 System.out.println(randomEmail);
//		 
		
//		Random rand = new Random();
//		int i = rand.nextInt(1000);//0 inclusive , bound exclusive- 0 to 999
//		System.out.println(i);
//	 
//		 int lBound=65;
//		 int uBound=90;
//		 
//
//		 int j = lBound+rand.nextInt(uBound-lBound);
//		 char ch =(char) j;
//		 System.out.println("Character:::"+ ch);
//	 
	}

	public static int generateRandomNumber(int bound)
	{
		Random rand = new Random();
		int i = rand.nextInt(bound);//0 inclusive , bound exclusive
		System.out.println(i);
		return i ;
	}
	public static int generateRandomNumber(int lBound,int uBound)
	{
		Random rand = new Random();
		//LB+rand.nextInt(UB-LB)
		int i = lBound + rand.nextInt(uBound-lBound);
		System.out.println(i);
		return i;
	}
	public static char generateRandomChars(int lBound,int uBound)
	{
		Random rand = new Random();
		//LB+rand.nextInt(UB-LB)
		int i = lBound + rand.nextInt(uBound-lBound);
		char ch = (char) i ;
		System.out.println(ch);
		return ch;
	}
	public static String generateRandomNumberDigitOf(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Digit count must be greater than 0.");
        }
        SecureRandom random = new SecureRandom();
        StringBuilder number = new StringBuilder();

        // First digit: 1–9 (to avoid leading zero)
        number.append(random.nextInt(9) + 1);

        // Remaining digits: 0–9
        for (int i = 1; i < length; i++) {
            number.append(random.nextInt(10));
        }
        return number.toString();
    }
	public static String generateRandomPassword(int length) {
//        if (length < 8) {
//            throw new IllegalArgumentException("Password length must be at least 8.");
//        }
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String allChars = upper + lower + digits + "!@#$%^&*()-_=+[]{}|:,.<>?";

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        // Ensure at least one lowercase, one uppercase, one digit
        password.append(lower.charAt(random.nextInt(lower.length())));
        password.append(upper.charAt(random.nextInt(upper.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));

        // Fill the remaining characters
        for (int i = 3; i < length; i++) {
            password.append(allChars.charAt(random.nextInt(allChars.length())));
        }

        return password.toString(); 
    }
	public static String generateRandomEmailID(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder email = new StringBuilder();

        for (int i = 0; i < length; i++) {
            email.append(chars.charAt(random.nextInt(chars.length())));
        }

        email.append("@example.com"); // You can change this domain as needed
        return email.toString();
    }
}
