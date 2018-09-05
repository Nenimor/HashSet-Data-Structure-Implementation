import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * this class contains the main method which runs the time tests for each set of the following:
 * OpenHashSet, ClosedHashSet, treeSet, linkedList, hashSet
 * @author Ronen Mor.
 */
public class SimpleSetPerformanceAnalyzer {
    private static final int CONVERT_TO_MS=1000000; //variable to divide in order to convert [ns] to [ms]
    private static final int LINKED_LIST_INDEX = 3;
    private static final int LINKED_LIST_WARMUP = 7000;
    private static final int WARMUP_ROUNDS = 70000;

    public static void main(String[] args){
        //next rows transfers a text file into arrays of strings
        //variables to store current time in nanoseconds
        long timeBefore, difference;
        int warmupRounds;
        String[] data1 = Ex3Utils.file2array("data1.txt");
        String[] data2 = Ex3Utils.file2array("data2.txt");

        SimpleSet[] setTimeTestArray = new SimpleSet[5];

        LinkedList<String> linkedList = new LinkedList<String>();
        TreeSet<String> treeSet = new TreeSet<>();
        HashSet<String> hashSet = new HashSet<String>();

        setTimeTestArray[0] = new OpenHashSet();
        setTimeTestArray[1] = new ClosedHashSet();
        setTimeTestArray[2] = new CollectionFacadeSet(treeSet);
        setTimeTestArray[3] = new CollectionFacadeSet(linkedList);
        setTimeTestArray[4] = new CollectionFacadeSet(hashSet);

        System.out.println("time measurement for each set with data1 strings (checks #1,3,4)");
        for (int i=0; i<setTimeTestArray.length; i++) {

            //1. time measurement in milliseconds for adding all data1 words
            timeBefore = System.nanoTime();
            for (String value : data1) {
                setTimeTestArray[i].add(value);
            }
            difference = (System.nanoTime()-timeBefore)/CONVERT_TO_MS;
            System.out.println("Set number "+i+" added all words in "+difference+" milliseconds");

            //3. time measurement in nano second for searching the value "hi"
            //warming up
            warmupRounds = WARMUP_ROUNDS;
            if (i == LINKED_LIST_INDEX) warmupRounds = LINKED_LIST_WARMUP;
            for (int round=0; round<warmupRounds; round++)
                setTimeTestArray[i].contains("hi");
            //time measurement
            timeBefore = System.nanoTime();
            for (int round=0; round<warmupRounds; round++)
                setTimeTestArray[i].contains("hi");
            difference = (System.nanoTime()-timeBefore)/warmupRounds;
            System.out.println("Set number "+i+" found 'hi' in "+difference+" nanoseconds");

            //4. time measurement in nanoseconds for searching the value "-13170890158"
            //warming up
            for (int round=0; round<warmupRounds; round++)
                setTimeTestArray[i].contains("-13170890158");
            //time measurement
            timeBefore = System.nanoTime();
            for (int round=0; round<warmupRounds; round++)
                setTimeTestArray[i].contains("-13170890158");
            difference = (System.nanoTime()-timeBefore)/warmupRounds;
            System.out.println("Set number "+i+" found '-13170890158' in "+difference+" nanoseconds");
        }

        // resetting the array with empty sets
        setTimeTestArray[0] = new OpenHashSet();
        setTimeTestArray[1] = new ClosedHashSet();
        setTimeTestArray[2] = new CollectionFacadeSet(treeSet);
        setTimeTestArray[3] = new CollectionFacadeSet(linkedList);
        setTimeTestArray[4] = new CollectionFacadeSet(hashSet);

        System.out.println("time measurement for each set with data1 strings (checks #2,5,6)");
        //next loop: time measurement for sets with data2 strings
        for (int i=0; i<setTimeTestArray.length; i++) {

            //2. time measurement in milliseconds for adding all data2 words
            timeBefore = System.nanoTime();
            for (String value : data2) {
                setTimeTestArray[i].add(value);
            }
            difference = (System.nanoTime()-timeBefore)/CONVERT_TO_MS;
            System.out.println("Set number "+i+" added all words in "+difference+" milliseconds");

            //5. time measurement for searching the value "23"
            //warming up
            warmupRounds = WARMUP_ROUNDS;
            if (i == LINKED_LIST_INDEX) warmupRounds = LINKED_LIST_WARMUP;
            for (int round=0; round<warmupRounds; round++)
                setTimeTestArray[i].contains("23");
            //time measurement
            timeBefore = System.nanoTime();
            for (int round=0; round<warmupRounds; round++)
                setTimeTestArray[i].contains("23");
            difference = (System.nanoTime()-timeBefore)/warmupRounds;
            System.out.println("Set number "+i+" found '23' in "+difference+" nanoseconds");

            //6. time measurement in nano second for searching the value "hi"
            //warming up
            for (int round=0; round<warmupRounds; round++)
                setTimeTestArray[i].contains("hi");
            //time measurement
            timeBefore = System.nanoTime();
            for (int round=0; round<warmupRounds; round++)
                setTimeTestArray[i].contains("-hi");
            difference = (System.nanoTime()-timeBefore)/warmupRounds;
            System.out.println("Set number "+i+" found 'hi' in "+difference+" nanoseconds");

        }
    }
}
