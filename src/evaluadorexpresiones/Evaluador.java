package evaluadorexpresiones;

public class Evaluador {

    public static double evaluar(String infija) {

        String posfija = convertir(infija);
        System.out.println("La expresión posfija es: "+posfija);
        return evaluarPosfija(posfija);
    }

    private static String convertir(String infija) {

        String posfija = "";
        Pila pila = new Pila(100);
        for (int i = 0; i < infija.length(); i++) {
            char letra = infija.charAt(i);
            if (esOperador(letra)) {

                if (pila.estaVacia()) {
                    pila.apilar(letra);
                } else {

                    int pe = prioridadEnExpresion(letra);
                    int pp = prioridadEnPila((char) pila.elementoTope());

                    if (pe > pp) {
                        pila.apilar(letra);
                    } else {

                        posfija += pila.desapilar();
                        pila.apilar(letra);
                    }
                }

            } else {
                posfija += letra;

            }
        }

        while (!pila.estaVacia()) {
            posfija += pila.desapilar();
        }
        return posfija;
    }

    private static double evaluarPosfija(String posfija) {
       
        Pila pila=new Pila(100);
        
        for (int i = 0; i < posfija.length(); i++) {
            char letra= posfija.charAt(i);
            
            if(!esOperador(letra)){
                double num= new Double(letra+"");
                pila.apilar(num);
                
            }else{
            //operacion importante para autonamata no determinista lamdana
            double num2=(double)pila.desapilar();
            double num1=(double)pila.desapilar();
            double num3= operacion(letra, num1, num2);
            
            pila.apilar(num3);
            }
        }
        
        
        
        return (double)pila.elementoTope();
    }

    private static boolean esOperador(char letra) {

        if (letra == '*' || letra == '/' || letra == '+'
                || letra == '-' || letra == '(' || letra == ')' || letra == '^') {
            return true;
        } else {
            return false;
        }
    }

    private static int prioridadEnExpresion(char operador) {

        if (operador == '^') {
            return 4;
        }
        if (operador == '*' || operador == '/') {
            return 2;
        }
        if (operador == '+' || operador == '-') {
            return 1;
        }
        if (operador == '(') {
            return 5;
        }
        return 0;
    }

    private static int prioridadEnPila(char operador) {

        if (operador == '^') {
            return 3;
        }
        if (operador == '*' || operador == '/') {
            return 2;
        }
        if (operador == '+' || operador == '-') {
            return 1;
        }
        if (operador == '(') {
            return 0;
        }
        return 0;
    }

    private static double operacion(char letra, double num1, double num2) {
       
        if(letra=='*') return num1*num2;
        if(letra=='/') return num1/num2;
        if(letra=='+') return num1+num2;
        if(letra=='-') return num1-num2;
        if(letra=='^') return Math.pow(num1, num2);
                
       return 0;
        
    }
}
