package com.lms.gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;

class AddEntryDialog {
    private final JDialog addEntryDialog;
    private final JButton saveBtn = new JButton("Save");
    private static final Color CADET_BLUE = Color.decode("#58A4B0");
    private final JTextField bookTf = new JTextField();
    private final JTextField authorTf = new JTextField();
    private final JTextField isbnTf = new JTextField();
    private final JTextField borrowerTf = new JTextField();
    private final JTextField affiliationTf = new JTextField();
    private final JTextField dateBTf = new JTextField();
    private final JTextField dueDTf = new JTextField();
    private final JButton datePicker1 = new JButton();
    private final JButton datePicker2 = new JButton();

    private final Path PATH = Path.of(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Library Borrowing System" + File.separator + "Unreturned");

    public AddEntryDialog(JFrame parent) {
        addEntryDialog = new JDialog();
        addEntryDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addEntryDialog.setModal(true);
        addEntryDialog.setSize((int) (get_screen_width() / 2.7), (int) (get_screen_height() / 2.5));
        addEntryDialog.setLocationRelativeTo(parent);
        var icon = new ImageIcon("C:\\Users\\Lenovo\\IdeaProjects\\LMS\\src\\com\\lms\\gui\\icon.png").getImage();
        addEntryDialog.setIconImage(icon);
        showUI();
        saveBtn.addActionListener(e -> saveButtonAction());
        datePicker1.addActionListener(ae -> dateBTf.setText(new DatePicker(addEntryDialog).getPickedDate()));
        datePicker2.addActionListener(ae -> dueDTf.setText(new DatePicker(addEntryDialog).getPickedDate()));
        addEntryDialog.setVisible(true);
    }

    public void showUI() {
        var bookLabel = new JLabel("Title of the Book:");
        var authorLabel = new JLabel("Author:");
        var isbnLabel = new JLabel("ISBN");
        var dateBorrowedLabel = new JLabel("Date Borrowed:");
        var borrowedLabel = new JLabel("Borrower Name:");
        var affiliationLabel = new JLabel("Affiliation:");
        var dueDateLabel = new JLabel("Due Date:");
        var bookCont = new JPanel();
        var authorCont = new JPanel();
        var isbnCont = new JPanel();
        var dateBorrowedCont = new JPanel();
        var borrowerCont = new JPanel(new GridLayout(2, 1));
        var affiliationCont = new JPanel(new GridLayout(2, 1));
        var dueDateCont = new JPanel(new GridLayout(2, 1));

//        Placeholder
        placeholder(bookTf, "Enter Book Title");
        placeholder(isbnTf, "Must have at least 10 digits");
        placeholder(authorTf, "Last Name, First Name");
        placeholder(borrowerTf, "Last Name, First Name");
        placeholder(affiliationTf, "Department/Course/Year/Block");
        placeholder(dateBTf, "MM-DD-YYYY");
        placeholder(dueDTf, "MM-DD-YYYY");

//        Title Container
        var title = new JLabel("Add New Entry", JLabel.LEFT);
        title.setFont(new Font("Inter", Font.BOLD, 30));
        title.setForeground(Color.WHITE);

        var titleContainer = new JPanel();
        titleContainer.setLayout(new BorderLayout());
        titleContainer.setBackground(CADET_BLUE);
        titleContainer.setSize(-1, 200);
        titleContainer.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        titleContainer.add(title, BorderLayout.LINE_START);

//        Left Side
        var left = new JPanel();
        left.setLayout(new GridLayout(4, 1));
        left.setBackground(Color.white);
        left.setSize(100, -1);
        left.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 10));
        left.add(panelFormat(bookCont, bookLabel, bookTf));
        left.add(panelFormat(authorCont, authorLabel, authorTf));
        left.add(panelFormat(isbnCont, isbnLabel, isbnTf));
        left.add(panelFormat(dateBorrowedCont, dateBorrowedLabel, dateBTf, datePicker1));

//        Right Side
        var right = new JPanel(new GridLayout(4, 1));
        right.setBackground(Color.white);
        right.setSize(100, -1);
        right.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 20));
        right.add(panelFormat(borrowerCont, borrowedLabel, borrowerTf));
        right.add(panelFormat(affiliationCont, affiliationLabel, affiliationTf));
        right.add(panelFormat(dueDateCont, dueDateLabel, dueDTf, datePicker2));

//        Button
        var saveBtnContainer = new JPanel(new BorderLayout());
        saveBtnContainer.setBackground(Color.white);
        saveBtnContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        saveBtn.setFont(new Font("Inter", Font.BOLD, 18));
        saveBtn.setForeground(Color.white);
        saveBtn.setBackground(CADET_BLUE);
        saveBtnContainer.add(saveBtn, BorderLayout.CENTER);
        right.add(saveBtnContainer);

//        Container for left and right container
        var innerCont = new JPanel();
        innerCont.setLayout(new GridLayout(0, 2));
        innerCont.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        innerCont.setBackground(Color.white);
        innerCont.add(left);
        innerCont.add(right);

        addEntryDialog.setTitle("Library Borrowing System | Add New Entry");
        addEntryDialog.setResizable(false);
        addEntryDialog.add(titleContainer, BorderLayout.NORTH);
        addEntryDialog.add(innerCont, BorderLayout.CENTER);
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
            JOptionPane.showMessageDialog(addEntryDialog, "Fields can't be empty!", "Error", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(addEntryDialog, "Entry Saved", "Notification", JOptionPane.PLAIN_MESSAGE);
            addEntryDialog.dispose();
        }
    }

    private static void placeholder(JTextField tf, String text) {
        var tx = new TextPrompt(text, tf);
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

    private static JPanel panelFormat(JPanel panel, JLabel label, JTextField field, JButton button) {
        panel.setLayout(new GridLayout(2, 1));
        panel.setBackground(Color.white);
        var tfBtnCont = new JPanel(new BorderLayout(5, 0));
        tfBtnCont.setBackground(panel.getBackground());
        tfBtnCont.add(textFieldFormat(field), BorderLayout.CENTER);
        tfBtnCont.add(button, BorderLayout.LINE_END);
        ImageIcon btnIco = new ImageIcon("C:\\Users\\Lenovo\\IdeaProjects\\LMS\\src\\com\\lms\\gui\\calendar_icon.png");
        button.setIcon(btnIco);
//        button.setFont(new Font("Inter", Font.BOLD, 14));
        button.setBackground(CADET_BLUE);
        button.setFocusable(false);
        button.setPreferredSize(new Dimension(30, 0));
        button.setToolTipText("Date Picker");
        UIManager.put("ToolTip.background", Color.white);
        UIManager.put("ToolTip.foreground", Color.black);
        UIManager.put("ToolTip.font", new Font("Inter", Font.PLAIN, 12));
        panel.add(labelFormat(label));
        panel.add(tfBtnCont);
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