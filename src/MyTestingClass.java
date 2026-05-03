public class MyTestingClass {
    private int id;
    private String name;

    public MyTestingClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = hash * 31 + id;
        hash = hash * 31 + name.length();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MyTestingClass)) return false;

        MyTestingClass other = (MyTestingClass) obj;
        return this.id == other.id && this.name.equals(other.name);
    }
}