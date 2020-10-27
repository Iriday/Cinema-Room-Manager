public class Main {
    public static void main(String[] args) {
        int[] numOfRowsSeats = View.getCinemaRoomSize();
        View.showCinemaRoom(Model.generateCinemaRoom(numOfRowsSeats[0], numOfRowsSeats[1]));
        View.showIncome(Model.calcIncome(numOfRowsSeats[0], numOfRowsSeats[1], 10, 8));
    }
}
