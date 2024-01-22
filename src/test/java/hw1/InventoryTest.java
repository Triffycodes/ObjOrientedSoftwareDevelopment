package hw1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class InventoryTest{



 @Test
  public void testInventory(){
        Inventory inv = new InventorySet();
        String title1 = "video";
        String director1 = "video1";
        String title2 = " XX ";
        String director2 = " XY ";


        int year1 = 2002;
        int year2 = 2003;


        VideoObj v1 = new VideoObj(title1, year1, director1);
        VideoObj v2 = new VideoObj(title2, year2, director2);


        inv.addNumOwned(v1,1);
        inv.addNumOwned(v1, 2);


        inv.addNumOwned(v2, 4);
        inv.addNumOwned(v2, 5);

        assertEquals(3, inv.get(v1).numOwned());
        assertEquals(9, inv.get(v2).numOwned());

        inv.checkOut(v1);
        assertEquals(1, inv.get(v1).numOut());

        inv.checkOut(v1);
        assertEquals(2, inv.get(v1).numOut());
  }
}