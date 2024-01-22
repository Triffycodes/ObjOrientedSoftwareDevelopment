package tree;

import java.util.Optional;

public class TreeNode<T> {
    private T value;
    private Optional<TreeNode<T>> left;
    private Optional<TreeNode<T>> right;

    Optional<TreeNode<T>> left() { return Optional.empty();}
    Optional<TreeNode<T>> right() {return Optional.empty();}
    T value() {return null;}

    //private static <T> void printInorderBasic(TreeNode<T> node) {
        // if node == null return;
    //    if (node != null) {
    //       printInorderBasic(node.left);
    //        System.out.println(node.value);
    //        printInorderBasic(node.right);
    //    }
    // }

    public boolean lookup(T value){
        // TODO
        return false;
    }

   

    public String printInorder(){
        return printInorder(Optional.of(this));
        // could be Optional.ofNullable(this)
    }

    private static <T> String printInorderNonNull(TreeNode<T> node) {
        return printInorder(node.left()) + node.value() + printInorder(node.right());
    }   

    static <T> String printInorder(Optional<TreeNode<T>> node) {
    return node.map(TreeNode::printInorderNonNull)
               .orElse("");
    }

    public String printPreorder(){
        // TODO
        return "";
        
    }

    public String printPostorder(){
        // TODO
        return "";
        
    }

    public int size(){
        // TODO
        return 0;
    }

    public int height(){
        // TODO
        return 0;
    }

    public String printBFS(){
        // TODO
        return "";
    }

    public void mirror() {
        // TODO 
       }


    public void doubleTree() {
        // TODO 
       }

    public boolean sameTree(TreeNode<T> other) {
        // TODO 
        return false;
       }
    
}
