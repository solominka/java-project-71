package hexlet.code.model;

public enum CompareResult {
    EQUALS(" "),
    ADDED("+"),
    REMOVED("-");

    private final String value;

    CompareResult(String newValue) {
        value = newValue;
    }

    @Override
    public String toString() {
        return value;
    }
}
