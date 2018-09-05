/**
 * this class implements 'SimpleSet', and is used to work with a given collection with the method names of
 * 'SimpleSet'. This class allows us to create an array of sets, and test their running time.
 * @author Ronen Mor.
 */
public class CollectionFacadeSet implements SimpleSet{
    protected java.util.Collection<java.lang.String> wrappedCollection;

    public CollectionFacadeSet(java.util.Collection<java.lang.String> collection){
        wrappedCollection=collection;
    }
    public boolean add(java.lang.String newValue){
        if (wrappedCollection.contains(newValue)){
            return wrappedCollection.add(newValue);
        }
        return false;
    }
    public boolean delete(java.lang.String toDelete){
        return wrappedCollection.remove(toDelete);
    }
    public boolean contains(java.lang.String searchVal){
        return wrappedCollection.contains(searchVal);
    }
    public int size(){
        return wrappedCollection.size();
    }
}
