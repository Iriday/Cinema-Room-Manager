import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class View {
    private Controller controller;
    private Scanner scn;
    private static final String WRONG_INPUT = "Wrong input! Please try again.\n";

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
                            0. Exit"""
                    , 0, 3);
            switch (option) {
                case 1 -> showCinemaRoom(controller.getCinemaRoom());
                case 2 -> {
                    int[][] room = controller.getCinemaRoom();

                    while (true) {
                        int[] seatCoords = getSeatCoords(scn, room.length, room[0].length);
                        try {
                            OptionalDouble ticketPriceIfSeatIsEmpty = controller.buyTicket(seatCoords[0], seatCoords[1]);
                            if (ticketPriceIfSeatIsEmpty.isPresent()) {
                                View.showTicketPrice(ticketPriceIfSeatIsEmpty.getAsDouble());
                                break;
                            }
                            System.out.println("The ticket has already been purchased!\n");
                        } catch (NoTicketsLeftException e) {
                            System.out.println("All tickets have already been sold! Please come back later.");
                            break;
                        }
                    }
                }
                case 3 -> showStatistic(controller.getNumOfPurchasedTickets(), controller.getNumOfPurchasedTicketsAsPercentage(), controller.getCurrentIncome(), controller.getTotalIncome());
                case 0 -> System.exit(0);
            }
        }
    }

    public int[] getCinemaRoomSize() {
        return new int[]{getNum(scn, "Enter the number of rows:", 1, Integer.MAX_VALUE),
                getNum(scn, "Enter the number of seats in each row:", 1, Integer.MAX_VALUE)};
    }

    private static int[] getSeatCoords(Scanner scn, int rows, int seats) {
        return new int[]{getNum(scn, "Enter a row number:", 1, rows),
                getNum(scn, "Enter a seat number in that row:", 1, seats)};
    }

    private static int getNum(Scanner scn, String msg, int leftBoundIncl, int rightBoundIncl) {
        while (true) {
            System.out.println(msg);
            try {
                int input = Integer.parseInt(scn.next());
                if (input >= leftBoundIncl && input <= rightBoundIncl) return input;

                System.out.println(WRONG_INPUT);
            } catch (NumberFormatException e) {
                System.out.println(WRONG_INPUT);
            }
        }
    }

    private static void showCinemaRoom(int[][] room) {
        System.out.println("\nCinema:");
        System.out.println(formatCinemaRoom(room, "S", "B"));
    }

    private static String formatCinemaRoom(int[][] room, String emptySeatSymbol, String occupiedSeatSymbol) {
        int firstColWidth = String.valueOf(room.length).length() + 1;
        int colWidth = String.valueOf(room[0].length).length() + 1;
        var sb = new StringBuilder(adjustLen(" ", firstColWidth));
        AtomicInteger rowNum = new AtomicInteger(0);

        IntStream.rangeClosed(1, room[0].length).forEach(v -> sb.append(adjustLen(v, colWidth)));
        sb.append("\n");

        Arrays.stream(room).forEach(row -> sb.append(Arrays.stream(row)
                .mapToObj(v -> adjustLen(v == 0 ? emptySeatSymbol : occupiedSeatSymbol, colWidth))
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
