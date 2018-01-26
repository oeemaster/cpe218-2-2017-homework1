public class Stack {
Node[] arr; // regular array
int capacity;
int size;
public Stack(int cap){
    capacity = cap;
    arr = new Node[capacity];
    size=0;
    
    
}

public void push(Node node){
    if (!isFull()){
        arr[size] = node;
        size++;
        // do something
    }else{
        System.out.println("Stack Overflow!!!");
    }
}
public Node pop(){
    if (!isEmpty()){
       size--;
       return arr[size];
        // do something
    }else{
        System.out.println("Stack Underflow!!!");
    }
    return null; // fix this (out of place)
}
public boolean isFull(){
    if(size==capacity){
    return true;}
    else
    return false; // fix this
    }
public boolean isEmpty(){
    if(size==0){
    return true;}
    else
    return false;// fix this
}

public void printStack(){
    if (!isEmpty()) {
        System.out.print("[Bottom] ");
      for(int i=0;i<size;i++){  
       System.out.print(arr[i].key + " ");
      }
   // do something here
        System.out.println("[Top]");
    } else {
        System.out.println("Empty Stack!!!");
    }
}
}