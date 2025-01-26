import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryParser {

    public static Query parseQuery(String sql) {
        Query query = new Query();
        extractSelectClause(sql, query);
        extractFromClause(sql, query);
        extractJoinClauses(sql, query);
        extractWhereClause(sql, query);
        extractGroupByClause(sql, query);
        extractHavingClause(sql, query);
        extractOrderByClause(sql, query);
        extractLimitClause(sql, query);
        return query;
    }

    private static void extractSelectClause(String sql, Query query) {
        Pattern selectPattern = Pattern.compile("(?i)select\\s+(.*?)\\s+from");
        Matcher selectMatcher = selectPattern.matcher(sql);
        if (selectMatcher.find()) {
            query.setColumns(Arrays.asList(selectMatcher.group(1).split("\\s*,\\s*")));
        }
    }

    private static void extractFromClause(String sql, Query query) {
        Pattern fromPattern = Pattern.compile("(?i)from\\s+(.*?)\\s+(left|right|inner|outer|join|where|group by|having|order by|limit|$)");
        Matcher fromMatcher = fromPattern.matcher(sql);
        if (fromMatcher.find()) {
            query.setFromSources(parseSources(fromMatcher.group(1)));
        }
    }

    private static void extractJoinClauses(String sql, Query query) {
        Pattern joinPattern = Pattern.compile("(?i)(left|right|inner|full)?\\s*join\\s+(.*?)\\s+on\\s+(.*?)\\s+(where|group by|having|order by|limit|$)");
        Matcher joinMatcher = joinPattern.matcher(sql);
        List<Join> joins = new ArrayList<>();
        while (joinMatcher.find()) {
            Join join = new Join();
            join.setType(joinMatcher.group(1) != null ? joinMatcher.group(1).toUpperCase() : "INNER");
            join.setTable(joinMatcher.group(2));
            join.setCondition(joinMatcher.group(3));
            joins.add(join);
        }
        query.setJoins(joins);
    }

    private static void extractWhereClause(String sql, Query query) {
        Pattern wherePattern = Pattern.compile("(?i)where\\s+(.*?)\\s+(group by|having|order by|limit|$)");
        Matcher whereMatcher = wherePattern.matcher(sql);
        if (whereMatcher.find()) {
            query.setWhereClauses(parseConditions(whereMatcher.group(1)));
        }
    }

    private static void extractGroupByClause(String sql, Query query) {
        Pattern groupByPattern = Pattern.compile("(?i)group by\\s+(.*?)\\s+(having|order by|limit|$)");
        Matcher groupByMatcher = groupByPattern.matcher(sql);
        if (groupByMatcher.find()) {
            query.setGroupByColumns(Arrays.asList(groupByMatcher.group(1).split("\\s*,\\s*")));
        }
    }

    private static void extractHavingClause(String sql, Query query) {
        Pattern havingPattern = Pattern.compile("(?i)having\\s+(.*?)\\s+(order by|limit|$)");
        Matcher havingMatcher = havingPattern.matcher(sql);
        if (havingMatcher.find()) {
            query.setHavingConditions(parseConditions(havingMatcher.group(1)));
        }
    }

    private static void extractOrderByClause(String sql, Query query) {
        Pattern orderByPattern = Pattern.compile("(?i)order by\\s+(.*?)\\s+(limit|$)");
        Matcher orderByMatcher = orderByPattern.matcher(sql);
        if (orderByMatcher.find()) {
            query.setSortColumns(parseSortColumns(orderByMatcher.group(1)));
        }
    }

    private static void extractLimitClause(String sql, Query query) {
        Pattern limitPattern = Pattern.compile("(?i)limit\\s+(\\d+)(?:\\s+offset\\s+(\\d+))?");
        Matcher limitMatcher = limitPattern.matcher(sql);
        if (limitMatcher.find()) {
            query.setLimit(Integer.parseInt(limitMatcher.group(1)));
            if (limitMatcher.group(2) != null) {
                query.setOffset(Integer.parseInt(limitMatcher.group(2)));
            }
        }
    }

    private static List<Source> parseSources(String fromClause) {
        List<Source> sources = new ArrayList<>();
        for (String source : fromClause.split("\\s*,\\s*")) {
            sources.add(new Source(source.trim()));
        }
        return sources;
    }

    private static List<WhereClause> parseConditions(String conditions) {
        List<WhereClause> clauses = new ArrayList<>();
        for (String condition : conditions.split("\\s+and\\s+")) {
            clauses.add(new WhereClause(condition.trim()));
        }
        return clauses;
    }

    private static List<Sort> parseSortColumns(String orderByClause) {
        List<Sort> sorts = new ArrayList<>();
        for (String sort : orderByClause.split("\\s*,\\s*")) {
            String[] parts = sort.trim().split("\\s+");
            sorts.add(new Sort(parts[0], parts.length > 1 ? DirectionType.valueOf(parts[1]) : DirectionType.ASC));
        }
        return sorts;
    }
}



