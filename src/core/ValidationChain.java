package core;
public abstract class ValidationChain {
    private ValidationChain next;

    public ValidationChain linkWith(ValidationChain next) {
        this.next = next;
        return next;
    }

    public boolean validate(Object input) {
        if (!check(input)) return false;
        return next == null || next.validate(input);
    }

    protected abstract boolean check(Object input);
}
