package core;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class PercentSplitStrategy implements SplitStrategy {
    private final Map<String, Double> percents;

    /**
     * @param percents Map from member usernames to their percent responsibility (should sum to 100.0)
     */
    public PercentSplitStrategy(Map<String, Double> percents) {
        if (percents == null || percents.isEmpty())
            throw new IllegalArgumentException("Percent map cannot be null or empty.");
        double total = percents.values().stream().mapToDouble(Double::doubleValue).sum();
        if (Math.abs(total - 100.0) > 0.01)
            throw new IllegalArgumentException("Percent split: percentages must sum to 100.");
        this.percents = new LinkedHashMap<>(percents);
    }

    @Override
    public Map<String, Double> split(double amount, Set<String> members) {
        if (!members.equals(percents.keySet()))
            throw new IllegalArgumentException("Members in percent map must match members set.");
        Map<String, Double> split = new LinkedHashMap<>();
        double runningTotal = 0.0;
        int i = 0;
        for (String member : members) {
            double percent = percents.getOrDefault(member, 0.0);
            double value;
            if (++i == members.size()) {
                // assign remainder to last to deal with rounding (so sum is always exact)
                value = Math.round((amount - runningTotal) * 100.0) / 100.0;
            } else {
                value = Math.round((amount * percent / 100.0) * 100.0) / 100.0;
                runningTotal += value;
            }
            split.put(member, value);
        }
        return split;
    }
}
