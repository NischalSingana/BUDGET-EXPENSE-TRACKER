package core;
import java.io.Serializable;
import java.util.*;
import java.time.LocalDateTime;

public class BudgetManager implements Serializable {
    private static volatile BudgetManager instance;


    private Map<String, Account> accounts;
    private Map<String, MemberProfile> memberProfiles;
    private Map<String, Budget> budgets;
    private List<AuditLogEntry> auditLogs;

    private BudgetManager() {
        accounts = new HashMap<>();
        memberProfiles = new HashMap<>();
        budgets = new HashMap<>();
        auditLogs = new ArrayList<>();
    }

    public static BudgetManager getInstance() {
        if (instance == null) {
            synchronized (BudgetManager.class) {
                if (instance == null) {
                    instance = new BudgetManager();
                }
            }
        }
        return instance;
    }

    // ----- Account Methods -----
    public void addAccount(Account account) {
        if (account != null) {
            accounts.put(account.getAccountId(), account);
        }
    }

    public Account getAccount(String accountId) {
        return accounts.get(accountId);
    }

    public Collection<Account> getAllAccounts() {
        return Collections.unmodifiableCollection(accounts.values());
    }

    public void removeAccount(String accountId) {
        accounts.remove(accountId);
    }

    public void addMember(MemberProfile member) {
        if (member != null) {
            memberProfiles.put(member.getUsername(), member);
        }
    }

    public MemberProfile getMember(String username) {
        return memberProfiles.get(username);
    }

    public Collection<MemberProfile> getAllMembers() {
        return Collections.unmodifiableCollection(memberProfiles.values());
    }

    // ----- Budget Methods -----
    public void addBudget(Budget budget) {
        if (budget != null) {
            budgets.put(budget.getBudgetId(), budget);
        }
    }

    public Budget getBudget(String budgetId) {
        return budgets.get(budgetId);
    }

    public Collection<Budget> getAllBudgets() {
        return Collections.unmodifiableCollection(budgets.values());
    }

    public void logAudit(AuditLogEntry audit) {
        if (audit != null) {
            auditLogs.add(audit);
        }
    }

    public List<AuditLogEntry> getAuditLogs() {
        return Collections.unmodifiableList(auditLogs);
    }

    public void clearAll() {
        accounts.clear();
        memberProfiles.clear();
        budgets.clear();
        auditLogs.clear();
    }
}
