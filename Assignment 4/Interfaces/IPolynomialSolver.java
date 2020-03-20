package eg.edu.alexu.csd.datastructure.linkedList;

public interface IPolynomialSolver {
    void setPolynomial(char poly, int[][] terms);//done
    String print(char poly);//done
    void clearPolynomial(char poly);//done
    float evaluatePolynomial(char poly, float value);//done
    int[][] add(char poly1, char poly2);
    int[][] subtract(char poly1, char poly2);
    int[][] multiply(char poly1, char poly2);//done
}
