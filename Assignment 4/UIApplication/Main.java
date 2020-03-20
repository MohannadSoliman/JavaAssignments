package eg.edu.alexu.csd.datastructure.linkedList;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        PolyOperations operator = new PolyOperations();
        Scanner userInput = new Scanner(System.in);
        while(true) {
            System.out.println(
                    "Please choose an action\r\n" +
                            "-----------------------\r\n" +
                            "1- Set a polynomial variable\r\n" +
                            "2- Print the value of a polynomial variable\r\n" +
                            "3- Add two polynomials\r\n" +
                            "4- Subtract two polynomials\r\n" +
                            "5- Multiply two polynomials\n"+
                            "6- Evaluate a polynomial at some point\r\n" +
                            "7- Clear a polynomial variable\r\n" +
                            "===================================================================="
            );
            int mainMenuChoice = userInput.nextInt();
            if(mainMenuChoice == 1)
            {
                System.out.println("Insert the variable name: A, B or C");
                String inputHolder = userInput.next();
                char polynomialVar = inputHolder.charAt(0);
                if(polynomialVar != 'A' && polynomialVar != 'B' && polynomialVar != 'C' && polynomialVar != 'a' && polynomialVar != 'b' && polynomialVar != 'c') {
                    throw new Exception("Invalid input!");
                }
                System.out.println(
                        "Insert the polynomial terms in the form:\n" +
                                "(coeff1, exponent1), (coeff2, exponent2), ..");
                String garbage = userInput.nextLine();
                String expression = userInput.nextLine();
                //operator.convertStringIntoArray(expression);
                //-------------------------> don't forget to put a way to fill the array of polynomial using expression <---------------------
                //here I used these values just to test the program
                int [][] poly = operator.convertStringIntoArray(expression);
                operator.setPolynomial(polynomialVar, poly);
                System.out.println("Polynomial "+polynomialVar+" is set");
                //////////////////////////////////////////////////////////////////////////////////////////////////
            }else if(mainMenuChoice == 2)
            {

                System.out.println("Insert the variable name: A, B, C or R");
                String inputHolder = userInput.next();
                char polynomialVar = inputHolder.charAt(0);
                if((polynomialVar == 'A' || polynomialVar == 'a') && !operator.A.isEmpty()) System.out.println(operator.print(polynomialVar));
                else if((polynomialVar == 'B' || polynomialVar == 'b') && !operator.B.isEmpty()) System.out.println(operator.print(polynomialVar));
                else if((polynomialVar == 'C' || polynomialVar == 'c') && !operator.C.isEmpty()) System.out.println(operator.print(polynomialVar));
                else if((polynomialVar == 'R' || polynomialVar == 'r') && !operator.R.isEmpty()) System.out.println(operator.print(polynomialVar));
                else throw new Exception("Invalid input!");
                ///////////////////////////////////////////////////////////////////////////////////////////////////
            }else if(mainMenuChoice == 3)
            {
                char polynomialVar1 = 0, polynomialVar2 = 0;
                boolean operandValid = false;
                while(!operandValid)
                {
                    System.out.println("Insert first operand variable name: A, B or C");
                    String inputHolder = userInput.next();
                    polynomialVar1 = inputHolder.charAt(0);
                    if(polynomialVar1 != 'A' && polynomialVar1 != 'B' && polynomialVar1 != 'C' && polynomialVar1 != 'a' && polynomialVar1 != 'b' && polynomialVar1 != 'c') {
                        throw new Exception("Invalid input!");
                    }else if(operator.selectPoly(polynomialVar1).isEmpty())
                    {
                        System.out.println("Variable not set");
                    }else
                    {
                        operandValid = true;
                    }
                }
                operandValid = false;
                while(!operandValid)
                {
                    System.out.println("Insert second operand variable name: A, B or C");
                    String inputHolder = userInput.next();
                    polynomialVar2 = inputHolder.charAt(0);
                    if(polynomialVar2 != 'A' && polynomialVar2 != 'B' && polynomialVar2 != 'C' && polynomialVar2 != 'a' && polynomialVar2 != 'b' && polynomialVar2 != 'c') {
                        throw new Exception("Invalid input!");
                    }else if(operator.selectPoly(polynomialVar2).isEmpty())
                    {
                        System.out.println("Variable not set");
                    }else
                    {
                        operandValid = true;
                    }
                }
                operator.add(polynomialVar1, polynomialVar2);
                System.out.println("Result set in R:" + operator.printResult('R'));
                ///////////////////////////////////////////////////////////////////////////////////////////////////
            }else if(mainMenuChoice == 4)
            {
                char polynomialVar1 = 0, polynomialVar2 = 0;
                boolean operandValid = false;
                while(!operandValid)
                {
                    System.out.println("Insert first operand variable name: A, B or C");
                    String inputHolder = userInput.next();
                    polynomialVar1 = inputHolder.charAt(0);
                    if(polynomialVar1 != 'A' && polynomialVar1 != 'B' && polynomialVar1 != 'C' && polynomialVar1 != 'a' && polynomialVar1 != 'b' && polynomialVar1 != 'c') {
                        throw new Exception("Invalid input!");
                    }else if(operator.selectPoly(polynomialVar1).isEmpty())
                    {
                        System.out.println("Variable not set");
                    }else
                    {
                        operandValid = true;
                    }
                }
                operandValid = false;
                while(!operandValid)
                {
                    System.out.println("Insert second operand variable name: A, B or C");
                    String inputHolder = userInput.next();
                    polynomialVar2 = inputHolder.charAt(0);
                    if(polynomialVar2 != 'A' && polynomialVar2 != 'B' && polynomialVar2 != 'C' && polynomialVar2 != 'a' && polynomialVar2 != 'b' && polynomialVar2 != 'c') {
                        throw new Exception("Invalid input!");
                    }else if(operator.selectPoly(polynomialVar2).isEmpty())
                    {
                        System.out.println("Variable not set");
                    }else
                    {
                        operandValid = true;
                    }
                }
                operator.subtract(polynomialVar1, polynomialVar2);
                System.out.println("Result set in R:" + operator.printResult('R'));
                ///////////////////////////////////////////////////////////////////////////////////////////////////
            }else if(mainMenuChoice == 5)
            {
                char polynomialVar1 = 0, polynomialVar2 = 0;
                boolean operandValid = false;
                while(!operandValid)
                {
                    System.out.println("Insert first operand variable name: A, B or C");
                    String inputHolder = userInput.next();
                    polynomialVar1 = inputHolder.charAt(0);
                    if(polynomialVar1 != 'A' && polynomialVar1 != 'B' && polynomialVar1 != 'C' && polynomialVar1 != 'a' && polynomialVar1 != 'b' && polynomialVar1 != 'c') {
                        throw new Exception("Invalid input!");
                    }else if(operator.selectPoly(polynomialVar1).isEmpty())
                    {
                        System.out.println("Variable not set");
                    }else
                    {
                        operandValid = true;
                    }
                }
                operandValid = false;
                while(!operandValid)
                {
                    System.out.println("Insert second operand variable name: A, B or C");
                    String inputHolder = userInput.next();
                    polynomialVar2 = inputHolder.charAt(0);
                    if(polynomialVar2 != 'A' && polynomialVar2 != 'B' && polynomialVar2 != 'C' && polynomialVar2 != 'a' && polynomialVar2 != 'b' && polynomialVar2 != 'c') {
                        throw new Exception("Invalid input!");
                    }else if(operator.selectPoly(polynomialVar2).isEmpty())
                    {
                        System.out.println("Variable not set");
                    }else
                    {
                        operandValid = true;
                    }
                }
                operator.multiply(polynomialVar1, polynomialVar2);
                System.out.println("Result set in R:" + operator.printResult('R'));
                ///////////////////////////////////////////////////////////////////////////////////////////////////
            }else if(mainMenuChoice == 6)
            {
                char polynomialVar;
                while(true){
                    System.out.println("Insert variable to be evaluated");
                    String inputHolder = userInput.next();
                    polynomialVar = inputHolder.charAt(0);
                    if(polynomialVar != 'A' && polynomialVar != 'B' && polynomialVar != 'C' && polynomialVar != 'a' && polynomialVar != 'b' && polynomialVar != 'c') {
                        throw new Exception("Invalid input !");
                    }else if(operator.selectPoly(polynomialVar).isEmpty())
                    {
                        System.out.println("Variable not set");
                    }else
                    {
                        break;
                    }
                }
                System.out.println("Enter value");
                int input = userInput.nextInt();
                System.out.println("The Result = " + operator.evaluatePolynomial(polynomialVar, input));
                ///////////////////////////////////////////////////////////////////////////////////////////////////
            }else if(mainMenuChoice == 7)
            {
                char polynomialVar;
                while(true){
                    System.out.println("Insert variable to be cleared");
                    String inputHolder = userInput.next();
                    polynomialVar = inputHolder.charAt(0);
                    if(polynomialVar != 'A' && polynomialVar != 'B' && polynomialVar != 'C' && polynomialVar != 'a' && polynomialVar != 'b' && polynomialVar != 'c') {
                        throw new Exception("Invalid input !");
                    }else if(operator.selectPoly(polynomialVar).isEmpty())
                    {
                        System.out.println("Variable not set");
                    }else
                    {
                        break;
                    }
                }
                operator.clearPolynomial(polynomialVar);
                System.out.println("Variable "+ polynomialVar + "cleared");
            }else
            {
                throw new Exception("Invalid input!");
            }
        }
    }
}