import java.util.*;
import java.io.*;
import java.text.*;

class Expense {
    private Date date;
    private String category;
    private double amount;

    public Expense(Date date, String category, double amount) {
        this.date = date;
        this.category = category;
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return "Date: " + dateFormat.format(date) + ", Category: " + category + ", Amount: $" + amount;
    }
}

class ExpenseTracker {
    private List<Expense> expenses;

    public ExpenseTracker() {
        expenses = new ArrayList<>();
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public double getTotalExpenseForCategory(String category) {
        double total = 0;
        for (Expense expense : expenses) {
            if (expense.getCategory().equalsIgnoreCase(category)) {
                total += expense.getAmount();
            }
        }
        return total;
    }

    public void saveExpensesToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            for (Expense expense : expenses) {
                writer.println(expense);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error saving expenses to file: " + e.getMessage());
        }
    }

    public void loadExpensesFromFile(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(", ");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormat.parse(parts[0].substring(6));
                String category = parts[1].substring(10);
                double amount = Double.parseDouble(parts[2].substring(8));
                expenses.add(new Expense(date, category, amount));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
        }
    }
}

public class Expense_Tracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseTracker tracker = new ExpenseTracker();

        while (true) {
            System.out.println("1. Add Expense\n2. View Expenses\n3. Save Expenses\n4. Load Expenses\n5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter date (yyyy-MM-dd): ");
                    String dateString = scanner.nextLine();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date date = dateFormat.parse(dateString);
                        System.out.print("Enter category: ");
                        String category = scanner.nextLine();
                        System.out.print("Enter amount: ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        tracker.addExpense(new Expense(date, category, amount));
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                    }
                    break;
                case 2:
                    List<Expense> expenses = tracker.getExpenses();
                    for (Expense expense : expenses) {
                        System.out.println(expense);
                    }
                    break;
                case 3:
                    System.out.print("Enter file name to save: ");
                    String saveFileName = scanner.nextLine();
                    tracker.saveExpensesToFile(saveFileName);
                    break;
                case 4:
                    System.out.print("Enter file name to load: ");
                    String loadFileName = scanner.nextLine();
                    tracker.loadExpensesFromFile(loadFileName);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
