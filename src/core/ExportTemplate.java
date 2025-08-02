package core;
import java.util.List;

public abstract class ExportTemplate<T> {
    public final String export(List<T> data) {
        String header = makeHeader();
        String body = makeBody(data);
        String footer = makeFooter();
        return header + body + footer;
    }
    protected abstract String makeHeader();
    protected abstract String makeBody(List<T> data);
    protected abstract String makeFooter();
}
