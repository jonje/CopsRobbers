package copsandrobbers.helpers;

/**
 * Created by stephen on 10/30/15.
 */
public class Tuple<T1, T2> {
    public final T1 item1;
    public final T2 item2;

    private Tuple( T1 item1, T2 item2 ) {
        this.item1 = item1;
        this.item2 = item2;
    }

    public static <T1, T2> Tuple<T1, T2> create( T1 item1, T2 item2 ) {
        return new Tuple<>(item1, item2);
    }

    @Override
    public String toString() {
        return String.format("( %s, %s )", item1, item2);
    }
}
