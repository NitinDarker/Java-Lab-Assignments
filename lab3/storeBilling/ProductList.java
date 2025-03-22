package lab3.storeBilling;

public class ProductList {
    private Product head;

    public ProductList() {
        head = null;
    }

    public ProductList(Product p) {
        head = p;
    }

    public void addProduct(Product newProduct) {
        newProduct.setNext(head);
        head = newProduct;
    }

    public void addProduct(String name, int quantity, double price) {
        Product newProduct = new Product(name, quantity, price);
        addProduct(newProduct);
    }

    public void showProductList() {
        System.out.println();
        Product temp = head;
        while (temp != null) {
            temp.showProduct();
            temp = temp.getNext();
        }
    }
}
