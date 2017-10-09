import java.util.*;

public class Solution {
    /*
     * @param numCourses: a total of n courses
     * @param prerequisites: a list of prerequisite pairs
     * @return: the course order
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {


        Map<Integer,ArrayList<Integer>> map = getMap(prerequisites);

        Map<Integer,Integer> indegree = getIndegree(prerequisites);

        ArrayList<Integer> start = getStart(numCourses,indegree);

        ArrayList<Integer> order = getOrder(map,indegree,start);

        if (order.size() == numCourses){
            int [] arrOrder = new int[order.size()];
            for(int i = 0 ; i < order.size() ; i++){
                arrOrder[i]  = order.get(i);
            }
            return arrOrder;
        }

        return new int[0];

    }


    public Map<Integer,ArrayList<Integer>> getMap(int[][] prerequisites){

        Map<Integer,ArrayList<Integer>> map = new HashMap<>();

        for(int i = 0 ; i < prerequisites.length ; i++){
            int start = prerequisites[i][1];
            int end = prerequisites[i][0];
            if(!map.containsKey(start)){
                map.put(start,new ArrayList<>());
                map.get(start).add(end);
            }
            else{
                map.get(start).add(end);
            }
        }
        return map;
    }

    public Map<Integer,Integer> getIndegree(int[][] prerequisites){

        Map<Integer,Integer> indegree = new HashMap<>();

        for(int i = 0 ; i < prerequisites.length ; i++){
            int end = prerequisites[i][0];
            if(!indegree.containsKey(end)){
                indegree.put(end,1);
            }else{
                indegree.put(end,indegree.get(end)+1);
            }
        }
        return indegree;
    }

    public ArrayList<Integer> getStart(int numCourses, Map<Integer,Integer> indegree){

        ArrayList<Integer> start = new ArrayList<>();

        for(int i = 0 ; i < numCourses ; i++){
            if(!indegree.containsKey(i)){
                start.add(i);
            }
        }
        return start;

    }

    public ArrayList<Integer> getOrder(Map<Integer,ArrayList<Integer>> map,Map<Integer,Integer> indegree,ArrayList<Integer> start){

        Queue<Integer> queue = new LinkedList<>(start);
        ArrayList<Integer> order = new ArrayList<>(start);

        while(!queue.isEmpty()){
            int head = queue.poll();
            if(!map.containsKey(head)){
                continue;
            }
            for(int next : map.get(head)){
                indegree.put(next,indegree.get(next)-1);
                if(indegree.get(next) == 0){
                    queue.offer(next);
                    order.add(next);
                }
            }
        }
        return order;
    }

}