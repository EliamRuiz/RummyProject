/*******************************************************************
Programado por  Eliam Ruiz Agosto (para anonimato en kritik)
Numero de estudiante 801-18-7235 (para anonimato en kritik)
CCOM 4029 Lenguajes de alto nivel
Sección 0U1
*/

/********************************************************************
 * Propósito - Esta clase servirá como el  stack de cartas en el 
 * juego de Rummy para el proyecto de la clase CCOM4029. Los 
 * jugadores podrán descartas sus cartas o tomar la última carta 
 * que fue descartada en el stack por algún jugador.  
 * ******************************************************************
 * Librería de variables - 
 * 
 * stackArray = es el arreglo sobre el cual se implementaran las
 *              funciones de un stack.
 * 
 * top = esta variable es un entero que nos ayudará a mantener record
 *       de la carta que esta ultima en el stack
 * 
 * data = es la variable tipo carta que se utiliza para pasar 
 *        las cartas a las funciones y que hagan sus trabajos de stack; 
 * 
 * thereIsAvalue = un booleano que nos ayuda a recorrer el arreglo
 *                 y verificar si esta vacío o si tiene cartas
 * ******************************************************************
*/

public class MyStack{

    Card stackArray[];
    int top = 0;

    // constructor de mystack
    public MyStack() {
        stackArray = new Card[52];
    }

    // funcion para anadir cartas al stack
    public void push(Card data){
        stackArray[top] = data;
        top++; 
    }
    // funcion para sacar la ultima carta del stack
    public Card pop(){ 

        if (top == 0){
            return null;
        }
        else {
            Card data;
            data = stackArray[top - 1];
            top--;
            return data;
        }

        
    }
    // funcion para devolver cual es la ultima carta del stack
    public Card top(){
        if (top == 0){
            return null;
        }
        else{
        return stackArray[top-1];
        }
    }
    // funcion que devuelve true si el satck esta vacio y falso si tiene cartas
    public Boolean isEmpty(){

        boolean thereIsAvalue = false;
        for (Card i : stackArray){
            if (i != null){
                thereIsAvalue = true;
            }
        } 
        if (!thereIsAvalue) {
            return true;
        }
        else{
            return false;
        }
    }

    public static void main( String args[] ) {
        // crea el objetco de clase mystack
        MyStack stack = new MyStack(); 
        // verifica que se creó vacio correctamente
        System.out.print(stack.isEmpty());
        // crea una carta para anadir al stack
        Card card = new Card(Card.suit[1], Card.rank[1]);
        // entra la carta en el stack
        stack.push(card);
        // verifica que se halla anadido al stack y que ahora no este vacio
        System.out.print(stack.isEmpty());
        // remueve la carta anadida
        stack.pop();
        // el stack esta vacio nuevbamente
        System.out.print(stack.isEmpty());
    }

}

