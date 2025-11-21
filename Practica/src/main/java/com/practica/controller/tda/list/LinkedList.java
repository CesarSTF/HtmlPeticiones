package com.practica.controller.tda.list;

import java.util.List;

import com.practica.controller.excepcion.ListEmptyException;

public class LinkedList <E> {
    private Node<E> head;
    private Node<E> last;
    private Integer size;

    public LinkedList(){
        this.head = null;
        this.last = null;
        this.size = 0;
    }

    public Node<E> getHead() {
        return this.head;
    }

    public void setHead(Node<E> head) {
        this.head = head;
    }

    public Node<E> getLast() {
        return this.last;
    }

    public void setLast(Node<E> last) {
        this.last = last;
    }

    public Integer getSize() {
        return this.size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public boolean isEmpty(){
        return head == null;
    }

    public void addHeader(E data) {
        Node<E> aux = new Node<>(data);
        if (isEmpty()) {
            head = aux;
            last = head;
        } else {
            aux.setNext(head);
            head = aux;
        }
        size++;
    }

    private void addLast(E data) {
        Node<E> aux = new Node<>(data);  
        if (isEmpty()) {
            head = aux;  
            last = head; 
        } else {
            last.setNext(aux); 
            last = aux;
        }
        size++; 
    }
    public void add(E data) {
        addLast(data);
    }

    public void add(E data, Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Indice fuera de rango");
        }
        System.out.println("Agregando en índice: " + index + ", data: " + data);
        
        if (index == 0) {
            addHeader(data);
        } else if (index.intValue() == size) {
            addLast(data);
        } else {
            Node<E> search = getNode(index - 1);
            System.out.println("Nodo anterior encontrado: " + search.getData());
            
            Node<E> aux = new Node<>(data);
            aux.setNext(search.getNext());
            search.setNext(aux);
            size++;
        }
    }

    private Node<E> getNode(Integer index)throws ListEmptyException, IndexOutOfBoundsException{
        if (isEmpty()) {
            throw new ListEmptyException("La lista esta vacia");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Indice fuera de rango");
        }
        if (index == size - 1) {
            return last;
        }
        Node<E> search = head;
        Integer count = 0;
        while (count < index) {
            search = search.getNext();
            count++;
        }
        return search;
    }
    public E get(Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        return getNode(index).getData();
    }

    public void set(Integer index, E data) throws ListEmptyException, IndexOutOfBoundsException {
            getNode(index).setData(data);
    }

    public void reset() {
        head = null;
        last = null;
        size = 0;
    }

    public void delete(Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Indice fuera de rango");
        }
        if (isEmpty()) {
            throw new ListEmptyException("La lista está vacía");
        }
        
        if (index == 0) { 
            head = head.getNext();
            if (head == null) { 
                last = null;
            }
        } else {
            Node<E> prevNode = getNode(index - 1);
            Node<E> delNode = prevNode.getNext();
            prevNode.setNext(delNode.getNext());
            if (delNode == last) { 
                last = prevNode;
            }
        }
        size--; 
    }
    
    
    
    public E deleteHeader() throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("La lista esta vacia");
        }else{
            E element = head.getData();
            Node<E> aux = head.getNext();
            head = aux;
            if(size == 1){
            last = null;
            }
            size--;
            return element;
        }
    }
    public E deleteLast() throws ListEmptyException {
        if(isEmpty()){
            throw new ListEmptyException("La lista esta vacia");
        }else{
            E element =last.getData();
            Node<E> aux = getNode(size - 2);
            if(aux == null){
                head = null;
            last = null;
                if(size == 2 ){
                last = head;
                }else{
                    head = null;
                }
            }else{
            last = null;
            last = aux;
            last.setNext(null);
            }
            size--;
            return element;
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> search = head;
        while (search != null) {
            sb.append(search.getData());
            sb.append("\n");
            search = search.getNext();
        }
        return sb.toString();
    }
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node<E> current = head;
        int index = 0;
    
        while (current != null) {
            array[index++] = current.getData();
            current = current.getNext();
        }
    
        return array;
    }
    
    public boolean updateByValue(E oldData, E newData) throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("La lista está vacía");
        }
        Node<E> current = head;
        while (current != null) {
            if (current.getData().equals(oldData)) {
                current.setData(newData);
                return true; 
            }
            current = current.getNext();
        }
        return false; 
    }

    public static<E> LinkedList<E> toList(E[] matrix) {
        LinkedList<E> list = new LinkedList<>();
        for (E data : matrix) {
            list.add(data);
        }
        return list;
    }   

    public E[] toTypedArray(E[] a) {
        if (a.length < size) {
            a = (E[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        }
        Node<E> current = head;
        int index = 0;
        while (current != null) {
            a[index++] = current.getData();
            current = current.getNext();
        }
        return a;
    }
    
}
