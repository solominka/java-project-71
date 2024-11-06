package hexlet.code.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DiffEntry {
    private String key;
    private Object value;
    private Object oldValue;
    private CompareResult compareResult;
}
