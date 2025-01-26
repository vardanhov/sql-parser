public class Join {
    private String type;
    private String table;
    private String condition;

    public void setType(String type) {
        this.type = type;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return type + " JOIN " + table + " ON " + condition;
    }
}
