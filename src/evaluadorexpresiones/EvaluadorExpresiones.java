
package evaluadorexpresiones;

import java.util.Scanner;

public class EvaluadorExpresiones {

  
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Digite expresión que desea evaluar:");
        String infija=sc.next();
        System.out.println("El resultado es: "+Evaluador.evaluar(infija));
        
    }
    
}
