import java.util.ArrayList;

public class Catalog <T>{

    //Need to bind generic to ensure generics can be used instead of explicit arraylist
    T catalogItem;
    ArrayList<LibraryItem> libItem = new ArrayList<>();
    
    public Catalog(T catalogItem){
        //Driver Code needs a catalog object for adding
        this.catalogItem = catalogItem;
    }
    public Catalog(){
        libItem.add(new LibraryItem<>(0, "The Lord of the Rings", "JRR Tolkein"));
    }
    
    public void consoleOutput(){
        System.out.println("The item id is "+libItem.get(0).getItemID() + " the book title is " + libItem.get(0).getTitle() + " the author is " + libItem.get(0).getAuthor());
    }
    public void addItem(LibraryItem lib){
        libItem.add(lib);
    }
    //Need to add itemID to remove
    public void removeItem(){
        libItem.remove();
    }


}
