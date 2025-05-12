package ITEM;
import java.util.ArrayList;
public class Datamanager {
	 private static ArrayList<item> items = new ArrayList<>();
	    public static ArrayList<item> getItems() {
	        return items;
	    }
	    public static void addItem(item newItem) {
	        items.add(newItem);
	    }
	    public static void removeItem(item itemToRemove) {
	        items.remove(itemToRemove);
	    }
	    public static long getTotalPrice() {
	        long total = 0;
	        for (item item : items) {
	            total += item.getPrice();
	        }
	        return total + 10000; 
	    }
	   public static void main(String[] args) {
		System.out.println(getTotalPrice());	
	}
}
