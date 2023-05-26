import java.util.Comparator;

public class WorkerComparator implements Comparator<Worker> {

    @Override
    public int compare(Worker o1, Worker o2) {
        int ret=o1.getName().compareTo(o2.getName());
        if (ret !=0) return ret;
        ret = Integer.compare(o1.getLevel(), o2.getLevel());
        if (ret !=0) return ret;
        return Double.compare(o1.getPower(),o2.getPower());
    }
}
