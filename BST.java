package KatalogBoyo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Katalog
 */
public class BST {

    Data root;

    public void insert(Data input) {
        if (isEmpty()) {
            root = input;
        } else {
            Data current = root;
            Data parent = null;
            boolean diKiri = true;
            while (current != null) {
                parent = current;
                if (current.id.compareToIgnoreCase(input.id) < 0) {
                    current = current.kanan;
                    diKiri = false;
                } else {
                    current = current.kiri;
                    diKiri = true;
                }
            }
            if (diKiri) {
                parent.kiri = input;
            } else {
                parent.kanan = input;
            }
        }
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public Data search(String key) {
        Data node = null;
        Data current = root;
        while (current != null) {
            if (current.id.equalsIgnoreCase(key)) {
                node = current;
                return node;
            } else {
                if (current.id.compareTo(key) < 0) {
                    current = current.kanan;
                } else {
                    current = current.kiri;
                }
            }
        }
        return node;
    }

}
