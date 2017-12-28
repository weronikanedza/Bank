package Base;

import java.io.Serializable;
import java.util.List;

public class ListInvestment implements Serializable {
    public List<InvestmentHistory> list;
    public String error;

    @Override
    public String toString() {
        return "ListInvestment{" +
                "list=" + list +
                ", error='" + error + '\'' +
                '}';
    }
}