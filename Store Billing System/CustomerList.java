class CustomerList {
    class CustomerNode {
        Customer data;
        CustomerNode next;
    
        CustomerNode(String name) {
            data = new Customer(name);
            next = null;
        }
    
    }
    public void insert_at_start(String name) {
        C newNode = new Customer(name);
        // Assuming that there is atLeast one node 
        newNode.next = data;
        data = newNode;
    }
}
