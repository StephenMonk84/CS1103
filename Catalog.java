import java.util.ArrayList;

public class Catalog <T>{

    T catalogItem;
    ArrayList<LibraryItem> libItem = new ArrayList<>();
    
    public Catalog(T catalogItem){
        this.catalogItem = catalogItem;
    }


}
