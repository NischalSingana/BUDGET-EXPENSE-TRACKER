package core;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Bill implements Serializable {
    private static final long serialVersionUID = 1L;

    private String billId;
    private String name;
    private double amount;
    private LocalDate dueDate;
    private BillRecurrence recurrence;
    private BillStatus status;
    private String payeeUsername;
    private List<String> receipts;
    private List<ReminderTask> reminders;

    public Bill() {
        this.receipts = new ArrayList<>();
        this.reminders = new ArrayList<>();
        this.status = BillStatus.UPCOMING;
    }

    public Bill(String billId, String name, double amount, LocalDate dueDate,
                BillRecurrence recurrence, String payeeUsername) {
        this();
        this.billId = billId;
        this.name = name;
        this.amount = amount;
        this.dueDate = dueDate;
        this.recurrence = recurrence;
        this.payeeUsername = payeeUsername;
    }

    // Getters and Setters
    public String getBillId() { return billId; }
    public void setBillId(String billId) { this.billId = billId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public BillRecurrence getRecurrence() { return recurrence; }
    public void setRecurrence(BillRecurrence recurrence) { this.recurrence = recurrence; }
    public BillStatus getStatus() { return status; }
    public void setStatus(BillStatus status) { this.status = status; }
    public String getPayeeUsername() { return payeeUsername; }
    public void setPayeeUsername(String payeeUsername) { this.payeeUsername = payeeUsername; }
    public List<String> getReceipts() { return receipts; }
    public void addReceipt(String receipt) { this.receipts.add(receipt); }
    public List<ReminderTask> getReminders() { return reminders; }
    public void addReminder(ReminderTask reminder) { this.reminders.add(reminder); }

    public boolean isOverdue() {
        return (status != BillStatus.PAID) && LocalDate.now().isAfter(dueDate);
    }

    public void pay() {
        this.status = BillStatus.PAID;
    }

    @Override
    public String toString() {
        return String.format("Bill{id='%s', name='%s', amount=%.2f, due=%s, status=%s}",
                billId, name, amount, dueDate, status);
    }
}
