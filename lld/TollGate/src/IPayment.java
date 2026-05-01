public interface IPayment {
    boolean collectPayment(Vehicle vehicle) throws InsufficientBalanceException;
}
