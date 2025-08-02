package core;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Expense implements Serializable {
    private String expenseId;
    private double amount;
    private LocalDate date;
    private String description;
    private ExpenseCategory category;
    private Set<String> tags;
    private String payerUsername;
    private String accountId;
    private boolean recurring;
    private ExpenseStatus status;

    public Expense(String expenseId, double amount, LocalDate date, String description,
                   ExpenseCategory category, String payerUsername, String accountId,
                   boolean recurring) {
        this.expenseId = expenseId;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.category = category;
        this.tags = new HashSet<>();
        this.payerUsername = payerUsername;
        this.accountId = accountId;
        this.recurring = recurring;
        this.status = ExpenseStatus.PENDING;
    }

    // Getters and Setters
    public String getExpenseId() { return expenseId; }
    public void setExpenseId(String expenseId) { this.expenseId = expenseId; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public ExpenseCategory getCategory() { return category; }
    public void setCategory(ExpenseCategory category) { this.category = category; }
    public Set<String> getTags() { return tags; }
    public void setTags(Set<String> tags) { this.tags = tags; }
    public void addTag(String tag) { this.tags.add(tag); }
    public String getPayerUsername() { return payerUsername; }
    public void setPayerUsername(String payerUsername) { this.payerUsername = payerUsername; }
    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }
    public boolean isRecurring() { return recurring; }
    public void setRecurring(boolean recurring) { this.recurring = recurring; }
    public ExpenseStatus getStatus() { return status; }
    public void setStatus(ExpenseStatus status) { this.status = status; }

    public void clear() { this.status = ExpenseStatus.CLEARED; }

    @Override
    public String toString() {
        return String.format(
            "Expense{id='%s', amount=%.2f, date=%s, category=%s, status=%s}",
            expenseId, amount, date, category, status);
    }
}

