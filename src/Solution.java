import java.util.*;

public class Solution {
    /*
     * @param numCourses: a total of n courses
     * @param prerequisites: a list of prerequisite pairs
     * @return: the course order
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {


        List<Integer>[] map = getMap(numCourses,prerequisites);

        int[] indegree = getIndegree(numCourses,prerequisites);

        int [] order = getOrder(numCourses,map,indegree);

        return order;

    }


    public List<Integer>[] getMap(int numCourses, int[][] prerequisites){

        List<Integer>[] map = new ArrayList[numCourses];

        for(int i = 0 ; i < numCourses ; i++){
            map[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < prerequisites.length ; i++){
            int start = prerequisites[i][1];
            int end = prerequisites[i][0];
            map[start].add(end);
        }
        return map;
    }

    public int [] getIndegree(int numCourses , int[][] prerequisites){

        int [] indegree = new int[numCourses];

        for(int i = 0 ; i < prerequisites.length ; i++){
            int end = prerequisites[i][0];
            indegree[end]++;
        }
        return indegree;
    }


    public int [] getOrder(int numCourses,List<Integer>[] map,int[] indegree){

        Queue<Integer> queue = new LinkedList<>();

        for(int i = 0 ; i < indegree.length; i++){
            if(indegree[i] == 0){
                queue.offer(i);
            }
        }

        int [] order = new int [numCourses];
        int count = 0;

        while(!queue.isEmpty()){
            int head = queue.poll();
            order[count] = head;
            count++;
            for(int next : map[head]){
                indegree[next]--;
                if(indegree[next] == 0){
                    queue.offer(next);
                }
            }
        }

        if(count == numCourses){
            return order;
        }

        return new int[0];
    }

}