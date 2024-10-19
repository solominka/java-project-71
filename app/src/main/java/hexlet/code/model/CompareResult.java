package hexlet.code.model;

public enum CompareResult {
    EQUALS(" "),
    ADDED("+"),
    REMOVED("-");

    private final String value;

    CompareResult(String value_) {
        value = value_;
    }

    @Override
    public String toString() {
        return value;
    }
}