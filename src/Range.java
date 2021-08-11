import java.util.Objects;

public class Range {
    private final int start;
    private final int end;

    public Range(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object range) {
        if (this == range) return true;
        if (!(range instanceof Range that)) return false;
        return this.start == that.start && this.end == that.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.start, this.end);
    }

    @Override
    public String toString() {
        return "Start: " + start + ", End: " + end;
    }
}
