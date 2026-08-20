public interface PaymentGateway {
    boolean pay(double amount);
    String getGatewayName();
}
