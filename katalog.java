package KatalogBoyo;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

class Data {

    String id;
    String judul;
    String penulis;
    String property;
    Data kanan;
    Data kiri;

}

public class katalog {

    static katalog k = new katalog();
    static Random r = new Random();
    static ArrayList<Data> al1 = new ArrayList<Data>();
    static ArrayList<Data> al2 = new ArrayList<Data>();
    static BST bst = new BST();

    public static void main(String[] args) {
        Scanner masukkan = new Scanner(System.in);
        File file = new File("C:\\Users\\Andika\\Documents\\NetBeansProjects\\Tubes\\src\\KatalogBoyo\\GUTINDEX.ALL");
        //baca file GUTINDEX dengan memasukkan pathnya
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            String strings = "";
            String id = "";
            String judul;
            String penulis = "";
            String property = "";
			//set data awal

            while ((text = reader.readLine()) != null) { // baca text setiap baris
                if (text.length() != 0) { // jika panjang data text tidak sama dengan nol.
                    String[] splitted = text.split("[ ]{3,}"); // split data menjadi dua dengan jarak 3 spasi atau lebih
                    strings = strings + splitted[0]; // variable string berada pada bagian pertama
                    if (splitted.length > 1) {
                        id = splitted[1]; // variable id berada pada bagian kedua
                    }
                } else {
                    String[] splitted = strings.split(", by "); // split variable strings menjadi 2 yang dipisahkan oleh kata "by"

                    judul = splitted[0];// judul berada pada bagian pertama sebelum "by"
                    if (splitted.length > 1) { // jika data yang telah di split lebih dari 1 baris
                        int index = splitted[1].indexOf("["); //maka data yang diawali dengan "[" merupakan property
                        if (index >= 0) {
                            penulis = splitted[1].substring(0, index); //bagian pertama merupakan penulis
                            property = splitted[1].substring(index);//bagian kedua merupakan property
                        } else {
                            penulis = splitted[1];

                        }

                        Data tmp = new Data(); // buat sebuah objek tmp dari class Data
                        tmp.judul = judul.trim();
                        tmp.penulis = penulis.trim();
                        tmp.property = property.trim();
                        tmp.id = id;
                        // masukkan judul, penulis, property, dan id ke dalam tmp
                        al1.add(tmp);// memasukkan data tmp kedalam DLLD
                        al2.add(tmp);

                    }
                    strings = "";
                    
                }
            }
            while (!al1.isEmpty()) {
                int r = new Random().nextInt(al1.size());
                bst.insert(al1.remove(r));
            }
            menu();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void menu() {
        Scanner masukkan = new Scanner(System.in);
        System.out.println("Cari data berdasarkan : ");
        System.out.println("1. Judul");
        System.out.println("2. Penulis");
        System.out.println("3. Keluar");
                System.out.print("Pilihan : ");
        int pilihan = masukkan.nextInt();
        masukkan.nextLine();
        Data tmp;
        String cari;
        if (pilihan == 1) {
            System.out.print("masukkan judul :");
            cari = masukkan.nextLine();
            k.searchTitle(cari);
            menu();
        } else if (pilihan == 2) {
            System.out.print("masukkan penulis :");
            cari = masukkan.nextLine();
            k.searchAuthor(cari);
            menu();
        }else if(pilihan==3){
            System.exit(0);
        } else {
            System.out.println("maaf pilihan tersebut tidak ada");
        }
    }

    public void searchAuthor(String cari) {
        Data tmp;
        Boolean isFound = false;
        for (int i = 0; i < al2.size(); i++) {
            if (al2.get(i).penulis.equalsIgnoreCase(cari)) {
                tmp = bst.search(al2.get(i).id);
                displayData(tmp);
                isFound = true;
            }
        }
        if (!isFound) {
            System.out.println("");
            System.out.println("### Data Not Found !");
            System.out.println("");
        }

    }

    public void searchTitle(String cari) {
        Data tmp;
        Boolean isFound = false;
        for (int i = 0; i < al2.size(); i++) {
            if (al2.get(i).judul.toLowerCase().contains(cari.toLowerCase())) {
                tmp = bst.search(al2.get(i).id);
                displayData(tmp);
                isFound = true;
            }

        }
        if (!isFound) {
            System.out.println("");
            System.out.println("### Data Not Found !");
            System.out.println("");
        }
    }

    public void displayData(Data tmp) {
        if (tmp != null) {
            System.out.println("");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("Pengarang : " + tmp.penulis);
            System.out.println("Judul : " + tmp.judul);
            System.out.println("Id Buku : " + tmp.id);
            System.out.println("Properti :");
            if (tmp.property.equals("")) {
                System.out.println("Tidak Ada");
            } else {
                System.out.println(tmp.property);
            }
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("");
        }
    }

}
