import java.util.*;

public class Query {
    private List<String> columns = new ArrayList<>();
    private List<Source> fromSources = new ArrayList<>();
    private List<Join> joins = new ArrayList<>();
    private List<WhereClause> whereClauses = new ArrayList<>();
    private List<String> groupByColumns = new ArrayList<>();
    private List<WhereClause> havingConditions = new ArrayList<>();
    private List<Sort> sortColumns = new ArrayList<>();
    private Integer limit;
    private Integer offset;

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<Source> getFromSources() {
        return fromSources;
    }

    public void setFromSources(List<Source> fromSources) {
        this.fromSources = fromSources;
    }

    public List<Join> getJoins() {
        return joins;
    }

    public void setJoins(List<Join> joins) {
        this.joins = joins;
    }

    public List<WhereClause> getWhereClauses() {
        return whereClauses;
    }

    public void setWhereClauses(List<WhereClause> whereClauses) {
        this.whereClauses = whereClauses;
    }

    public List<String> getGroupByColumns() {
        return groupByColumns;
    }

    public void setGroupByColumns(List<String> groupByColumns) {
        this.groupByColumns = groupByColumns;
    }

    public List<WhereClause> getHavingConditions() {
        return havingConditions;
    }

    public void setHavingConditions(List<WhereClause> havingConditions) {
        this.havingConditions = havingConditions;
    }

    public List<Sort> getSortColumns() {
        return sortColumns;
    }

    public void setSortColumns(List<Sort> sortColumns) {
        this.sortColumns = sortColumns;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    // Getters and setters for all fields
    // toString method for debugging

    @Override
    public String toString() {
        return "Query{" +
                "columns=" + columns +
                ", fromSources=" + fromSources +
                ", joins=" + joins +
                ", whereClauses=" + whereClauses +
                ", groupByColumns=" + groupByColumns +
                ", havingConditions=" + havingConditions +
                ", sortColumns=" + sortColumns +
                ", limit=" + limit +
                ", offset=" + offset +
                '}';
    }
}
