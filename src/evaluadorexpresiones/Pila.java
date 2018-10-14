package evaluadorexpresiones;

public class Pila {
    private int n;
    private int tope;
    private Object pila[];
    
    public Pila(int n)
    {
        this.n=n;
        this.tope=0;
        this.pila=new Object[n];
    }
    
    public boolean estaVacia(){
        return tope==0;
    }
    
    public boolean estaLlena(){
    return tope==n;
    }
    
    public boolean apilar(Object dato){
        
        if(estaLlena()){
            return false;
        }
        pila[tope]=dato;
        tope++;
        return true;
    }
    
    public Object desapilar(){
        if(estaVacia()){
            return null;
        }
        tope--;
        return pila[tope];
    }
    
    public Object elementoTope(){
        
        return pila[tope-1];
    }
    
}

