import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.tree.DefaultTreeCellRenderer;


public class Homework1  extends JPanel
		implements TreeSelectionListener {

	static tree eT;
	JTree tree;
	JEditorPane htmlPane;
	String get;
	DefaultMutableTreeNode ThisNode;
	DefaultMutableTreeNode top;

	public Homework1(){
		super(new GridLayout(1,0));

		top = new DefaultMutableTreeNode(eT.root.key);
		createNodes(top,eT.root);

		tree = new JTree(top);
		tree.getSelectionModel().setSelectionMode
				(TreeSelectionModel.SINGLE_TREE_SELECTION);

		tree.addTreeSelectionListener(this);

		tree.putClientProperty("JTree.lineStyle","None");
		ImageIcon NodeIcon =  createImageIcon("middle.gif");
		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
		renderer.setOpenIcon(NodeIcon);
		renderer.setClosedIcon(NodeIcon);
		tree.setCellRenderer(renderer);


		JScrollPane treeView = new JScrollPane(tree);

		htmlPane = new JEditorPane();

		JScrollPane htmlView = new JScrollPane(htmlPane);

		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setTopComponent(treeView);
		splitPane.setBottomComponent(htmlView);

		Dimension minimumSize = new Dimension(100, 50);
		htmlView.setMinimumSize(minimumSize);
		treeView.setMinimumSize(minimumSize);
		splitPane.setDividerLocation(100);
		splitPane.setPreferredSize(new Dimension(500, 300));

		add(splitPane);
	}




	public static void main(String[] args) {

		String pf = "251-*32*+";
//       String pf = "25-";
		if(args.length>0)pf=args[0];
		eT = new tree(pf);
		eT.creattree();
		eT.infix(eT.root);
		eT.calculate(eT.root);
		System.out.printf(" = " + eT.sum);
                
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	private static void createAndShowGUI() {

		JFrame frame = new JFrame("Homework1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Homework1 newContentPane = new Homework1();
		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);

		frame.pack();
		frame.setVisible(true);
	}

	private void createNodes(DefaultMutableTreeNode top , Node t) {

		if(t.left!=null)
		{
			DefaultMutableTreeNode L = new DefaultMutableTreeNode(t.left.key);
			top.add(L);
			createNodes(L,t.left);
		}
		if(t.right!=null)
		{
			DefaultMutableTreeNode R = new DefaultMutableTreeNode(t.right.key);
			top.add(R);
			createNodes(R,t.right);
		}
	}



	private ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = Homework1.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}


	public void valueChanged(TreeSelectionEvent tse) {

		ThisNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
		tree.getLastSelectedPathComponent();
		if(ThisNode == null){
			return;
		}
		String text = inorder(top);
		if(!ThisNode.isLeaf()) text += "=" + calculate(ThisNode);
		htmlPane.setText(text);

	}

	public String inorder(DefaultMutableTreeNode node) {
		if (node == null) return "null";
		if(node == ThisNode && !node.isLeaf()) {
			return 	inorder(node.getNextNode()) + node.toString() + inorder(node.getNextNode().getNextSibling()); //letf node right
		}else if(eT.op(node.toString().charAt(0)) && node != top) {
                    return "(" + inorder(node.getNextNode()) + node.toString() + inorder(node.getNextNode().getNextSibling()) + ")";
                }else {
                    return node.toString();
                }
	}



	public int calculate(DefaultMutableTreeNode node) {
		if(node.isLeaf()) return Integer.parseInt(node.toString());
		int sum = 0;
		switch(node.toString()) {
			case "+" : sum = calculate(node.getNextNode()) + calculate(node.getNextNode().getNextSibling()); break;
			case "-" :sum = calculate(node.getNextNode()) - calculate(node.getNextNode().getNextSibling()); break;
			case "*" :sum = calculate(node.getNextNode()) * calculate(node.getNextNode().getNextSibling()); break;
			case "/" :sum = calculate(node.getNextNode()) / calculate(node.getNextNode().getNextSibling()); break;
			default : sum = calculate(node.getNextNode()) + calculate(node.getNextNode().getNextSibling()); break;
		}
		return sum;
	}
}

 /*
public class Homework1 extends JPanel
                      implements TreeSelectionListener {
    private JEditorPane htmlPane;
    private JTree tree;
    private URL helpURL;
    private static boolean DEBUG = false;
 
    //Optionally play with line styles.  Possible values are
    //"Angled" (the default), "Horizontal", and "None".
    private static boolean playWithLineStyle = false;
    private static String lineStyle = "Horizontal";
     
    //Optionally set the look and feel.
    private static boolean useSystemLookAndFeel = false;
 
    public Homework1() {
        super(new GridLayout(1,0));
 
        //Create the nodes.
        DefaultMutableTreeNode top =
            new DefaultMutableTreeNode("The Java Series");
        createNodes(top,tree.roo);
    ImageIcon NodeIcon =  createImageIcon("middle.gif");
    DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
		renderer.setOpenIcon(NodeIcon);
		renderer.setClosedIcon(NodeIcon);
		tree.setCellRenderer(renderer);
        //Create a tree that allows one selection at a time.
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);
        //Listen for when the selection changes.
        tree.addTreeSelectionListener(this);
 
        if (playWithLineStyle) {
            System.out.println("line style = " + lineStyle);
            tree.putClientProperty("JTree.lineStyle", lineStyle);
        }
 
        //Create the scroll pane and add the tree to it. 
        JScrollPane treeView = new JScrollPane(tree);
 
        //Create the HTML viewing pane.
        htmlPane = new JEditorPane();
        htmlPane.setEditable(false);
        initHelp();
        JScrollPane htmlView = new JScrollPane(htmlPane);
 
        //Add the scroll panes to a split pane.
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(treeView);
        splitPane.setBottomComponent(htmlView);
 
        Dimension minimumSize = new Dimension(100, 50);
        htmlView.setMinimumSize(minimumSize);
        treeView.setMinimumSize(minimumSize);
        splitPane.setDividerLocation(100); 
        splitPane.setPreferredSize(new Dimension(500, 300));
 
        //Add the split pane to this panel.
        add(splitPane);
    }
 
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                           tree.getLastSelectedPathComponent();
 
        if (node == null) return;
 
        Object nodeInfo = node.getUserObject();
        if (node.isLeaf()) {
            BookInfo book = (BookInfo)nodeInfo;
            displayURL(book.bookURL);
            if (DEBUG) {
                System.out.print(book.bookURL + ":  \n    ");
            }
        } else {
            displayURL(helpURL); 
        }
        if (DEBUG) {
            System.out.println(nodeInfo.toString());
        }
    }
 
    private class BookInfo {
        public String bookName;
        public URL bookURL;
 
        public BookInfo(String book, String filename) {
            bookName = book;
            bookURL = getClass().getResource(filename);
            if (bookURL == null) {
                System.err.println("Couldn't find file: "
                                   + filename);
            }
        }
 
        public String toString() {
            return bookName;
        }
    }
 
    private void initHelp() {
        String s = "TreeDemoHelp.html";
        helpURL = getClass().getResource(s);
        if (helpURL == null) {
            System.err.println("Couldn't open help file: " + s);
        } else if (DEBUG) {
            System.out.println("Help URL is " + helpURL);
        }
 
        displayURL(helpURL);
    }
 
    private void displayURL(URL url) {
        try {
            if (url != null) {
                htmlPane.setPage(url);
            } else { //null url
        htmlPane.setText("File Not Found");
                if (DEBUG) {
                    System.out.println("Attempted to display a null URL.");
                }
            }
        } catch (IOException e) {
            System.err.println("Attempted to read a bad URL: " + url);
        }
    }
 
        private void createNodes(DefaultMutableTreeNode top , Node t) {

		if(t.left!=null)
		{
			DefaultMutableTreeNode L = new DefaultMutableTreeNode(t.left.key);
			top.add(L);
			createNodes(L,t.left);
		}
		if(t.right!=null)
		{
			DefaultMutableTreeNode R = new DefaultMutableTreeNode(t.right.key);
			top.add(R);
			createNodes(R,t.right);
		}
	}
    
         
    private static void createAndShowGUI() {
        if (useSystemLookAndFeel) {
            try {
                UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("Couldn't use system look and feel.");
            }
        }
 
        //Create and set up the window.
        JFrame frame = new JFrame("TreeDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add content to the window.
        frame.add(new Homework1());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    private ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = Homework1.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
 
    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
*/