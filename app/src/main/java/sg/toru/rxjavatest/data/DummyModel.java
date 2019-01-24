package sg.toru.rxjavatest.data;

import java.util.Objects;

public class DummyModel {
    private String type;
    private String index;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DummyModel)) return false;
        DummyModel that = (DummyModel) o;
        return Objects.equals(getType(), that.getType()) &&
                Objects.equals(getIndex(), that.getIndex());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getIndex());
    }
}
