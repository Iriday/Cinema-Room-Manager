import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class View {
    public static int showMainMenu() {
        return getNum(new Scanner(System.in), """
                
                1. Show the seats
                2. Buy a ticket
                0. Exit""");
    }

    public static int[] getCinemaRoomSize() {
        var scn = new Scanner(System.in);
        return new int[]{getNum(scn, "Enter the number of rows:"), getNum(scn, "Enter the number of seats in each row:")};
    }

    public static int[] getSeatCoords() {
        var scn = new Scanner(System.in);
        return new int[]{getNum(scn, "Enter a row number:"), getNum(scn, "Enter a seat number in that row:")};
    }

    private static int getNum(Scanner scn, String msg) {
        System.out.println(msg);
        return scn.nextInt();
    }

    public static void showCinemaRoom(int[][] room) {
        System.out.println("\nCinema:");
        System.out.println(formatCinemaRoom(room, "S", "B"));
    }

    private static String formatCinemaRoom(int[][] room, String freeSeatSymbol, String occupiedSeatSymbol) {
        int firstColWidth = String.valueOf(room.length).length() + 1;
        int colWidth = String.valueOf(room[0].length).length() + 1;
        var sb = new StringBuilder(adjustLen(" ", firstColWidth));
        AtomicInteger rowNum = new AtomicInteger(0);

        IntStream.rangeClosed(1, room[0].length).forEach(v -> sb.append(adjustLen(v, colWidth)));
        sb.append("\n");

        Arrays.stream(room).forEach(row -> sb.append(Arrays.stream(row)
                .mapToObj(v -> adjustLen(v == 0 ? freeSeatSymbol : occupiedSeatSymbol, colWidth))
                .collect(Collectors.joining("", adjustLen(rowNum.incrementAndGet(), firstColWidth), "\n"))));

        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private static String adjustLen(Object str, int len) {
        return String.format("%" + len + "s", str.toString());
    }

    public static void showIncome(double income) {
        System.out.println("Total income:\n$" + income);
    }

    public static void showTicketPrice(double price) {
        System.out.println("\nTicket price: $" + price);
    }
}
