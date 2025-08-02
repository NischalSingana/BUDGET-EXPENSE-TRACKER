package core;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TransactionSplit implements Serializable {
    private String splitId;
    private String expenseId;
    private Set<String> memberUsernames;
    private SplitType splitType;
    private Map<String, Double> splitAmounts;
    private boolean settled;

    public TransactionSplit(String splitId, String expenseId, Set<String> memberUsernames, SplitType splitType) {
        this.splitId = splitId;
        this.expenseId = expenseId;
        this.memberUsernames = memberUsernames;
        this.splitType = splitType;
        this.splitAmounts = new HashMap<>();
        this.settled = false;
    }

    // Getters and Setters
    public String getSplitId() { return splitId; }
    public void setSplitId(String splitId) { this.splitId = splitId; }
    public String getExpenseId() { return expenseId; }
    public void setExpenseId(String expenseId) { this.expenseId = expenseId; }
    public Set<String> getMemberUsernames() { return memberUsernames; }
    public void setMemberUsernames(Set<String> memberUsernames) { this.memberUsernames = memberUsernames; }
    public SplitType getSplitType() { return splitType; }
    public void setSplitType(SplitType splitType) { this.splitType = splitType; }
    public Map<String, Double> getSplitAmounts() { return splitAmounts; }
    public void setSplitAmounts(Map<String, Double> splitAmounts) { this.splitAmounts = splitAmounts; }
    public boolean isSettled() { return settled; }
    public void setSettled(boolean settled) { this.settled = settled; }

    /**
     * Evenly split the given total among all members.
     */
    public void calculateEqualSplit(double total) {
        double perMember = total / memberUsernames.size();
        for (String username : memberUsernames) {
            splitAmounts.put(username, perMember);
        }
    }

    /**
     * Utility for custom/itemized splits.
     */
    public void setCustomSplit(Map<String, Double> customSplits) {
        this.splitAmounts.clear();
        this.splitAmounts.putAll(customSplits);
    }

    @Override
    public String toString() {
        return String.format(
            "TransactionSplit{id='%s', type=%s, splitAmounts=%s, settled=%b}",
            splitId, splitType, splitAmounts, settled);
    }
}
