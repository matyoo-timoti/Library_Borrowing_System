package com.lms.gui;

import javax.swing.*;
import java.awt.*;

public class ModifyEntryGUI {
    private final JDialog modEntryDialog;
    private final JButton saveBtn = new JButton("Save");
    private static final Color CADET_BLUE = Color.decode("#58A4B0");
    private final EntryItem entryItem;
    private final JTextField bookTf;
    private final JTextField authorTf;
    private final JTextField isbnTf;
    private final JTextField borrowerTF;
    private final JTextField affiliationTf;
    private final JTextField dateBTf;
    private final JTextField dueDTf;
    private final JButton datePicker1 = new JButton();
    private final JButton datePicker2 = new JButton();

    public ModifyEntryGUI(EntryItem entry) {
        modEntryDialog = new JDialog();
        modEntryDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        modEntryDialog.setModal(true);
        modEntryDialog.setSize((int) (get_screen_width() / 2.7), (int) (get_screen_height() / 2.5));
        modEntryDialog.setLocationRelativeTo(null);
        var icon = new ImageIcon("C:\\Users\\Lenovo\\IdeaProjects\\LMS\\src\\com\\lms\\gui\\icon.png").getImage();
        modEntryDialog.setIconImage(icon);

        entryItem = entry;
        modEntryDialog.setSize((int) (get_screen_width() / 2.7), (int) (get_screen_height() / 2.5));
        bookTf = new JTextField(entryItem.getBook());
        authorTf = new JTextField(entryItem.getAuthor());
        isbnTf = new JTextField(entryItem.getIsbn());
        borrowerTF = new JTextField(entryItem.getBorrower());
        affiliationTf = new JTextField(entryItem.getAffiliation());
        dateBTf = new JTextField(entryItem.getDateBorrowed());
        dueDTf = new JTextField(entryItem.getDueDate());

        saveBtn.addActionListener(e -> saveButtonAction());
        datePicker1.addActionListener(ae -> dateBTf.setText(new DatePicker(modEntryDialog).getPickedDate()));
        datePicker2.addActionListener(ae -> dueDTf.setText(new DatePicker(modEntryDialog).getPickedDate()));

        showUI();
        modEntryDialog.setVisible(true);
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

        //Placeholder
        placeholder(bookTf, "Enter Book Title");
        placeholder(isbnTf, "Must have at least 10 digits");
        placeholder(authorTf, "Last Name, First Name");
        placeholder(borrowerTF, "Last Name, First Name");
        placeholder(affiliationTf, "Department/Course/Year/Block");
        placeholder(dateBTf, "MM-DD-YYYY");
        placeholder(dueDTf, "MM-DD-YYYY");

        //Title Container
        JLabel title = new JLabel("Modify Entry", JLabel.LEFT);
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
        left.add(panelFormat(dateBorrowedCont, dateBorrowedLabel, dateBTf, datePicker1));

//        Right Side
        JPanel right = new JPanel(new GridLayout(4, 1));
        right.setBackground(Color.white);
        right.setSize(100, -1);
        right.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 20));
        right.add(panelFormat(borrowerCont, borrowedLabel, borrowerTF));
        right.add(panelFormat(affiliationCont, affiliationLabel, affiliationTf));
        right.add(panelFormat(dueDateCont, dueDateLabel, dueDTf, datePicker2));


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
        modEntryDialog.setTitle("Library Borrowing System | Modify Entry");
        modEntryDialog.setResizable(false);
        modEntryDialog.add(titleContainer, BorderLayout.NORTH);
        modEntryDialog.add(innerCont, BorderLayout.CENTER);
    }

    private void saveButtonAction() {
        if (isFieldsEmpty()) {
            JOptionPane.showMessageDialog(modEntryDialog, "Fields can't be empty!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            var updateFile = new UpdateFile(entryItem.getFile().getAbsoluteFile());
            entryItem.setBook(getText(bookTf));
            entryItem.setAuthor(getText(authorTf));
            entryItem.setIsbn(getText(isbnTf));
            entryItem.setBorrower(getText(borrowerTF));
            entryItem.setAffiliation(getText(affiliationTf));
            entryItem.setDateBorrowed(getText(dateBTf));
            entryItem.setDueDate(getText(dueDTf));
            updateFile.clear();
            updateFile.writeln(entryItem.getBook());
            updateFile.writeln(entryItem.getAuthor());
            updateFile.writeln(entryItem.getIsbn());
            updateFile.writeln(entryItem.getBorrower());
            updateFile.writeln(entryItem.getAffiliation());
            updateFile.writeln(entryItem.getDateBorrowed());
            updateFile.writeln(entryItem.getDueDate());
            JOptionPane.showMessageDialog(modEntryDialog, "Entry Saved!", "Notification", JOptionPane.PLAIN_MESSAGE);
            modEntryDialog.dispose();
        }
    }

    private boolean isFieldsEmpty() {
        return (bookTf.getText().isEmpty() || authorTf.getText().isEmpty() || isbnTf.getText().isEmpty() ||
                dateBTf.getText().isEmpty() || borrowerTF.getText().isEmpty() || affiliationTf.getText().isEmpty() ||
                dueDTf.getText().isEmpty());
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


    private static void placeholder(JTextField tf, String text) {
        TextPrompt tx = new TextPrompt(text, tf);
        tx.setForeground(Color.GRAY);
//        tx.setForeground(Color.decode("#232323"));
        tx.setFont(new Font("Inter Medium", Font.PLAIN, 14));
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
