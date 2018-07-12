public class __Main {
    public static void main(String[] args){
        _List list = new ArrayList();
        _Deque deque = new LinkedList();
        System.out.print("A is "); list.isEmpty();
        System.out.print("D is "); deque.isEmpty();

        for (int i=0;i<15;i++){
            list.add(i);
        }

        System.out.println("A"+list);
        System.out.println("D"+deque);
        deque.size();
        System.out.println(deque.size());
        deque.add(98);
        deque.add(97);
        deque.add(96);
        deque.addFirst(99);
        System.out.println("D"+deque);
        System.out.print("A is "); list.isEmpty();
        System.out.print("D is "); deque.isEmpty();
        list.clear();
        System.out.println("A"+list);
        System.out.print("A is "); list.isEmpty();
        deque.remove(95);
        deque.addFirst(95);
        deque.remove(99);
        System.out.println("D"+deque);
    }
}
