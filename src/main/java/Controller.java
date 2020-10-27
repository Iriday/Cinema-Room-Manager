public class Controller {
    public static void run() {
        int[] numOfRowsSeats = View.getCinemaRoomSize();
        int[][] room = Model.generateCinemaRoom(numOfRowsSeats[0], numOfRowsSeats[1]);

        while (true) {
            switch (View.showMainMenu()) {
                case 1 -> View.showCinemaRoom(room);
                case 2 -> {
                    int[] seatCoords = View.getSeatCoords();
                    double ticketPrice = Model.getTicketPrice(numOfRowsSeats[0], numOfRowsSeats[1], 10, 8, seatCoords[0]);
                    Model.markSeatAsOccupied(room, seatCoords[0], seatCoords[1]);
                    View.showTicketPrice(ticketPrice);
                }
                case 0 -> System.exit(0);
            }
        }
//        View.showIncome(Model.calcIncome(numOfRowsSeats[0], numOfRowsSeats[1], 10, 8));
    }
}
