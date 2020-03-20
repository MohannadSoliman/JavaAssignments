package eg.edu.alexu.csd.datastructure.linkedList;

public class SinglyLinkedList implements ILinkedList{
    private class SNode {
        Object data;
        SNode next;
        SNode(Object data){
            this.data = data;
            this.next = null;
        }
    }

    private SNode head;
    private SNode tail;
    private int listSize = 0;

    public void add(int index, Object element){
        if(index < 0) return;
        int pos = 0;
        SNode currentNode = head;
        SNode newNode = new SNode(element);
        if(index == 0){
            newNode.next = head;
            head = newNode;
        }else{
            while(currentNode.next != null && pos++ < index-1) currentNode = currentNode.next;
            if(currentNode.next == null) return;
            newNode.next = currentNode.next;
            currentNode.next = newNode;
        }
        listSize ++;
    }

    public void add(Object element) {
        SNode newNode = new SNode(element);
        if(head == null) head = newNode;
        else tail.next = newNode;
        tail = newNode;
        listSize ++;
    }

    public Object get(int index){
        if(index < 0) return null;
        int pos = 0;
        SNode currentNode = head;
        while(currentNode != null && pos++ < index) currentNode = currentNode.next;
        if(currentNode == null) return null;
        return currentNode.data;
    }

    public void set(int index, Object element){
        if(index < 0) return;
        int pos = 0;
        SNode currentNode = head;
        while(currentNode != null && pos++ < index) currentNode = currentNode.next;
        if(currentNode == null) return;
        currentNode.data = element;
    }

    public void clear() {
        this.head = this.tail = null;
        listSize = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void remove(int index){
        if(index < 0) return;
        int pos = 0;
        SNode currentNode = head;
        if(head == null) return; //empty list
        if(index == 0) head = head.next;
        else{
            while(currentNode != null && pos++ < index-1) currentNode = currentNode.next;
            if(currentNode == null) return;
            currentNode.next = currentNode.next.next;
        }
        listSize --;
    }

    public int size() {
        return listSize;
    }

    public ILinkedList sublist(int fromIndex, int toIndex) {
        if(fromIndex < 0) return null;
        if(toIndex > size()-1) return null;
        if(fromIndex > toIndex) return null;
        SinglyLinkedList sublist = new SinglyLinkedList();
        SNode current = head;
        int index = 0;
        while(current != null && index ++ != fromIndex) current = current.next;
        sublist.add(current.data);
        //current = current.next;
        while(current != null && index ++ <= toIndex){
            current = current.next;
            sublist.add(current.data);
        }
        return sublist;
    }

    public boolean contains(Object o) {
        SNode currentNode = head;
        while(currentNode != null && currentNode.data != o) currentNode = currentNode.next;
        if(currentNode == null) return false;
        return true;
    }

    public void printList(){
        SNode currentNode = head;
        while (currentNode != null) {
            System.out.print(currentNode.data + " ");
            currentNode = currentNode.next;
        }
    }
}