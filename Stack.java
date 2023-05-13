import java.sql.ResultSet;
import java.util.Scanner;

public class Stack {
	public char data;
	public Stack next;
	static Stack Top=null;
	static Stack Top2=null;
	boolean check=false;
	public  String Expression;
	public  String PostFix="";
		Double data2;
		Stack next2;

	Scanner sc=new Scanner(System.in);
		
	public void push(char ch) {
			Stack p=new Stack();
			p.data=ch;
			if(Top==null) {
				Top=p;
			}else {
				Stack temp=new Stack();
				temp=Top;
				Top=p;
				Top.next=temp;
			}
		}
	public void push2(double ch3) {
		Stack p2=new Stack();
		p2.data2=ch3;
		if(Top2==null) {
			Top2=p2;
		}else {
			Stack temp2=new Stack();
			temp2=Top2;
			Top2=p2;
			Top2.next2=temp2;
		}
	}
		public char pop() {
			char ch2;
			 ch2=Top.data;
			Top=Top.next;
			return ch2;
				}
		public Double pop2() {
			Double ch4;
			 ch4=Top2.data2;
			Top2=Top2.next2;
			return ch4;
				}
	
public void Evaluation() {
		String[] arr=PostFix.split(",");
		Double x,y;
		for(int i=0;i<arr.length;i++) {
			switch(arr[i]) {
			case "+":
				 x=pop2();
				 y=pop2();
				push2(y+x);
				break;
			case "-":
				 x=pop2();
				 y=pop2();
				push2(y-x);
				break;
			case "*":
				 x=pop2();
				 y=pop2();
				push2(y*x);
				break;
			case "/":
				 x=pop2();
				 y=pop2();
				push2(y/x);
				break;
			default:
				int z=Integer.parseInt(arr[i]);
				push2(z);
				break;
			}
		}
}
		public void logic() {
		for(int i=0;i<Expression.length();i++) {
			if(Expression.charAt(i)=='('||Expression.charAt(i)=='/') {
				if(Stack.Top==null) {
				push(Expression.charAt(i));
				PostFix=PostFix+",";
				}
				else if(Expression.charAt(i-1)=='+'||Expression.charAt(i-1)=='-'||Expression.charAt(i-1)=='*'||Expression.charAt(i-1)=='/'){
					push(Expression.charAt(i));
			}else {
			push(Expression.charAt(i));
				PostFix=PostFix+",";
			}
		}else if(Expression.charAt(i)=='*') {
			check=false;
		do {
			if(Stack.Top==null) {
				PostFix=PostFix+",";
				push(Expression.charAt(i));
				check=true;
			}else if(Stack.Top.data=='/'){
				PostFix=PostFix+",";
				PostFix=PostFix+pop();
			}else {
				PostFix=PostFix+",";
				push(Expression.charAt(i));
				check=true;
			}
		}while(check==false);
		}else if(Expression.charAt(i)=='-') {
			check=false;
			do {
				if(Stack.Top==null) {
					push(Expression.charAt(i));
					PostFix=PostFix+",";
					check=true;
				}else if(Stack.Top.data=='/'||Stack.Top.data=='*'){
					PostFix=PostFix+",";
					PostFix=PostFix+pop();
				}else {
					PostFix=PostFix+",";
					push(Expression.charAt(i));	
					check=true;
				}
			}while(check==false);	
			}else if(Expression.charAt(i)=='+') {
				check=false;
				do {
					if(Stack.Top==null) {
						push(Expression.charAt(i));
						PostFix=PostFix+",";
						check=true;
					}else if(Stack.Top.data=='/'||Stack.Top.data=='*'||Stack.Top.data=='-'){
						PostFix=PostFix+",";
						PostFix=PostFix+pop();
					}else {
						PostFix=PostFix+",";
						push(Expression.charAt(i));	
						check=true;
					}
				}while(check==false);
			}else if(Expression.charAt(i)==')') {
				check=false;
				do {
				if(Stack.Top.data=='(') {
					pop();
					check=true;
				}else {
					PostFix=PostFix+','+pop();
				}
				}while(check==false);
				}else {
			PostFix=PostFix+Expression.charAt(i);
		}
		} 
		check=false;
		do {
			if(Stack.Top==null) {
				check=true;
			}else {
				PostFix=PostFix+","+pop();
			}
			
		}while(check==false);
		
		}
		public void display() {
			System.out.println("POSTFIX EXPRESSION IS : \n"+PostFix);	
		}
		
		public static void main(String[] args) {
			Scanner s = new Scanner(System.in);
			int choice;
			do {
				System.out.println("\n*** INFIX TO POSTFIX EVALUATION ***");
				System.out.println("PRESS 1 TO INSERT INFIX EXPRESSION IN DATABASE ");
				System.out.println("PRESS 2 TO DISPLAY ALL EXPRESSION TAKEN AND SOLVED ");
				System.out.println("PRESS 3 TO EXIT");
				choice = s.nextInt();
				
				 if(choice == 1) {
					 Stack stack=new Stack();
					 System.out.println("PLEASE ENTER INFIX EXPRESSION");
						stack.Expression=s.next();
						stack.logic();
						stack.Evaluation();
						
					try {
						dbmanager objMgr = new dbmanager();
						String query = String.format("insert into Infix values('%s','%s','%s')",stack.Expression,stack.PostFix,stack.pop2() );
						
						objMgr.insertUpdateDelete(query);
						System.out.println("\n..... YOUR DATA ENTERED SUCCESSFULLY .....");
					}catch(Exception ex) {
						System.out.println(ex.toString());
					}
				}
				else if(choice == 2) {
					try {
						dbmanager objMgr = new dbmanager();
						String query = "Select * from Infix";
						ResultSet res = objMgr.select(query);
						
						while(res.next()){
							System.out.println("\n*****INFIX TO POSTFIX SOLUTION *****\n");
							System.out.println("EXPRESSION :  "+res.getString(1)+"  SOLUTION :  "+res.getString(3));
							
							
						}
						res.close();
						
					}catch(Exception ex){
						System.out.println(ex.toString());
					}
				}
				
				
			}while(choice != 3);
			System.out.println("** THANKS FOR USING PROGRAM ** *\n** YOUR PROGRAM TERMINATED SUCCESSFULLY ***");
			}
}
