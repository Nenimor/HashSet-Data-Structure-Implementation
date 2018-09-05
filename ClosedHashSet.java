/**
 * this class extends 'SimpleHashSet' and creates a closed-hashing set . As any set, it has the following
 * methods: add, delete, contains. for further information about closed-hashing- read 'README'
 * @author Ronen Mor.
 */
public class ClosedHashSet extends SimpleHashSet{
    private final static int INVALID_INDEX = -1;
    private final static String FLAG = "";
    private final static float C1 = 0.5f;

    private String[] hashTable;

    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor){
        lowerFactor = lowerLoadFactor;
        upperFactor = upperLoadFactor;
        hashTable = new String[tableSize];
    }
    public ClosedHashSet(){
        lowerFactor = INITIAL_LOWER_FACTOR;
        upperFactor = INITIAL_UPPER_FACTOR;
        hashTable = new String[tableSize];
    }

    public ClosedHashSet(java.lang.String[] data){
        lowerFactor = INITIAL_LOWER_FACTOR;
        upperFactor = INITIAL_UPPER_FACTOR;
        hashTable = new String[tableSize];
        for (String value : data){
            add(value);
        }
    }

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set
     */
    public boolean contains(String searchVal){
        if (findIndex(searchVal)==INVALID_INDEX) return false;
        return true;
    }

    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set
     */
    public boolean add(String newValue){
        int attempt = 0;
        int index = getHashIndex(newValue, attempt);
        int finalIndex=INVALID_INDEX;
        //finds an index for newValue while checking for duplicates and with regard to flagged cells
        while (hashTable[index]!=null){
            if (hashTable[index].equals(newValue)) return false; //return false if newValue already exists
            if (hashTable[index].equals(FLAG) && finalIndex==INVALID_INDEX) finalIndex=index; // store the
            // flagged cell index for future addition
            attempt++;
            index = getHashIndex(newValue, attempt);
        }
        //if rehash was needed- find a new index for newValue- without regard to duplicates/flagged cells
        if (reHashAdd()) {
            attempt = 0;
            index = getHashIndex(newValue, attempt);
            while (hashTable[index] != null) {
                attempt++;
                index = getHashIndex(newValue, attempt);
            }
        }
        else if (finalIndex!=INVALID_INDEX) index=finalIndex;
        itemsNum++;
        hashTable[index]=newValue;
        return true;
        }

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted
     */
    public boolean delete(java.lang.String toDelete){
        int index = findIndex(toDelete);
        if (index!=INVALID_INDEX){
            if (reHashDelete())
                index = findIndex(toDelete);
            itemsNum--;
            hashTable[index] = FLAG;
            return true;
        }
        return false;
    }

    /**
     * this method calculates an index in the set for a given string by a formula given in ex3.pdf
     * @param hashValue- the string desired to get index for
     * @param i - an int representing the attempt number to find an empty index for the string
     * @return - an int representing a valid index the set
     */
    private int getHashIndex(String hashValue, int i){
        int hash = hashValue.hashCode();
        int index= (hash + (i + i * i)/2)&(capacity()- 1);
        return index;
    }

    //*returns the index of a value if found in the table, and returns -1 if not found.

    /**
     * this method returns the index of a given string if it exists in the set
     * @param valueToFind - the string desired to be checked
     * @return- the index of the string if it exists, -1 otherwise
     */
    private int findIndex(String valueToFind){
        int attempt = 0;
        int index = getHashIndex(valueToFind, attempt);
        while (hashTable[index]!=null){
            if (hashTable[index].equals(valueToFind)) return index;
            attempt++;
            index = getHashIndex(valueToFind, attempt);
        }
        return INVALID_INDEX;
    }

    /**
     * this method copies the set into a larger/smaller set in case that rehash is needed
     */
    protected void copyToNewTable() {
        String[] tempHashTable = new String[tableSize];
        for (String value: hashTable) {
            if (value!=null && !value.equals(FLAG)) {
                int attempt = 0;
                int index = getHashIndex(value, attempt);
                while (tempHashTable[index] != null) {
                    attempt++;
                    index = getHashIndex(value, attempt);
                }
                tempHashTable[index] = value;
            }
        }
        hashTable=tempHashTable;
    }
}
