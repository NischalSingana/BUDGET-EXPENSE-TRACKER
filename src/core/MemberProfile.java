package core;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class MemberProfile implements Serializable {
    private String username;
    private String pin;
    private UserRole role;
    private LocalDateTime lastLogin;
    private Set<Account> accounts = new HashSet<>();
    private Set<String> permissions = new HashSet<>();
    
   
    public MemberProfile(String username, String pin, UserRole role) {
        this.username = username;
        this.pin = pin;
        this.role = role;
        this.lastLogin = LocalDateTime.now();
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPin() { return pin; }
    public void setPin(String pin) { this.pin = pin; }
    public UserRole getRole() { return role; }
    public void setRole(UserRole role) { this.role = role; }
    public LocalDateTime getLastLogin() { return lastLogin; }
    public void setLastLogin(LocalDateTime lastLogin) { this.lastLogin = lastLogin; }
    public Set<Account> getAccounts() { return accounts; }
    public Set<String> getPermissions() { return permissions; }

    public void addAccount(Account acc) { accounts.add(acc); }
    public void removeAccount(Account acc) { accounts.remove(acc); }

    @Override
    public String toString() {
        return String.format("MemberProfile{username=%s, role=%s, lastLogin=%s}", username, role, lastLogin);
    }
}
