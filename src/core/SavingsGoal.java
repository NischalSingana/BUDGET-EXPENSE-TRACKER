package core;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SavingsGoal implements Serializable {
    private String goalId;
    private String name;
    private double targetAmount;
    private String accountId;
    private double progress;
    private LocalDate targetDate;
    private List<String> milestones;

    public SavingsGoal(String goalId, String name, double targetAmount, String accountId, LocalDate targetDate) {
        this.goalId = goalId;
        this.name = name;
        this.targetAmount = targetAmount;
        this.accountId = accountId;
        this.progress = 0;
        this.targetDate = targetDate;
        this.milestones = new ArrayList<>();
    }

    // Getters and Setters
    public String getGoalId() { return goalId; }
    public void setGoalId(String goalId) { this.goalId = goalId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getTargetAmount() { return targetAmount; }
    public void setTargetAmount(double targetAmount) { this.targetAmount = targetAmount; }
    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }
    public double getProgress() { return progress; }
    public void setProgress(double progress) { this.progress = progress; }
    public LocalDate getTargetDate() { return targetDate; }
    public void setTargetDate(LocalDate targetDate) { this.targetDate = targetDate; }
    public List<String> getMilestones() { return milestones; }
    public void addMilestone(String milestone) { this.milestones.add(milestone); }

    public boolean isAchieved() {
        return progress >= targetAmount;
    }

    public double getProgressPercent() {
        return (targetAmount == 0) ? 0 : (progress / targetAmount) * 100;
    }

    @Override
    public String toString() {
        return String.format("SavingsGoal{id='%s', name='%s', progress=%.2f/%.2f (%.1f%%)}",
                goalId, name, progress, targetAmount, getProgressPercent());
    }
}
