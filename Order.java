import java.util.ArrayList;
/**
 * Order Class that adds to and manipulates Order objects
 */
public class Order {
    public String date;
    ArrayList<Integer> itemsIndex = new ArrayList<>();
    ArrayList<Integer> quant = new ArrayList<>();
    int numItems;
    double totalCost;
    String authCode;

    /**
     * Order constructor that adds the date
     * @param date
     */
    public Order(String date) {
        this.date = date;
    }
    /**
     * gets the date of the order
     * @return
     */
    public String getDate() {
        return date;
    }
    /**
     * retrieves an order item number that has been added to the order already
     * @param index
     * @return
     */
    public int getItemOrdered(int index) {
        return itemsIndex.get(index);
    }
    /**
     * adds an item and quantity to the order
     * @param item
     * @param ammount
     */
    public void addItemToOrder(int item,int ammount) {
        itemsIndex.add(item);
        quant.add(ammount);
        
    }
    /**
     * gets the total number of items in the order
     * @return
     */
    public int getQuant() {
        for (int i=0; i<quant.size(); i++)
        {
            numItems+=quant.get(i);
        }
        return numItems;
    }
    /**
     * gets the total cost of the order
     * @return
     */
    public double getTotal() {
        totalCost=0;
        for (int i=0; i<quant.size(); i++)
        {
            totalCost += Catalog.getPrice(i)*quant.get(i);
            
        }
        totalCost= totalCost + totalCost*.1;
        return totalCost;
    }
    /**
     * gets Authentication code
     * @return
     */
    public String getAuthCode() {
        return authCode;
    }
    /**
     * sets authentication code
     * @param authCode
     */
    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    
}