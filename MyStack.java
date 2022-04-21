public class MyStack {
    int stackArray[] = new int[52];
    int top = 0;

    public void push(int data){
        stackArray[top] = data;
        top++; 
    }

    public int pop(){
         
        int data;
        top--;
        data = stackArray[top];
        stackArray[top] = 0;
        return data;
    }

    public int top(){
        return top;
    }

    public Boolean isEmpty(){
        if (stackArray.length == 0) {
            return true;
        }
        else{
            return false;
        }
    }

    public void show(){
        for(int n : stackArray){
            System.out.print(n + " ");
        }
    }
}
