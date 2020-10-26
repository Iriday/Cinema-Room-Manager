import java.util.stream.Stream;

public class CinemaRoomGen {
    public static int[][] generate(int rows, int seats) {
        return Stream.generate(() -> new int[seats]).limit(rows).toArray(int[][]::new);
    }
}
