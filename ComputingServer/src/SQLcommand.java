import com.sun.corba.se.impl.orbutil.threadpool.TimeoutException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**The class implements functions which gather data
 * from sqlite database
 */
public class SQLcommand {
    private Statement statement;
    private List<String> list;
    private String sql;
    private String login;

    public SQLcommand(Statement statement,List<String>list) {
        this.statement = statement;
        this.list=new ArrayList<>(list);
        login=list.get(1);
    }

    /**
     *Log in function allows person to log in and use application
     * @return list with error code and status, when error code equals 0 then there is no another param
     * @throws SQLException
     */
    public List<String> logIn() throws SQLException {
        sql="Select status from users where login= '3' and password= 'k3'";
        try {
            ResultSet rS= statement.executeQuery("Select password from users where login='"+list.get(1)+"' and password='"+list.get(2)+"'" );
            rS.next();

            if(list.get(2).equals(rS.getString("password"))){ //checks password capital/small letters
            statement.executeQuery("Select status from users where login='"+list.get(1)+"' and password='"+list.get(2)+"'" );
            list.clear();

            if(rS.next()) {
                list.add("0");
                list.add(rS.getString("status"));
                list.add(login);

                if(list.get(1).equals("C") && !(balance().equals(""))) { //add balance if client is a user
                    list.add(balance());
                }
                 }else {
                    throw new Exception();
                }
            }else throw new Exception();
        } catch(SQLException e){
            list.clear();
            System.out.println("resultset exception");
            System.out.println(e.getMessage());
            list.add("1");
        } catch (Exception e){
            list.clear();
            System.out.println("There is no that person in database");
            list.add("1");
        }
        return list;
    }

    /**
     * transfer function is responsible for transfers between accounts
     * @return list with error code 0-transfer approved / 1-transfer disapproved
     */
    public List<String> transfer() {
        double balance,transferAmount,senderBalance;
        if(!balance().equals("")) {
            balance = Double.parseDouble(balance());
            transferAmount = Double.parseDouble(list.get(3));
            senderBalance = balance - transferAmount;

            if (senderBalance < 0 || findAccount().equals("")) {
                System.out.println("You don't have enough money/ there is no account with given number");
                list.clear();
                list.add("1");
            }
            else {
                try {
                    sql = "UPDATE account a  join customers c on a.pesel=c.pesel  set a.balance= '" + senderBalance + "' WHERE c.customer_nr='" + login + "'";
                    statement.executeUpdate(sql); //update sender account
                    sql = "UPDATE account SET balance=balance+'" + transferAmount + "' WHERE id_account='" + list.get(2) + "'";
                    statement.executeUpdate(sql);
                    list.clear();
                    list.add("0"); //transfer accepted
                } catch (SQLException e) {
                    list.add("1");
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    list.add("1");
                    System.out.println("balance function exception");
                    System.out.println(e.getMessage());
                }
            }
        }else {
            list.clear();
            list.add("1");
        }
       return list;
    }

    /**
     * Funcion chcecks customer balance
     * @return balance for customer with specified login
     * @return empty string if there is no balance for specified login
     */
    private String balance(){
        sql="Select balance from account natural join customers where customer_nr='"+login+"'";
            try {
                ResultSet rS = statement.executeQuery(sql);
               if( rS.next())
                 return rS.getString("balance");
               else throw new Exception();
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }catch(Exception e){  //there is no client with
                System.out.println("balance function exception");
                System.out.println(e.getMessage());
        }
        return "";
    }

    /**
     * Function checks given account
     * @return empty string if there is no given account
     * @return string with account number
     */
    private String findAccount(){
        sql="Select id_account from account where id_account='"+list.get(2)+"'";
        try {
            ResultSet rS = statement.executeQuery(sql);
           if( rS.next()) //check rS if it doesn't contain any data then return empty string
               return rS.getString("id_account");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }catch(Exception e){
            System.out.println("findAccount function exception");
            System.out.println(e.getMessage());
        }
        return "";
    }

    public List<String> addCustomer(){
            //CHECK AGE
        return list;
    }

    int getAge(String pesel){
        return 0;
    }

}
