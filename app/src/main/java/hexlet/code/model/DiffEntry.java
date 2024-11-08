package hexlet.code.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DiffEntry {
    private String key;
    private Object value2;
    private Object value1;
    private CompareResult compareResult;
}
