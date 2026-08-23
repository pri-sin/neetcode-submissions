class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set=new HashSet<>(wordList);

        if(!set.contains(endWord)){
            return 0;
        }

        Queue<String> q=new ArrayDeque<>();
        q.offer(beginWord);

        int level=1;
        while(!q.isEmpty()){
            int levelSize=q.size();
            for(int i=0;i<levelSize;i++){
                String curr=q.poll();
                if(curr.equals(endWord)){
                    return level;
                }
                char []chars=curr.toCharArray();
                for(int j=0;j<chars.length;j++){
                    char currletter=chars[j];

                    for(char c='a';c<='z';c++){
                        if(currletter==c) continue;
                        chars[j]=c;
                        String newWord=new String(chars);

                        if(set.contains(newWord)){
                            set.remove(newWord);
                            q.offer(newWord);
                        }
                    }
                    chars[j]=currletter;
                }
            }
            level++;
        }
        return 0;
    }
}
