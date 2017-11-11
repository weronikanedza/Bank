import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {

        List<String> list=new ArrayList<>(); //keep operation type and data
        ReadFile input=new ReadFile("input.txt"); //open file
        list=input.read(); //read first line of file and input in list

        ConnectDB connectDB=new ConnectDB(list);

    }
}
