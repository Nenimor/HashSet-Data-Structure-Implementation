import java.util.LinkedList;

/**
 * Created by Ronen on 02-May-17.
 */
public class LinkedListWrapper {
    private LinkedList<String> linkedList;
    public LinkedListWrapper(){
        linkedList = new LinkedList<String>();
    }
    /**
     * Add a specified element to the list.
     * @param data- New value to add to the list
     */
    public void add(String data){
        linkedList.add(data);
    }
    /**
     * Remove the input element from the list.
     * @param data- Value to delete
     * @return True iff toDelete is found and deleted
     */
    public boolean delete(String data){
        return linkedList.remove(data);
    }
    /**
     * @return The number of elements currently in the list
     */
    public int size(){
        return linkedList.size();
    }
    /**
     * Look for a specified value in the list.
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set
     */
    public boolean contains(String searchVal){
        return linkedList.contains(searchVal);
    }

    /**
     *Retrieves, but does not remove, the first element of this list, or returns null if this list
     * is empty
     */
    public String getFirst(){
        return linkedList.peekFirst();
    }

    /**
     * @return the LinkedList of strings which holds the data
     */
    private LinkedList<String> getStringsList(){
        return linkedList;
    }

    /**
     * appends all the elements of the given collection to the linkedlist
     * @param listToAdd
     */
    public boolean addAll(LinkedListWrapper listToAdd){
        return linkedList.addAll(listToAdd.getStringsList());
    }
}
