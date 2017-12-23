package Base;
import java.io.Serializable;

public class AddAccReqDecision
        implements Serializable{
    public String decision;
    public String id_req;
    public PersonalData personalData;
    @Override
    public String toString() {
        return "AddAccReqDecision [decision=" + decision + ", id_req=" + id_req + "]";
    }

}
