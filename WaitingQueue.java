import java.util.Arrays;

public class WaitingQueue {
    private final Vehicle[] items;
    private int front = 0;
    private int rear = -1;
    private int count = 0;

    public WaitingQueue(int capacity) {
        items = new Vehicle[capacity];
    }

    public void add(Vehicle vehicle) {
        if (count == items.length) {
            throw new IllegalStateException("Waiting queue is full.");
        }
        rear = (rear + 1) % items.length;
        items[rear] = vehicle;
        count++;
    }

    public Vehicle remove() {
        if (isEmpty()) return null;
        Vehicle vehicle = items[front];
        items[front] = null;
        front = (front + 1) % items.length;
        count--;
        if (count == 0) { front = 0; rear = -1; }
        return vehicle;
    }

    public boolean cancel(String vehicleNumber) {
        String target = vehicleNumber.trim().toUpperCase();
        Vehicle[] kept = new Vehicle[items.length];
        int keptCount = 0;
        boolean removed = false;

        for (int i = 0; i < count; i++) {
            Vehicle current = items[(front + i) % items.length];
            if (!removed && current.getVehicleNumber().equals(target)) {
                removed = true;
            } else {
                kept[keptCount++] = current;
            }
        }

        if (removed) {
            Arrays.fill(items, null);
            System.arraycopy(kept, 0, items, 0, keptCount);
            front = 0;
            count = keptCount;
            rear = count - 1;
        }
        return removed;
    }

    public boolean contains(String vehicleNumber) {
        String target = vehicleNumber.trim().toUpperCase();
        for (int i = 0; i < count; i++) {
            if (items[(front + i) % items.length].getVehicleNumber().equals(target)) return true;
        }
        return false;
    }

    public boolean isEmpty() { return count == 0; }
    public int size() { return count; }

    public void show() {
        if (isEmpty()) {
            System.out.println("Waiting queue: Empty");
            return;
        }
        System.out.println("Waiting queue:");
        for (int i = 0; i < count; i++) {
            Vehicle v = items[(front + i) % items.length];
            System.out.println((i + 1) + ". " + v.getType() + " - " + v.getVehicleNumber());
        }
    }
}
