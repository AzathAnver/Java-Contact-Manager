package re;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Contact {
    private String name;
    private String phone;
    private String email;

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}

public class ContactManager {

    private static ArrayList<Contact> contacts = new ArrayList<>();

    public static void main(String[] args) {
    	int choice;
    	Scanner scanner = new Scanner(System.in);
    	do {
    	    System.out.println("1. Add Contact");
    	    System.out.println("2. Search Contact");
    	    System.out.println("3. Delete Contact");
    	    System.out.println("4. List Contacts");
    	    System.out.println("5. Exit");
    	    System.out.print("Enter your choice: ");
    	    choice = scanner.nextInt();

    	    switch (choice) {
    	        case 1:
    	            addContact();
    	            break;
    	        case 2:
    	            searchContact();
    	            break;
    	        case 3:
    	            deleteContact();
    	            break;
    	        case 4:
    	            listContacts();
    	            break;
    	        case 5:
    	            break;
    	        default:
    	            System.out.println("Invalid choice");
    	    }
    	} while (choice != 5);


        //scanner.close();
    }

    private static void addContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        Contact newContact = new Contact(name, phone, email);
        contacts.add(newContact);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("contacts.txt", true))) {
            writer.write(name + "," + phone + "," + email + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
       // scanner.close();
    }

    private static void searchContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();
        try (BufferedReader reader = new BufferedReader(new FileReader("contacts.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(name)) {
                    System.out.println("Name: " + parts[0]);
                    System.out.println("Phone: " + parts[1]);
                    System.out.println("Email: " + parts[2]);
                    break;
                    }
                    }
                    } catch (IOException e) {
                    e.printStackTrace();
                    }
                  //  scanner.close();
                    }

    private static void deleteContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name to delete: ");
        String name = scanner.nextLine();
        try (BufferedReader reader = new BufferedReader(new FileReader("contacts.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("temp.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (!parts[0].equals(name)) {
                    writer.write(line + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        java.io.File oldFile = new java.io.File("contacts.txt");
        java.io.File newFile = new java.io.File("temp.txt");
        if (!oldFile.delete()) {
            System.out.println("Could not delete file");
        }
        if (!newFile.renameTo(oldFile)) {
            System.out.println("Could not rename file");
        }
      //  scanner.close();
    }

private static void listContacts() {
    try (BufferedReader reader = new BufferedReader(new FileReader("contacts.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            System.out.println("Name: " + parts[0]);
            System.out.println("Phone: " + parts[1]);
            System.out.println("Email: " + parts[2]);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
