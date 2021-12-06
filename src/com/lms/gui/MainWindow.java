package com.lms.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainWindow {
    private final JFrame mainWindow = new JFrame("Library Borrowing System | By: Matthew Cabarle");
    private final JButton addNewButton = new JButton("Add New Entry");
    private final JLabel title = new JLabel("Library Borrowing System");
    private final Path pathUnreturned = Path.of(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Library Borrowing System" + File.separator + "Unreturned");
    private final Path pathReturned = Path.of(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Library Borrowing System" + File.separator + "Returned");
    Image icon = new ImageIcon("C:\\Users\\Lenovo\\IdeaProjects\\LMS\\src\\com\\lms\\gui\\icon.png").getImage();

    MainWindow() {
        mainWindow.setIconImage(icon);
        addNewButton.addActionListener(ae -> new AddEntryDialog(mainWindow));
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

//       Add New Button look
        addNewButton.setFocusable(false);
        addNewButton.setBackground(Color.decode("#58A4B0"));
        addNewButton.setForeground(Color.white);
        addNewButton.setFont(new Font("Inter", Font.BOLD, 18));
        addNewButton.setBorder(new EmptyBorder(10, 10, 10, 10));

//        Sort Combo Box
        var unreturned = "Unreturned";
        var returned = "Returned";
        String[] options = {unreturned, returned};
        var sortOptions = new JComboBox<>(options);
        sortOptions.setFont(new Font("Inter", Font.BOLD, 18));
        sortOptions.setEditable(false);
        sortOptions.setFocusable(false);
        sortOptions.setFocusable(false);
        sortOptions.setBackground(Color.decode("#58A4B0"));
        sortOptions.setForeground(Color.WHITE);
        sortOptions.setToolTipText("Pick which category to show");

//        Container for the add and sort buttons
        middleCont.setLayout(new BoxLayout(middleCont, BoxLayout.Y_AXIS));
        topInsideMiddleCont.setBorder(new EmptyBorder(10, 30, 10, 30));
        topInsideMiddleCont.add(addNewButton, BorderLayout.LINE_START);
        topInsideMiddleCont.add(sortOptions, BorderLayout.LINE_END);
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

        var unreturnedScrollPane = new ScrollPane();
        var returnedScrollPane = new ScrollPane();

        createScrollPane(pathReturned, returnedScrollPane);
        createScrollPane(pathUnreturned, unreturnedScrollPane);

//        Card Layout for changing panels
        var card = new JPanel(new CardLayout());
        card.add(unreturnedScrollPane.getScrollPane(), unreturned);
        card.add(returnedScrollPane.getScrollPane(), returned);

        sortOptions.addItemListener(evt -> {
            CardLayout cl = (CardLayout) (card.getLayout());
            cl.show(card, (String) evt.getItem());
        });

//        Keeps watch of the Returned and Unreturned folders for events and refreshes the content if there are.
        var t = new Thread(() -> {
            while (true) {
                try {
                    var watchService = FileSystems.getDefault().newWatchService();
                    pathUnreturned.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
                    pathReturned.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
                    WatchKey watchKey = watchService.take();
                    List<WatchEvent<?>> events = watchKey.pollEvents();
                    for (var event : events) {
                        if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE ||
                                event.kind() == StandardWatchEventKinds.ENTRY_MODIFY ||
                                event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
                            unreturnedScrollPane.removeAll();
                            returnedScrollPane.removeAll();

                            createScrollPane(pathUnreturned, unreturnedScrollPane);
                            createScrollPane(pathReturned, returnedScrollPane);

                            unreturnedScrollPane.refresh();
                            returnedScrollPane.refresh();
                        }
                    }
                    watchKey.reset();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

        mainWindow.setLayout(new BorderLayout());
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.add(card, BorderLayout.CENTER);
        mainWindow.add(topCont, BorderLayout.NORTH);
        mainWindow.setMinimumSize(new Dimension(1000, 500));
        mainWindow.setSize(900, 500);
        mainWindow.setVisible(true);
        mainWindow.setLocationRelativeTo(null);
    }

    private void createScrollPane(Path entryFolder, ScrollPane scrollPane) {
        var traverse = new TraverseFolder(entryFolder.toString());
        ArrayList<Row> list = new ArrayList<>();
        for (File file : traverse.getFiles()) {
            list.add(new Row(file));
        }
        if (list.isEmpty()) {
            return;
        }
        list.sort(Comparator.comparing(Row::getBookName));
        for (var li : list) {
            scrollPane.add(li.showGUI());
        }
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
