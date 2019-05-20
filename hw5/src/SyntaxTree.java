import java.util.Stack;

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

    public static SyntaxTree buildSyntaxTree(String[] expr) {
        // construct an expression syntax tree ...
        // to make Syntax tree use stack 
        Stack<String> stack = new Stack();
        for(int i = 0; i< expr.length ; i++){
            stack.push(expr[i]);
        }

        System.out.println(stack.toString());
        SyntaxTree syntax = new SyntaxTree();
        Position<String> parent = null;
        if( !stack.empty() ){
            syntax.addRoot(stack.pop());
            parent = syntax.root();

            while(!stack.empty()){
                Node<String> child = syntax.createNode(stack.pop(), null, null, null);

                if( syntax.isNumeric(child.getElement())  ){
                    continue;
                }
            }

        }else{
            return (SyntaxTree)syntax;
        }
        

        return syntax;
    }

    public String parenthesize() {
        // you may define helper recursive method and use it here...
        return "hi";

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


