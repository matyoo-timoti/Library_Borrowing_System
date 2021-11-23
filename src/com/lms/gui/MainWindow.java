package com.lms.gui;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;

import static javax.swing.Box.createRigidArea;

public class MainWindow {

    MainWindow() {
        var addNew = new AddEntryDialogGUI();
        BTN_ADD_NEW.addActionListener(e -> {
            if (!addNew.isRunning()) {
                Thread newThread = new Thread(addNew::showUI);
                newThread.start();
            }
        });
    }

    private static final JFrame WINDOW = new JFrame("Library Borrowing System | By: Matthew Cabarle");
    private static final String ICON_PATH = System.getProperty("user.dir") + File.separator + "icons";
    private static final JButton BTN_ADD_NEW = new JButton("Add New Entry");
    private static final JButton BTN_SORT = new JButton("Sort");

    public static void main(String[] args) {
        var main = new MainWindow();
        main.mainWindow();
    }

    public void mainWindow() {
        JPanel topCont = new JPanel();
        topCont.setLayout(new BoxLayout(topCont, BoxLayout.Y_AXIS));

        JPanel titleCont = new JPanel(new BorderLayout());
        titleCont.setBorder(new EmptyBorder(20, 20, 20, 20));
        titleCont.setBackground(new Color(43, 48, 58));

        JLabel title = new JLabel("Library Borrowing System");
        labelFormat(title, Color.white, 45);
        titleCont.add(title, BorderLayout.LINE_START);
        topCont.add(titleCont);

        JPanel middleCont = new JPanel();
        middleCont.setLayout(new BoxLayout(middleCont, BoxLayout.Y_AXIS));

        BTN_ADD_NEW.setBackground(Color.decode("#58A4B0"));
        BTN_ADD_NEW.setForeground(Color.white);
        BTN_ADD_NEW.setFont(new Font("Inter", Font.BOLD, 18));

        JPanel insideMiddleTop = new JPanel(new BorderLayout());
        insideMiddleTop.setBorder(new EmptyBorder(10, 15, 10, 15));
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
        JPanel contLabel4 = colName("Status");
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

        WINDOW.setLayout(new BorderLayout());
        WINDOW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WINDOW.add(topCont, BorderLayout.NORTH);
        WINDOW.add(scrollPane, BorderLayout.CENTER);
        WINDOW.setMinimumSize(new Dimension(1000, 500));
        WINDOW.setSize(900, 500);
        WINDOW.setVisible(true);
        WINDOW.setLocationRelativeTo(null);
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
