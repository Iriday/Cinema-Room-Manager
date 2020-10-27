import java.util.stream.Stream;

public class Model {
    public static int[][] generateCinemaRoom(int rows, int seats) {
        return Stream.generate(() -> new int[seats]).limit(rows).toArray(int[][]::new);
    }

    public static double calcIncome(int rows, int seats, double frontSeatPrice, double backSeatPrice) {
        int numOfSeats = rows * seats;
        int numOfFrontRows = rows / 2;
        return numOfSeats < 60 ? numOfSeats * frontSeatPrice :
                numOfFrontRows * seats * frontSeatPrice + (rows - numOfFrontRows) * seats * backSeatPrice;
    }

    public static double getTicketPrice(int rows, int seats, double frontSeatsPrice, double backSeatsPrice, int row) {
        return rows * seats < 60 ? frontSeatsPrice : (row <= rows / 2 ? frontSeatsPrice : backSeatsPrice);
    }

    public static void markSeatAsOccupied(int[][] room, int row, int seat) {
        room[row - 1][seat - 1] = 1;
    }
}
