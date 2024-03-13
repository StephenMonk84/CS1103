public class Main {
    public static void main(String[] args){
        LibraryItem<Integer, String> newItem = new LibraryItem<Integer,String>(12, "How to Train a Dragon", "Lindsay Monk");
        newItem.printerInfo();
        Catalog<?> catItem = new Catalog<>();
        catItem.consoleOutput();
        LibraryItem libItem = new LibraryItem<>(1, "Starship Troopers", "Robert Heinlein");
        Catalog catItem01 = new Catalog<>(libItem);
        catItem01.consoleOutput();
    }
    //Add menu method this needs to check for the correct data type
    //Fully Implement Catalog class make sure add, delete, and display all the items on screen
    
}
