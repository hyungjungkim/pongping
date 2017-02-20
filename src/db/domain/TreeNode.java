package db;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TreeNode {
	/**DirFile 의 root */
	private DirFile root;
	/**Directory or File Path*/
	private String path;	
	/**DirFile 객체 */
	//	private DirFile dirFile;
	/**TreeNode Child List*/
	//private ArrayList<DirFile> children;
	/**User List*/
	private ArrayList<User> user;
	
	public TreeNode(){
		//root= new DirFile("");
		root = new DirFile("/Users/LeeSun-A/Documents/workspace/");
		//root = new DirFile("C:\\");
	//	this.children = new ArrayList<>();
	}

	 public boolean isEmpty(){
	    return root==null;
	 }

	 public void add(String str)
	 {
		 DirFile current = root;
		 StringTokenizer s = new StringTokenizer(str, "/");
		 while(s.hasMoreElements())
		 {
			 str = (String)s.nextElement();
			 DirFile child = current.getChild(str);
			 if(child==null)
			 {
				 current.addChild(new DirFile(root.getAbsolutePath()+"/"+current.getAbsolutePath()+"/"+str));		//str이 없으면, 추가  
				 child = current.getChild(str);
				 //System.out.println(child.getName());
			 }
			 current = child;
		 }
	 }
	 
	 ////////////////////////////////////////////////////
	 public void print()
	    {
		// System.out.println(this.root);
	        print(this.root);
	        
	    }

	    private void print(DirFile n)
	    {
	        if(n==null){
	        	System.out.print("");
	            return;
	        }
	        for(DirFile c : n.getChildren())
	        {
	        	if((c.getChildren().size())!=0){
	        		System.out.println(c.getName());
	        		print(c);
	        	}else{
	        		System.out.print(c.getName()+" ");
	        	}
	        }
	    }
	   
	    public static void main(String[] args)
	    {
	        TreeNode t = new TreeNode();
	        t.add("2017Project/FileServer/1/1_1");
	        t.add("2017Project/FileServer/1/1_1/test2.txt");
	        t.add("2017Project/FileServer/2/2_1/test2_2.rtf");
	        
	        t.print();
	        //t.print(new Node());
	    }


}

