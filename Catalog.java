import java.util.ArrayList;

public class Catalog <T extends LibraryItem>{

    /*This Generic class contains an arraylist of the library items as well as methods to access and remove items */
    private ArrayList<LibraryItem> libItem = new ArrayList<>();
    
    public Catalog(T catalogItem){
        /*Constructor that takes generic objects of LibraryItem type to add to ArrayList */
        libItem.add(catalogItem);
    }
    public Catalog(){
        /**Default Constructor in case no values are given */
        libItem.add(new LibraryItem<>(0, "The Lord of the Rings", "JRR Tolkein"));
    }
   
    
    public void collectionOutput(){
        /*This method outputs the entire collection */
        for(int i=0; i < libItem.size(); i++){
            System.out.println("The item id is "+libItem.get(i).getItemID() + " the book title is " + libItem.get(i).getTitle() + " and the author is " + libItem.get(i).getAuthor());
        }
    }
     
    public void removeItem(int index){
        /*This first checks if the arraylist is empty, if it is empty is leaves the method, otherwise removes the
         * selected based on the itemID
         */
        if(!libItem.isEmpty()){
            for(int i =0;i < libItem.size();i++){
                if(libItem.get(i).getItemID() == index){
                    libItem.remove(libItem.get(i));
                }
            }
        }
        else{
            System.out.println("The arraylist is empty");
        }
        
    }

    public LibraryItem findItem(int index){
        /*This method looks for the selected item based on the itemID, if it can't find the item it returns the first value in the arraylist */
        for(int i =0;i < libItem.size();i++){
            if(libItem.get(i).getItemID() == index){
                return libItem.get(i);
            }
        }
        System.out.println("There is no item with that item id");
        return libItem.get(0);
    }
   
    public void addItem(T catalogItem){
        /*Method for adding items to the catalog */
        libItem.add(catalogItem);
    }

}
