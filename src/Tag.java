import java.util.Objects;

public class Tag {
    private String prefix;
    private String value;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object tag) {
        if (tag == this) return true;
        if (!(tag instanceof Tag)) return false;
        Tag that = (Tag) tag;
        return this.prefix.equals(that.prefix) && this.value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prefix, value);
    }
}
