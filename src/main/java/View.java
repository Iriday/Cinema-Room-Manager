import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class View {
    private Controller controller;
    private Scanner scn;

    public void initialize(Controller controller) {
        this.controller = controller;
        this.scn = new Scanner(System.in);
    }

    public void showMainMenu() {
        while (true) {
            int option = getNum(scn, """
                                    
                    1. Show the seats
                    2. Buy a ticket
                    3. Statistic
                    0. Exit""");
            switch (option) {
                case 1 -> showCinemaRoom(controller.getCinemaRoom());
                case 2 -> {
                    int[] seatCoords = getSeatCoords(scn);
                    double ticketPrice = controller.buyTicket(seatCoords[0], seatCoords[1]);
                    View.showTicketPrice(ticketPrice);
                }
                case 3 -> showStatistic(controller.getNumOfPurchasedTickets(), controller.getNumOfPurchasedTicketsAsPercentage(), controller.getCurrentIncome(), controller.getTotalIncome());
                case 0 -> System.exit(0);
            }
        }
    }

    public int[] getCinemaRoomSize() {
        return new int[]{getNum(scn, "Enter the number of rows:"), getNum(scn, "Enter the number of seats in each row:")};
    }

    private static int[] getSeatCoords(Scanner scn) {
        return new int[]{getNum(scn, "Enter a row number:"), getNum(scn, "Enter a seat number in that row:")};
    }

    private static int getNum(Scanner scn, String msg) {
        System.out.println(msg);
        return scn.nextInt();
    }

    private static void showCinemaRoom(int[][] room) {
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

    private static void showTicketPrice(double price) {
        System.out.println("\nTicket price: $" + price);
    }

    private static void showStatistic(int numOfPurchasedTickets, double numOfPurchasedTicketsAsPercentage, double currentIncome, double totalIncome) {
        System.out.format("""
                                
                Number of purchased tickets: %d
                Percentage: %.2f%s
                Current income: $%.2f
                Total income $%.2f
                """, numOfPurchasedTickets, numOfPurchasedTicketsAsPercentage, "%", currentIncome, totalIncome);
    }
}
