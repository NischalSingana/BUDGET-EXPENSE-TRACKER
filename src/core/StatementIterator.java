package core;
import java.util.Iterator;
import java.util.List;

public class StatementIterator<T> implements Iterator<T> {
    private List<T> list;
    private int index = 0;
    
    public StatementIterator(List<T> list) { this.list = list; }
    public boolean hasNext() { return index < list.size(); }
    public T next() { return list.get(index++); }
}
