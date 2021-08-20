package com.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.app.customer.dao.CustomLoginDao;
import com.app.customer.dao.impl.CustomerLoginDaoImpl;
import com.app.exception.BussinessException;
import com.app.model.Cart;
import com.app.model.Customer;
import com.app.model.Order;
import com.app.model.Product;
import com.app.service.CustomerService;
import com.app.service.impl.CustomerLoginServiceImpl;
import com.app.service.impl.EmployeeLoginServiceImpl;

public class Main {
	private static Logger log = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		log.info("========================================");
		log.info("Welcome to the Gadget APP");
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
							case 1:// customer view product
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
									case 1:// searching a product by name
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
									case 2:// searching a product by price
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
									totalInCart(customerEmail);

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
			case 2:// Employee Login
				log.info("Enter Employee mail id");
				String id = scanner.nextLine();
				if (id.equals("kratika@mail.com")) {
					log.info("Enter Employee Password");
					String empPassword = scanner.nextLine();
					if (empPassword.equals("1234")) {

						log.info("Emplyee Login Sucessful");

						log.info("Enter your Choice");

						int empMainOptions = 0;

						do {

							log.info("1)Add Product");
							log.info("2)Search Customer");
							log.info("3)Change order status");
							log.info("4)Exit");
							empMainOptions = Integer.parseInt(scanner.nextLine());

							switch (empMainOptions) {
							case 1:

								String choice = "Y";
								List<Product> products = new ArrayList<Product>();
								// Add Product
								while (choice.equals("Y") || choice.equals("y")) {
									log.info("Enter product details :");
									Product p = new Product();
									log.info("Name :");
									p.setProductName(scanner.nextLine());
									log.info("Price :");
									p.setProductPrice(scanner.nextLine());
									products.add(p);
									log.info("Do you want to add more Products Y/N");
									choice = scanner.nextLine();
								}

								EmployeeLoginServiceImpl empServiceImpl = new EmployeeLoginServiceImpl();
								try {
									empServiceImpl.addProduct(products);
									log.info("Products added Sucessfully");
								} catch (BussinessException e) {
									e.printStackTrace();
									log.info("Products not added");
								}

								break;
							case 2:
								int searchOptionsEmp = 0;
								// Search Customer
								do {
									log.info("\nSearch Customer by:");
									log.info("1)Name");
									log.info("2)Id");
									log.info("3)Email ID ");
									log.info("4)OrderID");
									log.info("5)Exit");

									searchOptionsEmp = Integer.parseInt(scanner.nextLine());
									EmployeeLoginServiceImpl emImpl = new EmployeeLoginServiceImpl();
									switch (searchOptionsEmp) {
									case 1:
										// Search by Name
										log.info("Enter Customer Name : <FisrtName> <LastName>");
										try {
											for (Customer customer : emImpl.getCustomerByName(scanner.nextLine())) {
												log.info(customer.toString());
											}
										} catch (BussinessException e) {
											// TODO Auto-generated catch block
											log.error("Customer Name not Found");
										}
										break;

									case 2:
										// Search by Id
										log.info("Enter Customer ID : ");
										try {
											for (Customer customer : emImpl.getCustomerById(scanner.nextLine())) {
												log.info(customer.toString());
											}
										} catch (BussinessException e) {
											// TODO Auto-generated catch block
											log.error("Customer Id not Found");
										}

										break;
									case 3:
										// Search by Email
										log.info("Enter Customer Email : ");
										try {
											for (Customer customer : emImpl.getCustomerByEmail(scanner.nextLine())) {
												log.info(customer.toString());
											}
										} catch (BussinessException e) {
											// TODO Auto-generated catch block
											log.error("Customer Email not Found");
										}

										break;
									case 4:
										// Search by OrderId
										log.info("Enter Customer OrderId : ");
										try {
											for (Customer customer : emImpl.getCustomerByOrderId(scanner.nextLine())) {
												log.info(customer.toString());
											}
										} catch (BussinessException e) {
											// TODO Auto-generated catch block
											log.error("Customer OrderID not Found");
										}

										break;
									case 5:
										// exit
										break;
									}

								} while (searchOptionsEmp != 5);
								break;
							case 3:
								// Change Order status
								EmployeeLoginServiceImpl emImpl= new EmployeeLoginServiceImpl();
								try {
									for (Order order : emImpl.viewOrders()) {
										
										log.info("OrderID : "+ order.getOrderId()+" OrderStatus : "+ order.getStatus());
									}
								} catch (BussinessException e) {
									log.error(e);
								}
								log.info("Enter the orderId for which status needs to be updated as , seperated");
								String order=scanner.nextLine();
								try {
									
									if (emImpl.changeOrderStatus(order)) {
										for (Order orderUpdated : emImpl.viewOrders()) {
											
											log.info("OrderID : "+ orderUpdated.getOrderId()+" OrderStatus : "+ orderUpdated.getStatus());
										}
										log.info("Orders status updated");
												}
								
								} catch (BussinessException e) {
									log.error(e);
								}
								
								
							case 4:
								// Exit
								break;

							}
						} while (empMainOptions != 4);

					}
				}
				break;

			case 3:
				// Register Customer
				log.info("Register new User");
				log.info("Enter Customer details :");
				Customer c = new Customer();
				log.info("First Name :");
				c.setCustomerFirstName(scanner.nextLine());
				log.info("Last Name :");
				c.setCustomerLastName(scanner.nextLine());
				log.info("Email :");
				c.setCustomerEmail(scanner.nextLine());
				log.info("Password :");
				c.setPassword(scanner.nextLine());
				try {
					if (customerService.registerCustomer(c)) {
						log.info("Customer Registration Successful");
					}
				} catch (BussinessException e) {
					log.error(e);
				}

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
		} else
			log.error("Sorry unable to place your order");
	}

	private static int totalInCart(String customerEmail) throws BussinessException {
		CustomerLoginServiceImpl customerLoginServiceImpl = new CustomerLoginServiceImpl();
		int total = customerLoginServiceImpl.totalInCart(customerEmail);
		if (customerLoginServiceImpl.totalInCart(customerEmail) != 0) {
			log.info("Total:" + total);
		}
		return total;
	}

}