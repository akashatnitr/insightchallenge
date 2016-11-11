package insightData;
/**
 * Created by akash sahoo on 11/10/16.
 */
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import java.util.*;
import java.io.*;

public class Solution {
    Map<Integer,List<Integer>> myGraph;


    public Solution()
    {
        myGraph = new HashMap<>();
        new HashMap<>();

    }

    private class Pair{
        int ver;
        int dis;
        public Pair(int _v,int _d)
        {
            ver = _v;
            dis = _d;
        }

    }


    public boolean checkIfWithin4(int u,int v,Map<Integer,List<Integer>>graph)
    {
        Set<Integer> reachable=new HashSet<>();
        int d = 0;
        Queue<Pair> nodeDist = new LinkedList<>();

        Pair src = new Pair(u,0);
        nodeDist.add(src);
        while (!nodeDist.isEmpty())
        {
            Pair curr = nodeDist.poll();
            int currVer = curr.ver;
            int currDis = curr.dis;
            reachable.add(currVer);
            if(currDis<5 && reachable.contains(v))
                return true;
            else if(currDis==5)
            {
                break;
            }
            for(int ver:graph.get(u))
            {
                Pair p = new Pair(ver,currDis+1);
                nodeDist.add(p);
                reachable.add(ver);

            }

        }

        return false;
    }



    public static void main(String[] args)
    {
       // long startTime = System.nanoTime();
        int i=0;
        Path file = Paths.get("/Users/akash/Documents/eclipseWorkspace/Challenge2Server/data/batch_payment.csv");
        //final int n=40000;

        int maxi = 0;

        Solution sol = new Solution();
        Map<Integer,List<Integer>> graph = sol.myGraph;

        try
        {
            //Java 8: Stream class


            // Add an empty adjacency list for each vertex




            Stream<String> lines = Files.lines( file, StandardCharsets.UTF_8 );
            //System.out.println(lines.count());

            for(  String line : (Iterable<String>) lines::iterator )
            {
                if(i==0){
                    i++;
                    continue;
                }
                String[] csvParser = line.split(",");
//                if(i%100==0) {
//
//
//                    //System.out.println(csvParser[1]+" "+csvParser[2]);
//                }

                //System.out.println(csvParser[1] + csvParser[2] );
                //g.addEdge(Integer.parseInt(csvParser[1].trim()), Integer.parseInt(csvParser[2].trim()));

                if(csvParser.length>2)
                {
                    try {
                        int u = Integer.parseInt(csvParser[1].trim());
                        int v = Integer.parseInt(csvParser[2].trim());

                        List<Integer> adj = graph.getOrDefault(u, new ArrayList<>());
                        adj.add(v);
                        
                        graph.put(u, adj);
                    }
                    catch (NumberFormatException e)
                    {
                        //System.out.println(line);
                        i++;
                        continue;

                    }

                }




                //maxi = Math.max(maxi, Integer.parseInt(csvParser[1].trim()));
                i++;

            }


        } catch (Exception ioe){

            //System.out.println("fkdsmk "+csvParser[1]+" "+csvParser[2]);
            ioe.printStackTrace();
        }
        System.out.println(i+" ");

        //g.BFS(31119);
        // g.BFS(2489);
        //g.BFS(2);
        //dfs(adjLists, 31119, 2489);


        System.out.println(graph.size());

        boolean flag = sol.checkIfWithin4(38721,1428,graph); //1428 - 1234 && 38721 - 1234
        System.out.println(flag);

         
        

//        long endTime = System.nanoTime();
//        long elapsedTimeInMillis = TimeUnit.MILLISECONDS.convert((endTime - startTime), TimeUnit.NANOSECONDS);
//        System.out.println("Total elapsed time: " + elapsedTimeInMillis + " ms");



    }
}
