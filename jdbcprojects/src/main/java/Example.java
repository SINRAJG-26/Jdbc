import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Example{
	public static void main(String[] args)throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("1.Register");
		System.out.println("2.Login");
		System.out.println("Enter your choice");
		int choice = sc.nextInt();
			
			switch(choice) {
			case 1: register();
			break;
			case 2: login();
			break;
			default: System.out.println("Invalid choice");
			break;
			}
		}
		static void register()throws Exception {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/june_2024", "root", "root");
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter name");
			String name= sc.next();
			System.out.println("Enter user_name");
			String user_name = sc.next();
			
			while(true) {
				String s = "select*from user where user_name=?";
				PreparedStatement pstmt = con.prepareStatement(s);
				pstmt.setString(1, user_name);
				ResultSet res = pstmt.executeQuery();
				
				if(res.next()) {
					System.out.println("user_name already exists. Please enter another user_name");
					user_name = sc.next();		
					}
				else break;
			}
			String password;
			String CPassword;
			do {
				System.out.println("Enter Pasword");
				password =sc.next();
				System.out.println("Enter Conform Password");
				CPassword = sc.next();
			}while(!password.equals(CPassword));
			
			System.out.println("Enter email_id");
			String email_id = sc.next();
			
			String s1 = "insert into user value(?,?,?,?)";
			PreparedStatement pstmt1 = con.prepareStatement(s1);
			pstmt1.setString(1,name);
			pstmt1.setString(2,user_name);
			pstmt1.setString(3,password);
			pstmt1.setString(4,email_id);
			pstmt1.executeUpdate();
			
			
		}
		static void login() throws Exception {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/june_2024", "root", "root");
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter user_name:");
			String user_name = sc.next();
			System.out.println("Enter the password");
			String password =sc.next();
			String s="select*from user where user_name=?";
			PreparedStatement pstmt=con.prepareStatement(s);
			pstmt.setString(1, user_name);
			ResultSet res= pstmt.executeQuery();
			if(res.next()) {
				if(password.equals(res.getString(3))) {
					System.out.println("login successful");
				}else {
					System.out.println("invalid password");
					
				}
			}else {
				System.out.println("invalid user_name");
			}
				
			}
			
			
		}
