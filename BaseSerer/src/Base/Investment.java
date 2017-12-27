package Base;

import java.io.Serializable;

public class Investment implements Serializable {
    public String amount;
    public String time;
    public String login;

    public Investment() {
    }

    public Investment(String amount, String time, String login) {
        this.amount = amount;
        this.time = time;
        this.login = login;
    }


    @Override
    public String toString() {
        return "Investment{" +
                "amount='" + amount + '\'' +
                ", time='" + time + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
