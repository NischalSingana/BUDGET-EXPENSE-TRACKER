package core;
import java.io.Serializable;
import java.time.LocalDate;

public class Budget implements Serializable {
    private String budgetId;
    private double amount;
    private String category;
    private LocalDate startPeriod;
    private LocalDate endPeriod;
    private String accountId;
    private double utilized;

    public Budget(String budgetId, double amount, String category, LocalDate start, LocalDate end, String accountId) {
        this.budgetId = budgetId;
        this.amount = amount;
        this.category = category;
        this.startPeriod = start;
        this.endPeriod = end;
        this.accountId = accountId;
        this.utilized = 0.0;
    }

    public String getBudgetId() { return budgetId; }
    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public LocalDate getStartPeriod() { return startPeriod; }
    public LocalDate getEndPeriod() { return endPeriod; }
    public String getAccountId() { return accountId; }
    public double getUtilized() { return utilized; }

    public void addUtilization(double amount) { this.utilized += amount; }
    public boolean isExceeded() { return utilized > amount; }
}

