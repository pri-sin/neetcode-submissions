class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> list=new HashMap<>();
        List<Integer> res=new ArrayList<>();

        for(int i=0;i<numCourses;i++){
             list.put(i, new ArrayList<>());
        }

        int []indegree=new int[numCourses];
        for(int i=0;i<prerequisites.length;i++){
            list.get(prerequisites[i][1]).add(prerequisites[i][0]);
            indegree[prerequisites[i][0]]++;
        }

        Queue<Integer> q=new ArrayDeque<>();

        for(int i=0;i<numCourses;i++){
            if(indegree[i]==0){
                q.offer(i);
            }
        }
        while(!q.isEmpty()){
            int curr=q.poll();
            res.add(curr);

            for(int neighbor: list.get(curr)){
                indegree[neighbor]--;
                if(indegree[neighbor]==0){
                    q.offer(neighbor);
                }
            }
        }
    
    return res.size() == numCourses 
        ? res.stream().mapToInt(Integer::intValue).toArray() 
        : new int[0];    }
}

/*
`res.toArray(...)` only works when `res` is a list of arrays (`List<int[]>`), not a list of single numbers (`List<Integer>`).

**`List<int[]>` (2D Array Output)**
When solving problems that return a 2D array (like *Merge Intervals* or *Pacific Atlantic*), `int[]` acts as an Object. Java generics can bind to `int[]`, producing an `int[][]`:

```java
List<int[]> res = new ArrayList<>();
return res.toArray(new int[0][]); // Works! (Outputs int[][])

```

**`List<Integer>` (1D Array Output)**
When `res` holds `Integer` objects, Java generic methods (`<T> T[] toArray(T[] a)`) cannot convert the wrapper class `Integer` directly into the primitive type `int`:

```java
List<Integer> res = new ArrayList<>();
return res.toArray(new int[0]); // Compile Error!

```

To output an `int[]` from a `List<Integer>`, you must map it manually with a loop or convert it using Streams (`res.stream().mapToInt(Integer::intValue).toArray()`).
*/