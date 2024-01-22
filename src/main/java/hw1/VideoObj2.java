package hw1;

public class VideoObj2 implements Video {
    private final String _title;
    private final int _year;
    private final String _director;

    VideoObj2(String title, int year, String director) {
        if ((title == null) || (director == null) || (year <= 1800) || (year >= 5000)) {
            throw new IllegalArgumentException();
        }
        _title = title.trim();
        _director = director.trim();
        _year = year;
        if (("".equals(_title)) || ("".equals(_director))) {
            throw new IllegalArgumentException();
        }
    }

    public String director() {
        return _director;
    }

    public String title() {
        return _title;
    }

    public int year() {
        return _year;
    }

    public boolean equals(Object thatObject) {
        if (!(thatObject instanceof Video)) {
            return false;
        }
        Video that = (Video) thatObject;
        return _title.equals(that.title()) && (_year == that.year()) && _director.equals(that.director());
    }

    public int hashCode() {
        int result = _title.hashCode();
        result = 31*result + _year;
        result = 31*result + _director.hashCode();
        return result;
    }

    public int compareTo(Video that) {
        int titleDiff = _title.compareTo(that.title());
        if (titleDiff != 0) {
          return titleDiff;
        }
        int yearDiff = _year - that.year();
        if (yearDiff != 0) {
          return yearDiff;
        }
        int directorDiff = _director.compareTo(that.director());
        if (directorDiff != 0) {
          return directorDiff;
        }
        return 0;
    }

    public String toString() {
    StringBuilder buffer = new StringBuilder();
    buffer.append(_title);
    buffer.append(" (");
    buffer.append(_year);
    buffer.append(") : ");
    buffer.append(_director);
    return buffer.toString();
    }
    
}
