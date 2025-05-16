package bughunters.Grafika;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class GombaInfo extends JFrame {
    private JTable adatok;

    public GombaInfo() {
        setTitle("Gombafajok tulajdonságai");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);

        String[] oszlopNevek = {
            "Kép", 
            "Fajnév", 
            "Hatás", 
            "Tapanyag", 
            "Spóra termelés", 
            "Spóra mennyiség", 
            "Fejlettség", 
            "Elhalás", 
            "Test létrehozás"
        };

        Object[][] sorAdatok = {
            {"images/legy616_galoca.png", "Legy616 galoca", "bénítő", "x", "x kir", "6", "x szórást követően", "x szórást követően", "x sporabél"},
            {"images/varganya.png", "Varganya gomba", "lassító", "x", "x kor", "x", "x szórást követően", "x szórást követően", "x spérabél"},
            {"images/csiperke.png", "Csiperke gomba", "gyorsító", "x", "x", "16", "x szórást követően", "x szórást követően", "x spérabél"},
            {"images/szegfu.png", "Szegfűgomba", "nem vág fonalat", "x", "x kér", "x", "x szórást követően", "x szórást követően", "x spérabél"},
            {"images/piiffeteg.png", "Foltos piiffeteg", "osztódó", "x", "x kor", "16", "x szórást követően", "x szórást követően", "x spérabél"}
        };

        // Egyedi TableModel a képek kezeléséhez
        DefaultTableModel model = new DefaultTableModel(sorAdatok, oszlopNevek) {
            @Override
            public Class<?> getColumnClass(int column) {
                return column == 0 ? ImageIcon.class : Object.class;
            }
        };

        adatok = new JTable(model) {
            // Egyedi renderer a képek megjelenítéséhez
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                if (column == 0) {
                    ImageIcon icon = (ImageIcon) getValueAt(row, column);
                    JLabel label = new JLabel();
                    if (icon != null) {
                        label.setIcon(new ImageIcon(icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
                    }
                    return label;
                }
                return super.prepareRenderer(renderer, row, column);
            }
        };

        // Táblázat beállításai
        adatok.setRowHeight(80); // Képek méretéhez igazítva
        adatok.getColumnModel().getColumn(0).setPreferredWidth(100); // Kép oszlop szélessége
        
        JScrollPane scrollPane = new JScrollPane(adatok);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }
}
