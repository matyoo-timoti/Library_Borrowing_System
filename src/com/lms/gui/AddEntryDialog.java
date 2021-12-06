package com.lms.gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;

class AddEntryDialog {
    private final JDialog dialog;
    private final JButton saveBtn = new JButton("Save");
    private final Color CADET_BLUE = Color.decode("#58A4B0");
    private final JTextField bookTf = new JTextField();
    private final JTextField authorTf = new JTextField();
    private final JTextField isbnTf = new JTextField();
    private final JTextField borrowerTf = new JTextField();
    private final JTextField affiliationTf = new JTextField();
    private final JTextField dateBTf = new JTextField();
    private final JTextField dueDTf = new JTextField();
    private final Path PATH = Path.of(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Library Borrowing System" + File.separator + "Unreturned");

    public AddEntryDialog(JFrame parent) {
        dialog = new JDialog();
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        var icon = new ImageIcon("C:\\Users\\Lenovo\\IdeaProjects\\LMS\\src\\com\\lms\\gui\\icon.png").getImage();
        dialog.setIconImage(icon);
        showUI(parent);
        saveBtn.addActionListener(e -> saveButtonAction());
        dialog.setVisible(true);
    }

    public void showUI(JFrame parent) {
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
        placeholder(bookTf, "Enter Book Title");
        placeholder(isbnTf, "Must have at least 10 digits");
        placeholder(authorTf, "Last Name, First Name");
        placeholder(borrowerTf, "Last Name, First Name");
        placeholder(affiliationTf, "Department/Course/Year/Block");
        placeholder(dateBTf, "MM-dd-yyyy");
        placeholder(dueDTf, "MM-dd-yyyy");

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
        left.add(panelFormat(bookCont, bookLabel, bookTf));
        left.add(panelFormat(authorCont, authorLabel, authorTf));
        left.add(panelFormat(isbnCont, isbnLabel, isbnTf));
        left.add(panelFormat(dateBorrowedCont, dateBorrowedLabel, dateBTf));

//        Right Side
        JPanel right = new JPanel(new GridLayout(4, 1));
        right.setBackground(Color.white);
        right.setSize(100, -1);
        right.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 20));
        right.add(panelFormat(borrowerCont, borrowedLabel, borrowerTf));
        right.add(panelFormat(affiliationCont, affiliationLabel, affiliationTf));
        right.add(panelFormat(dueDateCont, dueDateLabel, dueDTf));

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

        dialog.setSize((int) (get_screen_width() / 2.7), (int) (get_screen_height() / 2.5));
        dialog.setLocationRelativeTo(parent);
        dialog.setTitle("Library Borrowing System | Add New Entry");
        dialog.setResizable(false);
        dialog.add(titleContainer, BorderLayout.NORTH);
        dialog.add(innerCont, BorderLayout.CENTER);
    }

    private void saveButtonAction() {
        var book = getText(bookTf);
        var author = getText(authorTf);
        var isbn = getText(isbnTf);
        var borrower = getText(borrowerTf);
        var affiliation = getText(affiliationTf);
        var dateBorrowed = getText(dateBTf);
        var dueDate = getText(dueDTf);
        if (isUnfilled()) {
            JOptionPane.showMessageDialog(dialog, "Fields can't be empty!", "Error", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(dialog, "Entry Saved", "Notification", JOptionPane.PLAIN_MESSAGE);
            dialog.dispose();
        }
    }

    private static void placeholder(JTextField tf, String text) {
        TextPrompt tx = new TextPrompt(text, tf);
        tx.setForeground(Color.GRAY);
        tx.setFont(new Font("Inter Medium", Font.PLAIN, 14));
    }

    private boolean isUnfilled() {
        var bt = getText(bookTf);
        var ad = getText(authorTf);
        var is = getText(isbnTf);
        var bn = getText(borrowerTf);
        var af = getText(affiliationTf);
        var db = getText(dateBTf);
        var dd = getText(dueDTf);
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