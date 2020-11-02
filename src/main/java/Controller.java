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
        return model.buyTicket(row, seat);
    }

    public int getNumOfPurchasedTickets() {
        return model.getNumOfPurchasedTickets();
    }

    public double getNumOfPurchasedTicketsAsPercentage() {
        return model.getNumOfPurchasedTicketsAsPercentage();
    }

    public double getCurrentIncome() {
        return model.getCurrentIncome();
    }

    public double getTotalIncome() {
        return model.getTotalIncome();
    }
}
