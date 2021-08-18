package com.app;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.app.customer.dao.CustomLoginDao;
import com.app.customer.dao.impl.CustomerLoginDaoImpl;
import com.app.exception.BussinessException;
import com.app.service.CustomerService;
import com.app.service.impl.CustomerLoginServiceImpl;

public class Main {
	private static Logger log=Logger.getLogger(Main.class);
	public static void main(String[]args) {
		Scanner scanner=new Scanner(System.in);
		log.info("========================================");
		log.info("Welcome to the Fashion's APP");
		log.info("========================================");
		int mainOptions=0;
		CustomerService customerService = new CustomerLoginServiceImpl();
		do {
			log.info("\nLogin Option:");
			log.info("1)Login as Customer");
			log.info("2)Login as Employee");
			log.info("3)Register a new User");
			log.info("4)EXIT ");
			log.info("ENTER YOUR CHOICE(1-4):");
			try {
				mainOptions=Integer.parseInt(scanner.nextLine());
				}
				catch(NumberFormatException e) {
				}
			switch (mainOptions) {
			case 1:
				log.info("========================================");
				log.info("Welcome to Customer Login");
				log.info("========================================");
				//log.info("========================================");
				log.info("Enter the your email:");
				String customerEmail = scanner.nextLine();
				if(customerEmail==null) {
					break;
				}
				log.info("Enter your Password:");
				String password= scanner.nextLine();
				try {
					if(customerService.customerLoginService(customerEmail, password)) {
						//int CustomerOptions=0;
					}int CustomerOptions=0;
						do {
							log.info("\nHey customer");
							log.info("1)Search Product");
							log.info("2)View Cart");
							log.info("3)EXIT ");
							log.info("ENTER YOUR CHOICE(1-3):");
							
							try {
								CustomerOptions=Integer.parseInt(scanner.nextLine());
								}
								catch(NumberFormatException e) {
								}
						
						switch (CustomerOptions) {
						case 1:
							log.info("Under construction");
				   			
							
							break;
                        case 2:
                        	log.info("Under construction");
                   			
							break;
                        case 3:
                        	log.info("Thank you for your time,Hope to see you again");
                   			
							break;

						default:log.warn("Choose a valid option");
							break;
						}
					}while(CustomerOptions!=3);
				
				} catch (BussinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
				
           case 2:
        	   log.info("Under construction");
   			
				break;

           case 3:
        	   //log.info("Under construction");
   			
	
	           break;

           case 4:
        	   log.info("Thank you for your time,Hope to see you again");
	
	           break;


			default:
	        	   log.warn("Choose a valid option");
				break;
			}
			}while(mainOptions!=0);
		
		
		

 }
}