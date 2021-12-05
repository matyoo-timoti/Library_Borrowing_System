package com.lms.gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;

class AddEntryDialogGUI {
    private final JFrame window = new JFrame("Library Borrowing System | Add New Entry");
    private final JButton saveBtn = new JButton("Save");
    private final Color CADET_BLUE = Color.decode("#58A4B0");
    private final JTextField bookTextField = new JTextField();
    private final JTextField authorTextField = new JTextField();
    private final JTextField isbnTextField = new JTextField();
    private final JTextField dateBorrowedTextField = new JTextField();
    private final JTextField borrowerTextField = new JTextField();
    private final JTextField affiliationTextField = new JTextField();
    private final JTextField dueDateTextField = new JTextField();
    private final Path PATH = Path.of(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Library Borrowing System" + File.separator + "Unreturned");

    public AddEntryDialogGUI() {
        saveBtn.addActionListener(e -> saveButtonAction());
        window.setSize((int) (get_screen_width() / 2.7), (int) (get_screen_height() / 2.5));
    }

    public void focusable() {
        window.setAutoRequestFocus(true);
        window.toFront();
        window.setLocationRelativeTo(null);
    }

    public void showUI() {
        JLabel bookLabel = new JLabel("Title of the Book:");
        JLabel authorLabel = new JLabel("Author:");
        JLabel isbnLabel = new JLabel("ISBN");
        JLabel dateBorrowedLabel = new JLabel("Date Borrowed:");
        JLabel borrowedLabel = new JLabel("Borrower Name:");
        JLabel affiliationLabel = new JLabel("Affiliation:");
        JLabel dueDateLabel = new JLabel("Due Date:");
        JPanel bookCont = new JPanel();
        JPanel authorCont = new JPanel();
        JPanel isbnCont = new JPanel();
        JPanel dateBorrowedCont = new JPanel();
        JPanel borrowerCont = new JPanel(new GridLayout(2, 1));
        JPanel affiliationCont = new JPanel(new GridLayout(2, 1));
        JPanel dueDateCont = new JPanel(new GridLayout(2, 1));

//        Placeholder
        placeholder(bookTextField, "Enter Book Title");
        placeholder(isbnTextField, "Must have at least 10 digits");
        placeholder(authorTextField, "Last Name, First Name");
        placeholder(borrowerTextField, "Last Name, First Name");
        placeholder(affiliationTextField, "Department/Course/Year/Block");
        placeholder(dateBorrowedTextField, "MM-DD-YYYY");
        placeholder(dueDateTextField, "MM-DD-YYYY");

//        Title Container
        JLabel title = new JLabel("Add New Entry", JLabel.LEFT);
        title.setFont(new Font("Inter", Font.BOLD, 30));
        title.setForeground(Color.WHITE);

        JPanel titleContainer = new JPanel();
        titleContainer.setLayout(new BorderLayout());
        titleContainer.setBackground(CADET_BLUE);
        titleContainer.setSize(-1, 200);
        titleContainer.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        titleContainer.add(title, BorderLayout.LINE_START);

//        Left Side
        JPanel left = new JPanel();
        left.setLayout(new GridLayout(4, 1));
        left.setBackground(Color.white);
        left.setSize(100, -1);
        left.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 10));
        left.add(panelFormat(bookCont, bookLabel, bookTextField));
        left.add(panelFormat(authorCont, authorLabel, authorTextField));
        left.add(panelFormat(isbnCont, isbnLabel, isbnTextField));
        left.add(panelFormat(dateBorrowedCont, dateBorrowedLabel, dateBorrowedTextField));

//        Right Side
        JPanel right = new JPanel(new GridLayout(4, 1));
        right.setBackground(Color.white);
        right.setSize(100, -1);
        right.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 20));
        right.add(panelFormat(borrowerCont, borrowedLabel, borrowerTextField));
        right.add(panelFormat(affiliationCont, affiliationLabel, affiliationTextField));
        right.add(panelFormat(dueDateCont, dueDateLabel, dueDateTextField));

//        Button
        JPanel saveBtnContainer = new JPanel(new BorderLayout());
        saveBtnContainer.setBackground(Color.white);
        saveBtnContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        saveBtn.setFont(new Font("Inter", Font.BOLD, 18));
        saveBtn.setForeground(Color.white);
        saveBtn.setBackground(CADET_BLUE);
        saveBtnContainer.add(saveBtn, BorderLayout.CENTER);
        right.add(saveBtnContainer);

//        Container for left and right container
        JPanel innerCont = new JPanel();
        innerCont.setLayout(new GridLayout(0, 2));
        innerCont.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        innerCont.setBackground(Color.white);
        innerCont.add(left);
        innerCont.add(right);

//        Container for all components
        JPanel mainCont = new JPanel();
        mainCont.setLayout(new GridLayout());
        mainCont.setOpaque(true);
        mainCont.setBackground(new Color(255, 255, 255, 10));
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setResizable(false);
        window.setContentPane(mainCont);
        window.setLayout(new BorderLayout());
        window.add(titleContainer, BorderLayout.NORTH);
        window.add(innerCont, BorderLayout.CENTER);
    }

    private void saveButtonAction() {
        var book = getText(bookTextField);
        var author = getText(authorTextField);
        var isbn = getText(isbnTextField);
        var borrower = getText(borrowerTextField);
        var affiliation = getText(affiliationTextField);
        var dateBorrowed = getText(dateBorrowedTextField);
        var dueDate = getText(dueDateTextField);

        if (isUnfilled()) {
            JOptionPane.showMessageDialog(window, "Fields can't be empty!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String id = book + "-" + borrower + "-" + dateBorrowed + "-" + dueDate;
            var file = new CreateFile(PATH + File.separator, id);
            var updateFile = new UpdateFile(file.getFile());
            updateFile.writeln(book);
            updateFile.writeln(author);
            updateFile.writeln(isbn);
            updateFile.writeln(borrower);
            updateFile.writeln(affiliation);
            updateFile.writeln(dateBorrowed);
            updateFile.writeln(dueDate);
            updateFile.write("false");
            JOptionPane.showMessageDialog(window, "Entry Saved", "Notification", JOptionPane.PLAIN_MESSAGE);
            window.dispose();
        }
    }

    public boolean isRunning() {
        return window.isVisible();
    }

    private static void placeholder(JTextField tf, String text) {
        TextPrompt tx = new TextPrompt(text, tf);
        tx.setForeground(Color.GRAY);
//        tx.setForeground(Color.decode("#232323"));
        tx.setFont(new Font("Inter Medium", Font.PLAIN, 14));
    }

    private boolean isUnfilled() {
        var bt = getText(bookTextField);
        var ad = getText(authorTextField);
        var is = getText(isbnTextField);
        var bn = getText(borrowerTextField);
        var af = getText(affiliationTextField);
        var db = getText(dateBorrowedTextField);
        var dd = getText(dueDateTextField);
        return bt.isEmpty() || ad.isEmpty() || is.isEmpty() || bn.isEmpty() || af.isEmpty() || db.isEmpty() || dd.isEmpty();
    }

    private static JPanel panelFormat(JPanel panel, JLabel label, JTextField field) {
        panel.setLayout(new GridLayout(2, 1));
        panel.setBackground(Color.white);
        panel.add(labelFormat(label));
        panel.add(textFieldFormat(field));
        return panel;
    }

    private static JLabel labelFormat(JLabel label) {
        label.setForeground(Color.black);
        label.setFont(new Font("Inter Medium", Font.PLAIN, 15));
        return label;
    }

    private static JTextField textFieldFormat(JTextField tf) {
        tf.setFont(new Font("Inter Medium", Font.PLAIN, 15));
        tf.setBackground(new Color(245, 245, 245)); // default 248,248,248
//        tf.setBorder(new LineBorder(Color.LIGHT_GRAY)); //default 255,255,255
        return tf;
    }

    private static String getText(JTextField tf) {
        return tf.getText().trim();
    }

    private static double get_screen_width() {
        return Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    }

    private static double get_screen_height() {
        return Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    }
}