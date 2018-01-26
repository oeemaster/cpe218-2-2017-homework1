import java.util.NoSuchElementException;
public class tree {
    Node root;
    String text;
    int sum;
    boolean op = true;
    
   public tree(String t){
        text = t;
        }
   
   public void creattree(){
       Stack stack = new Stack(text.length());
       for(int i=0;i<text.length();i++){
           char num = text.charAt(i);
       Node node = new Node(num);
           if(op(node.key)){ 
         node.right = stack.pop();
         node.left = stack.pop();
         stack.push(node);
       }
       else{
           stack.push(node);
       }     
   }
       root = stack.pop();
   }
   public boolean op(char o){
       if(o == '+' || o == '-' || o == '*' || o == '/'){
           return true;
       }
       else{
           return false;
   }
   }
     
   public void inorder(Node n){
         if (n == null){
            return ;}
         else{
            inorder(n.left);
            System.out.print(n.key);         
            inorder(n.right);
         }
   }
   public void infix(Node n){
  System.out.print(n);
   }
   public int calculate(Node n){
       if(op(n.key)){
           switch(n.key){
               case '+': sum = calculate(n.left)+calculate(n.right);
               break;
               case '-': sum = calculate(n.left)-calculate(n.right);
               break;
               case '*': sum = calculate(n.left)*calculate(n.right);
               break;
               case '/': sum = calculate(n.left)/calculate(n.right);             
               break;
           }
       }
       else{
          int b = n.key-'0';
        return b;
        }
       return sum;
   }
}


