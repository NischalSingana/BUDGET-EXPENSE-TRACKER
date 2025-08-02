package core;
import java.util.Map;
import java.util.Set;
public interface SplitStrategy {
    Map<String, Double> split(double amount, Set<String> members);
}
