package core;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ReminderTask implements Serializable {
    private String reminderId;
    private LocalDateTime remindAt;
    private String message;
    private boolean triggered;

    public ReminderTask(String reminderId, LocalDateTime remindAt, String message) {
        this.reminderId = reminderId;
        this.remindAt = remindAt;
        this.message = message;
        this.triggered = false;
    }

    // Getters and Setters
    public String getReminderId() { return reminderId; }
    public void setReminderId(String reminderId) { this.reminderId = reminderId; }
    public LocalDateTime getRemindAt() { return remindAt; }
    public void setRemindAt(LocalDateTime remindAt) { this.remindAt = remindAt; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public boolean isTriggered() { return triggered; }
    public void setTriggered(boolean triggered) { this.triggered = triggered; }

    public void trigger() { this.triggered = true; }

    @Override
    public String toString() {
        return String.format("Reminder{id='%s', at=%s, triggered=%b}", reminderId, remindAt, triggered);
    }
}
