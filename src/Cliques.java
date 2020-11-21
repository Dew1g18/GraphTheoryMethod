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



    // This finds all adjacencies shared by a pair of nodes!
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


    /**
     * This method originally took the 3 cycles, hence the variable name, however now instead
     * it can be used to take in any connected incuded subgraph and it will look through all the nodes to find
     * a node outside of the clique which is connected to all of them, and then add it to the graph.
     * @param cyc
     * @return
     */
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


    /**
     * This is the same as the method above only it uses ArrayLists instead of the primitive format.
     *
     * There are much nicer ways of doing this. It's probably super computationally slow to have everything in the form
     * of arrayLists but its an easy object to work with and I'm doing this for the maths not the highly cohesive,
     * loosely coupled elegance that is my usual goal haha
     * @param cyc
     * @return
     */
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


    /**
     * This is essenctially an init() function which actually just runs all of the code,
     * I have done things this way as I am not really building a Math Package, I am just
     * splitting the code up into classes for neatness and organisation rather than to use their true purpose...
     */
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
         * I plan to eventually come back and make this a lot cleaner by soaking the previous loop which handles 3 cycles
         * before we move on to the slightly more generic 'cliques'.
         *
         * Here I want to write something which will, recursively perhaps, find larger complete subgraphs.
         *
         * The following runs until the largest clique is found.
         *
         * it works by trying to find a larger clique than before, if none such clique exists the method biggerCliques()
         * will return nothing, which is why I store the previous round, and if it fails then I take that to mean that
         * any of the previously found cliques would be the largest possible.
         */
        ArrayList<ArrayList<Integer>> last = new ArrayList<>();
        int n = 0;
        while(clics.size()>0 && n<20){
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


    /**
     * This goes from whatever size cliques I have and triest to make larger ones, if the init function notices that
     * it is no longer doing anything it will give up.. I know, not elegant or quick, but with the size of what we're doing
     * it's fine. I would like to say that I could comb through and make things a lot cleaner and more efficient later on
     * but the current goal is just to make sure that I understand how I would go about finding these invariants.
     * @param clics
     * @return
     */
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
