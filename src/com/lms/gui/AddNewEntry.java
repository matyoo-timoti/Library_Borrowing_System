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
    private static final JButton BTN_SAVE = new JButton("Save");
    private static final Color CADET_BLUE = new Color(88, 164, 176);
    private static final JPanel cont_popup = new JPanel();
    private static final JLabel label_popup = new JLabel("Entry Saved!");

    //Title of the Book
    private static final JLabel label_book = new JLabel("Title of the Book:");
    private static final JPanel cont_book = new JPanel();
    private static final JTextField tf_book = new JTextField();

    //Author &  Date Published
    private static final JLabel label_author = new JLabel("Author:");
    private static final JTextField tf_author = new JTextField();
    private static final JPanel cont_author = new JPanel();

    //ISBN
    private static final JLabel label_isbn = new JLabel("ISBN");
    private static final JTextField tf_isbn = new JTextField();
    private static final JPanel cont_isbn = new JPanel();

    //Date Borrowed
    private static final JLabel label_date_borrowed = new JLabel("Date Borrowed:");
    private static final JTextField tf_date_borrowed = new JTextField();
    private static final JPanel cont_date_borrowed = new JPanel();

    //Name of Borrower
    private static final JLabel label_borrower = new JLabel("Borrower Name:");
    private static final JTextField tf_borrower = new JTextField();
    private static final JPanel cont_borrower = new JPanel(new GridLayout(2, 1));

    //Affiliation
    private static final JLabel label_affiliation = new JLabel("Affiliation:");
    private static final JTextField tf_affiliation = new JTextField();
    private static final JPanel cont_affiliation = new JPanel(new GridLayout(2, 1));

    //Due Date
    private static final JLabel label_due = new JLabel("Due Date:");
    private static final JTextField tf_due = new JTextField();
    private static final JPanel cont_due = new JPanel(new GridLayout(2, 1));

    private static final String PATH = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Library Borrowing System";

    public static void main(String[] args) {
        new_entry_window();
    }

    public static void new_entry_window(){
        WINDOW.setSize((int) (get_screen_width() / 2.7), (int) (get_screen_height() / 2.5));
        //Pop-up Notification
        label_popup.setFont(new Font("Inter", Font.BOLD, 20));
        label_popup.setForeground(Color.WHITE);
        cont_popup.setSize(200, 40);
        cont_popup.setBackground(new Color(148, 210, 189));
        cont_popup.setBorder(BorderFactory.createLineBorder(new Color(94, 187, 156), 1, true));
        cont_popup.add(label_popup);
        cont_popup.setVisible(false); //hidden at first

        //Placeholder
        placeholder(tf_book, "E.g. Violet Evergarden");
        placeholder(tf_isbn, "E.g. 9784907064433");
        placeholder(tf_author, "E.g. Akatsuki Kana");
        placeholder(tf_borrower, "E.g. Matthew Cabarle");
        placeholder(tf_affiliation, "E.g. ICS / BSIT-2C");
        placeholder(tf_date_borrowed, "mm-dd-yyyy");
        placeholder(tf_due, "mm-dd-yyyy");

        //Title Container
        JLabel title = new JLabel("Add New Entry", JLabel.LEFT);
        title.setFont(new Font("Inter", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        JPanel panel_title = new JPanel();
        panel_title.setLayout(new BorderLayout());
        panel_title.setBackground(CADET_BLUE);
        panel_title.setSize(-1, 200);
        panel_title.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panel_title.add(title, BorderLayout.LINE_START);
        panel_title.add(cont_popup, BorderLayout.EAST);

        //Left Side Container
        JPanel cont_left = new JPanel();
        cont_left.setLayout(new GridLayout(4, 1));
        cont_left.setBackground(Color.white);
        cont_left.setSize(100, -1);
        cont_left.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 10));
        cont_left.add(panel_format(cont_book, label_book, tf_book));
        cont_left.add(panel_format(cont_author, label_author, tf_author));
        cont_left.add(panel_format(cont_isbn, label_isbn, tf_isbn));
        cont_left.add(panel_format(cont_date_borrowed, label_date_borrowed, tf_date_borrowed));

        //Right Side Container
        JPanel cont_right = new JPanel(new GridLayout(4, 1));
        cont_right.setBackground(Color.white);
        cont_right.setSize(100, -1);
        cont_right.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 20));
        cont_right.add(panel_format(cont_borrower, label_borrower, tf_borrower));
        cont_right.add(panel_format(cont_affiliation, label_affiliation, tf_affiliation));
        cont_right.add(panel_format(cont_due, label_due, tf_due));

        //Save Button
        JPanel cont_btn_save = new JPanel(new BorderLayout());
        cont_btn_save.setBackground(Color.white);
        cont_btn_save.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        BTN_SAVE.setFont(new Font("Inter", Font.BOLD, 18));
        BTN_SAVE.setForeground(Color.white);
        BTN_SAVE.setBackground(CADET_BLUE);
        cont_btn_save.add(BTN_SAVE, BorderLayout.CENTER);
        cont_right.add(cont_btn_save);

        //Inner Container
        JPanel cont_inner = new JPanel();
        cont_inner.setLayout(new GridLayout(0, 2));
        cont_inner.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        cont_inner.setBackground(Color.white);
        cont_inner.add(cont_left);
        cont_inner.add(cont_right);

        //Main Container
        JPanel cont_main = new JPanel();
        cont_main.setLayout(new GridLayout());
        cont_main.setOpaque(true);
        cont_main.setBackground(new Color(255, 255, 255, 10));

        //Window Configuration
        WINDOW.setContentPane(cont_main);
        WINDOW.setLayout(new BorderLayout());
        WINDOW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WINDOW.setLocationRelativeTo(null);
        WINDOW.setVisible(true);
        WINDOW.add(panel_title, BorderLayout.NORTH);
        WINDOW.add(cont_inner, BorderLayout.CENTER);
        WINDOW.setResizable(false);

        BTN_SAVE.addActionListener(e -> {
            var bt = get_text(tf_book);
            var ad = get_text(tf_author);
            var is = get_text(tf_isbn);
            var bn = get_text(tf_borrower);
            var af = get_text(tf_affiliation);
            var db = get_text(tf_date_borrowed);
            var dd = get_text(tf_due);

            if (form_incomplete()) {
                JOptionPane.showMessageDialog(WINDOW, "Fields can't be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                save(bt, ad, is, bn, af, db, dd);
                JOptionPane.showMessageDialog(WINDOW, "Entry Saved", "Notification", JOptionPane.PLAIN_MESSAGE);
                WINDOW.dispose();
            }
        });
    }

    private static void placeholder(JTextField tf, String text) {
        TextPrompt tx = new TextPrompt(text, tf);
        tx.setForeground(Color.lightGray);
        tx.setFont(new Font("Inter Medium", Font.PLAIN, 14));
    }

    private static void popup() {
        cont_popup.setVisible(!cont_popup.isVisible());
    }

    public static void save(String book, String author, String isbn, String borrower, String
            affiliation, String date_borrowed, String due) {
        StringBuilder id = new StringBuilder();
        id.append(book).append("_").append(borrower).append("_").append(date_borrowed).append("_").append(due);
        File folder = new File(PATH);
        if (folder.exists() || folder.mkdirs()) {
            File new_file = new File(PATH + File.separator + id + ".txt");
            try {
                if (new_file.createNewFile()) {
                    System.out.println("File created: " + new_file.getName());
                    try {
                        FileWriter file_writer = new FileWriter(PATH + File.separator + id + ".txt");
                        file_writer.write(book + "\n");
                        file_writer.write(author + "\n");
                        file_writer.write(isbn + "\n");
                        file_writer.write(borrower + "\n");
                        file_writer.write(affiliation + "\n");
                        file_writer.write(date_borrowed + "\n");
                        file_writer.write(due);
                        file_writer.close();
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(WINDOW, "An Error Occurred");
                        e.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(WINDOW, "There are similar entry in the system.", "Error", JOptionPane.ERROR_MESSAGE);
                    System.out.println("File Already Exists!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Not created");
        }
    }

    private static boolean form_incomplete() {
        var bt = get_text(tf_book);
        var ad = get_text(tf_author);
        var is = get_text(tf_isbn);
        var bn = get_text(tf_borrower);
        var af = get_text(tf_affiliation);
        var db = get_text(tf_date_borrowed);
        var dd = get_text(tf_due);
        return bt.isEmpty() || ad.isEmpty() || is.isEmpty() || bn.isEmpty() || af.isEmpty() || db.isEmpty() || dd.isEmpty();
    }

    private static JPanel panel_format(JPanel panel, JLabel label, JTextField field) {
        panel.setLayout(new GridLayout(2, 1));
        panel.setBackground(Color.white);
        panel.add(label_format(label));
        panel.add(field_format(field));
        return panel;
    }

    private static JLabel label_format(JLabel label) {
        label.setForeground(Color.black);
        label.setFont(new Font("Inter Medium", Font.PLAIN, 15));
        return label;
    }

    private static JTextField field_format(JTextField tf) {
        tf.setFont(new Font("Inter Medium", Font.PLAIN, 15));
        tf.setBackground(new Color(252, 252, 252)); // default 248,248,248
        tf.setBorder(new LineBorder(new Color(210, 210, 210))); //default 255,255,255
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

    private static void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}