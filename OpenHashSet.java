import java.util.Map;

/**
 * this class extends 'SimpleHashSet' and creates an open-hashing set . As any set, it has the following
 * methods: add, delete, contains. for further information about open-hashing- read 'README'
 * @author Ronen Mor.
 */
public class OpenHashSet extends SimpleHashSet{

    private LinkedListWrapper[] hashTable;

    public OpenHashSet(){
        lowerFactor = INITIAL_LOWER_FACTOR;
        upperFactor = INITIAL_UPPER_FACTOR;
        hashTable = new LinkedListWrapper[tableSize];
    }

    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor){
        lowerFactor = lowerLoadFactor;
        upperFactor = upperLoadFactor;
        hashTable = new LinkedListWrapper[tableSize];
    }

    public OpenHashSet(java.lang.String[] data){
        lowerFactor = INITIAL_LOWER_FACTOR;
        upperFactor = INITIAL_UPPER_FACTOR;
        hashTable = new LinkedListWrapper[tableSize];
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
        int index = getHashIndex(searchVal);
        if (hashTable[index]!=null && hashTable[index].contains(searchVal)) return true;
        return false;
    }
    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set
     */
    public boolean add(String newValue){
        int index = getHashIndex(newValue);
        if(hashTable[index]==null){
            if (reHashAdd())
                index = getHashIndex(newValue);
            itemsNum++;
            if(hashTable[index]==null)
                hashTable[index] = new LinkedListWrapper();
            hashTable[index].add(newValue);
            return true;
        }
        else if (!hashTable[index].contains(newValue)){
            if (reHashAdd())
                index = getHashIndex(newValue);
            itemsNum++;
            if(hashTable[index]==null)
                hashTable[index] = new LinkedListWrapper();
            hashTable[index].add(newValue);
            return true;
        }
        return false;
    }
    /**
     * this method calculates an index in the set for a given string by a formula given in ex3.pdf
     * @param hashValue- the string desired to get index for
     * @return - an int representing a valid index the set
     */
    private int getHashIndex(String hashValue){
        int hash = hashValue.hashCode();
        int index = (int)(Math.abs(hash)%tableSize);
        return index;
    }
    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted
     */
    public boolean delete(java.lang.String toDelete){
        int index = getHashIndex(toDelete);
        if (hashTable[index]!=null && hashTable[index].contains(toDelete)){
            if (reHashDelete())
                index = getHashIndex(toDelete);
            itemsNum--;
            hashTable[index].delete(toDelete);
            return true;
        }
        return false;
    }

    /**
     * this method copies the set into a larger/smaller set in case that rehash is needed
     */
    protected void copyToNewTable() {
        LinkedListWrapper[] tempHashTable = new LinkedListWrapper[tableSize];
        for (int i = 0; i < hashTable.length; i++) {
            //checks whether the list in index i is empty
            if (hashTable[i] != null && hashTable[i].getFirst()!=null) {
                //next 2 rows get the first string in the list, and check it's new index in the table
//                System.out.println(hashTable[i].getFirst());
                String listHead = hashTable[i].getFirst();
                int newIndex = getHashIndex(listHead);
                //next row copies the whole list in the index i to it's new index
                if (tempHashTable[newIndex]==null){
                    tempHashTable[newIndex] = new LinkedListWrapper();
                }
                tempHashTable[newIndex].addAll(hashTable[i]);
            }
        }
        hashTable = tempHashTable;
    }

}


