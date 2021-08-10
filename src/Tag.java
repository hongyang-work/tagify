import java.util.Objects;

public class Tag {
    private String prefix;
    private String value;
    private Range range;

    public String getPrefix() {
        return this.prefix;
    }

    public String getValue() {
        return this.value;
    }

    public Range getRange() {
        return this.range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

    @Override
    public boolean equals(Object tag) {
        if (tag == this) return true;
        if (!(tag instanceof Tag)) return false;
        Tag that = (Tag) tag;
        boolean isEquals = this.prefix.equals(that.prefix) && this.value.equals(that.value);
        return  range == null ? isEquals : isEquals && this.range.equals(that.range);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.prefix, this.value, this.range);
    }

    @Override
    public String toString() {
        return this.prefix + this.value;
    }
}
