package com.lms.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class MainWindow {
    private final JFrame mainWindow = new JFrame("Library Borrowing System | By: Matthew Cabarle");
    private final JButton addNewButton = new JButton("Add New Entry");
    private final JButton sortButton = new JButton("Sort");
    private final JLabel title = new JLabel("Library Borrowing System");
    private final ScrollPane scrollPane = new ScrollPane();
    private static final Path path = Path.of(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Library Borrowing System" + File.separator + "Unreturned");

    MainWindow() {
        addNewButton.addActionListener(e -> {
            var addNew = new AddEntryDialogGUI();
            if (!addNew.isRunning()) {
                addNew.showUI();
            }
            addNew.focusable();
        });
    }

    public static void main(String[] args) {
        var main = new MainWindow();
        main.showUI();
    }

    public void showUI() {
        JPanel topCont = new JPanel();
        JPanel titleCont = new JPanel(new BorderLayout());
        JPanel middleCont = new JPanel();
        JPanel topInsideMiddleCont = new JPanel(new BorderLayout());
        JPanel botInsideMiddleCont = new JPanel(new GridBagLayout());
        JPanel contLabel0 = colName("Book");
        JPanel contLabel1 = colName("Borrower");
        JPanel contLabel2 = colName("Date Borrowed");
        JPanel contLabel3 = colName("Due Date");
        JPanel contLabel4 = colName("Status");
        JPanel contLabel5 = colName("Actions");

        topCont.setLayout(new BoxLayout(topCont, BoxLayout.Y_AXIS));
//        container for the title
        labelFormat(title, Color.white, 45);
        titleCont.setBorder(new EmptyBorder(20, 20, 20, 20));
        titleCont.setBackground(new Color(43, 48, 58));
        titleCont.add(title, BorderLayout.LINE_START);
        topCont.add(titleCont);

//        Button look
        addNewButton.setBackground(Color.decode("#58A4B0"));
        addNewButton.setForeground(Color.white);
        addNewButton.setFont(new Font("Inter", Font.BOLD, 18));
        addNewButton.setBorder(new EmptyBorder(10, 10, 10, 10));
        sortButton.setBackground(Color.decode("#58A4B0"));
        sortButton.setForeground(Color.white);
        sortButton.setFont(new Font("Inter", Font.BOLD, 18));
        sortButton.setBorder(new EmptyBorder(10, 10, 10, 10));

//        Container for the add and sort buttons
        middleCont.setLayout(new BoxLayout(middleCont, BoxLayout.Y_AXIS));
        topInsideMiddleCont.setBorder(new EmptyBorder(10, 30, 10, 30));
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

        Thread t2 = new Thread(() -> {
            do {
                try {
                    WatchService watcher = path.getFileSystem().newWatchService();
                    path.register(watcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
                    WatchKey watchKey = watcher.take();
                    List<WatchEvent<?>> events = watchKey.pollEvents();
                    for (var event : events) {
                        if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE || event.kind() == StandardWatchEventKinds.ENTRY_MODIFY || event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
                            scrollPane.removeAll();
                            scrollPane();
                            scrollPane.refresh();
                        }
                    }
                    watchKey.reset();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        });
        t2.start();

        scrollPane();
        mainWindow.setLayout(new BorderLayout());
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.add(topCont, BorderLayout.NORTH);
        mainWindow.setMinimumSize(new Dimension(1000, 500));
        mainWindow.setSize(900, 500);
        mainWindow.setVisible(true);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.add(scrollPane.getScrollPane(), BorderLayout.CENTER);

    }

    private void scrollPane() {
        var traverse = new TraverseFolder(path.toString());
        ArrayList<Row> list = new ArrayList<>();
        for (File file : traverse.getFiles()) {
            list.add(new Row(file));
        }
        for (var li : list) {
            scrollPane.add(li.showGUI());
        }
//        scrollPane.add(new JToolBar.Separator(new Dimension(0, 3)));
    }

    private static void labelFormat(Component label, Color fontColor, int fontSize) {
        label.setForeground(fontColor);
        label.setFont(new Font("Inter", Font.BOLD, fontSize));
    }

    private static void panelFormat(JPanel panel) {
        panel.setBorder(new EmptyBorder(10, 20, 10, 20));
        panel.setBackground(Color.white);
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
