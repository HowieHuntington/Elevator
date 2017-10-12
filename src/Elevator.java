/* 
 * Robert Huntington
 * Data Strutures Assignment 2
 * 
 * The purpose of this program is to simulate the way an elevator operates for a building containing 12 floors.
 * There are two main functions of the elevator, those functions being that the elevator can either go up or down.
 * The elevator always starts at the first floor.
 * Initially, all of the floors that will be stopped at are randomly generated into two array lists (one for ascending,
 * one for descending), and displayed. 
 * Eight floors are generated for the up cue, five for the down cue.  
 * Once all floors on the ascending cue are reached, the elevator will descend and stop at floors on the way down.
 * While the elevator is in motion, and there are still floors to be stopped at on the up or down cue, the elevator cannot
 * change directions until the last floor on the cue is visited (similar to a real elevator).  
 * At each stop, the user is prompted to either add a new floor to the cue, or to proceed to the next floor on the list.
 * Floors that are already on the cue cannot be repeated and floors below the current floor cannot be added for the up cue, nor 
 * can floors above the current floor be added for the down cue.   
 * The elevator program displays the current floor, whether those floors are to be stopped at or not,
 * as well as the direction the elevator is moving.  
 * Once the last floor on the descending cue is reached, the user will be prompted to either ride the elevator again, or 
 * terminate the program.  
 * 
 * The program contains one class named Elevator, from which the entire program is executed.
 * This class contains one Main method, and six instance methods.  Two of these methods are sleep methods
 * to create time delays during method execution to simulate the time it takes to move from one floor to the next
 * on the elevator.  
 * The other four methods contain the data structures, algorithms, and logic used to make the program run as intended to.
 * 
 * Methods Used: Main Method, elevatorUp, elevatorDown, goingUp, goingDown, longSleep, shortSleep.
 * 
 * Solution:
 *  -Main Method:
 *   Creates two new Array List objects called 'upList' and 'downList'. 
 *   These are set equal to the data structure methods'elevatorUp' and 'elevatorDown' which generate the floors
 *   the elevator will be stopped at by randomly populating array lists with floors ranging between 1-12. 
 *   Prints 'upList'.
 *   Calls 'goingUp'.
 *   Prints 'downList'.
 *   Calls 'goingDown'.
 *   Repeats the program while user input is 'y'.
 *   
 */


import java.util.*;

public class Elevator {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String repeat = "";

		do {

			ArrayList<Integer> upList = elevatorUp();
			ArrayList<Integer> downList = elevatorDown();
			System.out.println("Elevator ascending cue: " + upList.toString());
			goingUp(upList);
			System.out.println();
			System.out.println("Elevator descending cue: " + downList.toString());
			goingDown(downList, upList);
			System.out.println("Would you like to run the elevator again? (type 'y' or 'n' and press return.");
			repeat = sc.nextLine();
			
		} while (repeat.equals("y"));
			
			System.out.println();
			System.out.println("Thanks For Riding the Elevator");

	}

	/*
	 * This is a method to create the cue for floors stopped at while ascending in the elevator.
	 * These floors are integers that are stored in an Array List 'ar'.
	 * Floors for the 'elevatorUp' method are randomly generated using a Random object ranging int's from 2-12.
	 * An int from the Random object is added to the array list WHILE the array list is less than size 8, and 
	 * only IF the list DOES NOT contain the int already as to eliminate the chance of duplicates.
	 * The array list is sorted from least to greatest using the Collections class.
	 * 
	 * Postcondition: The method then returns the sorted array list of floors to be stopped at.
	 * 
	 */
	
	public static ArrayList<Integer> elevatorUp() {
		ArrayList<Integer> ar = new ArrayList<Integer>();
		Random n = new Random();
		int input = n.nextInt(11) + 2;
		ar.add(input);
		while (ar.size() < 8) {
			input = n.nextInt(11) + 2;
			if (!ar.contains(input)) {
				ar.add(input);
			}
		}
		Collections.sort(ar);
		return ar;
	}
	
	
	/*
	 * This is a method to create the cue for floors stopped at while descending in the elevator.
	 * These floors are integers that are stored in an Array List 'ar'.
	 * Floors for the 'elevatorDown' method are randomly generated using a Random object ranging int's from 1-12.
	 * An int from the Random object is added to the array list WHILE the array list is less than size 5, and 
	 * only IF the list DOES NOT contain the int already as to eliminate the chance of duplicates.
	 * The array list is sorted from greatest to least using the Collections class.
	 * 
	 * Postcondition: The method then returns the sorted (greatest to least) array list of floors to be stopped at.
	 * 
	 */

	public static ArrayList<Integer> elevatorDown(){
		ArrayList<Integer> ar = new ArrayList<Integer>();
		Random n = new Random();
		int input = n.nextInt(11) + 2;
		while (ar.size() < 5) {

			if (!ar.contains(input)) {
				ar.add(input);
			}

			input = n.nextInt(11) + 2;

		}
		Collections.sort(ar, Collections.reverseOrder());
		return ar;

	}

	/*
	 * This method if the functional piece of the ascending elevator.  It contains the algorithms used to effectively 
	 * simulate the elevator passing each floor in between stops.  To do this I used a nested for/while loop.
	 * The shortSleep method is called within the while loop to simulate the elevator moving between floors.
	 * When a value of the array list is reached the longSleep method is called to simulate the elevator stopping and 
	 * people entering and exiting the elevator.
	 * It also prompts the user at each stop to add a floor to the cue or to continue riding to the next listed stop.
	 * The cell of the floor is then removed from the array list.  
	 * 
	 * Preconditions: sorted array list called upList
	 * Postcondition: elements of upList and the integers that are between values at each index 
	 * Invariant: upList array list
	 */
	public static void goingUp(ArrayList<Integer> upList) {
		Scanner sc = new Scanner(System.in);
		int f = upList.get(upList.size() - 1);
		int counter = 1;
		int secondTL = upList.get(upList.size() - 2);
		String add = "";
		String up = " ";

		System.out.println();
		System.out.println("Starting at floor 1");
		System.out.println();

		while (!up.equals("")) {
			System.out.println("Press return key to start elevator.");
			up = sc.nextLine();
		}

		for (int i = 0; i < upList.size() - 1; i++) {
			while (f > 1) {
				shortSleep();
				System.out.println("^");
				System.out.println("|");
				shortSleep();
				System.out.println("^");
				System.out.println("|");
				shortSleep();
				System.out.println("^");
				System.out.println("|");
				System.out.println();
				System.out.print("Floor " + (counter + 1));
				System.out.println();
				f = upList.get(i) - counter;
				counter++;
				System.out.println();
			}

			System.out.print("Stopping at floor " + upList.get(i) + " for ");
			longSleep();
			System.out.print("1 ");
			longSleep();
			System.out.print("2 ");
			longSleep();
			System.out.println("3 ");
			counter = upList.get(i);
			f = counter;

			System.out
					.println("****Add a floor to the 'going up' cue or press enter to continue without new stops.****");
			add = sc.nextLine();

			try {
				if (upList.contains(Integer.parseInt(add))) {
					System.out.println("Floor " + add + " is already selected.");
				}else

				if (Integer.parseInt(add) > upList.get(0) && Integer.parseInt(add) <= 12
						&& Integer.parseInt(add) > upList.get(i)) {
					upList.add(Integer.parseInt(add));
				} else {
					System.out.println("Going up, cannot add your floor to the cue");
				}

			}

			catch (NumberFormatException e) {

			}
			Collections.sort(upList);
			upList.remove(i);
			i -= 1;
		}
		if (secondTL + 1 == upList.get(0)) {

		} else {
			for (int j = secondTL + 1; j < upList.get(0); j++) {
				shortSleep();
				System.out.println("^");
				System.out.println("|");
				shortSleep();
				System.out.println("^");
				System.out.println("|");
				shortSleep();
				System.out.println("^");
				System.out.println("|");
				System.out.println();
				System.out.println("Floor " + j);
				System.out.println();
			}
		}
		
		shortSleep();
		System.out.println("^");
		System.out.println("|");
		shortSleep();
		System.out.println("^");
		System.out.println("|");
		shortSleep();
		System.out.println("^");
		System.out.println("|");
		System.out.println();

		System.out.println("Floor " + upList.get(upList.size() - 1) + "    ....(final ascending stop)");
		System.out.println();
		System.out.println();
	}

	/*
	 * This method if the functional piece of the descending elevator.  It contains the algorithms used to effectively 
	 * simulate the elevator passing each floor in between stops.  To do this I used a nested for/while loop.
	 * The conditions of the for/while loop are different to account for a reversed sorted array list.  
	 * The shortSleep method is called within the while loop to simulate the elevator moving between floors.
	 * When a value of the array list is reached the longSleep method is called to simulate the elevator stopping and 
	 * people entering and exiting the elevator.
	 * It also prompts the user at each stop to add a floor to the cue or to continue riding to the next listed stop.
	 * The cell of the floor is then removed from the array list.  
	 * 
	 * Preconditions: sorted array list called upList, sorted reversed array list called downList. 
	 * Postcondition: elements of downList and the integers that are between values at each index 
	 * Invariant: downList array list
	 * 
	 */
	
	public static void goingDown(ArrayList<Integer> downList, ArrayList<Integer> upList) {
		Scanner sc = new Scanner(System.in);
		int f = downList.get(0);
		int counter = f;
		int secondTL = downList.get(downList.size() - 2);
		String add = "";
		String down = "  ";
		// System.out.println("Elevator descending cue: " + downList + "\n");
		System.out.println("Starting at floor " + downList.get(0) + "\n");

		while (!down.equals("")) {
			System.out.println("Press return key to start elevator.");
			down = sc.nextLine();
		}

		for (int i = 0; i < downList.size() - 1; i++) {
			while (f > downList.get(i)) {
				shortSleep();
				System.out.println("|");
				System.out.println("v");
				shortSleep();
				System.out.println("|");
				System.out.println("v");
				shortSleep();
				System.out.println("|");
				System.out.println("v");
				System.out.println();
				System.out.println("Floor " + (counter - 1));
				f--;
				counter--;
				System.out.println();
			}

			if (i > 0) {
				System.out.print("Stopping at floor " + downList.get(i) + " for ");
				longSleep();
				System.out.print("1 ");
				longSleep();
				System.out.print("2 ");
				longSleep();
				System.out.println("3 ");
				counter = downList.get(i);
				f = counter;

				System.out.println(
						"****Add a floor to the 'going down' cue or press enter to continue without new stops.****");
				add = sc.nextLine();

				try {

					if (downList.contains(Integer.parseInt(add))) {
						System.out.println("Floor " + add + " is already selected.");
					}else

					if (Integer.parseInt(add) > downList.get(0) && Integer.parseInt(add) <= 12
							&& Integer.parseInt(add) > downList.get(i)) {
						downList.add(Integer.parseInt(add));
					} else {
						System.out.println("Going down, cannot add your floor to the cue");
					}

				}

				catch (NumberFormatException e) {

				}

				Collections.sort(downList, Collections.reverseOrder());
				downList.remove(0);
				i -= 1;
			}

		}

		if (secondTL - 1 == downList.get(downList.size() - 1)) {

		} else {
			for (int j = secondTL - 1; j > downList.get(downList.size() - 1); j--) {
				shortSleep();
				System.out.println("|");
				System.out.println("v");
				shortSleep();
				System.out.println("|");
				System.out.println("v");
				shortSleep();
				System.out.println("|");
				System.out.println("v");
				System.out.println();
				System.out.println("Floor " + j);
				System.out.println();
			}
		}
		shortSleep();
		System.out.println("|");
		System.out.println("v");
		shortSleep();
		System.out.println("|");
		System.out.println("v");
		shortSleep();
		System.out.println("|");
		System.out.println("v");
		System.out.println();
		System.out.println("Floor " + downList.get(downList.size() - 1) + "    ....(final descending stop)");
		System.out.println();
		System.out.println();
	}

	/*
	 * A method to create a 3 second delay before finishing its execution.
	 */
	public static void longSleep() {
		try {
			Thread.sleep(1000L);
		} catch (Exception e) {

		}
	}
	
	/*
	 * A method to create a 2 second delay before finishing its execution.
	 */
	public static void shortSleep() {
		try {
			Thread.sleep(666L);
		} catch (Exception e) {

		}
	}

}
