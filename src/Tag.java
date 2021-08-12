import java.util.Objects;

public class Tag {
    private String id;
    private String prefix;
    private String value;
    private String type;
    private Range range;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Range getRange() {
        return this.range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

    public int length() {
        return toString().length();
    }

    public String reverse() {
        return new StringBuilder().append(this.prefix).append(this.value).reverse().toString();
    }

    @Override
    public boolean equals(Object tag) {
        if (tag == this) return true;
        if (!(tag instanceof Tag)) return false;
        Tag that = (Tag) tag;
        boolean isEqualPrefix = this.prefix.equals(that.prefix);
        boolean isEqualValue = this.value.equals(that.value);
        boolean isEqualRange = this.range.equals(that.range);
        return  isEqualPrefix && isEqualValue && isEqualRange;
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
