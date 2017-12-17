package Base;

import java.io.Serializable;

public class Transfer implements Serializable {
    public String login;
    public String accNoFrom;
    public String accNoTo;
    public String amount;
    public String title;

    public Transfer(String login, String accNoFrom, String accNoTo, String amount, String title) {
        this.login = login;
        this.accNoFrom = accNoFrom;
        this.accNoTo = accNoTo;
        this.amount = amount;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Transfer [accNoFrom=" + accNoFrom + ", accNoTo=" + accNoTo + ", amount=" + amount + ", title=" + title
                + "]";
    }

}