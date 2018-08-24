package com.ebook.trian.kripto.Model;

import java.util.ArrayList;

public class Vigenere_Scan {
    private int dataPesanInt;
    private int dataKunciInt;
    private ArrayList listPesan = new ArrayList();
    private ArrayList listKunci = new ArrayList();

    public ArrayList scannPesan(String pesan, String dataHuruf) {
        for (int i = 0; i < pesan.length(); i++) {
            for (int j = 0; j < dataHuruf.length(); j++) {

                char p = pesan.charAt(i);
                char dh = dataHuruf.charAt(j);

                if (p == dh) {
                    dataPesanInt = dataHuruf.indexOf(dh);
                    listPesan.add(dataPesanInt);
                }
            }
        }

        return listPesan;
    }
    public ArrayList scannKuci(String pesan, String kunci, String dataHuruf) {

        int jmlKunci = 0;
        while (kunci.length() < pesan.length()) {
            kunci += kunci.charAt(jmlKunci);
            jmlKunci++;
        }

        for (int i = 0; i < kunci.length(); i++) {
            for (int j = 0; j < dataHuruf.length(); j++) {
                char k = kunci.charAt(i);
                char dh = dataHuruf.charAt(j);

                if (k == dh) {
                    dataKunciInt = dataHuruf.indexOf(dh);
                    listKunci.add(dataKunciInt);
                }
            }
        }
        return listKunci;
    }
}
