package leetCode;

import leetCode.FindMiddleNode.Node;

public class Main {
    public static void main(String[] args) {
        FindMiddleNode fm = new FindMiddleNode(1);
        fm.append(2);
        fm.append(3);
        fm.append(4);
        fm.append(5);
        fm.append(6);
        fm.append(7);
        fm.append(8);
        fm.append(9);
        fm.append(10);
        fm.append(11);
        fm.append(12);


        fm.printAll();

        System.out.println("------");

        Node m = fm.findMiddleNode();
        System.out.println(m.value);
    }
}
