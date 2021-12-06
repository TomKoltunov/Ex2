package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class GraphAlgo implements DirectedWeightedGraphAlgorithms{
    private DirectedWeightedGraph g;
    public GraphAlgo(){
        this.g=new DirectedGraph();
    }
    @Override
    public void init(DirectedWeightedGraph g) {
        this.g=g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.g;
    }

    @Override
    public DirectedWeightedGraph copy() {
        DirectedWeightedGraph g1=new DirectedGraph(this.g);
        return g1;
    }
    private void InitNotVisited()
    {
        Iterator<NodeData>e=this.g.nodeIter();
        while (e.hasNext())
        {
            NodeData n=e.next();
            n.setTag(0);
        }
    }

    @Override
    public boolean isConnected() {
        if(this.g==null)
            return true;
        if(g.nodeSize()<=1)
            return true;
        if(g.nodeSize()>g.edgeSize())
            return false;
        return false;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        return 0;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        try {
            List<NodeData> ans =new LinkedList<NodeData>();
            NodeData sourceNode = this.g.getNode(src);
            ans.add(sourceNode);
            EdgeData current;
            List<NodeData> q = queue(sourceNode); // creates list with all vertices except sourceNode
            HashMap<NodeData, Double> map = initGraph(sourceNode); // creates hashmap with consists of:
            // (key = vertices), (values = weights). *At this moment all vertices hold 'Double.MAX_VALUE' and
            // sourceNode holds 0.0
            Iterator<EdgeData> i = this.g.edgeIter(src);
            if (i.hasNext() == false) {
                return null;
            } else {
                current = i.next();
                double w = current.getWeight();
                while(i.hasNext()) {
                    if (current.getDest() == dest) {
                        int answer = current.getDest();
                        ans.add(this.g.getNode(answer));
                        return ans;
                    } else {
                        w = Math.min(w, i.next().getWeight());
                    }
                }
            }
            return null;
        } catch (IOError e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<NodeData> queue(NodeData n) {
        List<NodeData> q = new LinkedList<NodeData>();
        Iterator<NodeData> i = this.g.nodeIter();
        while (i.hasNext()) {
            if (i.next().equals(n)) {
                continue;
            } else {
                q.add(i.next());
            }
        }
        return q;
    }

    private HashMap<NodeData, Double> initGraph(NodeData n) {
        HashMap<NodeData, Double> map = new HashMap<NodeData, Double>();
        Iterator<NodeData> i = this.g.nodeIter();
        while (i.hasNext()) {
            if (i.next().equals(n)) {
                map.put(i.next(), 0.0);
            } else {
                map.put(i.next(), Double.MAX_VALUE);
            }
        }
        return map;
    }

    private void relax(double du, double dv, double w) {
        if (dv > du + w) {
            dv = du + w;
        }
    }

    @Override
    public NodeData center() {
        double ave=0;
        int n=this.g.nodeSize();
        double min=Double.MAX_VALUE;
        NodeData ans=null;
        Iterator<NodeData>e=this.g.nodeIter();

        while (e.hasNext())
        {
            NodeData curr=e.next();
            Iterator<NodeData>e2=this.g.nodeIter();
            while(e2.hasNext())
            {
                NodeData next=e2.next();
                ave+=(shortestPathDist(curr.getKey(),next.getKey())/n);
            }
            if(ave<min)
            {
                min=ave;
                ans=curr;
            }

        }
        return ans;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        if(cities.isEmpty())
            return null;
        List<NodeData>ans =new LinkedList<NodeData>();
        double min=Double.MAX_VALUE;
        List<NodeData>l=new LinkedList<NodeData>();
        NodeData start= cities.get(0);
        cities.remove(0);
        int curr=0;
        ans.add(start);
        while(!cities.isEmpty())
        {
            for(int i=0;i<cities.size();i++)
            {
                if(shortestPathDist(start.getKey(), cities.get(i).getKey())<min){
                    min=shortestPathDist(start.getKey(), cities.get(i).getKey());
                    l=shortestPath(start.getKey(),cities.get(i).getKey());
                    curr=i;
                }
            }
            start=cities.get(curr);
            min=Double.MAX_VALUE;
            for (int i=0;i<l.size();i++)
            {
                ans.add(l.get(i));
                cities.remove(l.get(i));
            }

        }
        return ans;
    }

    @Override
    public boolean save(String file) {
        Gson gson=new GsonBuilder().create();
        String json=gson.toJson(this.g);
        try{
            PrintWriter pw= new PrintWriter(new File(file));
            pw.write(json);
            pw.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean load(String file) {
        try {
            GsonBuilder Gbuilde = new GsonBuilder();
            Gbuilde.registerTypeAdapter(DirectedWeightedGraph.class, new fromJson());
            Gson gson = Gbuilde.create();
            FileReader fr = new FileReader(file);
            this.g=gson.fromJson(fr,DirectedWeightedGraph.class);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
