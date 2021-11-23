package com.lms.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;

import static javax.swing.Box.createRigidArea;

public class ListView {
    private final String iconDirectory = System.getProperty("user.dir") + File.separator + "icons";
    private final ImageIcon edIco = new ImageIcon(iconDirectory + File.separator + "edit_icon.png");
    private final ImageIcon delIco = new ImageIcon(iconDirectory + File.separator + "delete_icon1.png");
    private final JPanel c1 = new JPanel(new BorderLayout());
    private final JPanel c2 = new JPanel(new BorderLayout());
    private final JPanel c3 = new JPanel(new GridBagLayout());
    private final JPanel c4 = new JPanel(new GridBagLayout());
    private final JPanel c5 = new JPanel(new GridBagLayout());
    private final JPanel c6 = new JPanel(new GridBagLayout());
    private final JPanel container = new JPanel();
    private final JLabel bookName;
    private final JLabel author;
    private final JLabel borrower;
    private final JLabel affiliation;
    private final JLabel dateBorrowed;
    private final JLabel dueDate;
    private final JCheckBox checkBox;

    ListView(String[] attributes) {
        bookName = new JLabel(attributes[0]);
        author = new JLabel(attributes[1]);
        borrower = new JLabel(attributes[3]);
        affiliation = new JLabel(attributes[4]);
        dateBorrowed = new JLabel(attributes[5]);
        dueDate = new JLabel(attributes[6]);
        checkBox = new JCheckBox("Returned");
    }

    public JPanel showInRows() {
        panelFormat(c1);
        labelFormat(bookName, author);
        c1.add(bookName, BorderLayout.CENTER);
        c1.add(author, BorderLayout.SOUTH);

        panelFormat(c2);
        labelFormat(borrower, affiliation);
        c2.add(borrower, BorderLayout.CENTER);
        c2.add(affiliation, BorderLayout.SOUTH);

        panelFormat(c3);
        labelFormat(dateBorrowed);
        c3.add(dateBorrowed, new GridBagConstraints());

        panelFormat(c4);
        labelFormat(dueDate);
        c4.add(dueDate, new GridBagConstraints());

        panelFormat(c5);
        checkBox.setBackground(Color.white);
        c5.add(checkBox, new GridBagConstraints());

        panelFormat(c6);
        JButton edBtn = new JButton();
        JButton delBtn = new JButton();
        edBtn.setBackground(Color.white);
        delBtn.setBackground(Color.white);
        buttonFormat(edBtn, edIco);
        buttonFormat(delBtn, delIco);
        iconResize(edIco, 40);
        c6.add(edBtn, new GridBagConstraints());
        c6.add(createRigidArea(new Dimension(25, 0)));
        c6.add(delBtn, new GridBagConstraints());

        container.setLayout(new GridLayout(1, 6));
        container.setBackground(Color.white);
        container.setBorder(new EmptyBorder(0, 10, 0, 0));
        container.setPreferredSize(new Dimension(700, 60));
        container.setMaximumSize(new Dimension(2000, 70));
        container.add(c1);
        container.add(c2);
        container.add(c3);
        container.add(c4);
        container.add(c5);
        container.add(c6);
        return container;
    }

    private static void labelFormat(JLabel label1, JLabel label2) {
        label1.setForeground(Color.black);
        label1.setFont(new Font("Inter", Font.BOLD, 18));
        label2.setForeground(Color.black);
        label2.setFont(new Font("Inter", Font.PLAIN, 12));
    }

    private static void labelFormat(Component label) {
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Inter", Font.BOLD, 18));
    }

    private static void panelFormat(JPanel panel) {
        panel.setBorder(new EmptyBorder(10, 20, 10, 20));
        panel.setBackground(Color.white);
    }

    private static void buttonFormat(JButton button, ImageIcon icon) {
        button.setIcon(iconResize(icon, 34));
        button.setPreferredSize(new Dimension(34, 34));
        button.setBorder(new EmptyBorder(0, 0, 0, 0));
    }

    private static ImageIcon iconResize(ImageIcon icon, int size) {
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }

}
