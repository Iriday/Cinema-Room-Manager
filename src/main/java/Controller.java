public class Controller {
    private final View view;
    private final Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        this.view.initialize(this);
        int[] numOfRowsSeats = view.getCinemaRoomSize();
        this.model.initialize(numOfRowsSeats[0], numOfRowsSeats[1], 10, 8);
    }

    public void run() {
        view.showMainMenu();
    }

    public int[][] getCinemaRoom() {
        return model.getCinemaRoom();
    }

    public double buyTicket(int row, int seat) {
        double ticketPrice = model.getTicketPrice(row);
        model.markSeatAsOccupied(row, seat);
        return ticketPrice;
    }
}
