package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

public class Test {
    public static void main(String[] args)
    {
        DirectedWeightedGraph g=new DirectedGraph();
        try {
            GsonBuilder Gbuilde = new GsonBuilder();
            Gbuilde.registerTypeAdapter(DirectedWeightedGraph.class, new fromJson());
            Gson gson = Gbuilde.create();
            FileReader fr = new FileReader("C:\\Users\\binya\\IdeaProjects\\Ex2\\src\\Ex2\\src\\api\\G2.json");
            g=gson.fromJson(fr,DirectedWeightedGraph.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        System.out.println(g.toString());

        Gson gson=new GsonBuilder().create();
        String json=gson.toJson(g);
        try{
            PrintWriter pw= new PrintWriter(new File("L.json"));
            pw.write(json);
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
    }
}
