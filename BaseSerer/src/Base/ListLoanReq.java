package Base;

import java.io.Serializable;
import java.util.List;

public class ListLoanReq implements Serializable {
    public List<LoanReq> loanList;
    public String error;

    public ListLoanReq() {
    }

    @Override
    public String toString() {
        return "ListLoanReq{" +
                "loanList=" + loanList +
                ", error='" + error + '\'' +
                '}';
    }
}
