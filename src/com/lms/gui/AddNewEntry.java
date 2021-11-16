package com.lms.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;


class AddNewEntry {
    private static final JFrame WINDOW = new JFrame("Library Borrowing System | Add New Entry");
    private static final JButton SAVE_BUTTON = new JButton("Save");
    private static final Color CADET_BLUE = new Color(88, 164, 176);
    private static final JPanel popup_container = new JPanel();

    public static void main(String[] args) {
        WINDOW.setSize((int) (get_screen_width() / 2.7), (int) (get_screen_height() / 2.5));
        //<--------------------Pop-up Notification-------------------->
        JLabel popup_label = new JLabel("Entry Saved!");
        popup_label.setFont(new Font("Inter", Font.BOLD, 20));
        popup_label.setForeground(Color.WHITE);

        popup_container.setSize(200, 40);
        popup_container.setBackground(new Color(148, 210, 189));
        popup_container.setBorder(BorderFactory.createLineBorder(new Color(94, 187, 156), 1, true));
        popup_container.add(popup_label);
        popup_container.setVisible(false); //hidden at first

        //<--------------------Title Container-------------------->
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

        //<--------------------Left Side Container-------------------->
        JPanel left_cont = new JPanel();
        left_cont.setLayout(new GridLayout(4, 1));
        left_cont.setBackground(Color.white);
        left_cont.setSize(100, -1);
        left_cont.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 10));

        //<--------------------Title of the Book-------------------->
        JLabel book_title_label = new JLabel("Title of the Book:");
        book_title_label.setForeground(Color.black);
        book_title_label.setFont(new Font("Inter Medium", Font.PLAIN, 15));

        JTextField book_title_tf = new JTextField();
        book_title_tf.setFont(new Font("Inter Medium", Font.PLAIN, 15));
        book_title_tf.setBackground(new Color(248, 248, 248));

        JPanel book_title_cont = new JPanel(new GridLayout(2, 1));
        book_title_cont.setBackground(Color.white);
        book_title_cont.add(book_title_label);
        book_title_cont.add(book_title_tf);
        left_cont.add(book_title_cont);

        //<--------------------Author &  Date Published-------------------->
        JLabel author_date_label = new JLabel("Author & Year Published:");
        author_date_label.setForeground(Color.black);
        author_date_label.setFont(new Font("Inter Medium", Font.PLAIN, 15));

        JTextField author_date_tf = new JTextField();
        author_date_tf.setFont(new Font("Inter Medium", Font.PLAIN, 15));
        author_date_tf.setBackground(new Color(248, 248, 248));

        JPanel author_date_cont = new JPanel(new GridLayout(2, 1));
        author_date_cont.setBackground(Color.white);
        author_date_cont.add(author_date_label);
        author_date_cont.add(author_date_tf);
        left_cont.add(author_date_cont);

        //<--------------------ISBN-------------------->
        JLabel isbn_label = new JLabel("ISBN:");
        isbn_label.setForeground(Color.black);
        isbn_label.setFont(new Font("Inter Medium", Font.PLAIN, 15));

        JTextField isbn_tf = new JTextField();
        isbn_tf.setFont(new Font("Inter Medium", Font.PLAIN, 15));
        isbn_tf.setBackground(new Color(248, 248, 248));

        JPanel isbn_cont = new JPanel(new GridLayout(2, 1));
        isbn_cont.setBackground(Color.white);
        isbn_cont.add(isbn_label);
        isbn_cont.add(isbn_tf);
        left_cont.add(isbn_cont);

        //<--------------------Date Borrowed-------------------->
        JLabel date_borrowed_label = new JLabel("Date Borrowed (mm-dd-yyyy):");
        date_borrowed_label.setForeground(Color.black);
        date_borrowed_label.setFont(new Font("Inter Medium", Font.PLAIN, 15));

        JTextField date_borrowed_tf = new JTextField();
        date_borrowed_tf.setFont(new Font("Inter Medium", Font.PLAIN, 15));
        date_borrowed_tf.setBackground(new Color(248, 248, 248));

        JPanel date_borrowed_cont = new JPanel(new GridLayout(2, 1));
        date_borrowed_cont.setBackground(Color.white);
        date_borrowed_cont.add(date_borrowed_label);
        date_borrowed_cont.add(date_borrowed_tf);
        left_cont.add(date_borrowed_cont);

        //<--------------------Right Side Container-------------------->
        JPanel right_cont = new JPanel(new GridLayout(4, 1));
        right_cont.setBackground(Color.white);
        right_cont.setSize(100, -1);
        right_cont.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 20));

        //<--------------------Name of Borrower-------------------->
        JLabel borrower_name_label = new JLabel("Borrower Name:");
        borrower_name_label.setForeground(Color.black);
        borrower_name_label.setFont(new Font("Inter Medium", Font.PLAIN, 15));

        JTextField borrower_name_tf = new JTextField();
        borrower_name_tf.setFont(new Font("Inter Medium", Font.PLAIN, 15));
        borrower_name_tf.setBackground(new Color(248, 248, 248));

        JPanel borrower_name_cont = new JPanel(new GridLayout(2, 1));
        borrower_name_cont.setBackground(Color.white);
        borrower_name_cont.add(borrower_name_label);
        borrower_name_cont.add(borrower_name_tf);
        right_cont.add(borrower_name_cont);

        //<--------------------Affiliation-------------------->
        JLabel affiliation_label = new JLabel("Department/Course/Section:");
        affiliation_label.setForeground(Color.black);
        affiliation_label.setFont(new Font("Inter Medium", Font.PLAIN, 15));

        JTextField affiliation_tf = new JTextField();
        affiliation_tf.setFont(new Font("Inter Medium", Font.PLAIN, 15));
        affiliation_tf.setBackground(new Color(248, 248, 248));

        JPanel affiliation_cont = new JPanel(new GridLayout(2, 1));
        affiliation_cont.setBackground(Color.white);
        affiliation_cont.add(affiliation_label);
        affiliation_cont.add(affiliation_tf);
        right_cont.add(affiliation_cont);

        //<--------------------Due Date-------------------->
        JLabel due_date_label = new JLabel("Due Date (mm-dd-yyyy):");
        due_date_label.setForeground(Color.black);
        due_date_label.setFont(new Font("Inter Medium", Font.PLAIN, 15));

        JTextField due_date_tf = new JTextField();
        due_date_tf.setFont(new Font("Inter Medium", Font.PLAIN, 15));
        due_date_tf.setBackground(new Color(248, 248, 248));

        JPanel due_date_cont = new JPanel(new GridLayout(2, 1));
        due_date_cont.setBackground(Color.white);
        due_date_cont.add(due_date_label);
        due_date_cont.add(due_date_tf);
        right_cont.add(due_date_cont);

        //<--------------------Save Button-------------------->
        JPanel btn_cont = new JPanel(new BorderLayout());
        btn_cont.setBackground(Color.white);
        btn_cont.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        SAVE_BUTTON.setFont(new Font("Inter", Font.BOLD, 18));
        SAVE_BUTTON.setForeground(Color.white);
        SAVE_BUTTON.setBackground(CADET_BLUE);
        btn_cont.add(SAVE_BUTTON, BorderLayout.CENTER);
        right_cont.add(btn_cont);

        //<--------------------Inner Container-------------------->
        JPanel container_panel = new JPanel();
        container_panel.setLayout(new GridLayout(0, 2));
        container_panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        container_panel.setBackground(Color.white);
        container_panel.add(left_cont);
        container_panel.add(right_cont);

        //<--------------------Main Container-------------------->
        JPanel main_panel = new JPanel();
        main_panel.setLayout(new GridLayout());
        main_panel.setOpaque(true);
        main_panel.setBackground(new Color(255, 255, 255, 10));

        //<--------------------Window Configuration-------------------->
        WINDOW.setContentPane(main_panel);
        WINDOW.setLayout(new BorderLayout());
        WINDOW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WINDOW.setLocationRelativeTo(null);
        WINDOW.setVisible(true);
        WINDOW.add(title_panel, BorderLayout.NORTH);
        WINDOW.add(container_panel, BorderLayout.CENTER);
        WINDOW.setResizable(false);

        //<--------------------Button Action-------------------->
            SAVE_BUTTON.addActionListener(e -> {
                String bt = book_title_tf.getText().trim();
                String ad = author_date_tf.getText().trim();
                long is = Long.parseLong(isbn_tf.getText().trim());
                String bn = borrower_name_tf.getText().trim();
                String af = affiliation_tf.getText().trim();
                String db = date_borrowed_tf.getText().trim();
                String dd = due_date_tf.getText().trim();
                FileHandling.Save(bt, ad, is, bn, af, db, dd);
//                book_title_tf.setText(null);
//                author_date_tf.setText(null);
//                isbn_tf.setText(null);
//                borrower_name_tf.setText(null);
//                affiliation_tf.setText(null);
//                date_borrowed_tf.setText(null);
//                due_date_tf.setText(null);
//                popup_container.setVisible(true);
//                delay();
//                popup_container.setVisible(false);
                WINDOW.dispose();
            });
    }

    private static void is_empty(JTextField tf) {
        if (tf.getText().isEmpty()) {
            tf.setBorder(new LineBorder(Color.red, 1));
        }
    }

    private static double get_screen_width() {
        return Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    }

    private static double get_screen_height() {
        return Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    }

    private static void delay() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}