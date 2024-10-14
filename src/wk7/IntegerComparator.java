package wk7;
import java.util.Comparator;

public class IntegerComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer arg1, Integer arg2) {
        return arg1 - arg2;
    }
}
