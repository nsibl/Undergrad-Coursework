// Noah Sibley N01512207 Project 1 10/03/2023

package project1.java;

import java.util.Arrays;
import java.util.Scanner;

public class project1 {
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		String[] wordList = {};
		
		//Initialize wordList with user input
        wordList = newWordList();
		
		int userOption;
		
		//Main loop of the program
		do {
			displayMenu();
			userOption = scnr.nextInt();
			scnr.nextLine();
			
			//Switch statement that handles user menu options
			switch(userOption) {
				case 1:
					displayWordOrderAZ(wordList);
					break;
				case 2:
					displayWordLength(wordList);
					break;
				case 3: 
					displayListStats(wordList);
					break;
				case 4: 
					evenOddCount(wordList);
					break;
				case 5:
					primeLengthCount(wordList);
					break;
				case 6:
					wordList = newWordList();
					break;
				case 7:
					System.out.println("Exiting Program");
					break;
				default:
					System.out.println("Improper input");
			}
		} while(userOption != 7);
	}
	
	//Displays menu options
	public static void displayMenu() {
		System.out.println("Please make a selection:");
        System.out.println("1) Display List Ordered");
        System.out.println("2) Display Word Length");
        System.out.println("3) Display List Statistics");
        System.out.println("4) Number of Odd/Even Words");
        System.out.println("5) Check for Prime Length");
        System.out.println("6) Enter New Word List");
        System.out.println("7) Quit Program");
	}
	
	//Orders list alphabetically
	public static void displayWordOrderAZ(String[] wordList) {
		Arrays.sort(wordList);
		System.out.println(Arrays.toString(wordList));
	}
	
	//Shows length of each word
	public static void displayWordLength(String[] wordList) {
		for(String word : wordList) {
			System.out.println(word + " Length is " + word.length());
		}
	}
	//Shows the list's statistics
	public static void displayListStats(String[] wordList) {
		int lengthMin = shortestWordLength(wordList);
		int lengthMax = longestWordLength(wordList);
		int charTotal = totalChars(wordList);
		int numNouns = nounCount(wordList);
		double avgLength = avgWordLength(wordList);
		String listMode = getListMode(wordList);
		
		System.out.println("Min Word Length: " + lengthMin);
        System.out.println("Max Word Length: " + lengthMax);
        System.out.println("Total Number of Characters: " + charTotal);
        System.out.println("Number of Nouns: " + numNouns);
        System.out.println("Average Word Length: " + avgLength);
        System.out.println("Most Frequent Word: " + listMode);
	}
	
	//Counts number of even/odd word lengths
	public static void evenOddCount(String[] wordList) {
		int numEven = 0;
		int numOdd = 0;
		
		for(String word : wordList) {
			if(word.length() % 2 == 0) {
				numEven++;
			} else {
				numOdd++;
			}
		}
		
		System.out.println("Number even: " + numEven);
		System.out.println("Number odd: " + numOdd);
	}
	
	//Counts if any words have a prime length
	public static void primeLengthCount(String[] wordList) {
		int numPrimes = 0;
		
		for(String word : wordList) {
			if(isPrime(word.length())) {
				numPrimes++;
			}
		}
		
		System.out.println("Number of primes in list: " + numPrimes);
	}
	
	//Determines whether a word's length is prime 
	public static boolean isPrime(int n) {
		if(n <= 1) {
			return false;
		}
		
		for(int i = 2; i <= Math.sqrt(n); i++) {
			if(n % i == 0) {
				return false;
			}
		}
		
		return true;
	}
	
	//Allows user to create a new word list
	public static String[] newWordList() {
		Scanner scnr = new Scanner(System.in);
		System.out.println("Enter List of Words Separated by Spaces:");
		String input = scnr.nextLine();
		return input.split(" ");
	}
	
	//Finds the shortest length of a word
	public static int shortestWordLength(String[] wordList) {
		int minLength = Integer.MAX_VALUE;
		for (String word : wordList) {
			minLength = Math.min(minLength, word.length());
		}
		
		return minLength;
	}
	
	public static int longestWordLength(String[] wordList) {
		int maxLength = 0;
		for(String word : wordList) {
			maxLength = Math.max(maxLength, word.length());
		}
		
		return maxLength;
	}
	
	public static int totalChars(String[] wordList) {
		int numChars = 0;
		for(String word : wordList) {
			numChars += word.length();
		}
		
		return numChars;
	}
	
	//Determines if and how many nouns are in the list (upper case)
	public static int nounCount(String[] wordList) {
		int numNouns = 0;
		for(String word : wordList) {
			if(Character.isUpperCase(word.charAt(0))) {
				numNouns++;
			}
		}
		
		return numNouns;
	}
	
	public static double avgWordLength(String[] wordList) {
		if(wordList.length == 0) { 
			return 0;
		}
		
		int totalLength = 0;
		for(String word : wordList) {
			totalLength += word.length();
		}
		
		return (double) totalLength / wordList.length;
	}
	
	//Finds the mode of the list (if there is one)
	public static String getListMode(String[] wordList) {
		if(wordList.length == 0) {
			return "No Mode";	
		}
		
		String mode = wordList[0];
		int maxCount = 1;
		
		for(int i = 0; i < wordList.length; i++) {
			int count = 1;
			
			for( int j = i + 1; j < wordList.length; j++) {
				if(wordList[i].equalsIgnoreCase(wordList[j])) {
					count++;
				}
			}
			
			if(count > maxCount) {
				maxCount = count;
				mode = wordList[i];
			}
		}
		
		if(maxCount == 1) {
			return "No Mode";
		}
		
		
		return mode;
	}
	
	
	//End program
}


