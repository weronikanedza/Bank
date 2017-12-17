package ComputingServer;

import ComputingServer.MailSend;

public class Mail
{
    public static void main(String[] args) {


        MailSend newMail = new MailSend();
        String RECIPIENT = "mejl@gmail.com";

        String[] to = { RECIPIENT }; // list of recipient email addresses

        String subject = "NEW ACCOUNT";
        String body = "YOUR NEW ACCOUNT HAS BEEN CREATED!";

        newMail.sendFromGMail(to, subject, body);
    }


}