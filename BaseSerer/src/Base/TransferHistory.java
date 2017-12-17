package Base;

import java.io.Serializable;

public class TransferHistory implements Serializable {
    String login;
    String date;

    public TransferHistory(String login, String date) {
        this.login = login;
        this.date = date;
    }

    public TransferHistory(){}
}
