import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Independence {

    Integer adjacencies[][];

    public Independence(Integer adjacencies[][]){
        this.adjacencies = adjacencies;
    }

    public ArrayList<ArrayList<Integer>> getIndependentSets(){
        ArrayList<ArrayList<Integer>> independentSets = new ArrayList<>();
        for (int i = 0; i<adjacencies.length;i++){
            independentSets.add(getIndSet(i));
        }
        return independentSets;
    }

    public ArrayList<Integer> blacklistAdjs(ArrayList<Integer> blacklist, int node){
        blacklist.add(node+1);
        for (int adj : adjacencies[node]) {
            blacklist.add(adj);
        }
        return blacklist;
    }

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

    public int independenceNumber(ArrayList<ArrayList<Integer>> sets){
        int size = 0;
        for (List arr: sets){
            if (arr.size()>size) size = arr.size();
        }
        return size;
    }



}
