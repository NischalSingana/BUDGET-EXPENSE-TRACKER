package core;
public class BillDecorator extends Bill {
    protected Bill bill;

    public BillDecorator(Bill bill) {
        super(bill.getBillId(), bill.getName(), bill.getAmount(), bill.getDueDate(), bill.getRecurrence(), bill.getPayeeUsername());
        this.bill = bill;
    }

    @Override
    public BillStatus getStatus() {
        if (isOverdue()) return BillStatus.OVERDUE;
        return bill.getStatus();
    }
}
