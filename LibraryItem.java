public class LibraryItem <T, V>{
    private T itemID;
    private V title;
    private V author;

    
        
    public LibraryItem(T itemID, V title, V author){
        this.itemID = itemID;
        this.title = title;
        this.author = author;
    }

    public void setItemID(T itemID){
        this.itemID = itemID;
    }

    public void setTitle(V title){
        this.title = title;
    }

    public void setAuthor(V author){
        this.author = author;
    }

    public T getItemID(){
        return this.itemID;
    }

    public V getTitle(){
        return title;
    }

    public V getAuthor(){
        return author;
    }



    public void printerInfo(){
        System.out.println("The id of the book is " + itemID + " the title is " + title + " and the author is " + author);
    }

    
}
