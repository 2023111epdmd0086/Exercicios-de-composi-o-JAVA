package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import main.entities.Client;
import main.entities.Order;
import main.entities.OrderItem;
import main.entities.OrderStatus;
import main.entities.Product;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        Order order;

        System.out.println("Enter cliente data:");
        System.out.print("Name: ");
        String clientName = sc.nextLine();
        System.out.print("Email: ");
        String clientEmail = sc.nextLine();
        System.out.print("Birth date (DD/MM/YYYY): ");
        Date clientBirthDate = sdf.parse(sc.nextLine());
        Client client = new Client(clientName, clientEmail, clientBirthDate);

        System.out.println("Enter order data:");
        System.out.print("Status: ");
        OrderStatus status = OrderStatus.valueOf(sc.nextLine());
        System.out.print("How many items to this order? ");
        int qtd = sc.nextInt();
        sc.nextLine();
        Date date = new Date();
        order = new Order(date, status, client);
        for (int i = 0; i < qtd; i++) {
            System.out.println("Enter #" + (1 + i) + " item data:");
            System.out.print("Product name: ");
            String productName = sc.nextLine();
            System.out.print("Product price: ");
            double productPrice = sc.nextDouble();
            System.out.print("Quantity: ");
            int productQuantity = sc.nextInt();
            sc.nextLine();
            Product p = new Product(productName, productPrice);
            OrderItem orderItem = new OrderItem(productQuantity, p);
            order.addItem(orderItem);
        }

        System.out.println("ORDER SUMARY:");
        System.out.print("Order moment: " + order.getMoment()+"\n");
        System.out.print("Order status: " + order.getStatus()+"\n");
        System.out.print("Client: "
                + order.getClient().getName()
                + " ("
                + order.getClient().getBirthDate()
                + ") - "
                + order.getClient().getEmail()+"\n");
        System.out.println("Order Itens");
        for(OrderItem o : order.getOrderItem()) {
            System.out.println(o.toString());
        }
        System.out.println("\nTotal price: $"+String.format("%.2f",order.total()));

        sc.close();

    }
}
