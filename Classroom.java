import java.util.ArrayList;

public class Classroom implements Comparable<Classroom> {
    int lastFin;
    int number;
    ArrayList<Interval> lectures;

    public Classroom(int n){
        this.number = n;
        this.lastFin = 0;
        this.lectures =  new ArrayList<>();
    }

    public void setFin(int f){
        this.lastFin = f;
    }

    public int getLastFin(){
        return this.lastFin;
    }

    public void add(Interval i){
        lastFin = i.getEnd();
        lectures.add(i);
    }

    @Override
    public String toString(){
        StringBuilder out = new StringBuilder();
        out.append("Classroom " + this.number + ": ");
        for (Interval i: lectures) {
            out.append(i).append(", ");
        }
        return out.toString();
    }

    @Override
    public int compareTo(Classroom c){
        return Integer.compare(this.lastFin, c.getLastFin());
    }
}
