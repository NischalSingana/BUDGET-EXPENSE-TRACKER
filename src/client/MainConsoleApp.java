package client;
import core.*;
import java.time.LocalDate;
import java.util.*;

public class MainConsoleApp {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        BudgetManager manager = BudgetManager.getInstance();
        while (true) {
            printMainMenu();
            String input = scanner.nextLine().trim();
            switch (input) {
                case "1": handleCreateAccount(manager); break;
                case "2": handleAddMember(manager); break;
                case "3": handleAddIncome(manager); break;
                case "4": handleAddExpense(manager); break;
                case "5": handleAddBill(manager); break;
                case "6": handleListAccounts(manager); break;
                case "7": handleListExpenses(manager); break;
                case "8": handleListBills(manager); break;
                case "9": handleSplitExpense(manager); break;
                case "0": System.out.println("Goodbye!"); return;
                default: System.out.println("Invalid input. Try again.");
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\n=== Family Budget Tracker Menu ===");
        System.out.println("1.  Create Account");
        System.out.println("2.  Add Member");
        System.out.println("3.  Add Income");
        System.out.println("4.  Add Expense");
        System.out.println("5.  Add Bill");
        System.out.println("6.  List Accounts");
        System.out.println("7.  List Expenses (by Account)");
        System.out.println("8.  List Bills (by Account)");
        System.out.println("9.  Split Expense");
        System.out.println("0.  Exit");
        System.out.print("Select: ");
    }

    private static void handleCreateAccount(BudgetManager manager) {
        System.out.print("Enter Account ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Enter Account Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Is it JOINT or PERSONAL? ");
        AccountType type = AccountType.valueOf(scanner.nextLine().trim().toUpperCase());
        Account account = new Account(id, name, type);
        manager.addAccount(account);
        System.out.println("Account created.");
    }

    private static void handleAddMember(BudgetManager manager) {
        System.out.print("Enter Member Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine().trim();
        System.out.print("UserRole (ADMIN/MEMBER/GUEST): ");
        UserRole role = UserRole.valueOf(scanner.nextLine().trim().toUpperCase());
        MemberProfile member = new MemberProfile(username, pin, role);
        manager.addMember(member);
        System.out.println("Member added.");
    }

    private static void handleAddIncome(BudgetManager manager) {
        Account account = askAccount(manager);
        if (account == null) return;
        System.out.print("Enter source: ");
        String source = scanner.nextLine().trim();
        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        Income income = new Income(UUID.randomUUID().toString(), source, amount, LocalDate.now(), account.getAccountId());
        account.addIncome(income);
        System.out.println("Income added.");
    }

    private static void handleAddExpense(BudgetManager manager) {
        Account account = askAccount(manager);
        if (account == null) return;
        System.out.print("Enter payer username: ");
        String payer = scanner.nextLine().trim();
        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter description: ");
        String desc = scanner.nextLine().trim();
        System.out.print("Expense category (FOOD/TRAVEL/UTILITIES/EDUCATION/HEALTHCARE/OTHER): ");
        ExpenseCategory category = ExpenseCategory.valueOf(scanner.nextLine().trim().toUpperCase());
        Expense exp = new Expense(UUID.randomUUID().toString(), amount, LocalDate.now(), desc, category, payer, account.getAccountId(), false);
        account.addExpense(exp);
        System.out.println("Expense added.");
    }

    private static void handleAddBill(BudgetManager manager) {
        Account account = askAccount(manager);
        if (account == null) return;
        System.out.print("Enter bill name: ");
        String billName = scanner.nextLine().trim();
        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.print("Due date (yyyy-mm-dd): ");
        LocalDate due = LocalDate.parse(scanner.nextLine());
        System.out.print("Bill recurrence (ONE_TIME/MONTHLY/YEARLY): ");
        BillRecurrence recurrence = BillRecurrence.valueOf(scanner.nextLine().trim().toUpperCase());
        System.out.print("Payee username: ");
        String payee = scanner.nextLine().trim();
        Bill bill = new Bill(UUID.randomUUID().toString(), billName, amount, due, recurrence, payee);
        account.getBills().add(bill);
        System.out.println("Bill added.");
    }

    private static void handleListAccounts(BudgetManager manager) {
        int i = 1;
        for (Account acc : manager.getAllAccounts()) {
            System.out.printf("%d. %s%n", i++, acc);
        }
    }

    private static void handleListExpenses(BudgetManager manager) {
        Account account = askAccount(manager);
        if (account == null) return;
        int i = 1;
        for (Expense e : account.getExpenses()) {
            System.out.printf("%d. %s%n", i++, e);
        }
    }

    private static void handleListBills(BudgetManager manager) {
        Account account = askAccount(manager);
        if (account == null) return;
        int i = 1;
        for (Bill b : account.getBills()) {
            System.out.printf("%d. %s%n", i++, b);
        }
    }

    private static void handleSplitExpense(BudgetManager manager) {
        Account account = askAccount(manager);
        if (account == null) return;
        if (account.getExpenses().isEmpty()) {
            System.out.println("No expenses to split.");
            return;
        }
        System.out.println("Select expense to split:");
        int idx = 1;
        Map<Integer, Expense> map = new HashMap<>();
        for (Expense e : account.getExpenses()) {
            System.out.printf("%d. %s%n", idx, e);
            map.put(idx, e);
            idx++;
        }
        int sel = Integer.parseInt(scanner.nextLine());
        Expense exp = map.get(sel);
        Set<String> members = new HashSet<>();
        System.out.println("Enter usernames to split with (comma separated): ");
        for (String s : scanner.nextLine().split(",")) {
            members.add(s.trim());
        }
        SplitType splitType = selectSplitType();
        Map<String, Double> splitAmounts = null;
        switch (splitType) {
            case EQUAL:
                splitAmounts = new EqualSplitStrategy().split(exp.getAmount(), members);
                break;
            case PERCENT:
                Map<String, Double> percentMap = new LinkedHashMap<>();
                double totalPercent = 0.0;
                for (String m : members) {
                    System.out.printf("Enter percent for %s: ", m);
                    double pct = Double.parseDouble(scanner.nextLine());
                    percentMap.put(m, pct);
                    totalPercent += pct;
                }
                if (Math.abs(totalPercent - 100.0) > 0.01) {
                    System.out.println("Error: Percents must sum to 100.");
                    return;
                }
                splitAmounts = new PercentSplitStrategy(percentMap).split(exp.getAmount(), members);
                break;
            case ITEMIZED:
                Map<String, Double> itemMap = new LinkedHashMap<>();
                double total = 0.0;
                for (String m : members) {
                    System.out.printf("Enter amount for %s: ", m);
                    double amt = Double.parseDouble(scanner.nextLine());
                    itemMap.put(m, amt);
                    total += amt;
                }
                if (Math.abs(total - exp.getAmount()) > 0.01) {
                    System.out.println("Error: Itemized amounts must sum to total.");
                    return;
                }
                splitAmounts = new ItemizedSplitStrategy(itemMap).split(exp.getAmount(), members);
                break;
        }
        TransactionSplit split = new TransactionSplit(
                UUID.randomUUID().toString(),
                exp.getExpenseId(),
                members,
                splitType
        );
        split.setSplitAmounts(splitAmounts);
        account.getSplits().add(split);
        System.out.println("Expense split and recorded:");
        for (Map.Entry<String, Double> entry : splitAmounts.entrySet()) {
            System.out.printf("  %s: %.2f\n", entry.getKey(), entry.getValue());
        }
    }

    // --- Helpers below ---

    private static Account askAccount(BudgetManager manager) {
        List<Account> accs = new ArrayList<>(manager.getAllAccounts());
        if (accs.isEmpty()) {
            System.out.println("No accounts found. Please create one first.");
            return null;
        }
        System.out.println("Select Account:");
        for (int i = 0; i < accs.size(); i++)
            System.out.printf("%d. %s%n", i + 1, accs.get(i));
        int sel = Integer.parseInt(scanner.nextLine()) - 1;
        if (sel < 0 || sel >= accs.size()) {
            System.out.println("Invalid selection.");
            return null;
        }
        return accs.get(sel);
    }

    private static SplitType selectSplitType() {
        while (true) {
            System.out.print("Split Type - 1: EQUAL, 2: PERCENT, 3: ITEMIZED. Select: ");
            String input = scanner.nextLine();
            switch (input) {
                case "1": return SplitType.EQUAL;
                case "2": return SplitType.PERCENT;
                case "3": return SplitType.ITEMIZED;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
