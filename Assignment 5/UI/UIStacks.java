package eg.edu.alexu.csd.datastructure.stack;

import java.util.Scanner;

public class UIStacks {

	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		Stack mainStack = new Stack();
		while(true) {
			String input = new String();
			int choice = 0;
			System.out.println("Choose operation: ");
			System.out.println("1: Push\r\n" + 
					"2: Pop\r\n" + 
					"3: Peek\r\n" + 
					"4: Get size\r\n" + 
					"5: Check if empty");
			try {
				choice = userInput.nextInt();
			}catch(Exception e)
			{
				System.out.println("INVALID INPUT!");
			}
			try {
				switch(choice) {
				case 1:
					System.out.println("Enter element to be pushed: ");
					input = userInput.next();
					mainStack.push(input);
					break;
				case 2:
					System.out.println("Element poppped: " + mainStack.pop());
					break;
				case 3:
					System.out.println("Top of the stack: " + mainStack.peek());
					break;
				case 4:
					System.out.println("Size of stack: " + mainStack.size());
					break;
				case 5:
					if(!mainStack.isEmpty()) System.out.println("Stack is not empty");
					else System.out.println("Stack is empty");
					break;
				default:
					throw new RuntimeException("INVALID INPUT!");	
				}
			}catch(Exception e)
			{
				System.out.println("Invalid operation!");
			}
		}
		
		}
}
