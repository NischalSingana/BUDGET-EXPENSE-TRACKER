package core;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class EqualSplitStrategy implements SplitStrategy {

    @Override
    public Map<String, Double> split(double amount, Set<String> members) {
        Map<String, Double> split = new LinkedHashMap<>();
        if (members == null || members.isEmpty()) {
            throw new IllegalArgumentException("Members set cannot be null or empty.");
        }
        double perMember = Math.round((amount / members.size()) * 100.0) / 100.0;
        int i = 0;
        double sum = 0;
        for (String m : members) {
            if (++i == members.size()) {
                split.put(m, Math.round((amount - sum) * 100.0) / 100.0);
            } else {
                split.put(m, perMember);
                sum += perMember;
            }
        }
        return split;
    }
}
