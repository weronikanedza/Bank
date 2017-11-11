import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

    public class ReadFile {
       private String name;
       private File file;
       private List<String> list;

    public ReadFile(String name) {
        this.name = name;
        list = new ArrayList<>();
    }

    public List<String> read() throws IOException {
        try {
            file=new File(name);
            Scanner scanner=new Scanner(file);
            StringTokenizer token;
            token = new StringTokenizer(scanner.nextLine(), "|");
                 while (token.hasMoreElements()) {
                     list.add(token.nextToken());
                 }
            scanner.close();
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
            }

        return list;
        }
}
