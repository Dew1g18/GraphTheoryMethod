import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Independence {

    Integer adjacencies[][];

    public Independence(Integer adjacencies[][]){
        this.adjacencies = adjacencies;
    }


    /**
     * This will go through all the nodes, and for each node 'i' it will use the other methods in this class to find
     * the independence set starting with i as the first, and then going through them all numerically.
     * @return
     */
    public ArrayList<ArrayList<Integer>> getIndependentSets(){
        ArrayList<ArrayList<Integer>> independentSets = new ArrayList<>();
        for (int i = 0; i<adjacencies.length;i++){
            independentSets.add(getIndSet(i));
        }
        return independentSets;
    }

    /**
     * I am really proud of this method for finding the independence set for a given node.
     * The clever part I worked out is to create 2 arrays as I'm finding the independence set of a node,
     * and storing this extra array reduces the number of checks I have to do by a factor of O(n)!!
     * I do it by blacklisting nodes!! This will be explained in the next method's comment.
     * @param node
     * @return
     */
    public ArrayList<Integer> getIndSet(int node){
        ArrayList<Integer> indSet = new ArrayList<>();
        ArrayList<Integer> blacklist = new ArrayList<>();
        indSet.add(node+1);
        blacklist = blacklistAdjs(blacklist, node);
        for (int i=0;i<adjacencies.length;i++) {
            if(!blacklist.contains(i+1)){
                indSet.add(i+1);
                blacklist = blacklistAdjs(blacklist, i);
            }
        }
        return indSet;
    }



    /**
     * The reason for this is that instead having to check all of the adjacent nodes to all of the nodes in the
     * independence set I am building, when I add a node to the set I blacklist all of it's adjacent nodes, this way
     * when I get around to adding a new node, I need only check it isnt blacklisted which is checking a list of
     * max size n, instead of the n^2 checks I would have to do comparing it to every other node's adjacencies!
     * @param blacklist
     * @param node
     * @return
     */
    public ArrayList<Integer> blacklistAdjs(ArrayList<Integer> blacklist, int node){
        blacklist.add(node+1);
        for (int adj : adjacencies[node]) {
            if (!blacklist.contains(adj)) {
                blacklist.add(adj);
            }
        }
        return blacklist;
    }


    /**
     * just checks all the independence sets found and returns the size of the largest
     * @param sets The Independence sets found by getIndependenceSets()
     * @return
     */
    public int independenceNumber(ArrayList<ArrayList<Integer>> sets){
        int size = 0;
        for (List arr: sets){
            if (arr.size()>size) size = arr.size();
        }
        return size;
    }



}
