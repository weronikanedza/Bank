package Base;

import java.io.Serializable;

public class Loan implements Serializable{
    public String login;
    public String amount;
    public String instalment; // rate in a month
    public String numberOfMonths;
    public String bankRate; //5%
    public String salary;
    public String date;
    public String dateTo;
    public String error;

    public Loan(String login, String amount, String instalment, String numberOfMonths, String bankRate, String salary, String date, String dateTo, String error) {
        this.login = login;
        this.amount = amount;
        this.instalment = instalment;
        this.numberOfMonths = numberOfMonths;
        this.bankRate = bankRate;
        this.salary = salary;
        this.date = date;
        this.dateTo = dateTo;
        this.error = error;
    }

    public Loan(String login, String amount, String instalment, String numberOfMonths, String bankRate, String salary) {
        this.login = login;
        this.amount = amount;
        this.instalment = instalment;
        this.numberOfMonths = numberOfMonths;
        this.bankRate = bankRate;
        this.salary = salary;
    }

    public Loan() {
    }

    @Override
    public String toString() {
        return "Loan{" +
                "login='" + login + '\'' +
                ", amount='" + amount + '\'' +
                ", instalment='" + instalment + '\'' +
                ", numberOfMonths='" + numberOfMonths + '\'' +
                ", bankRate='" + bankRate + '\'' +
                ", salary='" + salary + '\'' +
                ", date='" + date + '\'' +
                ", dateTo='" + dateTo + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
