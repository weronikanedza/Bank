
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        List<String> list =new ArrayList<>();
       // list=logOn(); //logOn trial
        list=transfer();
        for (String s: list)
            System.out.print(s + " ");
        System.out.println("\n");
        ConnectDB connectDB=new ConnectDB(list);  //create connection
        list=connectDB.getList();

        System.out.println("OUTPUT LIST :");
        for (String j:list)
            System.out.print(j + " ");

    }

    public static void age() throws ParseException {
        String pesel="1611179345";

        String year=pesel.substring(0,2);
        String month=pesel.substring(2,4);
        String day=pesel.substring(4,6);

        Integer yearP=Integer.parseInt(year);
        Integer monthP=Integer.parseInt(month);
        Integer dayP=Integer.parseInt(day);

        System.out.println(yearP);
        System.out.println(monthP);

        String birthDate="";
        if (yearP>=0 && monthP>20 && dayP<=31){ //someone born after 2000
            birthDate="20"+year+"/"+(monthP-20)+"/"+day;
            // System.out.println(birthDate);
        }else if(yearP>=17 && monthP<20 && dayP<=31){
            birthDate="19"+year+"/"+(monthP)+"/"+day;
            //   System.out.println(birthDate);
        }else {
            System.out.println("za stary;");
        }


        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date dateb = format.parse(birthDate);
        System.out.println(dateb);

        // DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(format.format(date));

        long diff=date.getTime()-dateb.getTime();
        long d=(1000l*60*60*24*365);
        long years = Math.round(diff / d);
        int age=(int) years;
        System.out.println("AGE "+age);}


    public static List<String>  logOn(){
        List<String> list=new ArrayList<>() ;
        list.add("logowanie");
        list.add("2");
        list.add("Admin2");
        return list;
    }

    public static List<String> checkAddCustomer(){
        List<String> list=new ArrayList<>() ;
        list.add("dodawanie");
        list.add("Jurek");
        list.add("Owsiak");
        list.add("95020658964");
        list.add("Kraków");
        list.add("łąkowa");
        list.add("30-022");
        list.add("AYD231986");
        list.add("238123675");
        list.add("kn@gmail.com");
        return list;
    }

    public static  List<String> transfer(){
        List<String> list=new ArrayList<>() ;
        list.add("przelew"); //what
        list.add("3"); //from
        list.add("11111h111"); //to
        list.add("20.00"); //amount
        return  list;
    }
}
