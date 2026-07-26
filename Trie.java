class Trie {

    class Node
    {
        boolean eow = false;
        Node [] children = new Node[26];
    }
    Node root;
    public Trie() {
        root= new Node();
    }
    
    public void insert(String word) 
    {
        Node temp = root;
        for(int i=0;i<word.length();i++)
        {
            char ch = word.charAt(i);
            if(temp.children[ch-'a']==null)
                temp.children[ch-'a']=new Node();
            temp = temp.children[ch-'a'];
        }
        temp.eow = true;
    }
    
    public boolean search(String word) 
    {
        Node temp = root;
        for(int i=0;i<word.length();i++)
        {
            char ch = word.charAt(i);
            if(temp.children[ch-'a']==null)
                return false;
            temp = temp.children[ch-'a'];
        }  
        if(temp.eow == true)
            return true;
        else
            return false;
    }
    
    public boolean startsWith(String prefix) 
    {
        Node temp = root;
        for(int i=0;i<prefix.length();i++)
        {
            char ch = prefix.charAt(i);
            if(temp.children[ch-'a']==null)
                return false;
            temp = temp.children[ch-'a'];
        }  
        return true;
    }

}

class WordDictionary {
    class Node
    {
        boolean eow = false;
        Node [] children = new Node[26];
    }
    Node root;
    public WordDictionary() {
        root= new Node();
    }
    
    public void addWord(String word) {
        Node temp = root;
        for(int i=0;i<word.length();i++)
        {
            char ch = word.charAt(i);
            if(temp.children[ch-'a']==null)
                temp.children[ch-'a']=new Node();
            temp = temp.children[ch-'a'];
        }
        temp.eow = true;
    }
    
    public boolean search(String word) 
    {
        return searchHelper(word, 0, root);
    }
    public boolean searchHelper(String word,int idx,Node node)
    {
        if(idx == word.length())
            return true;
        char ch = word.charAt(idx);

        if(ch!='.')
        {
            if(node.children[ch-'a']==null)
                return false;
            else
                return searchHelper(word, idx+1, node.children[ch-'a']);
        }
        else // Dot Case
        {   
            for(char possibleCh = 'a';possibleCh<='z';possibleCh++)
            {
                if(node.children[possibleCh-'a']!=null)
                {
                   boolean recAns = searchHelper(word, idx+1, node.children[possibleCh-'a']);
                   if(recAns)
                       return true;
                }
            }
        }


        return false;
    }
}




// Custom class Trie with function to get 3 words starting with given prefix
class Trie {

    // Node definition of a trie
    class Node 
    {
        boolean isWord = false; //eow
        List<Node> children = Arrays.asList(new Node[26]);
    };
    Node Root, curr;
    List<String> resultBuffer;

    // Runs a DFS on trie starting with given prefix and adds all the words in the resultBuffer, limiting result size to 3
    void dfsWithPrefix(Node curr, String word) {
        if (resultBuffer.size() == 3)
            return;
        if (curr.isWord)
            resultBuffer.add(word);

        // Run DFS on all possible paths.
        for (char c = 'a'; c <= 'z'; c++)
            if (curr.children.get(c - 'a') != null)
                dfsWithPrefix(curr.children.get(c - 'a'), word + c);
    }
    Trie() {
        Root = new Node();
    }

    // Inserts the string in trie.
    void insert(String s) {

        // Points curr to the root of trie.
        curr = Root;
        for (char c : s.toCharArray()) {
            if (curr.children.get(c - 'a') == null)
                curr.children.set(c - 'a', new Node());
            curr = curr.children.get(c - 'a');
        }

        // Mark this node as a completed word.
        curr.isWord = true;
    }
    List<String> getWordsStartingWith(String prefix) {
        curr = Root;
        resultBuffer = new ArrayList<String>();
        // Move curr to the end of prefix in its trie representation.
        for (char c : prefix.toCharArray()) {
            if (curr.children.get(c - 'a') == null)
                return resultBuffer;
            curr = curr.children.get(c - 'a');
        }
        dfsWithPrefix(curr, prefix);
        return resultBuffer;
    }
};
class Solution {
    List<List<String>> suggestedProducts(String[] products,
                                         String searchWord) {
        Trie trie = new Trie();
        List<List<String>> result = new ArrayList<>();
        // Add all words to trie.
        for (String w : products)
            trie.insert(w);
        String prefix = new String();
        for (char c : searchWord.toCharArray()) {
            prefix += c;
            result.add(trie.getWordsStartingWith(prefix));
        }
        return result;
    }
};