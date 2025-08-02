package core;
import java.io.Serializable;
import java.time.LocalDateTime;

public class AuditLogEntry implements Serializable {
    private String action;
    private String username;
    private LocalDateTime timestamp;
    private String details;

    public AuditLogEntry(String action, String username, LocalDateTime timestamp, String details) {
        this.action = action;
        this.username = username;
        this.timestamp = timestamp;
        this.details = details;
    }

    public String getAction() { return action; }
    public String getUsername() { return username; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getDetails() { return details; }
    
    @Override
    public String toString() {
        return String.format("[%s] %s by %s: %s", timestamp, action, username, details);
    }
}
