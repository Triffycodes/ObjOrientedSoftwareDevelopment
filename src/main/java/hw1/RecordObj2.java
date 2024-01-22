package hw1;

public class RecordObj2 implements Record {
    Video video;
    int numOwned;
    int numOut;
    int numRentals;

    RecordObj2(Video video, int numOwned, int numOut, int numRentals) {
        this.video = video;
        this.numOwned = numOwned;
        this.numOut = numOut;
        this.numRentals = numRentals;
    }

    @Override
    public int numOwned() {
        return numOwned;
    }

    @Override
    public int numOut() {
        return numOut;
    }

    @Override
    public int numRentals() {
        return numRentals;
    }

    @Override
    public RecordObj copy() {
        return new RecordObj(video, numOwned, numOut, numRentals);
    }
    
}
