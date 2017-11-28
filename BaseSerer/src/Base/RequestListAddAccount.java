package Base;

import java.io.Serializable;
import java.util.List;

public class RequestListAddAccount
        implements Serializable
{
    public String error;
    public List<AddAccountRequest> data;
    @Override
    public String toString() {
        return "RequestListAddAccount [error=" + error + ", data=" + data + "]";
    }

}
