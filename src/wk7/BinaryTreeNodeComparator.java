package wk7;
import java.util.Comparator;

public class BinaryTreeNodeComparator implements Comparator<BinaryTreeNode> {

    @Override
    public int compare(BinaryTreeNode arg1, BinaryTreeNode arg2) {
        if (arg1.value != arg2.value)
            return arg1.value - arg2.value;
        return arg1.character - arg2.character;
    }
}
