package Base;

import java.io.Serializable;

public class LoanDecision implements Serializable {
    public String id_req;
    public String decision;

    public LoanDecision(String id_req, String decision) {
        this.id_req = id_req;
        this.decision = decision;
    }

    public LoanDecision()
    {

    }
}
