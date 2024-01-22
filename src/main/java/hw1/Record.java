package hw1;

public interface Record {
 
    public int numOwned();
    public int numOut();
    public int numRentals();
    public RecordObj copy();
    public String toString();
}