/*
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
*/
/**
*
@author ASUS
*/
public class Node {
Node left;
Node right;
Node parent;
char key;
String s;
public Node(char data){
key = data;
left = null;
right =null;
parent = null;
}
public Node(String st){
s = st;
}
 public String toString() {
        return (right == null && left == null) ? Character.toString(key) : "(" + left.toString()+ key + right.toString() + ")";
    }
}