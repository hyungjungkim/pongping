package DB;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class TreeNode {
	class Node{
		/**Directory or File Path*/
		String path;	
		/**TreeNode Child List*/
		ArrayList<DirFile> children;
		
		/***/
		public Node(String path){
			this.path = path;
			
		}
	}
}

class Tree
{	
    class Node
    {   	
        String data;
        ArrayList<Node> children;
        ArrayList<String> childrenFile;
        
        
        public Node(String data)
        {
            this.data = data;
            children = new ArrayList<Node>();
        }

        public Node getChild(String data)
        {
            for(Node n : children)
                if(n.data.equals(data))
                    return n;

            return null;
        }
        
    }

    private Node root;

    public Tree()
    {
        root = new Node("");
    }

    public boolean isEmpty()
    {
        return root==null;
    }

    public void add(String str)
    {
        Node current = root;
        StringTokenizer s = new StringTokenizer(str, "/");
        while(s.hasMoreElements())
        {
            str = (String)s.nextElement();
            Node child = current.getChild(str);
            if(child==null)
            {
                current.children.add(new Node(str));
                child = current.getChild(str);
            }
            current = child;
        }
    }

    public void print()
    {
        print(this.root);
        
    }

    private void print(Node n)
    {
        if(n==null){
        	System.out.print("");
            return;
        }
        for(Node c : n.children)
        {
        	if((c.children.size())!=0){
        		System.out.println(c.data);
        		print(c);
        	}else{
        		System.out.print(c.data+" ");
        	}
        }
    }

    public static void main(String[] args)
    {
        Tree t = new Tree();
        t.add("/Users/LeeSun-A/Documents/workspace/test5.rtf");
        t.add("/Users/LeeSun-A/Documents/workspace/samsung/test3.rtf");
        t.add("/Users/LeeSun-A/Documents/workspace/samsung/Test/test.rtf");
        t.add("/Users/LeeSun-A/Documents/workspace/samsung/Test/test2.rtf");
//        t.add("The World");
//        t.add("The World/Asia");
//        t.add("The World/Asia/Afghanistan");
//        t.add("The World/Asia/Iran");
//        t.add("The World/Asia/China");    // Even if you insert only this statement.
//                                          // You get the desired output, 
                                          // As any string not found is inserted

        t.print();
        //t.print(new Node());
    }
}
