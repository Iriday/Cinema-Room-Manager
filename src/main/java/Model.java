import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.stream.Stream;

public class Model {
    private int[][] room;
    private int rows;
    private int seats;
    private double frontSeatsPrice;
    private double backSeatsPrice;
    private double currentIncome;

    public void initialize(int rows, int seats, double frontSeatsPrice, double backSeatsPrice) {
        this.room = generateCinemaRoom(rows, seats);
        this.rows = rows;
        this.seats = seats;
        this.frontSeatsPrice = frontSeatsPrice;
        this.backSeatsPrice = backSeatsPrice;
        this.currentIncome = 0;
    }

    public int[][] getCinemaRoom() {
        return room;
    }

    public OptionalDouble buyTicket(int row, int seat) throws NoTicketsLeftException {
        if (isAllTicketsSold()) throw new NoTicketsLeftException();
        if (!isSeatEmpty(room, row, seat)) return OptionalDouble.empty();

        markSeatAsOccupied(room, row, seat);
        double ticketPrice = calcTicketPrice(rows, seats, frontSeatsPrice, backSeatsPrice, row);
        currentIncome += ticketPrice;
        return OptionalDouble.of(ticketPrice);
    }

    private boolean isAllTicketsSold() {
        return getNumOfPurchasedTickets() == rows * seats;
    }

    public static boolean isSeatEmpty(int[][] room, int row, int seat) {
        return room[row - 1][seat - 1] == 0;
    }

    public static void markSeatAsOccupied(int[][] room, int row, int seat) {
        room[row - 1][seat - 1] = 1;
    }

    public double getTotalIncome() {
        return calcTotalIncome(rows, seats, frontSeatsPrice, backSeatsPrice);
    }

    private static int[][] generateCinemaRoom(int rows, int seats) {
        return Stream.generate(() -> new int[seats]).limit(rows).toArray(int[][]::new);
    }

    private static double calcTotalIncome(int rows, int seats, double frontSeatPrice, double backSeatPrice) {
        int numOfSeats = rows * seats;
        int numOfFrontRows = rows / 2;
        return numOfSeats < 60 ? numOfSeats * frontSeatPrice :
                numOfFrontRows * seats * frontSeatPrice + (rows - numOfFrontRows) * seats * backSeatPrice;
    }

    private static double calcTicketPrice(int rows, int seats, double frontSeatsPrice, double backSeatsPrice, int row) {
        return rows * seats < 60 ? frontSeatsPrice : (row <= rows / 2 ? frontSeatsPrice : backSeatsPrice);
    }

    public double getCurrentIncome() {
        return currentIncome;
    }

    public int getNumOfPurchasedTickets() {
        return (int) Arrays.stream(room).flatMapToInt(Arrays::stream).filter(v -> v != 0).count();
    }

    public double getNumOfPurchasedTicketsAsPercentage() {
        return getNumOfPurchasedTickets() / (double) (rows * seats) * 100;
    }
}
