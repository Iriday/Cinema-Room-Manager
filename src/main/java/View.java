import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class View {
    public static void showCinemaRoom(int[][] room) {
        System.out.println("Cinema:");
        System.out.println(formatCinemaRoom(room, "S"));
    }

    public static String formatCinemaRoom(int[][] room, String emptySeatSymbol) {
        int firstColWidth = String.valueOf(room.length).length() + 1;
        int colWidth = String.valueOf(room[0].length).length() + 1;
        var sb = new StringBuilder(adjustLen(" ", firstColWidth));
        AtomicInteger rowNum = new AtomicInteger(0);

        IntStream.rangeClosed(1, room[0].length).forEach(v -> sb.append(adjustLen(v, colWidth)));
        sb.append("\n");

        Arrays.stream(room).forEach(row -> sb.append(Arrays.stream(row)
                .mapToObj(v -> adjustLen(emptySeatSymbol, colWidth))
                .collect(Collectors.joining("", adjustLen(rowNum.incrementAndGet(), firstColWidth), "\n"))));

        return sb.toString();
    }

    private static String adjustLen(Object str, int len) {
        return String.format("%" + len + "s", str.toString());
    }
}
