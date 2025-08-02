package core;

import java.io.Serializable;
import java.time.LocalDate;

public class Income implements Serializable {
    private String incomeId;
    private String source;
    private double amount;
    private LocalDate date;
    private String accountId;

    public Income(String incomeId, String source, double amount, LocalDate date, String accountId) {
        this.incomeId = incomeId;
        this.source = source;
        this.amount = amount;
        this.date = date;
        this.accountId = accountId;
    }

    public String getIncomeId() { return incomeId; }
    public void setIncomeId(String incomeId) { this.incomeId = incomeId; }
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }

    @Override
    public String toString() {
        return String.format(
            "Income{id='%s', source='%s', amount=%.2f, date=%s}",
            incomeId, source, amount, date
        );
    }
}
