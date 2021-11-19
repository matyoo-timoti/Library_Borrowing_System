package com.lms.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.io.File;

import static javax.swing.Box.createRigidArea;

public class MainWindow {
    private static final JFrame WINDOW = new JFrame("PANEL TRIAL");
    private static final String ICON_PATH = System.getProperty("user.dir") + File.separator + "icons";
    private static final JButton BTN_ADD_NEW = new JButton("Add New Entry");
    private static final JButton BTN_SORT = new JButton("Sort");

    public static void main(String[] args) {
        MainWindow.main_window();
        BTN_ADD_NEW.addActionListener(e -> {
            AddNewEntry.new_entry_window();
        });
    }

    private static void label_format(JLabel label_1, JLabel label_2) {
        label_1.setForeground(Color.black);
        label_1.setFont(new Font("Inter", Font.BOLD, 18));
        label_2.setForeground(Color.black);
        label_2.setFont(new Font("Inter", Font.PLAIN, 12));
    }

    private static void label_format(Component label, Color font_color, int font_size) {
        label.setForeground(font_color);
        label.setFont(new Font("Inter", Font.BOLD, font_size));
    }

    private static void panel_format(JPanel panel) {
        panel.setBorder(new EmptyBorder(10, 20, 10, 20));
        panel.setBackground(Color.white);
    }

    private static void button_format(JButton button, ImageIcon icon) {
        button.setIcon(icon_resize(icon, 34));
        button.setPreferredSize(new Dimension(34, 34));
    }

    private static JPanel show_in_rows(String[] attrib) {
        //Book Name and Author
        JPanel c1 = new JPanel(new BorderLayout());
        panel_format(c1);
        JLabel book_name = new JLabel(attrib[0]);
        JLabel author = new JLabel(attrib[1]);
        label_format(book_name, author);
        c1.add(book_name, BorderLayout.CENTER);
        c1.add(author, BorderLayout.SOUTH);

        JPanel c2 = new JPanel(new BorderLayout());
        panel_format(c2);
        JLabel borrower = new JLabel(attrib[3]);
        JLabel affiliation = new JLabel(attrib[4]);
        label_format(borrower, affiliation);
        c2.add(borrower, BorderLayout.CENTER);
        c2.add(affiliation, BorderLayout.SOUTH);

        JPanel c3 = new JPanel(new GridBagLayout());
        panel_format(c3);
        JLabel date_borrowed = new JLabel(attrib[5]);
        label_format(date_borrowed, Color.BLACK, 18);
        c3.add(date_borrowed, new GridBagConstraints());

        JPanel c4 = new JPanel(new GridBagLayout());
        panel_format(c4);
        JLabel due_date = new JLabel(attrib[6]);
        label_format(due_date, Color.BLACK, 18);
        c4.add(due_date, new GridBagConstraints());

        JPanel c5 = new JPanel(new GridBagLayout());
        panel_format(c5);
        JCheckBox check_box = new JCheckBox("Returned");
        check_box.setBackground(Color.white);
        c5.add(check_box, new GridBagConstraints());

        JPanel c6 = new JPanel(new GridBagLayout());
        panel_format(c6);
        JButton ed_btn = new JButton();
        ImageIcon ed_ico = new ImageIcon(ICON_PATH + File.separator + "edit_icon.png");
        button_format(ed_btn, ed_ico);
        ed_btn.setBackground(Color.white);
        JButton del_btn = new JButton();
        del_btn.setBackground(Color.white);
        ImageIcon del_ico = new ImageIcon(ICON_PATH + File.separator + "delete_icon1.png");
        button_format(del_btn, del_ico);
        c6.add(ed_btn, new GridBagConstraints());
        c6.add(createRigidArea(new Dimension(25,0)));
        c6.add(del_btn, new GridBagConstraints());

        JPanel container = new JPanel();
        container.setBackground(Color.white);
        container.setBorder(new EmptyBorder(0, 10, 0, 10));
        container.setLayout(new GridLayout(1, 6));
        container.setPreferredSize(new Dimension(700, 60));
        container.setMaximumSize(new Dimension(2000, 70));
        container.add(c1);
        container.add(c2);
        container.add(c3);
        container.add(c4);
        container.add(c5);
        container.add(c6);
        return container;
    }

    private static void custom_scroll_pane(JScrollPane scroll_pane) {
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

    public static void main_window() {
        var crud = new FileHandling();
        String[] entry_contents = crud.retrieve("Violet Evergarden_Matthew Cabarle_11-19-2021_12-01-2021");
        String[] entry_contents1 = crud.retrieve("MC-11-12-2021-11-15-2021");
        String[] entry_contents2 = crud.retrieve("MM-11-15-2010-12-12-2011");

        JPanel cont_north = new JPanel();
        cont_north.setLayout(new BoxLayout(cont_north, BoxLayout.Y_AXIS));

        JPanel cont_title = new JPanel(new BorderLayout());
        cont_title.setBorder(new EmptyBorder(20, 15, 20, 10));
        cont_title.setBackground(new Color(43, 48, 58));

        JLabel label_title = new JLabel("Library Borrowing System");
        label_format(label_title, Color.white, 45);
        cont_title.add(label_title, BorderLayout.LINE_START);
        cont_north.add(cont_title);

        JPanel cont_north_middle = new JPanel();
        cont_north_middle.setLayout(new BoxLayout(cont_north_middle, BoxLayout.Y_AXIS));

        JPanel cont_north_middle_top = new JPanel(new BorderLayout());
        cont_north_middle_top.setBorder(new EmptyBorder(10, 10, 10, 10));
        cont_north_middle_top.add(BTN_ADD_NEW, BorderLayout.LINE_START);
        cont_north_middle_top.add(BTN_SORT, BorderLayout.LINE_END);

        JPanel cont_north_middle_bot = new JPanel(new GridBagLayout());
        cont_north_middle_bot.setLayout(new GridLayout(0, 6));
        cont_north_middle_bot.setBorder(new EmptyBorder(0, 11, 0, 18));
        cont_north_middle_bot.setBackground(Color.WHITE);

        JPanel cont_label0 = col_name("Book");
        JPanel cont_label1 = col_name("Borrower");
        JPanel cont_label2 = col_name("Date Borrowed");
        JPanel cont_label3 = col_name("Due Date");
        JPanel cont_label4 = col_name("Returned");
        JPanel cont_label5 = col_name("Actions");
        cont_north_middle_bot.add(cont_label0);
        cont_north_middle_bot.add(cont_label1);
        cont_north_middle_bot.add(cont_label2);
        cont_north_middle_bot.add(cont_label3);
        cont_north_middle_bot.add(cont_label4);
        cont_north_middle_bot.add(cont_label5);
        cont_north_middle.add(cont_north_middle_top);
        cont_north_middle.add(cont_north_middle_bot);
        cont_north.add(cont_north_middle);

        JSeparator sep = new JSeparator();
        sep.setOrientation(1);
        JPanel cont_rows = new JPanel();
        cont_rows.setLayout(new BoxLayout(cont_rows, BoxLayout.Y_AXIS));
        for (int i = 0; i < 30; i++) {
            cont_rows.add(show_in_rows(entry_contents));
            cont_rows.add(show_in_rows(entry_contents1));
            cont_rows.add(show_in_rows(entry_contents2));
            cont_rows.add(sep);
        }

        UIManager.put("ScrollBar.width", ((int) UIManager.get("ScrollBar.width") - 10));
        JScrollPane scroll_pane = new JScrollPane(cont_rows);
        custom_scroll_pane(scroll_pane);

        WINDOW.setLayout(new BorderLayout());
        WINDOW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WINDOW.add(cont_north, BorderLayout.NORTH);
        WINDOW.add(scroll_pane, BorderLayout.CENTER);
        WINDOW.setMinimumSize(new Dimension(1000, 500));
        WINDOW.setSize(900,500);
        WINDOW.setVisible(true);
        WINDOW.setLocationRelativeTo(null);
    }

    private static JPanel col_name(String text) {
        JPanel cont = new JPanel();
        panel_format(cont);
        JLabel label = new JLabel(text);
        label_format(label, Color.BLACK, 18);
        cont.add(label);
        return cont;
    }

    private static ImageIcon icon_resize(ImageIcon icon, int size) {
        Image img = icon.getImage();
        Image new_img = img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        return new ImageIcon(new_img);
    }
}
