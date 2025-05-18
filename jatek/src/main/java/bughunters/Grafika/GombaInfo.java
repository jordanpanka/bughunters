package bughunters.Grafika;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

public class GombaInfo extends JFrame {
    private JTable adatok;

    public GombaInfo() {
        setTitle("Gombafajok tulajdonságai");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Középre igazítás
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        centerRenderer.setVerticalAlignment(SwingConstants.CENTER);

        String[] oszlopNevek = {
            "Fajnév", 
            "Szín",
            "Hatás", 
            "Tapanyag", 
            "Spóra termelés", 
            "Spóra mennyiség", 
            "Fejlettség", 
            "Elhalás", 
            "Test létrehozás"
        };

        Object[][] sorAdatok = {
            {"Legyölő galóca", "piros", "bénító", "30", "4 kör", "3", "2 szórást követően", "3 szórást követően", "3 spórából"},
            {"Vargánya gomba", "kék", "lassító", "15", "2 kör", "2", "3 szórást követően", "6 szórást követően", "3 spórából"},
            {"Csiperke gomba", "citromsárga", "gyorsító", "10", "2 kör", "2", "3 szórást követően", "6 szórást követően", "3 spórából"},
            {"Szegfűgomba", "lila", "nem vág fonalat", "20", "3 kör", "2", "2 szórást követően", "4 szórást követően", "3 spórából"},
            {"Foltos püffeteg", "rózsaszín", "osztódó", "10", "4 kör", "2", "2 szórást követően", "3 szórást követően", "3 spórából"}
        };

        DefaultTableModel model = new DefaultTableModel(sorAdatok, oszlopNevek);

        adatok = new JTable(model) {
            // Fejléc középre igazítása
            @Override
            protected JTableHeader createDefaultTableHeader() {
                JTableHeader header = super.createDefaultTableHeader();
                header.setDefaultRenderer(new CenterHeaderRenderer());
                return header;
            }
        };

        // Minden oszlophoz középre igazítás beállítása
        for(int i = 0; i < adatok.getColumnCount(); i++) {
            adatok.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        adatok.setRowHeight(50);
        adatok.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        adatok.getColumnModel().getColumn(0).setPreferredWidth(150);
        adatok.getColumnModel().getColumn(1).setPreferredWidth(100);
        adatok.getColumnModel().getColumn(2).setPreferredWidth(100);
        adatok.getColumnModel().getColumn(3).setPreferredWidth(100);
        adatok.getColumnModel().getColumn(4).setPreferredWidth(100);
        adatok.getColumnModel().getColumn(5).setPreferredWidth(100);
        adatok.getColumnModel().getColumn(6).setPreferredWidth(150);
        adatok.getColumnModel().getColumn(7).setPreferredWidth(150);
        adatok.getColumnModel().getColumn(8).setPreferredWidth(100);

        JScrollPane scrollPane = new JScrollPane(adatok);

        scrollPane.setPreferredSize(new Dimension(
            adatok.getPreferredScrollableViewportSize().width + 115,
            adatok.getRowHeight() * (adatok.getRowCount() + 1)
        ));

        add(scrollPane);
        pack();
        setLocationRelativeTo(null);

        setMinimumSize(new Dimension(
            scrollPane.getPreferredSize().width + 500,
            scrollPane.getPreferredSize().height + 50
        ));
    
        setVisible(true);
    }

     // Fejléc középre igazító osztály
    private static class CenterHeaderRenderer extends DefaultTableCellRenderer {
        public CenterHeaderRenderer() {
            setHorizontalAlignment(SwingConstants.CENTER);
            setVerticalAlignment(SwingConstants.CENTER);
        }
    }
}