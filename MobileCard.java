public class MobileCard extends Product{
    private String phoneNumber;

    public MobileCard(String name, double price, int quantity, String phoneNumber) {
        super(name, price, quantity);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
