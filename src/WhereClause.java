public class WhereClause {
    private String condition;

    public WhereClause(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return condition;
    }
}
