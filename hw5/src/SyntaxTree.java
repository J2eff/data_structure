import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Iterator;


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
                SyntaxTree subRight = stack.pop();
                SyntaxTree subLeft = stack.pop();
                temp.attach(temp.root(),subLeft,subRight); //attach two subtree to operator tree's root
    
                stack.push(temp);
           }
        }
        return stack.pop();
    }

    public String parenthesize() {
        // you may define helper recursive method and use it here...
        
        return this.leftparenthesize()+this.root().getElement()+this.rightparenthesize();
    }

    public String leftparenthesize() {
        // you may define helper recursive method and use it here...
        SyntaxTree leftSubTree =  new SyntaxTree();
        
        if( this.root.getLeft() != null){
            leftSubTree.setRootNode( (Node<String>)this.root.getLeft() );
            return "("+leftSubTree.parenthesize()+" ";
        }
        return "";
    }
    public String rightparenthesize() {
        // you may define helper recursive method and use it here...
        SyntaxTree rightSubTree = new SyntaxTree();
        
        if( this.root.getLeft()!= null){
            rightSubTree.setRootNode( (Node<String>)this.root.getRight() );
            return " "+rightSubTree.parenthesize()+")";

        }
    
        return "";
    }





    public double evaluate() {
       
        double value = 0.0;

        value = this.subTreeEvaluate();
        return value;
    }

    public double subTreeEvaluate(){ //sub method for evaluate
        
        if(this.height(this.root)  > 1){
            SyntaxTree leftSubTree =  new SyntaxTree();
            SyntaxTree rightSubTree = new SyntaxTree();
            
            Node<String> leftSubRoot = this.root.getLeft();
            Node<String> rightSubRoot = this.root.getRight();

            leftSubTree.setRootNode(leftSubRoot);
            rightSubTree.setRootNode(rightSubRoot);
            switch(this.root.getElement()) {
                case "+":
                    return leftSubTree.subTreeEvaluate() + rightSubTree.subTreeEvaluate();
                    
                case "-":
                    return leftSubTree.subTreeEvaluate() - rightSubTree.subTreeEvaluate();
                   
                case "*":
                    return leftSubTree.subTreeEvaluate() * rightSubTree.subTreeEvaluate();
                   
                case "/":
                    return leftSubTree.subTreeEvaluate() / rightSubTree.subTreeEvaluate();
                   
                default:
                    System.out.println("operator error");
                    break;
                }
        }else{
            if( !this.isNumeric(this.root.getElement() ) ) {
                switch(this.root.getElement()) {
                    case "+":
                        return Double.parseDouble(this.root.getLeft().getElement()) + Double.parseDouble(this.root.getRight().getElement());
                        
                    case "-":
                        return Double.parseDouble(this.root.getLeft().getElement()) - Double.parseDouble(this.root.getRight().getElement());
                    
                    case "*":
                        return Double.parseDouble(this.root.getLeft().getElement()) * Double.parseDouble(this.root.getRight().getElement());
                    
                    case "/":
                        return Double.parseDouble(this.root.getLeft().getElement()) / Double.parseDouble(this.root.getRight().getElement());
                    
                    default:
                        System.out.println("operator error");
                        break;
                }
            }else{
                return Double.parseDouble(this.root.getElement());
            }
        }
        return 0;
    }





    public Queue<Node<String>> makePreOrderQueue(){ //sub method for preorder queue
        Iterable<Position<String>> iter = this.positions();
        Queue<Node<String>> snapshot  = new LinkedList<Node<String>>();
        for (Position<String> item : iter) {
            snapshot.offer( (Node<String>)item);
        }

        return snapshot;
    }

    public String toPrefix() {
        Queue<Node<String>> snapshot  = this.makePreOrderQueue(); 
        String prefixString = "";
       
        Node<String> temp = snapshot.poll();
        SyntaxTree tempTree =  new SyntaxTree();
        tempTree.setRootNode(temp);

        while (temp != null) {
            
            prefixString = prefixString + temp.getElement()+" ";
            temp = snapshot.poll();
           
        }

        return prefixString.trim();

    }

    public String indentSyntaxTree() {

       
        Queue<Node<String>> snapshot  = this.makePreOrderQueue();
       

        String indentString = "";
        
        
        Node<String> temp = snapshot.poll();
        SyntaxTree tempTree =  new SyntaxTree();
        tempTree.setRootNode(temp);

        while (temp != null) {
            
            for(int i = 0 ; i < tempTree.depth(temp) ; i++){
                indentString = indentString+"  ";
            }

            indentString = indentString + temp.getElement()+"\n";
            temp = snapshot.poll();
           
        }

       

        return  indentString;

    }
    

    public static void main(String... args) {
        System.out.println("Homework 5");
        SyntaxTree tree = SyntaxTree.buildSyntaxTree("1 20 + 31 49 + *".split(" "));
        SyntaxTree tree1 = SyntaxTree.buildSyntaxTree("1 2 + 3 4 2 / 5 * + *".split(" "));
        System.out.println(tree.evaluate());
        System.out.println(tree1.evaluate());
       
    }
}


