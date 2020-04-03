package eg.edu.alexu.csd.datastructure.stack;


public class ExpressionEvaluator implements IExpressionEvaluator{
	/**
	 *Takes a symbolic/numeric infix expression as input and converts it to
	 * postfix notation. There is no assumption on spaces between terms or the
	 * length of the term (e.g., two digits symbolic or numeric term)
	 * @param expression
	 * @return String expression (postfix expression)
	 */
	public String infixToPostfix(String expression) {
		if(expression == null) throw new RuntimeException("Invalid input!");
		expression = expression.trim();
		if(expression.length() == 0) throw new RuntimeException("Invalid input!");
		Stack newExpression = new Stack();
		Stack holder2 = new Stack();
		Stack holder = stringToStack(expression);
		while(!holder.isEmpty()) {
			holder2.push(holder.pop());
		}
		String trial = new String("");
		while(!holder2.isEmpty())
		{
			trial += holder2.pop();
		}
		int length = trial.length();
		int i=0;
		while(i < length-1)
		{
			if((trial.charAt(i) == '+' || trial.charAt(i) == '/' || trial.charAt(i) == '*' || trial.charAt(i) == '-')
					&& (trial.charAt(i+1) == '+' || trial.charAt(i+1) == '/' || trial.charAt(i+1) == '*' || trial.charAt(i+1) == '-')) {
				throw new RuntimeException("INVALID INPUT!");
			}
			if(trial.charAt(i) == '(' && (trial.charAt(i+1) == '*' || trial.charAt(i+1) == '/')) throw new RuntimeException("INVALID INPUT!");
			i++;
		}
		if(trial.charAt(length-1) == '*' || trial.charAt(length-1) == '/' || trial.charAt(length-1) == '-' || trial.charAt(length-1) == '+') {

			throw new RuntimeException("Invalid input!");
		}
		holder = stringToStack(expression);
		int counter = holder.size();
		String answer = new String("");
		while(counter > 0)
		{
			newExpression.push(holder.pop());
			counter--;
		}
		counter = newExpression.size();
		Stack helper = new Stack();
		while(counter > 0)
		{
			boolean isNum = true;
			boolean isLetter = true;
			String str = String.valueOf(newExpression.pop());
			try {
				Integer.parseInt(str);
			}catch(Exception e)
			{
				isNum = false;
			}
			if(!Character.isAlphabetic(str.charAt(0))) isLetter = false;

			if(!isNum && !isLetter)
			{
				if(str.equals(String.valueOf('('))) helper.push(str);
				else if(str.equals(String.valueOf(')'))) {
					while(!helper.isEmpty() && (!helper.peek().equals(String.valueOf('(')))) {
						answer += helper.pop();
						answer += ' ';
					}
					if(!helper.isEmpty() && (!helper.peek().equals(String.valueOf('(')))) {
						throw new RuntimeException("Invalid input!");
					}else helper.pop();
				}
				else {
					while(!helper.isEmpty() && checkPriority(String.valueOf(str)) <= checkPriority(String.valueOf(helper.peek()))) {
						if(helper.peek().equals(String.valueOf('('))) throw new RuntimeException("Invalid input!");
						 answer += helper.pop();
						 answer += ' ';
					}
					helper.push(String.valueOf(str));
				}
			}
			else {
				answer += str;
				answer += ' ';
			}
			counter--;
		}
		counter = helper.size();
		while(counter > 0)
		{
			if(helper.peek().equals(String.valueOf('(')))throw new RuntimeException("Invalid input!");
			answer+=helper.pop();
			answer+=' ';
			counter--;
		}
		answer = answer.trim();
		return answer;
	}
	/**
	 *Evaluate a postfix numeric expression, with a single space separator
	 *@param expression
	 *@return int result (integer resultant of the postfix expression)
	 */
	public int evaluate(String expression) {
		int numOperators=0, numOperands=0;
		if(expression == null) throw new RuntimeException("Invalid input!");
		expression = expression.trim();
		if(expression.length() == 0) throw new RuntimeException("Invalid input!");
		float firstOperand, secondOperand;
		int i=0, from;
		int length = expression.length();
		String infixExpression = expression;
		while(i < length)
		{
			if(infixExpression.charAt(i) != '*' && infixExpression.charAt(i) != '/' && infixExpression.charAt(i) != '+'
					 && infixExpression.charAt(i) != '-' && infixExpression.charAt(i) != '('
					 && infixExpression.charAt(i) != ')' && infixExpression.charAt(i) != '*' && !Character.isDigit(infixExpression.charAt(i))
					 && infixExpression.charAt(i) != ' ' && infixExpression.charAt(i) != '\t')
			{
				throw new RuntimeException("Invalid input!");
			}
			if(Character.isDigit(infixExpression.charAt(i))){
				numOperands++;
				while(i < length && Character.isDigit(infixExpression.charAt(i))) {
					i++;
				}
			}else if(infixExpression.charAt(i) != ' ' && infixExpression.charAt(i) != ')' && infixExpression.charAt(i) != '(' 
					&& infixExpression.charAt(i) != '\t')
			{
				numOperators++;
			}
			i++;
		}
		if(!(numOperators < numOperands)) throw new RuntimeException("Invalid input!");
		i=0;
		Stack workPlace = new Stack();
		while(i < length)
		{
			if(Character.isDigit(expression.charAt(i))){
				from = i;
				while(i < length && expression.charAt(i) != ' ' && expression.charAt(i) != '\t')
				{
					i++;
				}
				workPlace.push(String.valueOf(expression.substring(from, i)));
			}else if(expression.charAt(i) != ' ' && infixExpression.charAt(i) != '\t'){
				if(workPlace.isEmpty()) throw new RuntimeException("Invalid input!");
				secondOperand = Float.parseFloat((String) workPlace.pop());
				if(workPlace.isEmpty()) throw new RuntimeException("Invalid input!");
				firstOperand = Float.parseFloat((String) workPlace.pop());
				switch(expression.charAt(i)) {
				case '+':
					workPlace.push(String.valueOf(firstOperand+secondOperand));
					break;
				case '-':
					workPlace.push(String.valueOf(firstOperand-secondOperand));
					break;
				case '*':
					workPlace.push(String.valueOf(firstOperand*secondOperand));
					break;
				case '/':
					if(secondOperand == 0) throw new RuntimeException("DIVISION BY ZERO!");
					workPlace.push(String.valueOf(firstOperand/secondOperand));
					break;
				}
			}
			i++;
		}
		if(workPlace.size() != 1) throw new RuntimeException("INVALID INPUT!");
		return (int)Float.parseFloat((String)workPlace.pop());
	}
	
	/**
	 * checks for the priority of the operators where multiplication and division have
	 * higher priorities than addition and subtraction
	 * @param input
	 * @return (integer value indicating the priority)
	 */
	public int checkPriority(String input)
	{
		if(input.equals(String.valueOf('*')) || input.equals(String.valueOf('/'))) return 2;
		else if(input.equals(String.valueOf('+')) || input.equals(String.valueOf('-'))) return 1;
		else return 0;
	}

	/**
	 * takes a string input and converts it to a stack.
	 * used in the infixToPostfix function
	 * @param input
	 * @return Stack which contains the components of the string
	 */
	public Stack stringToStack(String input)
	{
		int sizeString = input.length();
		String infixExpression = input;
		boolean twos = false;
		boolean looped = false;
		int i=0;
		while(i < sizeString)
		{
			if(infixExpression.charAt(i) != '*' && infixExpression.charAt(i) != '/' && infixExpression.charAt(i) != '+'
					 && infixExpression.charAt(i) != '-' && infixExpression.charAt(i) != '('
					 && infixExpression.charAt(i) != ')' && infixExpression.charAt(i) != '*' && !Character.isDigit(infixExpression.charAt(i))
					 && infixExpression.charAt(i) != ' ' && !Character.isAlphabetic(infixExpression.charAt(i)) && infixExpression.charAt(i) != '\t')
			{
				throw new RuntimeException("Invalid input!");
			}
			if((Character.isDigit(infixExpression.charAt(i)) || Character.isAlphabetic(infixExpression.charAt(i))) && !twos) {
				twos = true;
				if(i < sizeString && Character.isDigit(infixExpression.charAt(i))) {
					while(i < sizeString && Character.isDigit(infixExpression.charAt(i))) {
						i++;
						looped = true;
					}
				}
				else if(i < sizeString && Character.isAlphabetic(infixExpression.charAt(i))) {
					i++;
					looped = true;
				}
			}
			if(i < sizeString && !Character.isDigit(infixExpression.charAt(i)) && !Character.isAlphabetic(infixExpression.charAt(i)) 
					&& infixExpression.charAt(i) != '(' 
					&& infixExpression.charAt(i) != ')'
					&& infixExpression.charAt(i) != ' ') twos = false;
			if(i < sizeString && Character.isDigit(infixExpression.charAt(i)) && twos) throw new RuntimeException("INVALID INPUT!");
			if(i < sizeString && Character.isAlphabetic(infixExpression.charAt(i)) && twos) throw new RuntimeException("INVALID INPUT!");

			if(looped) looped = false;
			else i++;
		}
		input = input.trim();
		input = input.replaceAll("\\s", "");
		boolean negative = false;
		boolean negativeBracket = false;
		Stack holder = new Stack();
		int from, length = input.length();
		i=0;
		while(i < length) {
			if(i==0 && (input.charAt(i) == '-' || input.charAt(i) == '+' )) {
				holder.push(String.valueOf('0'));
				holder.push(String.valueOf(input.charAt(i)));
				i++;
			}
			if(i==0 && (input.charAt(i) == '*' || input.charAt(i) == '/' )) throw new RuntimeException("INVALID INPUT!");
			else if(i <= length-2 && i !=0 &&(input.charAt(i-1) == '(' || input.charAt(i-1) == '*' || input.charAt(i-1) == '/' || input.charAt(i-1) == '+')
					&& (input.charAt(i) == '-' || input.charAt(i) == '+')
					&& (Character.isAlphabetic(input.charAt(i+1)) || Character.isDigit(input.charAt(i+1)))) {
				holder.push(String.valueOf('('));
				holder.push(String.valueOf('0'));
				holder.push(String.valueOf(input.charAt(i)));
				negative = true;
				i++;
			}else if(i != 0 && (input.charAt(i-1) == '(' || input.charAt(i-1) == '+' || input.charAt(i-1) == '-' || input.charAt(i-1) == '*'
					|| input.charAt(i-1) == '/')
					&& (input.charAt(i) == '-' || input.charAt(i) == '+') && input.charAt(i+1) == '(') {
				negativeBracket = true;
				holder.push('(');
				holder.push(0);
				holder.push(input.charAt(i));
				i++;
			}
			if(Character.isDigit(input.charAt(i))) {
				from = i;
				while(i <input.length() && Character.isDigit(input.charAt(i))) i++;
				holder.push(input.substring(from, i));
				if(negative) {
					holder.push(String.valueOf(')'));
					negative = false;
				}
			}
			if(i < input.length() && Character.isAlphabetic(input.charAt(i))) {
				holder.push(String.valueOf(input.charAt(i)));
				if(negative){
					holder.push(String.valueOf(')'));
					negative = false;
				}
			}
			if(i <input.length() && input.charAt(i) != ' ' && !Character.isAlphabetic(input.charAt(i))) {
				holder.push(input.charAt(i));
				if(input.charAt(i) == ')' && negativeBracket)
				{
					holder.push(')');
					negativeBracket = false;
				}
			}

			i++;
		}
		return holder;
	}
}
