
public class LinkedListImple{
    public static void main(String[] args) {
      LinkedList l = new LinkedList();
      l.insertAtEnd(10);
      l.insertAtEnd(20);
      l.insertAtEnd(30);
      l.insertAtEnd(50);
      l.insertAtEnd(70);
      l.insertAtBeg(1);
      l.insertAtPos(1,2);
      l.deleteBeg();
      l.deleteAt(2);
      l.deleteEnd();
      l.display();
      l.reverse();
      l.display();
  }
}
class Node{
  int data;
  Node next;
  Node(int data){
    this.data=data;
    this.next=null;
  }
}
class LinkedList{
  Node head,tail;
  public void insertAtEnd(int data){
    Node newNode  = new Node(data);
    if(head==null){
      head=newNode;
      tail=newNode;
    }else{
      tail.next=newNode;
      tail=newNode;
    }
  }
  public void insertAtBeg(int data){
    Node newNode = new Node(data);
    newNode.next = head;
    head = newNode;
  }
  public void insertAtPos(int pos,int data){
    Node newNode = new Node(data);
    Node temp =head;
    for(int i = 0;i<pos-1;i++){
      temp=temp.next;
    }
    newNode.next = temp.next;
    temp.next=newNode;
  }
  public void display(){
    Node temp = head;
    while(temp!=null){
      System.out.print(temp.data+" ");
      temp=temp.next;
    }
    System.out.println();
  }
  public void deleteBeg(){
    Node temp = head;
    head=head.next;
    temp.next=null;
  }
  public void deleteAt(int pos){
    Node temp = head;
    for(int i=0;i<pos-1;i++){
      temp=temp.next;
    }
    temp.next=temp.next.next;
  }

  public void reverse(){
    Node prev = null;
    Node current = head;
    Node next=head.next;
    
    while(current!=null){
      next=current.next;
      current.next=prev;
      prev=current;
      current=next;
    }
    head=prev;
  }
  public void deleteEnd(){
    Node temp = head;
    while(temp.next!=tail){
      temp=temp.next;
    }
    temp.next=null;
    tail=temp;
  }
}