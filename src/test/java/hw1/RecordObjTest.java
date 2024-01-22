package hw1;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
public class RecordObjTest {
    @Test
    public void testConstructorAndAttributes() {
        String title1 = "XX";
        String director1 = "XY";

        int year = 2002;

        VideoObj v1 = new VideoObj(title1, year, director1);

        Record record = new RecordObj(v1, 4, 2, 8);

        assertTrue(4 == record.numOwned());
        assertTrue(2 == record.numOut());
        assertTrue(8 == record.numRentals());

    }

    @Test
    public void testcopy() {
        String title1 = "video";
        String director1 = "video1";

        int year = 2002;

        VideoObj v1 = new VideoObj(title1, year, director1);

        Record record = new RecordObj(v1, 4, 2, 8);

        Record temp = record.copy();

        assertEquals(temp.toString(), record.toString());
    }


    
}
