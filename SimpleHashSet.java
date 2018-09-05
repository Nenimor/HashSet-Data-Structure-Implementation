/**
 * Created by Ronen on 01-May-17.
 */
public abstract class SimpleHashSet implements SimpleSet {
    protected final static int INITIAL_CAPACITY = 16;
    protected final static float INITIAL_UPPER_FACTOR = 0.75f;
    protected final static float INITIAL_LOWER_FACTOR = 0.25f;
    protected float lowerFactor;
    protected float upperFactor;
    protected int itemsNum = 0;
    protected int tableSize = INITIAL_CAPACITY;
    protected float loadFactor;
    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set
     */
    public abstract boolean add(String newValue);
    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set
     */
    public abstract boolean contains(String searchVal);
    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted
     */
    public abstract boolean delete(String toDelete);
    /**
     * @return The number of elements currently in the set
     */
    public int size(){
        return itemsNum;
    }
    /**
     * @return The size of the table
     */
    public int capacity(){
        return tableSize;
    }
    /**
     * this method copies the set into a larger/smaller set in case that rehash is needed
     */
    protected abstract void copyToNewTable();

    /**
     * checks whether a rehash (resizing of the table) is needed after string removal
     * @return true iff rehash wash needed
     */
    protected boolean reHashDelete(){
        loadFactor = ((float) itemsNum-1) / ((float) tableSize);
        if (loadFactor < lowerFactor && tableSize != 1){ //minimal table size is 1
            tableSize = tableSize / 2;
            copyToNewTable();
            return true;
        }
        return false;
    }
    /**
     * checks whether a rehash (resizing of the table) is needed after string addition
     * * @return true iff rehash wash needed
     */
    protected boolean reHashAdd() {
        loadFactor = ((float) itemsNum+1) / ((float) tableSize);
        if (loadFactor > upperFactor) {
            tableSize = tableSize * 2;
            copyToNewTable();
            return true;
        }
        return false;
    }

}
