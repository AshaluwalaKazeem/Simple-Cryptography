import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class App {

	public static void main(String[] args) {
		generateToken();
	}

	/**
	 * Mediafy will call this method to generate token
	 */
	private static void generateToken() {
		//We will make sure that the four variables consist the appropriate value within the specified digit
		
		// variable one will have five digit
		int var1 = (int) Math.floor(Math.random()*(99999-10000+1)+10000);
		// variable two will have four digit
		int var2 = (int) Math.floor(Math.random()*(9999-1000+1)+1000);
		// variable three will have five digit
		int var3 = (int) Math.floor(Math.random()*(99999-10000+1)+10000);
		// variable four will have six digit
		int var4 = (int) Math.floor(Math.random()*(999999-100000+1)+100000);
		
		// we need to make sure that the four variable usage are dynamic
		// Create a list that will contain the variable position then use Collection.shuffle to shuffle the list;
		List<Integer> array = new ArrayList<>();
		for (int i = 1; i <= 4; i++) {
			array.add(i);
		}
		Collections.shuffle(array);
		String orderOfSequence = "";// This will save how our variable will be used
		for(int rowData : array) {
			switch(rowData){
				case 1:
					orderOfSequence = orderOfSequence + "s";
					break;
				case 2:
					orderOfSequence = orderOfSequence + "j";
					break;
				case 3:
					orderOfSequence = orderOfSequence + "i";
					break;
				case 4:
					orderOfSequence = orderOfSequence + "w";
					break;
			}
		}
		// If we patch the all the four int variable together and also the order of sequence the total length will be 24
		// That means that we have generate dynamic 20 characters to make the total token length be 44
		// The remaining 16 digit will be our encryption logic
		// If our first character in orderOfSequence variable start with (s), this is how the logic will go
		//   1. merge the char starting from index 5 to 10 and save it to a variable.... do this to get five char
		//	 2. merge the char starting from index 15 to 20 .... do this to get get six char
		//	 2. merge the char starting from index 0 to 5 .... do this to get get three char
		//	 2. merge the char starting from index 10 to 15 .... do this to get get two char
		// Else If our first character in orderOfSequence variable start with (j), this is how the logic will go
		//   1. merge the char starting from index 0 to 5 and save it to a variable (NOTE: index starts from 0).... do this to get five char
		//	 2. merge the char starting from index 15 to 20 .... do this to get get six char
		//	 2. merge the char starting from index 5 to 10 .... do this to get get three char
		//	 2. merge the char starting from index 10 to 15 .... do this to get get two char
		// Else If our first character in orderOfSequence variable start with (i), this is how the logic will go
		//   1. merge the char starting from index 5 to 10 and save it to a variable (NOTE: index starts from 0).... do this to get five char
		//	 2. merge the char starting from index 10 to 15 .... do this to get get six char
		//	 2. merge the char starting from index 0 to 5 .... do this to get get three char
		//	 2. merge the char starting from index 15 to 20 .... do this to get get two char
		// Else If our first character in orderOfSequence variable start with (w), this is how the logic will go
		//   1. merge the char starting from index 10 to 15 and save it to a variable (NOTE: index starts from 0).... do this to get five char
		//	 2. merge the char starting from index 0 to 5 .... do this to get get six char
		//	 2. merge the char starting from index 5 to 10 .... do this to get get three char
		//	 2. merge the char starting from index 15 to 20 .... do this to get get two char
		String generatedText = String.valueOf(var1) + String.valueOf(var2) + String.valueOf(var3) + String.valueOf(var4);
		String remainingText = "";
		
		switch(String.valueOf(orderOfSequence.charAt(0))){
			case "s":
				remainingText = remainingText + String.valueOf(generatedText.substring(5, 10));
				remainingText = remainingText + String.valueOf(generatedText.substring(15, 20));
				remainingText = remainingText + String.valueOf(generatedText.substring(0, 5));
				remainingText = remainingText + String.valueOf(generatedText.substring(10, 15));
				break;
			case "j":
				remainingText = remainingText + String.valueOf(generatedText.substring(0, 5));
				remainingText = remainingText + String.valueOf(generatedText.substring(15, 20));
				remainingText = remainingText + String.valueOf(generatedText.substring(5, 10));
				remainingText = remainingText + String.valueOf(generatedText.substring(10, 15));
				break;
			case "i":
				remainingText = remainingText + String.valueOf(generatedText.substring(5, 10));
				remainingText = remainingText + String.valueOf(generatedText.substring(10, 15));
				remainingText = remainingText + String.valueOf(generatedText.substring(0, 5));
				remainingText = remainingText + String.valueOf(generatedText.substring(15, 20));
				break;
			case "w":
				remainingText = remainingText + String.valueOf(generatedText.substring(10, 15));
				remainingText = remainingText + String.valueOf(generatedText.substring(0, 5));
				remainingText = remainingText + String.valueOf(generatedText.substring(5, 10));
				remainingText = remainingText + String.valueOf(generatedText.substring(15, 20));
				break;
		}
		String token = generatedText + orderOfSequence + remainingText;
		System.out.println("This is the original token code = " + token);// Displaying 44 dynamic Token
		String encryptedToken = "";
		for(int i = 0; i <= 43; i++) {
			encryptedToken = encryptedToken + encryptValue(String.valueOf(token.charAt(i)));
		}
		System.out.println("This is the encrypted token code that we will pass to the server " + encryptedToken);// Pass this to the server
		
		validateToken();
		
	}
	
	/**
	 * This method is responsible to encyrpt each value;
	 * @param value
	 * @return encrypted value
	 */
	private static String encryptValue(String value) {
		switch(value) {
			case "0":
				return "&";
			case "1":
				return "#";
			case "2":
				return "(";
			case "3":
				return ")";
			case "4":
				return "!";
			case "5":
				return "@";
			case "6":
				return "$";
			case "7":
				return "^";
			case "8":
				return "*";
			case "9":
				return ":";
			case "s":
				return ";";
			case "j":
				return "{";
			case "i":
				return "}";
			case "w":
				return "[";
			default:
				return "~";
		}
	}
	
	/**
	 * This method is responsible to decrypt encrypted value
	 * @param value
	 * @return decrypted value
	 */
	private static String decryptValue(String value) {
		switch(value) {
			case "&":
				return "0";
			case "#":
				return "1";
			case "(":
				return "2";
			case ")":
				return "3";
			case "!":
				return "4";
			case "@":
				return "5";
			case "$":
				return "6";
			case "^":
				return "7";
			case "*":
				return "8";
			case ":":
				return "9";
			case ";":
				return "s";
			case "{":
				return "j";
			case "}":
				return "i";
			case "[":
				return "w";
			default:
				return "o";
		}
	}
	
	/**
	 * Server will call this method to validate token
	 */
	private static void validateToken() {
		//Accept user input for the token
		Scanner in = new Scanner(System.in);
		System.out.println("Please Enter a valid token that is generated from mediafy: ");
        String token = in.nextLine();
        
        if(token.length() != 44) {
        	System.out.println("Invalid token");
        	validateToken();
        }else {
        	String decryptedToken = "";
        	for(int i = 0; i <= 43; i++) {
        		decryptedToken = decryptedToken + decryptValue(String.valueOf(token.charAt(i)));
    		}
        	String orderOfSequence = String.valueOf(decryptedToken.charAt(20));//Get the validation logic to use;
        	//The next step is to get our first 20 characters from the last 20 characters in decrypted token;
        	// if its the same return (valid token); then if false return (invalid token)
        	String logicText = String.valueOf(decryptedToken.substring(24, 44));//This is the last 20 text we use our algorithm to generate;
        	String generatedText = "";// This is the 20 digit text that is generated by the user system;
        	//The logic to get the original generated text (The first four variable data) that is generated by 
        	// by int var1 = (int) Math.floor(Math.random()*(99999-10000+1)+10000);
        	// is different. This is how to recover the text from the dynamic last 16 digit
        	switch(orderOfSequence){
	        	case "s":
	        		generatedText = generatedText + String.valueOf(logicText.substring(10, 15));
	        		generatedText = generatedText + String.valueOf(logicText.substring(0, 5));
	        		generatedText = generatedText + String.valueOf(logicText.substring(15, 20));
	        		generatedText = generatedText + String.valueOf(logicText.substring(5, 10));
					break;
				case "j":
					generatedText = generatedText + String.valueOf(logicText.substring(0, 5));
					generatedText = generatedText + String.valueOf(logicText.substring(10, 15));
					generatedText = generatedText + String.valueOf(logicText.substring(15, 20));
					generatedText = generatedText + String.valueOf(logicText.substring(5, 10));
					break;
				case "i":
					generatedText = generatedText + String.valueOf(logicText.substring(10, 15));
					generatedText = generatedText + String.valueOf(logicText.substring(0, 5));
					generatedText = generatedText + String.valueOf(logicText.substring(5, 10));
					generatedText = generatedText + String.valueOf(logicText.substring(15, 20));
					break;
				case "w":
					generatedText = generatedText + String.valueOf(logicText.substring(5, 10));
					generatedText = generatedText + String.valueOf(logicText.substring(10, 15));
					generatedText = generatedText + String.valueOf(logicText.substring(0, 5));
					generatedText = generatedText + String.valueOf(logicText.substring(15, 20));
					break;
			}

        	String firstCharText = decryptedToken.substring(0, 20);
        	String lastCharText = generatedText;
        	if(lastCharText.trim().contains(firstCharText)) {
        		System.out.println("Valid Token");
            	System.out.println("firstCharText is " + firstCharText + " and lastCharText " + lastCharText);
        	}else {
        		System.out.println("Invalid Token\nRe enter token");
        		validateToken();
        	}
        }
	}

}













