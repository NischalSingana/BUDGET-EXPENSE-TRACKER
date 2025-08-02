package core;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class ItemizedSplitStrategy implements SplitStrategy {
    private final Map<String, Double> amounts;
    public ItemizedSplitStrategy(Map<String, Double> amounts) {
        if (amounts == null || amounts.isEmpty())
            throw new IllegalArgumentException("Amounts map cannot be null or empty.");
        this.amounts = new LinkedHashMap<>(amounts);
    }

    @Override
    public Map<String, Double> split(double amount, Set<String> members) {
        if (!members.equals(amounts.keySet()))
            throw new IllegalArgumentException("Members in itemized map must match members set.");
        double sum = amounts.values().stream().mapToDouble(Double::doubleValue).sum();
        if (Math.abs(sum - amount) > 0.01)
            throw new IllegalArgumentException(
                "Itemized split: given amounts (" + sum + ") do not match total amount (" + amount + ")."
            );
        // Return a copy to avoid mutation
        return new LinkedHashMap<>(amounts);
    }
}
