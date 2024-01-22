package tree;

public class TreeNodeComparable<T extends Comparable<T>> extends TreeNode<T>{
    public boolean lookup(T value){
        // TODO
        return false;
    }

    public TreeNodeComparable<T> insert(T value){
        return null;
        // TODO
    }
    
    public int min(){
        return 0;
    }

    public int max(){
        return 0;
    }

    public boolean isBST(){
        return false;
    } 
}

