import java.util.*;

class Stack{
    static int n=5;
    static int[] arr = new int[n];
    static int top = -1;
    
    static int push(int data){
        if(top==n-1){
            System.out.println("Stack Overflow");
            return -1;
        }
        
        arr[++top]=data;
        return 1;
    }
    
    static int pop(){
        if(top==-1){
            System.out.println("Stack Underflow");
            return -1;
        }
        System.out.println("Poped Element is "+arr[top]);
        top--;
        return 1;
    }
    
    static void peek(){
        System.out.println("Peek is "+arr[top]);
    }
    
    static void isEmpty(){
        if(top==-1){
            System.out.println("Stack is Empty");
        }else{
            System.out.println("Stack is not Empty");
        }
    }
    
    static void display(){
        if(top==-1){
            System.out.println("Stack is Empty");
        }
        for(int i = 0;i<=top;i++){
            System.out.print(arr[i]+" ");
        }
    }
    
    
    
    
    public static void main(String[] args){
        push(1);
        push(2);
        push(3);
        push(4);
        push(5);
        pop();
        pop();
        isEmpty();
        peek();
        pop();
        pop();
        pop();
        isEmpty();
        display();
    }
}