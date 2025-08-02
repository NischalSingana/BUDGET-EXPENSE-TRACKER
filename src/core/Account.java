package core;
import java.io.Serializable;
import java.util.*;

public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    private String accountId;
    private String name;
    private AccountType type; 

    private Set<MemberProfile> members;
    private List<Budget> budgets;
    private List<Expense> expenses;
    private List<Income> incomes;
    private List<TransactionSplit> splits;
    private List<Bill> bills;
    private List<SavingsGoal> savingsGoals;

    private double balance;


    public Account() {
        this.members = new HashSet<>();
        this.budgets = new ArrayList<>();
        this.expenses = new ArrayList<>();
        this.incomes = new ArrayList<>();
        this.splits = new ArrayList<>();
        this.bills = new ArrayList<>();
        this.savingsGoals = new ArrayList<>();
    }

    // Main constructor
    public Account(String accountId, String name, AccountType type) {
        this();
        this.accountId = accountId;
        this.name = name;
        this.type = type;
    }

    // Getters and Setters
    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public AccountType getType() { return type; }
    public void setType(AccountType type) { this.type = type; }

    public Set<MemberProfile> getMembers() { return members; }
    public List<Budget> getBudgets() { return budgets; }
    public List<Expense> getExpenses() { return expenses; }
    public List<Income> getIncomes() { return incomes; }
    public List<TransactionSplit> getSplits() { return splits; }
    public List<Bill> getBills() { return bills; }
    public List<SavingsGoal> getSavingsGoals() { return savingsGoals; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public void addExpense(Expense e) {
        if (e != null) {
            expenses.add(e);
            this.balance -= e.getAmount();
        }
    }

    public void addIncome(Income i) {
        if (i != null) {
            incomes.add(i);
            this.balance += i.getAmount();
        }
    }

    public void addMember(MemberProfile m) {
        if (m != null) members.add(m);
    }

    public void removeMember(MemberProfile m) {
        members.remove(m);
    }

    @Override
    public String toString() {
        return String.format("Account{name=%s, type=%s, balance=%.2f}", name, type, balance);
    }
}

