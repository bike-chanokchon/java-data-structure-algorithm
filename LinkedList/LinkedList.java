public class LinkedList {
    private Node head;
    private Node tail;
    private int length;

    public LinkedList(int value) {
        Node node = new Node(value);
        this.head = node;
        this.tail = node;
        this.length = 1;
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next; // ขยับไปที่ node ถัดไป
        }
    }

    // นำข้อมูลใหม่ไปต่อท้าย
    public void append(int value) {
        Node node = new Node(value);
        if (length == 0) {
            this.head = node;
            this.tail = node;
        } else {
            this.tail.next = node; // ให้ tail ปัจจุบันเก็บค่าของ node ถัดไป
            this.tail = node; // ให้ tail มีค่าเป็น node ใหม่
        }

        this.length++;
    }

    // ลบรายการสุดท้ายออกจาก LinkedList
    public Node removeLast() {
        /*
         *
         * Concept ของการ remove คือ เราจะสร้างตัวแปรชื่อ temp และ pre
         * โดยเราจะให้ temp ลูบนำหน้าไปก่อน โดยจะตรวจสอบว่า next เป็น null หรือไม่
         * ถ้าไม่ใช่ก็ให้ pre = temp และ temp = next (ให้ temp = node ถัดไป)
         * 
         * จากนั้นถ้าเราพบว่า node ถั้ดไปของ temp มีค่าเป็น null ก็จะให้ tail = pre
         * เพราะเรารู็แล้วว่า pre เป็น node ก่อน node สุดท้าย
         * และเราก็จะทำให้ pre.next มีค่าเปฌ็น null
         * 
         */

        if (length == 0)
            return null;

        Node temp = head;
        Node pre = head;

        // loop ไปจนถึง node สุดท้าย โดย node สุดท้ายจะมีค่า next เป็น null
        // เสมอเพราะไม่ได้ชี้ไปที่ใคร
        while (temp.next != null) {
            pre = temp; // ให้ pre มีค่าเป็น node ปัจจุบัน
            temp = temp.next; // ให้ temp มีค่าเป็น node ถัดไป
        }

        // เมื่อหลุดออกมากจาก loop (แสดงว่า Loop จนถึง node สุดท้ายแล้ว) เราก็จะให้ tail
        // = node ก่อนหน้า ซึ่งถูกเก็บไว้ใน pre
        this.tail = pre;
        this.tail.next = null;
        this.length--;

        if (this.length == 0) {
            this.head = null;
            this.tail = null;
        }

        // return node ที่เราได้ลบออกไป
        return temp;
    }

    // นำข้อมูลใหม่มาต่อจากด้านหน้า
    public void prepend(int value) {
        Node node = new Node(value);

        if (length == 0) {
            this.head = node;
            this.tail = node;
        } else {
            node.next = this.head;
            this.head = node;
        }

        this.length++;
    }

    // ลบตัวแรกออกจาก LinkedList
    public Node removeFirst() {
        if (length == 0)
            return null;

        Node temp = this.head;
        this.head = this.head.next; // ให้ head ชี้ไปที่ node ถัดไป
        temp.next = null; // ทำให้ node ที่ถูกลบไม่ชี้ไปที่ node แรกที่เป็น head
        this.length--;

        if (this.length == 0) {
            this.tail = null;
        }

        return temp;
    }

    // ดึงข้อมูลของ node ในตำแหน่งที่ระบุ
    public Node get(int index) {
        if (index < 0 || index > this.length)
            return null;

        Node temp = head;
        for (int i = 0; i < index; i++) { // loop ไปจนถึง index ที่ระบุ
            temp = temp.next; // ให้ temp ทีค่าเป็น node ถัดไป
        }
        return temp;
    }

    // set value ใน index ที่ระบุ
    public boolean set(int index, int value) {
        Node temp = get(index); // ให้ temp ชี้ไปยังตำแหน่งเดียวกันกับ node ที่ได้รับมา

        if (temp != null) {
            // ไม่จำเป็นต้องยัด temp เข้าไปใน linkedList ใหม่ เพราะ class จะเป็นรูปของ pointer อยู่แล้ว
            temp.value = value;
            return true;
        }

        return false;
    }

    // ทำการแทรก node ใหม่เข้าไปในตำแหน่งที่ระบุ
    public boolean insert(int index, int value) {
        if (index < 0 || index > this.length) return false;

        if (index == 0) {
            this.prepend(value);
            return true;
        } else if (index == this.length) {
            this.append(value);
            return true;
        } else {
            Node newNode = new Node(value); 
            Node temp = this.get(index - 1);

            // เราสามารถชี้ไปที่ node ที่เข้ามาแทรกได้เลยโดยที่ไม่ต้องจัดเรียง node ใหม่ เพราะทำงานแบบ pointer
            newNode.next = temp.next; // ให้ node ใหม่ชี้ไปยัง node ถัดไป ซึ่งถูกเก็บไว้ใน temp.next
            temp.next = newNode; // ให้ node temp (node ก่อนหน้า) ชี้มาที่ node ตัวใหม่ที่จะเข้ามาแทรก
            this.length++;

            return true;
        }
    }

    public Node remove(int index) {
        if (index < 0 || index >= this.length) return null;

        if (index == 0) return removeFirst();

        if (index == this.length - 1) return removeLast();

        Node prev = this.get(index - 1); // ดึงข้อมูล node ก่อนหน้ามาเก็บไว้ใน prev
        Node temp = prev.next; // ดึงข้อมูล node ที่เราต้องการลบมาใส่ไว้ใน temp
        prev.next = temp.next; // ให้ next ของ node ก่อนหน้าทีค่าเท่ากับ next ของ node ตัวถัดไปของ node ปัจจุบัน
        temp.next = null; // ลบ node ที่เราต้องการเอาออก ออกจาก LinkedList
        this.length--;

        return temp;
    }

    public void reverse() {
        // swap head และ tail
        Node temp = this.head;
        this.head = this.tail;
        this.tail = temp;

        Node after = temp.next; // ชี้ไปที่ node ถัดไปของ temp
        Node before = null; // ชี้ไปที่ node ก่อนหน้า temp

        for (int i = 0; i < this.length; i++) {
            after = temp.next; // ให้ after มีค่าเท่ากับ node ก่อนหน้า temp
            temp.next = before; // temp ชี้ไปที่ node ก่อนหน้า
            before = temp; // ให้ตัวแปรที่ชี้ไปที่ node ก่อนหน้า ชี้ไปที่ node ปัจจุบันแทน
            temp = after; // ให้ node ปัจจุบันชี้ไปที่ node ถัดไป
        }
    }
}
