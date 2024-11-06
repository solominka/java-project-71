package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.model.DiffEntry;

import java.util.List;
import java.util.Map;

public class JsonFormatter {
    public static String format(List<DiffEntry> results) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(results);
    }
}
