package eg.edu.alexu.csd.datastructure.stack;

import java.util.Scanner;

public class UIExpressionEvaluator {
	public static void main(String[] args) {
		ExpressionEvaluator calc = new ExpressionEvaluator();
		try {
			Scanner userInput = new Scanner(System.in);
			System.out.println("Enter your choice: \n"
					+ "1) from Infix to Postfix only [Works with numbers and letters only]\n"
					+ "2) from Postfix to Evaluate only [Works with numbers only]\n"
					+ "3) from Infix to Postfix and Evaluate [Works with numbers only to be able to return a result]");
			int choice = userInput.nextInt();
			userInput.nextLine();
			switch(choice) {
			case 1:
				System.out.println("Enter infix expression: ");
				String str1 = userInput.nextLine();
				System.out.println("The postfix expression: "+calc.infixToPostfix(str1));
				break;
			case 2:
				System.out.println("Enter postfix expression: ");
				String str2 = userInput.nextLine();
				System.out.println("The result: " + calc.evaluate(str2));
				break;
			case 3:
				System.out.println("Enter infix Expression: ");
				String str3 = userInput.nextLine();
				System.out.println("The postfix expression: "+calc.infixToPostfix(str3));
				System.out.println("The result: " + calc.evaluate(calc.infixToPostfix(str3)));
				break;
			default:
				System.out.println("Invalid choice!");
			}
			userInput.close();
		}catch(Exception e)
		{
			System.out.println("Invalid input!");
		}

	}

}
