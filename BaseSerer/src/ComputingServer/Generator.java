package ComputingServer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Generator {
    private Statement statement=null;
    private ResultSet rS;

    public Generator(Statement statement) {
        this.statement = statement;
    }

    public String generateDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        System.out.println(dtf.format(localDate));
        return "" +dtf.format(localDate);
    }

    public String generatePassword() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 4) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        saltStr+="A1";
        return saltStr;
    }

    public String generateLogin() throws SQLException {
        Random random= new Random();
        while(true) {
            int login = random.nextInt(99999) + 1;
            String strLogin = Integer.toString(login);
            try {
                rS=statement.executeQuery("Select * from users where login='" + strLogin + "'");
                rS.next();
                rS.getString("login");
            }catch (Exception e){
                System.out.println("generateLogin exception");
                System.out.println(e.getMessage());
                return strLogin;
            }
        }
    }
    public String generateAccNr() throws SQLException {
        Random random= new Random();
        while(true) {
            int accNr = random.nextInt(999999999) + 1;
            String strAccNr = Integer.toString(accNr);
            try {
                rS=statement.executeQuery("Select * from account where id_account='" + strAccNr + "'");
                rS.next();
                rS.getString("balance");
            }catch (Exception e){
                System.out.println("generateAccNr exception");
                System.out.println(e.getMessage());
                return strAccNr;
            }
        }
    }
}
