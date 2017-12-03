package Base;

import java.io.Serializable;

public class Transfer implements Serializable {
    public String login;
    public String accNoFrom;
    public String accNoTo;
    public String amount;
    public String title;
    @Override
    public String toString() {
        return "Transfer [accNoFrom=" + accNoFrom + ", accNoTo=" + accNoTo + ", amount=" + amount + ", title=" + title
                + "]";
    }

}