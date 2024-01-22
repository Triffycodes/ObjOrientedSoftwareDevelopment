package hw1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VideoObjTest {
    
    @Test
    public void testConstructorAndAttributes() {
      String title1 = "XX";
      String director1 = "XY";
      String title2 = "XX";
      String director2 = "XY";
      String title3 = " XXY ";
      String director3 = "XYY";
      int year = 2002;
  
      VideoObj2 v1 = new VideoObj2(title1, year, director1);
      assertTrue(title1== v1.title());
      assertTrue(year== v1.year());
      assertTrue(director1== v1.director());
  
      VideoObj2 v2 = new VideoObj2(title2, year, director2);
           assertEquals(title1,v2.title());
      assertTrue(title1.equals(v2.title()));
      assertEquals(director1,v2.director());   
  
  
      VideoObj2 v3 = new VideoObj2(title3, year, director3);
      assertFalse(title3.equals(v3.title()));
      assertEquals(director3,v3.director());
    }
}