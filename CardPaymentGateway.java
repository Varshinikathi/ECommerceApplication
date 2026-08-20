public class CardPaymentGateway implements PaymentGateway {
    @Override
    public boolean pay(double amount) {
        System.out.printf("Card payment successful: Rs. %.2f%n", amount);
        return true;
    }

    @Override
    public String getGatewayName() {
        return "Card";
    }
}
