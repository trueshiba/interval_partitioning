import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);

        System.out.println("Please input the name of your file:");

        String fileName = input.nextLine();

        ArrayList<Interval> lecs = new ArrayList<>();
        String line;

        // lNum = number of lectures
        int lNum = 1;

        try {
            BufferedReader fileParse = new BufferedReader(new FileReader(fileName));

            while ((line = fileParse.readLine()) != null) {

                line = line.replaceAll("[()]", "");
                line = line.replaceAll("\\s", "");
                String[] s = line.split(",");

                System.out.println("Lecture " + String.valueOf(lNum) + ":" + s[0] + ", " + s[1] + ", " + s[2]);
                ++lNum;
                // Add new interval
                lecs.add(new Interval(s[0],Integer.parseInt(s[1]),Integer.parseInt(s[2])));
            }
        } catch (FileNotFoundException e) {
            System.exit(0);
        }


        // Sort lectures by start time so s(1)  s(2)  ...  s(n).
        Collections.sort(lecs);

        // Create number for initial classroom
        int cNum = 0;

        //create a single classroom and add first lecture
        Classroom c = new Classroom(cNum);
        c.add(lecs.get(0));

        // Create priority queue for classes. Add first classroom
        PriorityQueue<Classroom> Q = new PriorityQueue<>();
        Q.add(c);

        // Print initial classroom
        System.out.println("Classroom " + String.valueOf(cNum) + ": " + lecs.get(0));

        //for j = 1 to n number of lectures (skips first lecture -> j = 0)
        for (int j = 1; j < lecs.size(); ++j) {

            // Take smallest classroom 'c' from priority queue.
            c = Q.peek();

            // If lecture 'j' start time is after smallest classroom 'c' end time
            if (lecs.get(j).getEnd() >= c.getLastFin()) {
                // Schedule lecture 'j' in classroom 'c'
                c.add(lecs.get(j));

                System.out.println("Classroom " + String.valueOf(cNum) + ": " + lecs.get(j));

            } else {
                //allocate a new classroom
                ++cNum;
                Classroom cNew = new Classroom(cNum);
                cNew.add(lecs.get(j));


                // Add new classroom to priority queue
                Q.add(cNew);

                System.out.println("Classroom " + String.valueOf(cNum) + ": " + lecs.get(j));
            }

        }

        System.out.println();
        System.out.println("~ ~ ~ ~ ~ ~");

        for (Classroom cls : Q) {
            System.out.println(cls);
        }

    }
}