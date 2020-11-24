import java.net.*;  
import java.io.*; 
import java.lang.*;  
import java.util.*;
import java.math.BigInteger;
class Client{  
public static void main(String args[])throws Exception{  
//Connecting with server
Socket s=new Socket("localhost",3333);  
//Creating a object of class Scanner for getting user input
Scanner ip = new Scanner(System.in);
//int q = 7; //prime number
//int alpha = 3; //generator or primitive root
//int xb; //integer
//double yb; //public key of user B
//Declaring BigInteger varibles and assigning global parameter values
BigInteger yb,q,alpha,xb;
q = new BigInteger("7237");//another input 7
alpha = new BigInteger("26");//another input 3
yb = new BigInteger("0");
int ch;

//Creating objects for sending and receiving messages from server to client and vice versa
DataInputStream din=new DataInputStream(s.getInputStream());  
DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
  
String str="";  
do{ 
//Displaying global parameters 
System.out.println("Diffie Hellman Key Exchange Process User B");
System.out.println("Global parameters");
System.out.println("prime number q = " + q);
System.out.println("Generator (or Primitive root) = " + alpha);
//Getting private key from user B
System.out.println("Enter an integer less than q ");
//xb = Integer.parseInt(br.readLine());
//xb = new BigInteger(br.readLine());
xb = ip.nextBigInteger();
System.out.println("Private key of user B = "+xb);

//Calculating public key of user B by using the formula yb = alpha ^ xb mod q.Here with respect to BigInteger modPow method is used

yb = alpha.modPow(xb,q);
//yb = ((Math.pow(alpha,xb)) % q);
System.out.println("The public key of user B(yb) = "+yb);  
//str=din.readUTF(); 
//dout.writeUTF(Double.toString(yb)); 
dout.writeUTF(yb.toString(10)); 
System.out.println("Receiving public key of user A");  

str=din.readUTF();
//double ya = Double.parseDouble(str);
BigInteger ya = new BigInteger(str);
System.out.println("Public key of the user A = "+ya);   
 
//double K = (Math.pow(ya,xb) % q);
//Calculating secret key K = yb ^ xa mod q and display the result
BigInteger K = ya.modPow(xb,q);
//BigInteger K =(Math.pow(ya,xb) % q);
System.out.println("Secret Key = "+ K);
/* 
str=br.readLine();  
dout.writeUTF(str);  
dout.flush();  
str2=din.readUTF();  
System.out.println("Server says: "+str2);  */
System.out.println("Enter 1 for exchanging key another time else enter 2");
ch = ip.nextInt();
}while(ch!=2);  
  
dout.close();  
s.close();  
}}  