package com.app;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.app.customer.dao.CustomLoginDao;
import com.app.customer.dao.impl.CustomerLoginDaoImpl;
import com.app.exception.BussinessException;
import com.app.model.Cart;
import com.app.model.Product;
import com.app.service.CustomerService;
import com.app.service.impl.CustomerLoginServiceImpl;

public class Main {
	private static Logger log = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		log.info("========================================");
		log.info("Welcome to the Fashion's APP");
		log.info("========================================");
		int mainOptions = 0;
		String customerEmail = "";
		CustomerService customerService = new CustomerLoginServiceImpl();
		do {
			log.info("\nLogin Option:");
			log.info("1)Login as Customer");
			log.info("2)Login as Employee");
			log.info("3)Register a new User");
			log.info("4)EXIT ");
			log.info("ENTER YOUR CHOICE(1-4):");
			try {
				mainOptions = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
			}
			switch (mainOptions) {
			case 1:
				log.info("========================================");
				log.info("Welcome to Customer Login");
				log.info("========================================");
				// log.info("========================================");
				log.info("Enter the your email:");
				customerEmail = scanner.nextLine();
				if (customerEmail == null) {
					break;
				}
				log.info("Enter your Password:");
				String password = scanner.nextLine();
				try {
					if (customerService.customerLoginService(customerEmail, password)) {
						// int CustomerOptions=0;
						int CustomerOptions = 0;
						do {
							log.info("\nHey customer");
							log.info("1)View Product");
							log.info("2)View Cart");
							log.info("3)EXIT ");
							log.info("ENTER YOUR CHOICE(1-3):");

							try {
								CustomerOptions = Integer.parseInt(scanner.nextLine());
							} catch (NumberFormatException e) {
							}

							switch (CustomerOptions) {
							case 1:
								int ViewProduct = 0;
								do {
									log.info("\nSearch product by:");
									log.info("1)Product Name");
									log.info("2)Product Price");
									log.info("3)EXIT ");
									log.info("ENTER YOUR CHOICE(1-3):");

									try {
										ViewProduct = Integer.parseInt(scanner.nextLine());
									} catch (NumberFormatException e) {
									}

									switch (ViewProduct) {
									case 1:
										log.info("Enter the name of the product to be searched");
										String name = scanner.nextLine();
										List<Product> pList = customerService.getProductByName(name);
										if (pList != null && !pList.isEmpty()) {
											log.info("Products found");
											for (Product product : pList) {

												log.info("Product ID : " + product.getProductId() + "Product Name : "
														+ product.getProductName() + "Product Price"
														+ product.getProductPrice());
												// System.out.println(pList);
											}
										}
										addProductOptions(customerEmail, scanner);
										break;
									case 2:
										log.info("Enter the Price :");
										String productPrice = scanner.nextLine();
										List<Product> priceList = customerService.getProductByPrice(productPrice);
										if (priceList != null && !priceList.isEmpty()) {
											log.info("Products found");
											for (Product product : priceList) {

												log.info("Product ID : " + product.getProductId() + " Product Name : "
														+ product.getProductName() + " Product Price :"
														+ product.getProductPrice());
												// System.out.println(priceList);
											}
										}
										addProductOptions(customerEmail, scanner);
										break;
									case 3:
										log.info("Thank you for your time,Hope to see you again");

										break;

									default:
										log.warn("Choose a valid option");
										break;

									}
								} while (ViewProduct != 3);
								break;
							case 2:// view cart

								try {
									List<Product> productList = customerService.viewCart(customerEmail);
									log.info("Your Cart : ");
									for (Product product : productList) {

										log.info("Product Name:" + product.getProductName() + " " + "Product Price:"
												+ product.getProductPrice());

									}

									log.info("\n1)Do you want to place order:");
									log.info("2)exit");
									int placeOrder = 0;
									placeOrder = Integer.parseInt(scanner.nextLine());
									switch (placeOrder) {
									case 1:
										placeOrder(customerEmail);
										break;

									case 2:
										break;
									}

								} catch (BussinessException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								break;

							}
						} while (CustomerOptions != 3);
					}

				} catch (BussinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				// log.info("Employee login Under construction");

				break;

			case 3:
				// log.info("Under construction");

				break;

			case 4:
				log.info("Thank you for your time,Hope to see you again");

				break;

			default:
				log.warn("Choose a valid option");
				break;
			}
		} while (mainOptions != 4);

	}

	private static void addProductOptions(String customerEmail, Scanner scanner) {
		int addProduct = 0;
		do {
			log.info("\nSelect the operation on product:");
			log.info("1)Add product to cart");
			log.info("2)EXIT");
			addProduct = Integer.parseInt(scanner.nextLine());
			switch (addProduct) {
			case 1:

				log.info("Enter productId seperated By comma(,):");
				String pids = scanner.nextLine();
				if (pids == null) {
					break;
				} else {
					CustomerLoginServiceImpl customerLoginServiceImpl = new CustomerLoginServiceImpl();
					try {
						customerLoginServiceImpl.addProductToCart(pids, customerEmail);
					} catch (BussinessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				break;
			case 2:
				log.info("2)EXIT");

				break;

			default:
				break;
			}

		} while (addProduct != 2);

	}

	private static void placeOrder(String customerEmail) throws BussinessException {
		CustomerLoginServiceImpl customerLoginServiceImpl = new CustomerLoginServiceImpl();
		if (customerLoginServiceImpl.PlaceOrder(customerEmail)) {
			log.info("Your Order is Sucessful");
		}else
			log.error("Sorry unable to place your order");
	}

}