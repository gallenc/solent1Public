/**
 * 
 */
package org.solent.mad;

import java.util.Scanner;

/**
 * @author admin
 * If you try to access a member of an array outside the array bounds, a 
 * built-in exception type, the ArrayIndexOutOfBoundsException, will be thrown. 
 * Write a small test class, containing only a main() , 
 * which creates an array of the 12 months of the year (as Strings) 
 * and asks the user to input a month number. 
 * Your code should display the month in the array corresponding to the 
 * index that the user entered minus one (minus one because months
 * are numbered from 1 but array indices are numbered from 0).
 * Run the program, nputting an index which will look up a value outside the array bounds 
 * (i.e. less than 1 or greater than 12). What happens?
 * Now rewrite your program to catch the exception and provide a more 
 * user-friendly error message if an invalid month number is entered.
 */
public class ArrayBounds1 {

	private static final String[] MONTHS = {
			"January",
			"February",
			"March",
			"April",
			"May",
			"June",
			"July",
			"August",
			"September",
			"October",
			"November",
			"December"
	};
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Integer month=null;
		// keep asking for a valid input
		Scanner scanner=new Scanner(System.in);
		do {
			System.out.println("enter month number between 1 and 12: ");
			String monthNoStr=null;
			monthNoStr = scanner.nextLine();
			try {
				month = Integer.parseInt(monthNoStr);
			} catch(NumberFormatException nfe) {
				System.out.println("cannot parse input string "+monthNoStr+ "as Integer");
			} 
		} while (month==null || month<1 || month >12);
		System.out.println("Selected month number "+month
				+ " = "+MONTHS[month-1]);
		System.out.println("END OF PROGRAM");
		scanner.close();
	}

}
