package com.lms.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;


class AddNewEntry {
    private static final JFrame WINDOW = new JFrame("Library Borrowing System | Add New Entry");
    private static final JButton SAVE_BUTTON = new JButton("Save");
    private static final Color CADET_BLUE = new Color(88, 164, 176);
    private static final JPanel popup_container = new JPanel();
    private static final JLabel popup_label = new JLabel("Entry Saved!");

    //Title of the Book  
    private static final JLabel book_title_label = new JLabel("Title of the Book:");
    private static final JPanel book_title_cont = new JPanel();
    private static final JTextField book_title_tf = new JTextField();

    //Author &  Date Published  
    private static final JLabel author_date_label = new JLabel("Author & Year Published:");
    private static final JTextField author_date_tf = new JTextField();
    private static final JPanel author_date_cont = new JPanel();

    //ISBN  
    private static final JLabel isbn_label = new JLabel("ISBN:");
    private static final JTextField isbn_tf = new JTextField();
    private static final JPanel isbn_cont = new JPanel();

    //Date Borrowed  
    private static final JLabel date_borrowed_label = new JLabel("Date Borrowed (mm-dd-yyyy):");
    private static final JTextField date_borrowed_tf = new JTextField();
    private static final JPanel date_borrowed_cont = new JPanel();

    //Name of Borrower  
    private static final JLabel borrower_name_label = new JLabel("Borrower Name:");
    private static final JTextField borrower_name_tf = new JTextField();
    private static final JPanel borrower_name_cont = new JPanel(new GridLayout(2, 1));

    //Affiliation  
    private static final JLabel affiliation_label = new JLabel("Department/Course/Section:");
    private static final JTextField affiliation_tf = new JTextField();
    private static final JPanel affiliation_cont = new JPanel(new GridLayout(2, 1));

    //Due Date  
    private static final JLabel due_date_label = new JLabel("Due Date (mm-dd-yyyy):");
    private static final JTextField due_date_tf = new JTextField();
    private static final JPanel due_date_cont = new JPanel(new GridLayout(2, 1));

    static FileHandling file_handling = new FileHandling();

    public static void main(String[] args) {
        WINDOW.setSize((int) (get_screen_width() / 2.7), (int) (get_screen_height() / 2.5));
        //Pop-up Notification   
        popup_label.setFont(new Font("Inter", Font.BOLD, 20));
        popup_label.setForeground(Color.WHITE);
        popup_container.setSize(200, 40);
        popup_container.setBackground(new Color(148, 210, 189));
        popup_container.setBorder(BorderFactory.createLineBorder(new Color(94, 187, 156), 1, true));
        popup_container.add(popup_label);
        popup_container.setVisible(false); //hidden at first

        //Title Container  
        JLabel title = new JLabel("Add New Entry", JLabel.LEFT);
        title.setFont(new Font("Inter", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        JPanel title_panel = new JPanel();
        title_panel.setLayout(new BorderLayout());
        title_panel.setBackground(CADET_BLUE);
        title_panel.setSize(-1, 200);
        title_panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        title_panel.add(title, BorderLayout.LINE_START);
        title_panel.add(popup_container, BorderLayout.EAST);

        //Left Side Container  
        JPanel left_cont = new JPanel();
        left_cont.setLayout(new GridLayout(4, 1));
        left_cont.setBackground(Color.white);
        left_cont.setSize(100, -1);
        left_cont.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 10));
        left_cont.add(panel_format(book_title_cont, book_title_label, book_title_tf));
        left_cont.add(panel_format(author_date_cont, author_date_label, author_date_tf));
        left_cont.add(panel_format(isbn_cont, isbn_label, isbn_tf));
        left_cont.add(panel_format(date_borrowed_cont, date_borrowed_label, date_borrowed_tf));

        //Right Side Container  
        JPanel right_cont = new JPanel(new GridLayout(4, 1));
        right_cont.setBackground(Color.white);
        right_cont.setSize(100, -1);
        right_cont.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 20));

        right_cont.add(panel_format(borrower_name_cont, borrower_name_label, borrower_name_tf));
        right_cont.add(panel_format(affiliation_cont, affiliation_label, affiliation_tf));
        right_cont.add(panel_format(due_date_cont, due_date_label, due_date_tf));

        //Save Button  
        JPanel btn_cont = new JPanel(new BorderLayout());
        btn_cont.setBackground(Color.white);
        btn_cont.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        SAVE_BUTTON.setFont(new Font("Inter", Font.BOLD, 18));
        SAVE_BUTTON.setForeground(Color.white);
        SAVE_BUTTON.setBackground(CADET_BLUE);
        btn_cont.add(SAVE_BUTTON, BorderLayout.CENTER);
        right_cont.add(btn_cont);

        //Inner Container  
        JPanel container_panel = new JPanel();
        container_panel.setLayout(new GridLayout(0, 2));
        container_panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        container_panel.setBackground(Color.white);
        container_panel.add(left_cont);
        container_panel.add(right_cont);

        //Main Container  
        JPanel main_panel = new JPanel();
        main_panel.setLayout(new GridLayout());
        main_panel.setOpaque(true);
        main_panel.setBackground(new Color(255, 255, 255, 10));

        //Window Configuration  
        WINDOW.setContentPane(main_panel);
        WINDOW.setLayout(new BorderLayout());
        WINDOW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WINDOW.setLocationRelativeTo(null);
        WINDOW.setVisible(true);
        WINDOW.add(title_panel, BorderLayout.NORTH);
        WINDOW.add(container_panel, BorderLayout.CENTER);
        WINDOW.setResizable(false);

        //Button Action
        popup_container.setVisible(false);
        SAVE_BUTTON.addActionListener(e -> {
            var bt = get_text(book_title_tf);
            var ad = get_text(author_date_tf);
            var is = get_text(isbn_tf);
            var bn = get_text(borrower_name_tf);
            var af = get_text(affiliation_tf);
            var db = get_text(date_borrowed_tf);
            var dd = get_text(due_date_tf);

            if (form_incomplete()) {
                JOptionPane.showMessageDialog(WINDOW, "Fields can't be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                file_handling.save(bt, ad, is, bn, af, db, dd);
                book_title_tf.setText("");
                author_date_tf.setText("");
                isbn_tf.setText("");
                borrower_name_tf.setText("");
                affiliation_tf.setText("");
                date_borrowed_tf.setText("");
                due_date_tf.setText("");
            }
            popup_container.setVisible(true);
        });
        delay(1500);
    }

    private static boolean form_incomplete() {
        var bt = get_text(book_title_tf);
        var ad = get_text(author_date_tf);
        var is = get_text(isbn_tf);
        var bn = get_text(borrower_name_tf);
        var af = get_text(affiliation_tf);
        var db = get_text(date_borrowed_tf);
        var dd = get_text(due_date_tf);
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
        tf.setBackground(new Color(248, 248, 248));
        tf.setBorder(new LineBorder(new Color(225, 225, 225)));
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