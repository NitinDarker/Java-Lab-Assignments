public abstract class Customer {
    protected String name;
    protected long contactNum;
    protected String membershipInfo;

    protected Customer(String name, long contactNum) {
        this.name = name;
        this.contactNum = contactNum;
    }

    protected void getCustomerInfo() {
        System.out.println("Name: " + name);
        System.out.println("Contact Num: " + contactNum);
        System.out.println("Membership: " + membershipInfo);
    };
}

class RegularCustomer extends Customer {
    public RegularCustomer(String name, long contactNum) {
        super(name, contactNum);
        membershipInfo = "Regular";
    }
}

class PremiumCustomer extends Customer {
    public PremiumCustomer(String name, long contactNum) {
        super(name, contactNum);
        membershipInfo = "Premium";
    }
}