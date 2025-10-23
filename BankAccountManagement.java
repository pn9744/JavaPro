import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BankAccountManagement {
    static class Account {
        String owner;
        double balance;
        Account(String owner) { this.owner = owner; this.balance = 0.0; }
    }

    private final Map<Integer, Account> accounts = new HashMap<>();
    private int nextId = 1;

    public static void main(String[] args) {
        new BankAccountManagement().run();
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nBank Account Management");
            System.out.println("1. Create account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Balance");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            String c = sc.nextLine().trim();
            switch (c) {
                case "1" -> create(sc);
                case "2" -> deposit(sc);
                case "3" -> withdraw(sc);
                case "4" -> balance(sc);
                case "5" -> { System.out.println("Bye."); sc.close(); return; }
                default -> System.out.println("Invalid");
            }
        }
    }

    private void create(Scanner sc) {
        System.out.print("Owner name: ");
        String o = sc.nextLine().trim();
        int id = nextId++;
        accounts.put(id, new Account(o));
        System.out.println("Account created. ID=" + id);
    }

    private void deposit(Scanner sc) {
        System.out.print("Account id: ");
        int id = readInt(sc);
        Account a = accounts.get(id);
        if (a == null) { System.out.println("Not found"); return; }
        System.out.print("Amount: ");
        double amt = readDouble(sc);
        if (amt <= 0) { System.out.println("Invalid amount"); return; }
        a.balance += amt;
        System.out.println("Deposited. New balance=" + a.balance);
    }

    private void withdraw(Scanner sc) {
        System.out.print("Account id: ");
        int id = readInt(sc);
        Account a = accounts.get(id);
        if (a == null) { System.out.println("Not found"); return; }
        System.out.print("Amount: ");
        double amt = readDouble(sc);
        if (amt <= 0 || amt > a.balance) { System.out.println("Invalid/Insufficient funds"); return; }
        a.balance -= amt;
        System.out.println("Withdrawn. New balance=" + a.balance);
    }

    private void balance(Scanner sc) {
        System.out.print("Account id: ");
        int id = readInt(sc);
        Account a = accounts.get(id);
        if (a == null) { System.out.println("Not found"); return; }
        System.out.println("Owner: " + a.owner + " Balance: " + a.balance);
    }

    private int readInt(Scanner sc) {
        try { return Integer.parseInt(sc.nextLine().trim()); } catch (Exception e) { return -1; }
    }

    private double readDouble(Scanner sc) {
        try { return Double.parseDouble(sc.nextLine().trim()); } catch (Exception e) { return -1; }
    }
}
