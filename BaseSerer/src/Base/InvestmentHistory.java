package Base;

import java.io.Serializable;

public class InvestmentHistory implements Serializable{
    public String amount;
    public String dateFrom;
    public String dateTo;
    public String rate;
    public String status;
    public String finalAmount;

    public InvestmentHistory() {
    }

    public InvestmentHistory(String amount, String dateFrom, String dateTo, String rate, String status, String finalAmount) {
        this.amount = amount;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.rate = rate;
        this.status = status;
        this.finalAmount = finalAmount;
    }

    @Override
    public String toString() {
        return "InvestmentHistory{" +
                "amount='" + amount + '\'' +
                ", dateFrom='" + dateFrom + '\'' +
                ", dateTo='" + dateTo + '\'' +
                ", rate='" + rate + '\'' +
                ", status='" + status + '\'' +
                ", finalAmount='" + finalAmount + '\'' +
                '}';
    }
}
