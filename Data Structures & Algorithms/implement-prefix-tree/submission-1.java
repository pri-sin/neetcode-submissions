class PrefixTree {
    private Trienode root;

    public PrefixTree() {
         root=new Trienode();
    }

    public void insert(String word) {
        Trienode curr=root;
        for(char c : word.toCharArray()){
            int index=c-'a';
            if(curr.children[index]==null){
                curr.children[index]=new Trienode();
            }
            curr=curr.children[index];
        }
        curr.isEndOfWord=true;
    }

    public boolean search(String word) {
        Trienode curr=root;
        for(char c : word.toCharArray()){
            int index=c-'a';
            if(curr.children[index]==null){
                return false;
            }
            curr=curr.children[index];
        }
        return curr.isEndOfWord;
    }

    public boolean startsWith(String prefix) {
        Trienode curr=root;
        for(char c : prefix.toCharArray()){
            int index=c-'a';
            if(curr.children[index]==null){
                return false;
            }
            curr=curr.children[index];
        }
        return true;
    }
}

class Trienode{
    Trienode []children=new Trienode[26];
    boolean isEndOfWord;
}
