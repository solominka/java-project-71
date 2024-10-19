package hexlet.code.model;

public class DiffEntry {
    private final String key;
    private final Object value;
    private final CompareResult compareResult;

    public DiffEntry(String key_, Object value_, CompareResult compareResult_) {
        key = key_;
        value = value_;
        compareResult =  compareResult_;
    }

    @Override
    public String toString() {

        return String.format("%s %s: %s", compareResult, key, value);
    }
}
