package eg.edu.alexu.csd.datastructure.linkedList;

class Term {
    int coff;
    int exp;
    Term(int coff, int exp){
        this.coff = coff;
        this.exp = exp;
    }
}

public class PolyOperations implements IPolynomialSolver{
    SinglyLinkedList A = new SinglyLinkedList();
    SinglyLinkedList B = new SinglyLinkedList();
    SinglyLinkedList C = new SinglyLinkedList();
    SinglyLinkedList R = new SinglyLinkedList();

    public SinglyLinkedList selectPoly(char poly){
        if(poly == 'A' || poly == 'a') return A;
        else if(poly == 'B' || poly == 'b') return B;
        if(poly == 'C' || poly == 'c') return C;
        if(poly == 'R' || poly == 'r') return R;
        else throw new RuntimeException("invalid input");
    }
    /////////////////////////////////////////////////////////////////////////////
    public static void checkValidity(String input) throws Exception
    {
        int convexTrigger = 1;
        int numTrigger = 0;
        boolean sequence = false;
        boolean activated = false;
        for(int i=0; i<input.length(); i++)
        {
            if(input.charAt(i) != ' ' && input.charAt(i) != ',' && input.charAt(i) != '(' && input.charAt(i) != ')' && !Character.isDigit(input.charAt(i))) {
                throw new Exception("Invalid input !");
            }else if(convexTrigger == 1 && input.charAt(i) == ')')
            {
                throw new Exception("Invalid input!");
            }else if(convexTrigger == -1 && input.charAt(i) == ')') {
                convexTrigger *= -1;
            }else if(input.charAt(i) == '(' && convexTrigger == -1)
            {
                throw new Exception("Invalid input!");
            }else if(input.charAt(i) == '(' && convexTrigger == 1)
            {
                convexTrigger *= -1;
            }else if(convexTrigger == 1 && Character.isDigit(input.charAt(i)))
            {
                throw new Exception("Invalid input!");
            }
            if(convexTrigger == -1) {
                activated = true;
                if(Character.isDigit(input.charAt(i)) && Character.isDigit(input.charAt(i+1)) && sequence == false) {
                    numTrigger++;
                    sequence = true;
                    System.out.println("i: "+ i +" h1i"+numTrigger);
                }else if(Character.isDigit(input.charAt(i)) && !Character.isDigit(input.charAt(i+1)) && sequence == true) {
                    sequence = false;

                }else if(Character.isDigit(input.charAt(i)) && !Character.isDigit(input.charAt(i+1)) && sequence == false) {
                    numTrigger++;

                }
            }else if(convexTrigger == 1 && activated == true)
            {
                if(numTrigger != 2)
                {

                    throw new Exception("Invalid input!");
                }else
                {
                    numTrigger = 0;
                    activated = false;
                }
            }
        }
        if(convexTrigger == -1) throw new Exception("Invalid input!");
    }
    /////////////////////////////////////////////////////////////////////////////
    private int [][]convertLLIntoArray(SinglyLinkedList poly){
        int [][] output = new int[poly.size()][2];
        for(int i = 0; i < poly.size(); i++){
            output[i][0] = ((Term)poly.get(i)).coff;
            output[i][1] = ((Term)poly.get(i)).exp;
        }
        return output;
    }
    //////////////////////////////////////////////////////////////////////////////
    public int[][] convertStringIntoArray(String input) throws Exception {
        checkValidity(input);
        int len = 1;
        int i = 1;
        input = input.trim();
        char[] temp = input.toCharArray();
        char[] temp2 = new char[input.length()];
        temp2[0] = ',';
        ///////////////////////////////////////////////////////////////////////////////////
        for(int a = 0; a < temp.length; a++){
            if(a == 0 && temp[a] == ',') continue;
            if(temp[a] == ','){
                temp2[i++] = temp[a];
                len++;
                //a++;
                while (a < temp.length && (temp[a] == ',' || temp[a] ==' ')) a++;
                if(a == temp.length) len--;
                a--;
            }
            else {
                if(Character.isDigit(temp[a]) || temp[a] == '-'){temp2[i++] = temp[a]; len++;}
            }
        }
        //////////////////////////////////////////////////////////////////////////////////
        int [] numbers = new int[len];
        int b = 0;
        int j = 0;
        int number_len = 0;
        while(j < len){
            int a = 0;
            String num = "";
            if(temp2[j] == ','){
                j++;
                while(j < len && temp2[j] != ','){
                    if(temp2[j] == '-'){
                        while (temp2[j] == '-') {a++; j++;}
                        if(a % 2 != 0) num += "-";
                    }
                    num += temp2[j++];
                }
            }
            if(num != ""){
                numbers[b++] = Integer.parseInt(num);
                number_len++;
            }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////
        int[][] terms = new int[number_len/2][2];
        int c = 0;
        int d = 0;
        while (c < (number_len)){
            terms[d][0] = numbers[c++];
            terms[d][1] = numbers[c++];
            d++;
        }
        return terms;
    }
    /////////////////////////////////////////////////////////////////////////////
    public void setPolynomial(char poly, int[][] terms){
        int n = terms.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (terms[j][1] < terms[j+1][1]) {
                    int temp1 = terms[j][1];
                    int temp2 = terms[j][0];
                    terms[j][1] = terms[j+1][1];
                    terms[j][0] = terms[j+1][0];
                    terms[j+1][1] = temp1;
                    terms[j+1][0] = temp2;
                }

        for (int i = 0; i < n; i++)
            for (int j = i+1; j < n; j++)
                if(terms[j][1] == terms[i][1]){
                    terms[i][0] += terms[j][0];
                    for(int a = j; a < n-1; a++){
                        terms[a][0] = terms[a+1][0];
                        terms[a][1] = terms[a+1][1];
                    }
                    j--;
                    n--;
                }

        for(int i = 0; i < n; i++){
            if(terms[i][0] == 0){
                for(int a = i; a < n-1; a++){
                    terms[a][0] = terms[a+1][0];
                    terms[a][1] = terms[a+1][1];
                }
                i--;
                n--;
            }
        }
        if(n == 0){
            terms[0][0] = 0;
            terms[0][1] = 0;
            n++;
        }
        SinglyLinkedList polynomial = selectPoly(poly);
        polynomial.clear();
        for(int i = 0; i < n; i++){
            Term newTerm = new Term(terms[i][0],terms[i][1]);
            polynomial.add(newTerm);
        }
    }
    //////////////////////////////////////////////////////////////////////////
    public String print(char poly){
        SinglyLinkedList polynomial = selectPoly(poly);
        String output = "";
        for(int i = 0; i < polynomial.size(); i++){
            int coff = ((Term)polynomial.get(i)).coff;
            int exp = ((Term)polynomial.get(i)).exp;

            if(exp == 0 && coff > 0 ) output += "+" + String.valueOf(coff);
            else if(exp == 0 && coff == 0 ) output += String.valueOf(coff);
            else if(exp == 0) output += String.valueOf(coff);
            else if(exp == 1 && coff == 1) output += "+x";
            else if(exp == 1 && coff == -1) output += "-x";
            else if(exp == 1 && coff >= 0) output += "+" + String.valueOf(coff) + "x";
            else if(exp == 1) output += String.valueOf(coff) + "x";
            else if(coff == 1 && i == 0) output += "x^" + String.valueOf(exp);
            else if(coff == 1) output += "+x^" + String.valueOf(exp);
            else if(coff == -1) output += "-x^" + String.valueOf(exp);
            else if(coff > 0 && i != 0) output += "+" + String.valueOf(coff) + "x^" + String.valueOf(exp);
            else output += String.valueOf(coff) + "x^" + String.valueOf(exp);
        }
        return output;
    }
    ////////////////////////////////////////////////////////////////////////////
    public void clearPolynomial(char poly){
        SinglyLinkedList polynomial = selectPoly(poly);
        polynomial.clear();
    }
    ///////////////////////////////////////////////////////////////////////////
    public float evaluatePolynomial(char poly, float value){
        SinglyLinkedList polynomial = selectPoly(poly);
        float evaluation = 0;
        for(int i = 0; i < polynomial.size(); i++){
            int coff = ((Term)polynomial.get(i)).coff;
            int exp = ((Term)polynomial.get(i)).exp;
            evaluation += coff*(float)Math.pow(value, exp);
        }
        return evaluation;
    }
    ////////////////////////////////////////////////////////////////////////////
    public int[][] multiply(char poly1, char poly2){
        if((poly1 != 'R' && poly1 != 'r') &&  (poly2 != 'R' && poly2 != 'r')) R.clear();
        SinglyLinkedList poly_A = selectPoly(poly1);
        SinglyLinkedList poly_B = selectPoly(poly2);
        SinglyLinkedList temp = new SinglyLinkedList();
        int [][] result;
        int [][] output;
        for(int i = 0; i < poly_A.size(); i++){
            int coff_A = ((Term)poly_A.get(i)).coff;
            int exp_A = ((Term)poly_A.get(i)).exp;
            for(int j = 0; j < poly_B.size(); j++){
                int coff_B = ((Term)poly_B.get(j)).coff;
                int exp_B = ((Term)poly_B.get(j)).exp;
                int resultCoff = coff_A * coff_B;
                int resultExp = exp_A + exp_B;
                Term resultTerm = new Term(resultCoff, resultExp);
                temp.add(resultTerm);
            }
        }
        result = convertLLIntoArray(temp);
        R.clear();
        setPolynomial('R', result);
        result = convertLLIntoArray(R);
        return result;
    }
    ////////////////////////////////////////////////////////////////////////////
    public String printResult(char poly){
        SinglyLinkedList polynomial = selectPoly(poly);
        String output = "";
        for(int i = 0; i < polynomial.size(); i++){
            int coff = ((Term)polynomial.get(i)).coff;
            int exp = ((Term)polynomial.get(i)).exp;
            if(i == polynomial.size()-1)
                output += " (" + String.valueOf(coff) + " ," + String.valueOf(exp) + ")";
            else
                output += " (" + String.valueOf(coff) + " ," + String.valueOf(exp) + "),";

        }
        return output;
    }
    ///////////////////////////////////////////////////////////////////////////
    public int[][] add(char poly1, char poly2)
    {
        if((poly1 != 'R' && poly1 != 'r') &&  (poly2 != 'R' && poly2 != 'r')) R.clear();
        SinglyLinkedList poly_A = selectPoly(poly1);
        SinglyLinkedList poly_B = selectPoly(poly2);
        SinglyLinkedList temp = new SinglyLinkedList();
        int [][] result;
        int len1 = poly_A.size();
        for (int i = 0; i < len1; i++) {
            Term resultTerm = new Term(((Term) poly_A.get(i)).coff, ((Term) poly_A.get(i)).exp);
            temp.add(resultTerm);
        }
        int len2 = poly_B.size();
        for (int j = 0; j < len2; j++) {
            Term resultTerm = new Term(((Term) poly_B.get(j)).coff, ((Term) poly_B.get(j)).exp);
            temp.add(resultTerm);
        }
        result = convertLLIntoArray(temp);
        R.clear();
        setPolynomial('R', result);
        result = convertLLIntoArray(R);
        return result;
    }
    //////////////////////////////////////////////////////////////////////////////
    public int[][] subtract(char poly1, char poly2)
    {
        if((poly1 != 'R' && poly1 != 'r') &&  (poly2 != 'R' && poly2 != 'r')) R.clear();
        SinglyLinkedList poly_A = selectPoly(poly1);
        SinglyLinkedList poly_B = selectPoly(poly2);
        SinglyLinkedList temp = new SinglyLinkedList();
        int [][] result;
        int len1 = poly_A.size();
        int len2 = poly_B.size();
        for (int i = 0; i < len1; i++) {
            Term resultTerm = new Term(((Term) poly_A.get(i)).coff, ((Term) poly_A.get(i)).exp);
            temp.add(resultTerm);
        }
        for (int j = 0; j < len2; j++) {
            int coff = ((Term) poly_B.get(j)).coff * -1;
            Term resultTerm = new Term(coff, ((Term) poly_B.get(j)).exp);
            temp.add(resultTerm);
        }
        result = convertLLIntoArray(temp);
        R.clear();
        setPolynomial('R', result);
        result = convertLLIntoArray(R);
        return result;
    }
}