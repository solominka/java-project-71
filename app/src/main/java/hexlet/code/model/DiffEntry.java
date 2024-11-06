package hexlet.code.model;

public class DiffEntry {
    private final String key;
    private final Object value;
    private final CompareResult compareResult;

    public DiffEntry(String newKey, Object newValue, CompareResult newCompareResult) {
        key = newKey;
        value = newValue;
        compareResult =  newCompareResult;
    }

    @Override
    public String toString() {

        return String.format("%s %s: %s", compareResult, key, value);
    }
}
