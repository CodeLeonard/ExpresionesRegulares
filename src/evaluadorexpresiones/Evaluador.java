package evaluadorexpresiones;

import java.util.ArrayList;

public class Evaluador {

    public static double evaluar(String infija) {

        ArrayList<String> posfija = convertir(infija);
//        System.out.println("La expresión posfija es: " + posfija);
        return evaluarPosfija(posfija);
    }

    private static ArrayList<String> convertir(String infija) {
        ArrayList<String> posfija = new ArrayList<String>();
        String letra = "";

        Pila pila = new Pila(100);
        ArrayList<String> list = new ArrayList();

        for (int i = 0; i < infija.length(); i++) {
            String letraUnic = "" + infija.charAt(i);
            if (letraUnic.hashCode() >= 48 && letraUnic.hashCode() <= 57) {

                letra += letraUnic;

            } else if (esOperador(letraUnic)) {
                list.add(letra);
                list.add(letraUnic);
                letra = "";
            }
        }

        list.add(letra);

//        System.out.println(list.toString());
        for (int i = 0; i < list.size(); i++) {
            letra = list.get(i);

            if (esOperador(letra)) {

                if (pila.estaVacia()) {

                    pila.apilar(letra);
                } else {

                    int pe = prioridadEnExpresion(letra);
                    int pp = prioridadEnPila((String) pila.elementoTope());

                    if (pe > pp) {

                        pila.apilar(letra);

                    } else {
                        if (letra.equals(")")) {

                            while (!pila.elementoTope().equals("(")) {
                                posfija.add((String) pila.desapilar());
                            }
                            pila.desapilar();

                        } else {
                            posfija.add((String) pila.desapilar());
                            pila.apilar(letra);
                        }
                    }
                }
            } else {
                posfija.add(letra);

            }
        }

        while (!pila.estaVacia()) {
            posfija.add((String) pila.desapilar());
        }
        return posfija;
    }

    private static double evaluarPosfija(ArrayList<String> posfija) {

        Pila pila = new Pila(100);

        for (int i = 0; i < posfija.size(); i++) {

            if (!posfija.get(i).equals("")) {
                String letra = "" + posfija.get(i);
                if (!esOperador(letra)) {
                    double num = new Double(letra + "");
                    pila.apilar(num);

                } else {

                    double num2 = (double) pila.desapilar();
                    double num1 = (double) pila.desapilar();
                    double num3 = operacion(letra, num1, num2);

                    pila.apilar(num3);
                }
            }

        }

        return (double) pila.elementoTope();
    }

    private static boolean esOperador(String letra) {

        if (letra.equals("*") || letra.equals("/") || letra.equals("+")
                || letra.equals("-") || letra.equals("(") || letra.equals(")") || letra.equals("^")) {
            return true;
        } else {
            return false;
        }
    }

    private static int prioridadEnExpresion(String operador) {

        if (operador.equals("^")) {
            return 4;
        }
        if (operador.equals("*") || operador.equals("/")) {
            return 2;
        }
        if (operador.equals("+") || operador.equals("-")) {
            return 1;
        }
        if (operador.equals("(")) {
            return 5;
        }
        return 0;
    }

    private static int prioridadEnPila(String operador) {

        if (operador.equals("^")) {
            return 3;
        }
        if (operador.equals("*") || operador.equals("/")) {
            return 2;
        }
        if (operador.equals("+") || operador.equals("-")) {
            return 1;
        }
        if (operador.equals("(")) {
            return 0;
        }
        return 0;
    }

    private static double operacion(String letra, double num1, double num2) {

        if (letra.equals("*")) {
            return num1 * num2;
        }
        if (letra.equals("/")) {
            return num1 / num2;
        }
        if (letra.equals("+")) {
            return num1 + num2;
        }
        if (letra.equals("-")) {
            return num1 - num2;
        }
        if (letra.equals("^")) {
            return Math.pow(num1, num2);
        }

        return 0;

    }
}
