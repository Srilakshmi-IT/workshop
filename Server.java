import java.net.*;  
import java.io.*; 
import java.lang.*; 
import java.util.*;
import java.math.BigInteger;

class Server{  
public static void main(String args[])throws Exception{  
//Making connection
ServerSocket ss=new ServerSocket(3333);  
//Accepting client
Socket s=ss.accept();  
//Creating a object of class Scanner for getting user input
Scanner ip = new Scanner(System.in);
//int q = 7; // prime number
//int alpha = 3; // generator or primitive root
//int xa; //integer
//double ya; //public key of user A
//Declaring BigInteger varibles and assigning global parameter values
BigInteger ya,q,alpha,xa;
q = new BigInteger("7237"); //another input7
alpha = new BigInteger("26");another input //3
ya = new BigInteger("0");
int ch;
//Creating objects for sending and receiving messages from server to client and vice versa
DataInputStream din=new DataInputStream(s.getInputStream());  
DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  

String str="";  
do{  

//Displaying global parameters 
System.out.println("Diffie Hellman Key Exchange Process User A");
System.out.println("Global parameters");
System.out.println("prime number q = " + q);
System.out.println("Generator (or Primitive root) = " + alpha);
//Getting private key from user A
System.out.println("Enter an integer less than q ");
//xa = new BigInteger(br.readLine());
xa = ip.nextBigInteger();
System.out.println("Private key of user A = "+xa);
//Calculating public key of user A by using the formula ya = alpha ^ xa mod q.Here with respect to BigInteger modPow method is used
ya = alpha.modPow(xa,q); 
//ya = ((Math.pow(alpha,xa)) % q);
 
System.out.println("The public key of user A(ya) = "+ya);  
//str=din.readUTF(); 
//dout.writeUTF(Double.toString(ya)); 
dout.writeUTF(ya.toString(10));
System.out.println("Receiving public key of user B");  
 
str=din.readUTF();  
//double yb = Double.parseDouble(str);
BigInteger yb = new BigInteger(str);
System.out.println("Public key of user B(yb) = "+ yb);  
//double K = (Math.pow(yb,xa) % q); 
//BigInteger K = (Math.pow(yb,xa) % q); 
//Calculating secret key K = yb ^ xa mod q and display the result
BigInteger K = yb.modPow(xa,q);
System.out.println("Secret Key = "+K); 
dout.flush(); 
System.out.println("Enter 1 for exchanging key another time else enter 2");
ch = ip.nextInt(); 
}while(ch!=2);  
din.close();  
s.close();  
ss.close();  
}}  