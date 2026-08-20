public class UPIPaymentGateway implements PaymentGateway {
    @Override
    public boolean pay(double amount) {
        System.out.printf("UPI payment successful: Rs. %.2f%n", amount);
        return true;
    }

    @Override
    public String getGatewayName() {
        return "UPI";
    }
}
