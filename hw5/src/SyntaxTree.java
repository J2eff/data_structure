import java.util.Stack;
import java.util.Queue;

public class SyntaxTree extends LinkedBinaryTree<String> {
    
    public static boolean isNumeric(String input) {
        try {
          Double.parseDouble(input);
          return true;
        }
        catch (NumberFormatException e) {
          return false;
        }
      }
    
    private void setRootNode(Node<String> tartgetNode) {
        this.root = tartgetNode;
    }



    public static SyntaxTree buildSyntaxTree(String[] expr) {
        // construct an expression syntax tree ...
        // to make Syntax tree use stack 
        Stack<SyntaxTree> stack = new Stack();

        SyntaxTree syntax = new SyntaxTree();
        
        for(int i = 0; i< expr.length ; i++){
           if( syntax.isNumeric( expr[i])){
               SyntaxTree temp =  new SyntaxTree(); //make sub binaryTree for number
               temp.addRoot(expr[i]);
               stack.push(temp);

           }else{
                SyntaxTree temp =  new SyntaxTree(); //make sub binaryTree for operator
                temp.addRoot(expr[i]);

                temp.attach(temp.root(),stack.pop(),stack.pop()); //attach two subtree to operator tree's root
    
                stack.push(temp);
           }
        }
        return stack.pop();
    }

    public String parenthesize() {
        // you may define helper recursive method and use it here...
        
        SyntaxTree leftSubTree =  new SyntaxTree();
        SyntaxTree rightSubTree = new SyntaxTree();
        
        leftSubTree.setRootNode( (Node<String>)this.root.getLeft() );
        rightSubTree.setRootNode( (Node<String>)this.root.getRight() );

      
        return "("+leftSubTree.parenthesize() +" "+this.root().getElement() +" "+ rightSubTree.parenthesize()+")";
        

    }

    public double evaluate() {
        // you may define helper recursive method and use it here...
        return 1.0;
    }

    public String toPrefix() {
        // you may define helper recursive method and use it here...
        return "hi";

    }

    public String indentSyntaxTree() {
        // you may define helper recursive method and use it here...
        return "hi";

    }
    

    public static void main(String... args) {
        System.out.println("Homework 5");
        SyntaxTree tree = SyntaxTree.buildSyntaxTree("1 20 + 31 49 + *".split(" "));
    }
}


