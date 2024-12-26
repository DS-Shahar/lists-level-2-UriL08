
public class Main {

    public static void main(String[] args) {
    	
    	//EXC1: 1,3,5
        Node<Integer> list1 = new Node<>(1);
        list1.setNext(new Node<>(3));
        list1.getNext().setNext(new Node<>(5));
        
        //EXC2: 2,3,4,9
        Node<Integer> list2 = new Node<>(2);
        list2.setNext(new Node<>(3));
        list2.getNext().setNext(new Node<>(4));
        list2.getNext().getNext().setNext(new Node<>(9));
        
        //EXC3: 5,8,3,8,2,9
        Node<Integer> list3 = new Node<>(5);
        list3.setNext(new Node<>(8));
        list3.getNext().setNext(new Node<>(3));
        list3.getNext().getNext().setNext(new Node<>(8));
        list3.getNext().getNext().getNext().setNext(new Node<>(2));
        list3.getNext().getNext().getNext().getNext().setNext(new Node<>(9));
        int num = 8;

        //list4: 1,2,3,2 | list42: 2,3,4,9
        Node<Integer> list4 = new Node<>(1);
        list4.setNext(new Node<>(2));
        list4.getNext().setNext(new Node<>(3));
        list4.getNext().getNext().setNext(new Node<>(2));
        Node<Integer> list42 = new Node<>(2);
        list42.setNext(new Node<>(3));
        list42.getNext().setNext(new Node<>(4));
        list42.getNext().getNext().setNext(new Node<>(9));

        //6,2,3,2,4,2,1,6
        Node<Integer> list5 = new Node<>(6);
        list5.setNext(new Node<>(2));
        list5.getNext().setNext(new Node<>(3));
        list5.getNext().getNext().setNext(new Node<>(2)); // Duplicate
        list5.getNext().getNext().getNext().setNext(new Node<>(4));
        list5.getNext().getNext().getNext().getNext().setNext(new Node<>(2));
        list5.getNext().getNext().getNext().getNext().getNext().setNext(new Node<>(1)); // Duplicate
        list5.getNext().getNext().getNext().getNext().getNext().getNext().setNext(new Node<>(6));


        System.out.println("EXC1: " + exc1(list1, list2));

        System.out.println("EXC2: " + exc2(list1));
        System.out.println("Head of sorted list: " + exc2(list1).getValue());

        System.out.println("EXC3: Sum of distances for value " + num + " --> " +  exc3(list3, 8));

        System.out.println("EXC4 list4 has no duplicates: " + exc4(list4));
        System.out.println("EXC4 list42 has no duplicates: " + exc4(list42));

        System.out.println("EXC5 (original list5): " + list5);
        System.out.println("EXC5 (list5 without duplicates): " + exc5(list5));
    }

    public static Node<Integer> exc1(Node<Integer> list1, Node<Integer> list2) {
        Node<Integer> dummy = new Node<>(null);
        Node<Integer> current = dummy;

        while (list1 != null && list2 != null) {
            if (list1.getValue() <= list2.getValue()) {
                current.setNext(list1);
                list1 = list1.getNext();
            } else {
                current.setNext(list2);
                list2 = list2.getNext();
            }
            current = current.getNext();
        }

        if (list1 != null) {
            current.setNext(list1);
        } else if (list2 != null) {
            current.setNext(list2);
        }

        return dummy.getNext();
    }
    
    public static Node<Integer> exc2(Node<Integer> list1) {
        if (list1 == null || list1.getNext() == null) {
            return list1;
        }

        Node<Integer> head = list1;
        Node<Integer> current = head;

        while (current != null) {
            Node<Integer> minNode = current;
            Node<Integer> nextNode = current.getNext();

            while (nextNode != null) {
                if (nextNode.getValue() < minNode.getValue()) {
                    minNode = nextNode;
                }
                nextNode = nextNode.getNext();
            }

            if (minNode != current) {
                int temp = current.getValue();
                current.setValue(minNode.getValue());
                minNode.setValue(temp);
            }

            current = current.getNext();
        }

        return head;
    }
    
    public static int exc3(Node<Integer> list3, int n) {
        int sum = 0;
        Node<Integer> current = list3;
        int len = 0;

        while (current != null) {
            len++;
            current = current.getNext();
        }

        current = list3;
        boolean found = false;

        while (current != null) {
            if (current.getValue() == n) {
                found = true;
                int start = 0;
                Node<Integer> temp = list3;

                while (temp != current) {
                    start++;
                    temp = temp.getNext();
                }

                int end = len - start - 1; 
                sum += Math.min(start, end); 
            }
            current = current.getNext();
        }

        if (found) {
            return sum;
        } else {
            return -1; 
        }
    }

    public static boolean exc4(Node<Integer> list) {
        Node<Integer> current = list;
    
        while (current != null) {
            Node<Integer> runner = current.getNext();
    
            while (runner != null) {
                if (current.getValue().equals(runner.getValue())) {
                    return false; 
                }
                runner = runner.getNext();
            }
    
            current = current.getNext();
        }
    
        return true; 
    }

    public static Node<Integer> exc5(Node<Integer> list) {
        if (list == null) {
            return null; 
        }

        Node<Integer> dummy = new Node<>(null); 
        Node<Integer> currentNewList = dummy;
        Node<Integer> current = list;
    
        while (current != null) {
            
            Node<Integer> runner = dummy.getNext();
            boolean found = false;
    
            while (runner != null) {
                if (runner.getValue().equals(current.getValue())) {
                    found = true;
                    break;
                }
                runner = runner.getNext();
            }
    
            if (!found) {
                currentNewList.setNext(new Node<>(current.getValue()));
                currentNewList = currentNewList.getNext();
            }
    
            current = current.getNext();
        }
    
        return dummy.getNext(); 
    }
    
    

}


