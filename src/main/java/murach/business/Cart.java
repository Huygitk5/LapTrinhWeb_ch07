package murach.business;
import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable{
    private ArrayList<LineItem> items;
    
    public Cart() {
        items = new ArrayList<>();
    }
    
    public Cart(ArrayList<LineItem> items) {
        this.items = items;
    }
    
    public ArrayList<LineItem> getItems() {
        return items;
    }
    
    public void setItems(ArrayList<LineItem> items) {
        this.items = items;
    }
    
    public void addItem(LineItem item) {
        String code = item.getProduct().getCode();
        int quantity = item.getQuantity();
        
        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            // Da co sp
            if(lineItem.getProduct().getCode().equals(code)) {
                lineItem.setQuantity(lineItem.getQuantity() + quantity);
                return;
            }
        }
        // Chua co sp
        items.add(item);
    }
    
    public void removeItem (LineItem item) {
        String code = item.getProduct().getCode();
        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            // tim thay sp
            if(lineItem.getProduct().getCode().equals(code)) {
                items.remove(i);
                return;
            }
        }
    }
}
