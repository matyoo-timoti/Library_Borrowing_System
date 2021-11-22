package com.lms.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;

import static javax.swing.Box.createRigidArea;

public class MainWindow {
    private static final JFrame WINDOW = new JFrame("PANEL TRIAL");
    private static final String ICON_PATH = System.getProperty("user.dir") + File.separator + "icons";
    private static final JButton BTN_ADD_NEW = new JButton("Add New Entry");
    private static final JButton BTN_SORT = new JButton("Sort");

    public static void main(String[] args) {
        var main = new MainWindow();
        main.mainWindow();
        BTN_ADD_NEW.addActionListener(e -> {
            Thread newThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    var addNew = new AddNewEntry();
                    if (!addNew.isRunning())
                        addNew.newEntryWindow();
                }
            });
            newThread.start();
        });
    }

    public void mainWindow() {
        var handler = new FileHandling();
        String[] entryContents = handler.retrieve("Violet Evergarden_Matthew Cabarle_11-19-2021_12-01-2021");
        String[] entryContents1 = handler.retrieve("MC-11-12-2021-11-15-2021");
        String[] entryContents2 = handler.retrieve("MM-11-15-2010-12-12-2011");

        JPanel topCont = new JPanel();
        topCont.setLayout(new BoxLayout(topCont, BoxLayout.Y_AXIS));

        JPanel titleCont = new JPanel(new BorderLayout());
        titleCont.setBorder(new EmptyBorder(20, 15, 20, 10));
        titleCont.setBackground(new Color(43, 48, 58));

        JLabel title = new JLabel("Library Borrowing System");
        labelFormat(title, Color.white, 45);
        titleCont.add(title, BorderLayout.LINE_START);
        topCont.add(titleCont);

        JPanel middleCont = new JPanel();
        middleCont.setLayout(new BoxLayout(middleCont, BoxLayout.Y_AXIS));

        JPanel insideMiddleTop = new JPanel(new BorderLayout());
        insideMiddleTop.setBorder(new EmptyBorder(10, 10, 10, 10));
        insideMiddleTop.add(BTN_ADD_NEW, BorderLayout.LINE_START);
        insideMiddleTop.add(BTN_SORT, BorderLayout.LINE_END);

        JPanel insideMiddleBot = new JPanel(new GridBagLayout());
        insideMiddleBot.setLayout(new GridLayout(0, 6));
        insideMiddleBot.setBorder(new EmptyBorder(0, 11, 0, 8));
        insideMiddleBot.setBackground(Color.WHITE);

        JPanel contLabel0 = colName("Book");
        JPanel contLabel1 = colName("Borrower");
        JPanel contLabel2 = colName("Date Borrowed");
        JPanel contLabel3 = colName("Due Date");
        JPanel contLabel4 = colName("Returned");
        JPanel contLabel5 = colName("Actions");
        insideMiddleBot.add(contLabel0);
        insideMiddleBot.add(contLabel1);
        insideMiddleBot.add(contLabel2);
        insideMiddleBot.add(contLabel3);
        insideMiddleBot.add(contLabel4);
        insideMiddleBot.add(contLabel5);
        middleCont.add(insideMiddleTop);
        middleCont.add(insideMiddleBot);
        topCont.add(middleCont);

        JPanel contRows = new JPanel();
        contRows.setLayout(new BoxLayout(contRows, BoxLayout.Y_AXIS));
        for (int i = 0; i < 30; i++) {
            contRows.add(showInRows(entryContents));
            contRows.add(new JToolBar.Separator(new Dimension(0, 3)));
            contRows.add(showInRows(entryContents1));
            contRows.add(new JToolBar.Separator(new Dimension(0, 3)));
            contRows.add(showInRows(entryContents2));
            contRows.add(new JToolBar.Separator(new Dimension(0, 3)));
        }

        UIManager.put("ScrollBar.width", ((int) UIManager.get("ScrollBar.width") - 10));
        JScrollPane scrollPane = new JScrollPane(contRows);
        customScrollPane(scrollPane);

        WINDOW.setLayout(new BorderLayout());
        WINDOW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WINDOW.add(topCont, BorderLayout.NORTH);
        WINDOW.add(scrollPane, BorderLayout.CENTER);
        WINDOW.setMinimumSize(new Dimension(1000, 500));
        WINDOW.setSize(900, 500);
        WINDOW.setVisible(true);
        WINDOW.setLocationRelativeTo(null);
    }

    private static JPanel showInRows(String[] attrib) {
        //Book Name and Author
        JPanel c1 = new JPanel(new BorderLayout());
        panelFormat(c1);
        JLabel bookName = new JLabel(attrib[0]);
        JLabel author = new JLabel(attrib[1]);
        labelFormat(bookName, author);
        c1.add(bookName, BorderLayout.CENTER);
        c1.add(author, BorderLayout.SOUTH);

        JPanel c2 = new JPanel(new BorderLayout());
        panelFormat(c2);
        JLabel borrower = new JLabel(attrib[3]);
        JLabel affiliation = new JLabel(attrib[4]);
        labelFormat(borrower, affiliation);
        c2.add(borrower, BorderLayout.CENTER);
        c2.add(affiliation, BorderLayout.SOUTH);

        JPanel c3 = new JPanel(new GridBagLayout());
        panelFormat(c3);
        JLabel dateBorrowed = new JLabel(attrib[5]);
        labelFormat(dateBorrowed, Color.BLACK, 18);
        c3.add(dateBorrowed, new GridBagConstraints());

        JPanel c4 = new JPanel(new GridBagLayout());
        panelFormat(c4);
        JLabel dueDate = new JLabel(attrib[6]);
        labelFormat(dueDate, Color.BLACK, 18);
        c4.add(dueDate, new GridBagConstraints());

        JPanel c5 = new JPanel(new GridBagLayout());
        panelFormat(c5);
        JCheckBox checkBox = new JCheckBox("Returned");
        checkBox.setBackground(Color.white);
        c5.add(checkBox, new GridBagConstraints());

        JPanel c6 = new JPanel(new GridBagLayout());
        panelFormat(c6);
        JButton edBtn = new JButton();
        ImageIcon edIco = new ImageIcon(ICON_PATH + File.separator + "edit_icon.png");
        iconResize(edIco, 40);
        buttonFormat(edBtn, 34, edIco);
        edBtn.setBackground(Color.white);
        JButton delBtn = new JButton();
        delBtn.setBackground(Color.white);
        ImageIcon delIco = new ImageIcon(ICON_PATH + File.separator + "delete_icon1.png");
        buttonFormat(delBtn, 34, delIco);
        c6.add(edBtn, new GridBagConstraints());
        c6.add(createRigidArea(new Dimension(25, 0)));
        c6.add(delBtn, new GridBagConstraints());

        JPanel container = new JPanel();
        container.setBackground(Color.white);
        container.setBorder(new EmptyBorder(0, 10, 0, 0));
        container.setLayout(new GridLayout(1, 6));
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

    private static void labelFormat(Component label, Color fontColor, int fontSize) {
        label.setForeground(fontColor);
        label.setFont(new Font("Inter", Font.BOLD, fontSize));
    }

    private static void panelFormat(JPanel panel) {
        panel.setBorder(new EmptyBorder(10, 20, 10, 20));
        panel.setBackground(Color.white);
    }

    private static void buttonFormat(JButton button, int size, ImageIcon icon) {
        button.setIcon(iconResize(icon, 34));
        button.setPreferredSize(new Dimension(size, size));
        button.setBorder(new EmptyBorder(0, 0, 0, 0));
    }

    private static void customScrollPane(JScrollPane scroll_pane) {
        scroll_pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll_pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll_pane.getVerticalScrollBar().setUnitIncrement(10);//10 is default
        scroll_pane.getHorizontalScrollBar().setUnitIncrement(10);//10 is default
        scroll_pane.getVerticalScrollBar().setBackground(new Color(240, 240, 240));
        scroll_pane.getHorizontalScrollBar().setBackground(new Color(240, 240, 240));
        scroll_pane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton jbutton = new JButton();
                jbutton.setPreferredSize(new Dimension(0, 0));
                jbutton.setMinimumSize(new Dimension(0, 0));
                jbutton.setMaximumSize(new Dimension(0, 0));
                return jbutton;
            }

            protected void configureScrollBarColors() {
                this.thumbColor = new Color(225, 225, 225);
            }
        });
        scroll_pane.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton jbutton = new JButton();
                jbutton.setPreferredSize(new Dimension(0, 0));
                jbutton.setMinimumSize(new Dimension(0, 0));
                jbutton.setMaximumSize(new Dimension(0, 0));
                return jbutton;
            }

            protected void configureScrollBarColors() {
                this.thumbColor = new Color(225, 225, 225);
            }
        });

    }

    private static JPanel colName(String text) {
        JPanel cont = new JPanel();
        panelFormat(cont);
        JLabel label = new JLabel(text);
        labelFormat(label, Color.BLACK, 18);
        cont.add(label);
        return cont;
    }

    private static ImageIcon iconResize(ImageIcon icon, int size) {
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }
}
