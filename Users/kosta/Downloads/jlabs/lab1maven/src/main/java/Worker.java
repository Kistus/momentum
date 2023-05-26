import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Worker implements Comparable<Worker>{
    private String name;
    private double power;
    private int level;
    private Set<Worker> apprentices;

    public Worker(String name, double power, int level, String sorting) {
        this.name = name;
        this.level = level;
        this.power = power;
        if(sorting.equals("natural")){
            this.apprentices = new TreeSet<Worker>();
        }
        else if(sorting.equals("alternative")){
            this.apprentices = new TreeSet<Worker>(new WorkerComparator());
        }
        else if(sorting.equals("default")){
            this.apprentices = new HashSet<Worker>();
        }
    }


    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public double getPower() {
        return power;
    }

    public int getApprenticesAmount(){
        int res = 0;
        for(Worker child:apprentices){
            res+=child.getApprenticesAmount()+1;
        }
        return res;
    }

    public Set<Worker> getApprentices() {
        return apprentices;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(double power) {
        this.power = power;
    }

    public void setApprentices(Set<Worker> apprentices) {
        this.apprentices = apprentices;
    }

    public boolean equals(Worker worker){
        if(this == worker){
            return true;
        }
        else if(this.name.equals(worker.name)
                && this.level == worker.level
                && this.power == worker.power
                && this.apprentices.equals(worker.apprentices)){
            return true;
        }
        return false;
    }

    public void addApprentice(Worker worker){
        apprentices.add(worker);
    }

    public void print(){
        for(int i = 0; i < this.level; i++){
            System.out.print("-");
        }
        System.out.println(this);
        if(this.apprentices.size() != 0){
            for (Worker o: this.apprentices){
                o.print();
            }
        }
    }

    @Override
    public int compareTo(Worker worker) {
        int ret=this.name.compareTo(worker.name);
        if (ret !=0) return ret;
        ret =Integer.compare(this.level, worker.level);
        if (ret !=0) return ret;
        return Double.compare(this.power, worker.power);
    }

    public int HashCode(){
        int result = 9;
        result += this.power != 0 ? this.power*81 + 10 : 5;
        result += this.level != 0 ? this.level*81 + 21 : 5;
        result += this.name != null ? this.name.hashCode() : 5;
        return result;
    }

    @Override
    public String toString(){
        return "Worker{name="+ (this.name == null ? "''" : this.name ) +
                ", salary:"+this.power+"$, level="+this.level+"}";
    }
}

