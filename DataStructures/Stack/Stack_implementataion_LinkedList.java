import java.util.*;

class Main{
    
    static Node head = null;
    
    static void push(int data){
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }
    
    static void pop(){
        System.out.println("Poped Element "+head.data);
        head = head.next;
    }
    
    static void peek(){
        System.out.println("Peek Element "+head.data);
    }
    
    static void isEmpty(){
        if(head==null){
            System.out.println("Stack is Empty");
        }else{
         System.out.println("Stack is Not Empty");   
        }
    }
    
    static void display(){
        Node temp = head;
        
        while(temp!=null){
            System.out.print(temp.data+" ");
            temp=temp.next;
        }
        
    }
    public static void main(String[] args){
        push(10);
        push(20);
        push(30);
        pop();
        push(40);
        peek();
        pop();
        pop();
        pop();
        isEmpty();
        display();
    }
}

class Node{
    int data;
    Node next;
    
    Node(int data){
        this.data=data;
        this.next = null;
    }
}