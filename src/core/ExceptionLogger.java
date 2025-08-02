package core;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ExceptionLogger {
    private static ExceptionLogger instance;
    private List<String> logs = new ArrayList<>();

    private ExceptionLogger() {}

    public static ExceptionLogger getInstance() {
        if (instance == null) {
            instance = new ExceptionLogger();
        }
        return instance;
    }

    public void log(String message) {
        logs.add(LocalDateTime.now() + ": " + message);
    }

    public List<String> getLogs() { return logs; }
}
