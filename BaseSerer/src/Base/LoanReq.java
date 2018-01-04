package Base;

import java.io.Serializable;

public class LoanReq extends Loan implements Serializable{

    public String id_req;
    public PersonalData personalData;

    public LoanReq(String login, String amount, String instalment, String numberOfMonths, String bankRate, String salary, String id_req,PersonalData personalData) {
        super(login, amount, instalment, numberOfMonths, bankRate, salary);
        this.id_req = id_req;
        this.personalData=personalData;
    }


    public LoanReq(){

    }

    @Override
    public String toString() {
        return "LoanReq{" +
                "id_req='" + id_req + '\'' +
                ", login='" + login + '\'' +
                ", amount='" + amount + '\'' +
                ", instalment='" + instalment + '\'' +
                ", numberOfMonths='" + numberOfMonths + '\'' +
                ", bankRate='" + bankRate + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }
}

