import java.util.ArrayList;
import java.util.List;

/**
 * abstract class
 */
abstract class Operation {
    private String operationType;
    private List<String> data;

    public Operation(List<String> list) {
        data=new ArrayList<>(list);
        operationType=list.get(0);
    }

    public String getOperationType() {
        return operationType;
    }

    public List<String> getData() {
        return data;
    }
}
