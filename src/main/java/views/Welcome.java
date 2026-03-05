package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

import dao.UserDao;
import model.User;
import service.GenerateOTP;
import service.SendOTPService;
import service.UserService;

public class Welcome {
	public void welcomeScreen() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Welcome to the app");
		System.out.println("Press 1 to login");
		System.out.println("Press 2 to signup");
		System.out.println("Press 0 to Exit");
		int choice = 0;
		try {
			String input = br.readLine();
            choice = Integer.parseInt(input.trim());
		}catch (IOException ex) {
			ex.printStackTrace();  
		}
		
		switch (choice) {

        case 1 : login();
        break;
       
        case 2 : signup();
        break;
       
        case 0 : System.exit(0);
        break;
        }
		
	}
	private void login() {
		 Scanner sc = new Scanner(System.in);
		 System.out.println("Enter Email");
	        String email = sc.nextLine();
	        try {
	            if(UserDao.isExists(email)) {
	                String genOTP = GenerateOTP.getOTP();
	                SendOTPService.sendOTP(email, genOTP);
	                System.out.println("Enter the OTP");
	                String otp = sc.nextLine();
	                if(otp.equals(genOTP)) {
	                	new UserView(email).home();;
	                }
	                	else {
	                    System.out.println("Wrong OTP");
	                }
	            } else {
	                System.out.println("User not found");
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
		
	}
	private void signup() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter name");
		String name = sc.nextLine();
		System.out.println("Enter email");
		String email = sc.nextLine();
		 String genOTP = GenerateOTP.getOTP();
         SendOTPService.sendOTP(email, genOTP);
         System.out.println("Enter the OTP");
         String otp = sc.nextLine();
         if(otp.equals(genOTP)) {
        	 User user = new User(name, email);
        	 int response = UserService.saveUser(user);
             switch (response) {
                 case 0 : System.out.println("User registered");
                 break;
                 case 1 : System.out.println("User already exists");
                 break;
             }
         } else {
             System.out.println("Wrong OTP");
         }
	}
	

}
