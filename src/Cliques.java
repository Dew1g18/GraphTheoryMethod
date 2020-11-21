import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Cliques {


Integer adjacencies[][];
Integer cycles[][];

    public Cliques(Integer adj[][], Integer cyc[][]){
        this.adjacencies=adj;
        this.cycles = cyc;
    }


    public ArrayList<Integer> sharedAdj(int node1, int node2){
        ArrayList<Integer> shared = new ArrayList<>();
        for (int adj : adjacencies[node1]){
            for (int adj2: adjacencies[node2]){
                if (adj==adj2){
                    shared.add(adj-1);
                }
            }
        }
        return shared;
    }


    public ArrayList<Integer> biggerSubGraphs(Integer cyc[]){
        ArrayList<ArrayList<Integer>> pairShares = new ArrayList<>();
        for (int node : cyc) {
            for (int node2 : cyc){
                if(node<node2)pairShares.add(sharedAdj(node-1, node2-1));
            }
        }

        ArrayList<Integer> intersection = pairShares.get(0);
        for(ArrayList<Integer> shares : pairShares){
            intersection.retainAll(shares);
        }
        if(intersection.size()>0){
//            System.out.println("The cycle: "+ cyc+ " are each connected to the following nodes:");
            for (int i : intersection){
//                System.out.println(i);
            }
        }
        return intersection;
    }
    public ArrayList<Integer> biggerSubGraphsArr(ArrayList<Integer> cyc){
        ArrayList<ArrayList<Integer>> pairShares = new ArrayList<>();
        for (int node : cyc) {
            for (int node2 : cyc){
                if(node<node2)pairShares.add(sharedAdj(node-1, node2-1));
            }
        }

        ArrayList<Integer> intersection = pairShares.get(0);
        for(ArrayList<Integer> shares : pairShares){
            intersection.retainAll(shares);
        }
        if(intersection.size()>0){
//            System.out.println("The cycle: "+ cyc+ " are each connected to the following nodes:");
            for (int i : intersection){
//                System.out.println(i);
            }
        }
        return intersection;
    }




    public void initialRun(){
        ArrayList<ArrayList<Integer>> clics = new ArrayList<>();
        for (Integer cyc[]: this.cycles){
            ArrayList<Integer> intersection = biggerSubGraphs(cyc);
            if (intersection.size()>0){
                for (int node : intersection){
                    /**
                     * I want to stop here becuase this is really not particularly fast.
                     */
                    ArrayList<Integer> cycle = new ArrayList<>();
                    for (Integer n: cyc){
                        cycle.add(n);
                    }
                    cycle.add(node+1);
                    cycle.sort(Comparator.naturalOrder());
                    if (!clics.contains(cycle)){
                        clics.add(cycle);
                    }

                }
            }


        }



//        for(Integer node : clics.get(1)){
//            System.out.println(node);
//        }
        /**
         * at this point we have a bunch of 4-cliques.
         *
         * Here I want to write something which will, recursively perhaps, find larger complete subgraphs.
         */
        ArrayList<ArrayList<Integer>> last = new ArrayList<>();
        int n = 0;
        while(clics.size()>0 && n<10){
            last = clics;
            clics = biggerCliques(last);
            n++;
        }
        for (Integer i : last.get(0)){
            System.out.println(i);
        }
        System.out.println(last.get(0).toArray().toString());

    }

//    public ArrayList<ArrayList<Integer>> cliqueCleanup(ArrayList<ArrayList<Integer>> cliques){
//        for (ArrayList<Integer> clic : cliques){
//
//        }
//    }

    public ArrayList<ArrayList<Integer>> biggerCliques(ArrayList<ArrayList<Integer>> clics){
        ArrayList<ArrayList<Integer>> biggerCliques = new ArrayList<>();
        for (ArrayList<Integer> sub: clics){
            ArrayList<Integer> intersection = biggerSubGraphsArr(sub);
            if (intersection.size()>0){
                if (intersection.size()>0){
                    for (int node : intersection){
                        ArrayList<Integer> cycle = new ArrayList<>();
                        for (Integer n: sub){
                            cycle.add(n);
                        }
                        cycle.add(node+1);
                        cycle.sort(Comparator.naturalOrder());
                        if (!biggerCliques.contains(cycle)){
                            biggerCliques.add(cycle);
                        }
                    }
                }
            }
        }
        return biggerCliques;
    }

//
//    public ArrayList<int[]> cyclesOf3(){
//        for (int k=0; k<adjacencies.length; k++){
//            for(int m = k; m<adjacencies.length; m++){
//                for (int n = m; n<adjacencies.length; m++){
//                    if (k!=m && k!=n && n!=m){
//                        if()
//                    }
//                }
//            }
//        }
//    }


}
