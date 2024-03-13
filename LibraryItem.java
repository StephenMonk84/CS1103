public class LibraryItem <T extends Integer, V extends String>{
    /*This generic class stores all the basic information about a library item, it also provides all the getter and setter methods as well as constructors. 
     * The T generic will be an Integer to ensure item ids only contain numbers
     * The V generic will be a String
    */
    
    /*Private member variables are part of best practice */
    private T itemID;
    private V title;
    private V author;
      
    public LibraryItem(T itemID, V title, V author){
        /*The constructor to create an item */
        this.itemID = itemID;
        this.title = title;
        this.author = author;
    }

    public LibraryItem(){
        /*Just need an empty constructor for creating new entries and utilizing the Setter methods */
    }

    public void setItemID(T itemID){
        /*Setting Method for setting the itemID */
        this.itemID = itemID;
    }

    public void setTitle(V title){
        /*Setting Method for setting the Title */
        this.title = title;
    }

    public void setAuthor(V author){
        /*Setting Method for setting the Author */
        this.author = author;
    }

    public T getItemID(){
        /*Getting Method for getting the itemID */
        return this.itemID;
    }

    public V getTitle(){
        /*Getting Method for getting the Title */
        return title;
    }

    public V getAuthor(){
        /*Getting Method for getting the author */
        return author;
    }
}
