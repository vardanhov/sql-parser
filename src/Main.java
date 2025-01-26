public class Main {
    public static void main(String[] args) {
        String sql = """
                SELECT author.name, count(book.id), sum(book.cost)
                FROM author
                LEFT JOIN book ON (author.id = book.author_id)
                GROUP BY author.name
                HAVING COUNT(*) > 1 AND SUM(book.cost) > 500
                ORDER BY author.name DESC
                LIMIT 10
                """;

        Query query = QueryParser.parseQuery(sql);
        System.out.println(query);
    }
}