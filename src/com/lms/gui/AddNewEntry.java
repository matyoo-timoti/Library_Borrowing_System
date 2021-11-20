package com.lms.gui;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class AddNewEntry {
    private static final JFrame WINDOW = new JFrame("Library Borrowing System | Add New Entry");
    private static final JButton SAVE_BUTTON = new JButton("Save");
    private static final Color CADET_BLUE = Color.decode("#58A4B0");
    private static final JTextField bookTextField = new JTextField();
    private static final JTextField authorTextField = new JTextField();
    private static final JTextField isbnTextField = new JTextField();
    private static final JTextField dateBorrowedTextField = new JTextField();
    private static final JTextField borrowerTextField = new JTextField();
    private static final JTextField affiliationTextField = new JTextField();
    private static final JTextField dueDateTextField = new JTextField();

    private static final String PATH = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Library Borrowing System";

    public void newEntryWindow() {
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

        //Placeholder
        placeholder(bookTextField, "Enter Book Title");
        placeholder(isbnTextField, "Must have at least 10 digits");
        placeholder(authorTextField, "Last Name, First Name");
        placeholder(borrowerTextField, "Last Name, First Name");
        placeholder(affiliationTextField, "Department/Course/Year/Block");
        placeholder(dateBorrowedTextField, "MM-DD-YYYY");
        placeholder(dueDateTextField, "MM-DD-YYYY");

        //Title Container
        JLabel title = new JLabel("Add New Entry", JLabel.LEFT);
        title.setFont(new Font("Inter", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        JPanel titleContainer = new JPanel();
        titleContainer.setLayout(new BorderLayout());
        titleContainer.setBackground(CADET_BLUE);
        titleContainer.setSize(-1, 200);
        titleContainer.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        titleContainer.add(title, BorderLayout.LINE_START);

        /*
         * The left panel is the container for the
         * Title of the Book field, Author,
         * ISBN, and Date Borrowed fields.
         * */
        JPanel left = new JPanel();
        left.setLayout(new GridLayout(4, 1));
        left.setBackground(Color.white);
        left.setSize(100, -1);
        left.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 10));
        left.add(panelFormat(bookCont, bookLabel, bookTextField));
        left.add(panelFormat(authorCont, authorLabel, authorTextField));
        left.add(panelFormat(isbnCont, isbnLabel, isbnTextField));
        left.add(panelFormat(dateBorrowedCont, dateBorrowedLabel, dateBorrowedTextField));

        /*
         * The right panel is the container for the
         * Borrower Name, Affiliation, and  Due Date fields,
         * along with the Save button
         * */
        JPanel right = new JPanel(new GridLayout(4, 1));
        right.setBackground(Color.white);
        right.setSize(100, -1);
        right.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 20));
        right.add(panelFormat(borrowerCont, borrowedLabel, borrowerTextField));
        right.add(panelFormat(affiliationCont, affiliationLabel, affiliationTextField));
        right.add(panelFormat(dueDateCont, dueDateLabel, dueDateTextField));
        JPanel saveBtnContainer = new JPanel(new BorderLayout());
        saveBtnContainer.setBackground(Color.white);
        saveBtnContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        SAVE_BUTTON.setFont(new Font("Inter", Font.BOLD, 18));
        SAVE_BUTTON.setForeground(Color.white);
        SAVE_BUTTON.setBackground(CADET_BLUE);
        saveBtnContainer.add(SAVE_BUTTON, BorderLayout.CENTER);
        right.add(saveBtnContainer);

        /*
         * The inner Cont is the container for
         * both the left and right Container.
         * */
        JPanel innerCont = new JPanel();
        innerCont.setLayout(new GridLayout(0, 2));
        innerCont.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        innerCont.setBackground(Color.white);
        innerCont.add(left);
        innerCont.add(right);

        /*
         * The main Cont is the container for all
         * the components
         * */
        JPanel mainCont = new JPanel();
        mainCont.setLayout(new GridLayout());
        mainCont.setOpaque(true);
        mainCont.setBackground(new Color(255, 255, 255, 10));

        /*
         * Adding attributes to the JFrame/window
         * */
        WINDOW.setSize((int) (get_screen_width() / 2.7), (int) (get_screen_height() / 2.5));
        WINDOW.setContentPane(mainCont);
        WINDOW.setLayout(new BorderLayout());
        WINDOW.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        WINDOW.setLocationRelativeTo(null);
        WINDOW.setVisible(true);
        WINDOW.add(titleContainer, BorderLayout.NORTH);
        WINDOW.add(innerCont, BorderLayout.CENTER);
        WINDOW.setResizable(false);

        /*
         * Clicking the save button will retrieve the text
         * from the text fields inserted by the user and
         * saved as a string.
         *
         * If at least one of the fields is empty, it will
         * show an error dialogue. Else if there are
         * no errors, it displays a
         * */
        SAVE_BUTTON.addActionListener(e -> {
            var book = get_text(bookTextField);
            var author = get_text(authorTextField);
            var isbn = get_text(isbnTextField);
            var borrower = get_text(borrowerTextField);
            var affiliation = get_text(affiliationTextField);
            var dateBorrowed = get_text(dateBorrowedTextField);
            var dueDate = get_text(dueDateTextField);

            if (isUnfilled()) {
                JOptionPane.showMessageDialog(WINDOW, "Fields can't be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                var storeData = new StoreData();
                storeData.setBook(book);
                storeData.setAuthor(author);
                storeData.setIsbn(isbn);
                storeData.setBorrower(borrower);
                storeData.setAffiliation(affiliation);
                storeData.setDateBorrowed(dateBorrowed);
                storeData.setDueDate(dueDate);
                storeData.store();
                JOptionPane.showMessageDialog(WINDOW, "Entry Saved", "Notification", JOptionPane.PLAIN_MESSAGE);
                WINDOW.dispose();
            }
        });
    }

    public static void main(String[] args) {
        var entry = new AddNewEntry();
        entry.newEntryWindow();
    }

    public boolean isRunning() {
        return WINDOW.isVisible();
    }

    private static void placeholder(JTextField tf, String text) {
        TextPrompt tx = new TextPrompt(text, tf);
        tx.setForeground(Color.GRAY);
//        tx.setForeground(Color.decode("#232323"));
        tx.setFont(new Font("Inter Medium", Font.PLAIN, 14));
    }

    private static boolean isUnfilled() {
        var bt = get_text(bookTextField);
        var ad = get_text(authorTextField);
        var is = get_text(isbnTextField);
        var bn = get_text(borrowerTextField);
        var af = get_text(affiliationTextField);
        var db = get_text(dateBorrowedTextField);
        var dd = get_text(dueDateTextField);
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
        tf.setBackground(new Color(248, 248, 248)); // default 248,248,248
        tf.setBorder(new LineBorder(Color.LIGHT_GRAY)); //default 255,255,255
        return tf;
    }

    private static String get_text(JTextField tf) {
        return tf.getText().trim();
    }

    private static double get_screen_width() {
        return Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    }

    private static double get_screen_height() {
        return Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    }
}