package hw1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class VideoObj2Test {
    @Test
    public void testConstructorAndAttributes() {
      String title1 = "title1";
      String director1 = "director1";
      String title2 = "title2";
      String director2 = "director2";
      String title3 = "title3";
      String director3 = "director3";
      int year = 2002;
  
      VideoObj2 v1 = new VideoObj2(title1, year, director1);
      assertTrue(title1== v1.title());
      assertTrue(year== v1.year());
      assertTrue(director1== v1.director());
  
      VideoObj2 v2 = new VideoObj2(title2, year, director2);
      assertTrue(title2.equals(v2.title()));
      assertEquals(director2,v2.director());
  
      VideoObj2 v3 = new VideoObj2(title3, year, director3);
      assertTrue(title3.equals(v3.title()));
      assertEquals(director3,v3.director());
    }
   
}
