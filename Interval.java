import java.util.*;
import java.io.*;


public class Interval implements Comparable<Interval>{

    String name;
    int start;
    int end;

    /** Constructor
     * @param n -> Name of the interval
     * @param s -> Start time
     * @param e -> End time
     */
    public Interval(String n, int s, int e){
        this.name = n;
        this.start = s;
        this.end = e;
    }

    public int getEnd(){
        return end;
    }

    @Override
    public String toString(){
        // fix for whatever output required
        return "(" + this.name + ", " + this.start + ", " + this.end + ")";
    }

    // May need to override this if intervals get sorted in wrong way.
    @Override
    public int compareTo(Interval i){
        return Integer.compare(this.start, i.start);
    }

}
