package com.lms.gui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.File;

public class MainWindow {
    private final JFrame mainWindow = new JFrame("Library Borrowing System | By: Matthew Cabarle");
    private final JButton addNewButton = new JButton("Add New Entry");
    private final JButton sortButton = new JButton("Sort");
    private final JPanel topCont = new JPanel();
    private final JPanel titleCont = new JPanel(new BorderLayout());
    private final JPanel middleCont = new JPanel();
    private final JPanel topInsideMiddleCont = new JPanel(new BorderLayout());
    private final JPanel botInsideMiddleCont = new JPanel(new GridBagLayout());
    private final JPanel contLabel0 = colName("Book");
    private final JPanel contLabel1 = colName("Borrower");
    private final JPanel contLabel2 = colName("Date Borrowed");
    private final JPanel contLabel3 = colName("Due Date");
    private final JPanel contLabel4 = colName("Status");
    private final JPanel contLabel5 = colName("Actions");
    private final JLabel title = new JLabel("Library Borrowing System");

    MainWindow() {
        var addNew = new AddEntryDialogGUI();
        addNewButton.addActionListener(e -> {
            addNew.focusable();
            if (!addNew.isRunning()) {
                Thread newThread = new Thread(addNew::showUI);
                newThread.start();
            }
        });
    }

    public static void main(String[] args) {
        var main = new MainWindow();
        main.mainWindow();
    }

    public void mainWindow() {
        topCont.setLayout(new BoxLayout(topCont, BoxLayout.Y_AXIS));
//        container for the title
        labelFormat(title, Color.white, 45);
        titleCont.setBorder(new EmptyBorder(20, 20, 20, 20));
        titleCont.setBackground(new Color(43, 48, 58));
        titleCont.add(title, BorderLayout.LINE_START);
        topCont.add(titleCont);

//        Container for the add and sort buttons
        middleCont.setLayout(new BoxLayout(middleCont, BoxLayout.Y_AXIS));
        addNewButton.setBackground(Color.decode("#58A4B0"));
        addNewButton.setForeground(Color.white);
        addNewButton.setFont(new Font("Inter", Font.BOLD, 18));

        topInsideMiddleCont.setBorder(new EmptyBorder(10, 15, 10, 15));
        topInsideMiddleCont.add(addNewButton, BorderLayout.LINE_START);
        topInsideMiddleCont.add(sortButton, BorderLayout.LINE_END);
        middleCont.add(topInsideMiddleCont);

        botInsideMiddleCont.setLayout(new GridLayout(0, 6));
        botInsideMiddleCont.setBorder(new EmptyBorder(0, 11, 0, 8));
        botInsideMiddleCont.setBackground(Color.WHITE);
        botInsideMiddleCont.add(contLabel0);
        botInsideMiddleCont.add(contLabel1);
        botInsideMiddleCont.add(contLabel2);
        botInsideMiddleCont.add(contLabel3);
        botInsideMiddleCont.add(contLabel4);
        botInsideMiddleCont.add(contLabel5);
        middleCont.add(botInsideMiddleCont);

        topCont.add(middleCont);

        var traverse = new TraverseFolder(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Library Borrowing System");

        JPanel contRows = new JPanel();
        contRows.setLayout(new BoxLayout(contRows, BoxLayout.Y_AXIS));

        for (File file : traverse.getFiles()) {
            var read = new ReadFile(file);
            String[] contents = {
                    read.readLine(),
                    read.readLine(),
                    read.readLine(),
                    read.readLine(),
                    read.readLine(),
                    read.readLine(),
                    read.readLine(),
            };
            var listView = new ListView(contents);
            contRows.add(listView.showInRows());
            contRows.add(new JToolBar.Separator(new Dimension(0, 3)));
        }

        UIManager.put("ScrollBar.width", ((int) UIManager.get("ScrollBar.width") - 10));
        JScrollPane scrollPane = new JScrollPane(contRows);
        customScrollPane(scrollPane);

        mainWindow.setLayout(new BorderLayout());
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.add(topCont, BorderLayout.NORTH);
        mainWindow.add(scrollPane, BorderLayout.CENTER);
        mainWindow.setMinimumSize(new Dimension(1000, 500));
        mainWindow.setSize(900, 500);
        mainWindow.setVisible(true);
        mainWindow.setLocationRelativeTo(null);
    }

    private static void labelFormat(Component label, Color fontColor, int fontSize) {
        label.setForeground(fontColor);
        label.setFont(new Font("Inter", Font.BOLD, fontSize));
    }

    private static void panelFormat(JPanel panel) {
        panel.setBorder(new EmptyBorder(10, 20, 10, 20));
        panel.setBackground(Color.white);
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
}
