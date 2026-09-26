public class Main {
    public static void main(String[] args) {
        DynamicArr arr = new DynamicArr();

        arr.add(10);
        arr.add(20);
        arr.add(30);

        int removed = arr.remove(1);

        System.out.println(removed);
        System.out.println(arr.get(0));
        System.out.println(arr.get(1));
    }
}
