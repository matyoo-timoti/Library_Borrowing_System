package com.lms.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainWindow {
    private static final JFrame WINDOW = new JFrame("PANEL TRIAL");
    private static final String ICON_PATH = System.getProperty("user.dir") + File.separator + "icons";
    private static final String DIRECTORY = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Library Borrowing System";

    private static void label_format(JLabel label_1, JLabel label_2) {
        label_1.setForeground(Color.black);
        label_1.setFont(new Font("Inter", Font.BOLD, 18));
        label_2.setForeground(Color.black);
        label_2.setFont(new Font("Inter", Font.PLAIN, 12));
    }

    private static void label_format(Component label) {
        label.setForeground(Color.black);
        label.setFont(new Font("Inter", Font.BOLD, 18));
    }

    private static void centered(JLabel label) {
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setAlignmentY(Component.CENTER_ALIGNMENT);
    }

    private static void panel_format(JPanel panel) {
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.white);
    }

    private static JPanel list_panel(String[] attrib) {
        JPanel col_one = new JPanel();
        col_one.setLayout(new BoxLayout(col_one, BoxLayout.Y_AXIS));
        panel_format(col_one);
        JLabel book_name = new JLabel(attrib[0]);
        JLabel author_date = new JLabel(attrib[1]);
        label_format(book_name, author_date);
        col_one.add(book_name);
        col_one.add(author_date);

        JPanel col_two = new JPanel();
        col_two.setLayout(new BoxLayout(col_two, BoxLayout.Y_AXIS));
        panel_format(col_two);
        JLabel borrower = new JLabel(attrib[3]);
        JLabel affiliation = new JLabel(attrib[4]);
        label_format(borrower, affiliation);
        centered(borrower);
        centered(affiliation);
        col_two.add(borrower);
        col_two.add(affiliation);

        JPanel col_three = new JPanel(new BorderLayout());
        panel_format(col_three);
        JLabel date_borrowed = new JLabel(attrib[5]);
        centered(date_borrowed);
        label_format(date_borrowed);
        col_three.add(date_borrowed, BorderLayout.CENTER);

        JPanel col_four = new JPanel();
        panel_format(col_four);
        JLabel due_date = new JLabel(attrib[6]);
        label_format(due_date);
        centered(due_date);
        col_four.add(due_date);

        JPanel col_five = new JPanel();
        panel_format(col_five);
        JCheckBox check_box = new JCheckBox();
        check_box.setAlignmentX(Component.CENTER_ALIGNMENT);
        check_box.setAlignmentY(Component.CENTER_ALIGNMENT);
        col_five.add(check_box);

        JPanel col_six = new JPanel(new GridLayout(1, 2));
        panel_format(col_six);
        JButton edit_btn = new JButton();
        ImageIcon edit_ico = new ImageIcon(ICON_PATH + File.separator + "edit_icon.png");
        edit_btn.setIcon(icon_resize(edit_ico, 34));
        edit_btn.setBorder(new EmptyBorder(5, 5, 5, 5));
        edit_btn.setBackground(Color.white);
        JButton delete_btn = new JButton("");
        delete_btn.setBackground(Color.white);
        ImageIcon delete_ico = new ImageIcon(ICON_PATH + File.separator + "delete_icon1.png");
        delete_btn.setIcon(icon_resize(delete_ico, 30));
        delete_btn.setBorder(new EmptyBorder(5, 5, 5, 5));
        col_six.add(edit_btn);
        col_six.add(delete_btn);

        JPanel container = new JPanel();
        container.setBackground(Color.white);
        container.setBorder(new LineBorder(new Color(225, 225, 225)));
        container.setPreferredSize(new Dimension(300, 75));
        container.setLayout(new FlowLayout());
        container.add(col_one);
        container.add(col_two);
        container.add(col_three);
        container.add(col_four);
        container.add(col_five);
        container.add(col_six);
        return container;
    }

    public static void list_files() throws IOException {
        File directoryPath = new File(DIRECTORY);
        File[] filesList = directoryPath.listFiles();
        System.out.println("List of files and directories in the specified directory:");
        for (File file : filesList) {
            System.out.println("File name: " + file.getName());
            System.out.println("File path: " + file.getAbsolutePath());
            System.out.println();
        }
    }

    private static void custom_scroll_pane(JScrollPane scroll_pane){
        scroll_pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll_pane.getVerticalScrollBar().setUnitIncrement(10);//10 is default
        scroll_pane.getVerticalScrollBar().setBackground(new Color(240, 240, 240));
//        scroll_pane.getViewport().setScrollMode(JViewport.BLIT_SCROLL_MODE);
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
    }

    public static void main(String[] args) {
        var file_handle = new FileHandling();
        String[] attrib = file_handle.retrieve("MC-11-12-2021-11-15-2021");

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        for (int i = 0; i<15; i++) {
            container.add(list_panel(attrib));
        }

        UIManager.put("ScrollBar.width", ((int) UIManager.get("ScrollBar.width") - 10));
        JScrollPane scroll_pane = new JScrollPane(container);
        custom_scroll_pane(scroll_pane);

        WINDOW.setLayout(new BorderLayout());
        WINDOW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WINDOW.setVisible(true);
//        WINDOW.add(container, BorderLayout.CENTER);
        WINDOW.add(scroll_pane);
        WINDOW.setSize(800, 500);
        WINDOW.setLocationRelativeTo(null);
    }

    private static ImageIcon icon_resize(ImageIcon icon, int size) {
        Image img = icon.getImage();
        Image new_img = img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        return icon = new ImageIcon(new_img);
    }
}
