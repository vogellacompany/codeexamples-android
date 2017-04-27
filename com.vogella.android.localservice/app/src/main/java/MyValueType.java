import java.util.List;

public class MyValueType {
    private final int i;
    public final List<String> values;

    public MyValueType(int i, List<String> values) {
        this.i = i;
        this.values = values;
    }
}
