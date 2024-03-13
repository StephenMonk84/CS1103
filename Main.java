import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        /*I have tried to keep the main method clear of a lot of extra code */
        menu();
    }
    static void menu(){
        int inp;
        LibraryItem<Integer, String> libItem = new LibraryItem<>(1, "Starship Troopers", "Robert Heinlein");
        Catalog<LibraryItem> catItem = new Catalog<>(libItem);
        
        while(true){
            //This displays the menu to allow the user to select the correct option
            System.out.println("----Welcome to the Library Catalog Management System-----");
            System.out.println();
            System.out.println("Please enter the number from the menu below:");
            System.out.println("1. Add item");
            System.out.println("2. Modify Item");
            System.out.println("3. Remove Item");
            System.out.println("4. Display the Collection");
            System.out.println("5. Exit"); 
            inp = getIntInput();
           
            if (inp ==1){
                //This part creates new objects in the catalog
                libItem = new LibraryItem<>();
                System.out.println("Please the id number of the item");
                libItem.setItemID(getIntInput());
                System.out.println("Please enter the author.");
                libItem.setAuthor(getStringInput());
                System.out.println("Please enter the title.");
                libItem.setTitle(getStringInput());
                catItem.addItem(libItem);
                continue;
            }
            if(inp==2){
                //need to add the modify item logic
                System.out.println("Please enter the ID of the item you wish to modify");
                catItem.findItem(getIntInput());
                System.out.println("Please the new id number of the item");
                libItem.setItemID(getIntInput());
                System.out.println("Please enter new the author.");
                libItem.setAuthor(getStringInput());
                System.out.println("Please enter new the title.");
                libItem.setTitle(getStringInput());
                continue;
            }
            if(inp==3){
                //need to add the remove logic
                System.out.println("Please enter the ID of the item you wish to remove");
                catItem.removeItem(getIntInput());
                continue;
            }
            if(inp==4){
                //This is the part that displays the collection
                catItem.collectionOutput();
                continue;
            }
            if(inp ==5){
                //this quits the program
                break;
            }
            else{
                System.out.println("Please enter a valid selection");
            }
        }
    }
    static int getIntInput(){
        Scanner menuInp = new Scanner(System.in);
        String testInp = menuInp.nextLine();
        try{Integer.parseInt(testInp);
            return Integer.parseInt(testInp);
        }
        catch(NumberFormatException e){
            return 0;
        }
        
    }
    static String getStringInput(){
        Scanner menuInp = new Scanner(System.in);
        return menuInp.nextLine();
    }

  

    //Add menu method this needs to check for the correct data type
    //Fully Implement Catalog class make sure delete, and display all the items on screen
    
}
