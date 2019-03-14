import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	
	
	public static void main(String[] args) throws FileNotFoundException {
			
		Scanner userGuess = new Scanner(System.in);
		
		printTitleScreen();
		System.out.println("");
		String word = getRandomWordFromFile("/Users/trishulsathiyamoorthy/Desktop/words.txt");
		char[] wordCharArray = word.toCharArray();
		
		System.out.println("It's time to guess the word!");
		
		StringBuilder gameWord = new StringBuilder(printBlankSpaces(word));
		
		int successfulAttempts = 0;
		int failedAttempts = 0;
		int totalAttempts = 0;
		
		
		do {
			
			System.out.println("Your word looks like this: " + gameWord);
			System.out.println("Your guess: ");
			
			String userInput = userGuess.next();
			char charUserInput = userInput.charAt(0);
			
			if (isValidInput(userInput)) {
					System.out.println("Your guess is: " + userInput);
				
					if (!(containsUserInput(wordCharArray, userInput))) {
					
						letterNotInWordMessage(userInput);
						failedAttempts++;
					
					} else if (containsUserInput(wordCharArray, userInput)){
					
						letterInWordMessage(userInput);		
						gameWord.setCharAt(getMatchingIndex(wordCharArray, userInput), charUserInput);
						successfulAttempts++;

					}
			
			} else {
				
				System.out.println("Invalid input entered. Try again.");
				
			}
		
			totalAttempts = successfulAttempts + failedAttempts;
	
			
		} while (!word.equals(gameWord.toString()));
	
		
		if (word.equals(gameWord.toString())) {
			System.out.println("Congratulations, you correctly guessed the word!");
			System.out.println("The word is " + word);
			System.out.println("You guessed the word in " + totalAttempts + " attempts");
			userGuess.close();
		}
		
	}
	
	private static void printTitleScreen() {
		System.out.println("-------------------");
		System.out.println("Welcome to Hangman!");
		System.out.println("-------------------");
	}
	
	private static String printBlankSpaces(String word) {
		return new String(new char[word.length()]).replace('\0', '-');
	}
		
	private static void letterNotInWordMessage(String letter) {
		System.out.println("The letter " + letter + " is not in the word!");
	}
	
	private static void letterInWordMessage(String letter) {
		System.out.println("The letter " + letter + " is in the word!");
	}
	
	private static int getMatchingIndex(char[] wordArray, String userGuess) {
		int index = -1;
		for (int i = 0; i < wordArray.length && (index == -1); i++) {
			if (String.valueOf(wordArray[i]).equals(userGuess)) {
				index = i;
			}
		}
		return index;
	}
	
	private static boolean containsUserInput(char[] wordArray, String userGuess) {
		for (int i = 0; i < wordArray.length; i++) {
			if (wordArray[i] == userGuess.charAt(0)) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean isValidInput(String userGuess) {
		final int VALID_INPUT_LENGTH = 1;
		if (userGuess.length() == VALID_INPUT_LENGTH  && Character.isLetter(userGuess.charAt(0))) {
			return true;
		} 
		return false;
	}
	
	private static String getRandomWordFromFile(String fileName) throws FileNotFoundException {
		
		File file = new File(fileName);
		Scanner fileScanner = new Scanner(file);
		String fileContents = "";
		
		while(fileScanner.hasNextLine()) {
			fileContents = fileContents.concat(fileScanner.nextLine() + "\n");
		}
		
		fileScanner.close();
		String[] fileContentArray = fileContents.split("\n");
		return fileContentArray[(int) (Math.random() * fileContentArray.length)];
		
	}
		
	
}
