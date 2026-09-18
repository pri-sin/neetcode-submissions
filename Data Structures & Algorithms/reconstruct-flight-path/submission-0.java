/*class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, List<String>> map=new HashMap<>();

        for(List<String> ticket:tickets){
            if(!map.containsKey(ticket.get(0))){
                map.put(ticket.get(0), new ArrayList<>());
            }
            map.get(ticket.get(0)).add(ticket.get(1));
        }

        List<String> res=new ArrayList<>();
        List<String> curr=new ArrayList<>();

        solve(map, res, curr, "JFK", tickets.size());
        return curr;
    }

    public void solve(Map<String, List<String>> map, List<String> res, List<String> curr, String src, int i){
        System.out.println("came here for :"+i+" : "+curr);
        curr.add(src);

        if(i==0){
            System.out.println("returning :"+i+" : "+curr);
            res=new ArrayList<>(curr);
            return;
        }

        if(map.containsKey(src)){
            for(String str:map.get(src)){
                solve(map, res, curr, str, i-1);
            }
        }
    }
}

Key Issues in Your Code

Reference Reassignment: Writing res = new ArrayList<>(curr) inside solve() reassigns the local reference res rather than modifying the caller's list. Furthermore, findItinerary returns curr instead of res.

Missing Backtracking: You add airports using curr.add(src), but you never remove them (curr.remove(curr.size() - 1)) when a path fails or completes.

Unconsumed Tickets: The code iterates through map.get(src) without removing or marking used tickets, causing infinite recursion loops.

Missing Lexical Order: LeetCode 332 requires returning the itinerary with the smallest lexical order when multiple valid paths exist.
*/


class Solution {
    private Map<String, PriorityQueue<String>> targets = new HashMap<>();
    private LinkedList<String> route = new LinkedList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        // Build graph with min-heaps to automatically handle lexical order
        for (List<String> ticket : tickets) {
            targets.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).add(ticket.get(1));
        }

        dfs("JFK");
        return route;
    }

    private void dfs(String airport) {
        PriorityQueue<String> arrivals = targets.get(airport);
        
        // Traverse edges until out-degree is 0
        while (arrivals != null && !arrivals.isEmpty()) {
            dfs(arrivals.poll()); // Consumes ticket directly
        }
        
        // Post-order insertion builds the itinerary backwards
        route.addFirst(airport);
    }
}
