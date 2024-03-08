//Author's name: Kiringodage Amanda
//Student ID:w1956091
import java.util.*;
import  java.io.*;
import java.util.ArrayList;
public class Theatre {
    static int[] row1 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    static int[] row2 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    static int[] row3 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    static int[][] seats = {row1, row2, row3};//creating a 2D array which includes all three rows
    static ArrayList<Ticket> tickets = new ArrayList<>();//creating an array list of tickets to save all the Tickets

    public static void main(String[] args) {
        System.out.println("Welcome to the New Theatre");
        boolean quit = false;
        while (!quit) {
            System.out.println("-".repeat(80));
            System.out.println("Please select an option: ");
            System.out.println("1) Buy a ticket");
            System.out.println("2) Print seating area");
            System.out.println("3) Cancel ticket ");
            System.out.println("4) List available seats");
            System.out.println("5) Save to file");
            System.out.println("6) Load from file");
            System.out.println("7) Print ticket information and total price");
            System.out.println("8) Sort tickets by price");
            System.out.println("\t 0) Quit ");
            System.out.println("-".repeat(80));
            int option = intValidator(0,8,"Enter an option: ");
            switch (option) {
                case 1:
                    buy_ticket();
                    break;
                case 2:
                    print_seating_area();
                    break;
                case 3:
                    cancel_ticket();
                    break;
                case 4:
                    show_available();
                    break;
                case 5:
                    save();
                    break;
                case 6:
                    load();
                    break;
                case 7:
                    show_tickets_info();
                    break;
                case 8:
                    sort_tickets(tickets);
                    break;
                case 0:
                    quit=true;
                    break;
                default:
                    System.out.println("Invalid option!!");
            }
        }
    }
    public static int intValidator(int min, int max, String userInput) {
        while (true) {
            Scanner input = new Scanner(System.in);
            try {
                System.out.print(userInput);
                int data = input.nextInt();
                if (min <= data && data <= max) {
                    return data;
                } else {
                    System.out.println("Invalid number!!");
                    System.out.println("Please select a number from " + min + " to " + max+".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer!!");
            }
        }
    }

    public static int priceValidator(String userInput){
        while (true){
            Scanner input = new Scanner(System.in);
            try{
                System.out.print(userInput);
                int Price = input.nextInt();
                if(0<Price){
                    return Price;
                }else{
                    System.out.println("Please enter a valid ticket price!!");
                }
            }catch (InputMismatchException e){
                System.out.println("Please enter an integer!!");
            }
        }
    }

    public static String emailValidator(String userInput){
        while (true){
            Scanner input = new Scanner(System.in);
            System.out.print(userInput);
            String email = input.nextLine();
            if(email.contains("@") && email.contains(".")){
                return email;
            }else {
                System.out.println("Invalid email!! Please check again.");
            }
        }
    }

    public static String userInput(String userInput) {
        Scanner input = new Scanner(System.in);
        System.out.print(userInput);
        return input.next();
    }

    public static boolean bookAndCancel(int row_no, int seat_no, boolean seat_book) {
        //booking a seat
        if (seat_book) {
            if (row_no == 1) {
                if (seats[0][seat_no - 1] == 0) {
                    seats[0][seat_no - 1] = 1;
                    return true;
                }
            }
            if (row_no == 2) {
                if (seats[1][seat_no - 1] == 0) {
                    seats[1][seat_no - 1] = 1;
                    return true;
                }
            }
            if (row_no == 3) {
                if (seats[2][seat_no - 1] == 0) {
                    seats[2][seat_no - 1] = 1;
                    return true;
                }
            }
        }
        //cancelling a booked seat
        else {
            if (row_no == 1) {
                if (seats[0][seat_no - 1] == 1) {
                    seats[0][seat_no - 1] = 0;
                    return true;
                }
            }
            if (row_no == 2) {
                if (seats[1][seat_no - 1] == 1) {
                    seats[1][seat_no - 1] = 0;
                    return true;
                }
            }
            if (row_no == 3) {
                if (seats[2][seat_no - 1] == 1) {
                    seats[2][seat_no - 1] = 0;
                    return true;
                }
            }
        }
        return false;
    }

    public static void buy_ticket() {
        int row = intValidator(1, 3, "Enter a row number:");
        int seat;
        if (row == 1) {
            seat = intValidator(1, 12, "Enter a seat number:");
        } else if (row == 2) {
            seat = intValidator(1, 16, "Enter a seat number:");
        } else {
            seat = intValidator(1, 20, "Enter a seat number:");
        }
        if (bookAndCancel(row, seat, true)) {
            String name = userInput("Enter your name:");
            String surname = userInput("Enter your surname:");
            String email = emailValidator("Enter your email: ");
            double price = priceValidator( "Enter the ticket price: Rs.");
            Person person = new Person(name, surname, email);
            Ticket ticket = new Ticket(row, seat, price, person);
            tickets.add(ticket);
            System.out.println("You have booked seat "+seat+" of row "+row+"!!");
        } else {
            System.out.println("This seat is already booked!!");
        }
    }

    public static void cancel_ticket() {
        int row = intValidator(1, 3, "Enter a row number:");
        int seat;
        if (row == 1) {
            seat = intValidator(1, 12, "Enter a seat number:");
        } else if (row == 2) {
            seat = intValidator(1, 16, "Enter a seat number:");
        } else {
            seat = intValidator(1, 20, "Enter a seat number:");
        }
        if (bookAndCancel(row, seat, false)) {
            int count = 0;
            for (Ticket ticket : tickets) {
                if (ticket.row == row && ticket.seat == seat) {
                    tickets.remove(count);
                    break;
                }
                count++;
            }
            System.out.println("You have canceled seat "+seat+" of row "+row+"!!");
        } else {
            System.out.println("This seat isn't booked");
        }
    }

    public static void print_seating_area() {
        System.out.println("\t"+"***********");
        System.out.println("\t"+"*  STAGE  *");
        System.out.println("\t"+"***********");

        for(int[] i: seats){
            int count=0;
            for(int j:i){
                if(j==1 && count==(i.length-2)/2){
                    System.out.print("X ");
                }else if(j==0 && count==(i.length-2)/2){
                    System.out.print("O ");
                }else if(j==1){
                    System.out.print("X");
                }else{
                    System.out.print("O");
                }
                count++;
            }
            System.out.println();
        }
    }

    public static void show_available() {
        System.out.print("Seats available in row 1:");
        for (int i = 0; i < 12; i++) {
            if (seats[0][i] == 0) {
                System.out.print((i + 1) + ",");
            }
        }
        System.out.println("\b");
        System.out.print("Seats available in row 2:");
        for (int i = 0; i < 16; i++) {
            if (seats[1][i] == 0) {
                System.out.print((i + 1) + ",");
            }
        }
        System.out.println("\b");
        System.out.print("Seats available in row 3:");
        for (int i = 0; i < 20; i++) {
            if (seats[2][i] == 0) {
                System.out.print((i + 1) + ",");
            }
        }
        System.out.println("\b");
    }

    public static void show_tickets_info() {
        double total_price = 0;
        for (Ticket ticket : tickets) {
            total_price+=ticket.price;
            ticket.print();
        }
        System.out.println("Total ticket price is Rs." + total_price);//printing the total price of tickets
    }

    public static void sort_tickets(ArrayList<Ticket> array) {

        int n = array.size();//Getting the size of the arraylist

        //Looping through the arraylist
        for (int i = 0; i < n - 1; i++) {

            int minIndex = i;// Finding the smallest element of the unsorted array
            for (int j = i + 1; j < n; j++)
                if (array.get(j).price < array.get(minIndex).price)
                    minIndex = j;

            // Swapping the smallest element with the first element
            Ticket temp = array.get(minIndex);
            array.set(minIndex, array.get(i));
            array.set(i, temp);
        }
        for (Ticket ticket : array) {
            ticket.print();
        }
    }

    public static void save(){
        //Saving the current state of the seating plan
        try {
            //Creating a file object called filename to write the data
            FileWriter writer = new FileWriter("filename.txt");
            for(int[] row:seats){
                //Looping through the seats (2D) array
                for(int seat:row){
                    if(seat==1){
                        writer.write("1");
                    }else{
                        writer.write("0");
                    }
                }
                writer.write("\n");
            }
            writer.close();
            System.out.println("Details successfully wrote to the file.");
        }catch (IOException e){
            //Handling errors that may occur while writing to the file
            System.out.println("An error occurred!");
        }
    }

    public static void load(){
        try{
            //Creating a file object called filename to be read
            File file = new File("filename.txt");
            Scanner fileReader = new Scanner(file);
            //Reading the file line by line
            while(fileReader.hasNextLine()){
                //Reading a line and storing it in String variable called stored_line
                String stored_line = fileReader.nextLine();
                System.out.println(stored_line);
            }
            fileReader.close();
        }catch (IOException e){
            //Handling errors that may occur while reading the file
            System.out.println("An error occurred while reading the file!");
            e.printStackTrace();
        }
    }
}