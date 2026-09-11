/*class CountSquares {
    Map<String, Integer> map;

    public CountSquares() {
        map=new HashMap<>();
    }
    
    public void add(int[] point) {
        String s=Arrays.toString(point);
        map.put(s, map.getOrDefault(s,0)+1);
    }
    
    public int count(int[] point) {
        int tright=map.get(Arrays.toString(new int[]{point[0]+1,point[1]})) *
                  map.get(Arrays.toString(new int[]{point[0]+1,point[1]+1})) *
                  map.get(Arrays.toString(new int[]{point[0],point[1]+1}));

        int tleft=map.get(Arrays.toString(new int[]{point[0]-1,point[1]})) *
                  map.get(Arrays.toString(new int[]{point[0]-1,point[1]+1})) *
                  map.get(Arrays.toString(new int[]{point[0],point[1]+1}));

        int bright=map.get(Arrays.toString(new int[]{point[0]+1,point[1]})) *
                  map.get(Arrays.toString(new int[]{point[0]+1,point[1]-1})) *
                  map.get(Arrays.toString(new int[]{point[0],point[1]-1}));

        int bleft=map.get(Arrays.toString(new int[]{point[0]-1,point[1]})) *
                  map.get(Arrays.toString(new int[]{point[0]-1,point[1]-1})) *
                  map.get(Arrays.toString(new int[]{point[0],point[1]-1}));

        int count=tright+tleft+bright+bleft;
        return count;

    }
}*///without checking contains get gives null, also all size square are allowed

class CountSquares {
    // We need a list to iterate over points to find variable-sized diagonals
    List<int[]> points; 
    Map<String, Integer> map;

    public CountSquares() {
        points = new ArrayList<>();
        map = new HashMap<>();
    }
    
    public void add(int[] point) {
        points.add(point); // Add to list for iteration
        String s = Arrays.toString(point);
        map.put(s, map.getOrDefault(s, 0) + 1);
    }
    
    public int count(int[] point) {
        int px = point[0];
        int py = point[1];
        int total = 0;
        
        for (int[] p : points) {
            int x = p[0];
            int y = p[1];
            
            // 1. Is this a valid diagonal? (Distance on X equals distance on Y)
            if (Math.abs(px - x) != Math.abs(py - y) || px == x || py == y) {
                continue;
            }
            
            // 2. Identify the other two corners based on this dynamic distance
            String corner1 = Arrays.toString(new int[]{px, y});
            String corner2 = Arrays.toString(new int[]{x, py});
            
            // 3. Use getOrDefault to prevent NullPointerExceptions!
            int count1 = map.getOrDefault(corner1, 0);
            int count2 = map.getOrDefault(corner2, 0);
            
            total += count1 * count2;
        }
        
        return total;
    }
}