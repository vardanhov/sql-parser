public class Sort {
    private String column;
    private DirectionType direction;

    public Sort(String column, DirectionType direction) {
        this.column = column;
        this.direction = direction;
    }

    @Override
    public String toString() {
        return  column ;
    }
}
