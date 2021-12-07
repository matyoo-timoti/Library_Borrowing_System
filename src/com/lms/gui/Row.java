package com.lms.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import static javax.swing.Box.createRigidArea;

public class Row {
    private final String iconDirectory = System.getProperty("user.dir") + File.separator + "icons";
    private static final Path pathDiscarded = Path.of(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Library Borrowing System" + File.separator + "Discarded");
    private static final Path pathReturned = Path.of(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Library Borrowing System" + File.separator + "Returned");
    private static final Path pathUnreturned = Path.of(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Library Borrowing System" + File.separator + "Unreturned");
    private final EntryItem entryItem;
    private final JPanel container = new JPanel();
    private final JButton editButton = new JButton();
    private final JButton delButton = new JButton();
    private final JComboBox<String> status;
    private final String bookName;

    Row(File file) {
        entryItem = new EntryItem(file);
        bookName = entryItem.getBook();

        /*If the file is stored in the returned folder
         * sets the entry as returned, otherwise unreturned. */
        var unreturnedPath = pathUnreturned + File.separator + entryItem.getFile().getName();
        entryItem.setReturned(!Files.exists(Path.of(unreturnedPath)));

        String[] stats = {"Unreturned", "Returned"};
        status = new JComboBox<>(stats);
        status.setFocusable(false);

        if (entryItem.isReturned()) {
            status.setSelectedIndex(1);
        } else status.setSelectedIndex(0);

        delButton.addActionListener(e -> showDelDialog());

        editButton.addActionListener(e -> new ModifyEntryGUI(entryItem));

        status.addActionListener(e -> {
            entryItem.setReturned((status.getSelectedIndex() == 1));
            statChangeAction();
        });

        UIManager.put("ToolTip.background", Color.white);
        UIManager.put("ToolTip.foreground", Color.black);
        UIManager.put("ToolTip.font", new Font("Inter", Font.PLAIN, 12));
    }

    private void showDelDialog() {
        var msg = "<html>" + "Are you sure to remove this Entry?" + "<br>" +
                "Book Title   :" + entryItem.getBook() + "<br>" +
                "Author       : " + entryItem.getAuthor() + "<br>" +
                "Borrower     : " + entryItem.getBorrower() + "<br>" +
                "Affiliation  : " + entryItem.getAffiliation() + "<br>" +
                "Date Borrowed: " + entryItem.getDateBorrowed() + "<br>" +
                "Due Date     : " + entryItem.getDueDate() + "<br>"
                + "</html>";

        var delLabel = new JLabel("Entry removed successfully!");
        delLabel.setFont(new Font("Inter", Font.BOLD, 13));
        var msgLabel = new JLabel(msg);
        msgLabel.setFont(new Font("Inter", Font.PLAIN, 15));
        int reply = JOptionPane.showConfirmDialog(null, msgLabel, "Confirm", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, delLabel, "Delete Successful", JOptionPane.PLAIN_MESSAGE);
                remove();
        }
    }

    public JPanel showGUI() {
        JLabel bookNameLabel = new JLabel(entryItem.getBook());
        JLabel authorLabel = new JLabel(entryItem.getAuthor());
        JLabel borrowerLabel = new JLabel(entryItem.getBorrower());
        JLabel affiliationLabel = new JLabel(entryItem.getAffiliation());
        JLabel dateBorrowedLabel = new JLabel(entryItem.getDateBorrowed());
        JLabel dueDateLabel = new JLabel(entryItem.getDueDate());

        JPanel c1 = new JPanel(new BorderLayout());
        JPanel c2 = new JPanel(new BorderLayout());
        JPanel c3 = new JPanel(new GridBagLayout());
        JPanel c4 = new JPanel(new GridBagLayout());
        JPanel c5 = new JPanel(new GridBagLayout());
        JPanel c6 = new JPanel(new GridBagLayout());
        ImageIcon edIco = new ImageIcon(iconDirectory + File.separator + "edit_icon.png");
        ImageIcon delIco = new ImageIcon(iconDirectory + File.separator + "delete_icon1.png");

        panelFormat(c1);
        labelFormat(bookNameLabel, authorLabel);
        c1.add(bookNameLabel, BorderLayout.CENTER);
        c1.add(authorLabel, BorderLayout.SOUTH);

        panelFormat(c2);
        labelFormat(borrowerLabel, affiliationLabel);
        c2.add(borrowerLabel, BorderLayout.CENTER);
        c2.add(affiliationLabel, BorderLayout.SOUTH);

        panelFormat(c3);
        labelFormat(dateBorrowedLabel);
        c3.add(dateBorrowedLabel, new GridBagConstraints());

        panelFormat(c4);
        labelFormat(dueDateLabel);
        c4.add(dueDateLabel, new GridBagConstraints());

        //ComboBox for status of entry
        //Remove borders of comboBox
        status.setFont(new Font("Inter", Font.BOLD, 18));
        status.setForeground(Color.BLACK);
        status.setEditable(false);
        status.setFocusable(false);
        status.setToolTipText("Click to set status");
        status.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                return new JButton() {
                    @Override
                    public int getWidth() {
                        return 0;
                    }
                };
            }
        });

        panelFormat(c5);
        status.setBackground(Color.white);
        c5.add(status, new GridBagConstraints());

        panelFormat(c6);
        editButton.setBackground(Color.white);
        delButton.setBackground(Color.white);
        buttonFormat(editButton, edIco);
        buttonFormat(delButton, delIco);
        iconResize(edIco, 40);
        c6.add(editButton, new GridBagConstraints());
        c6.add(createRigidArea(new Dimension(20, 0)));
        c6.add(delButton, new GridBagConstraints());

        var lineBottomPanel = new JPanel(new GridLayout(1, 1));
        lineBottomPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
        container.setLayout(new GridLayout(1, 6));
        container.setBackground(Color.white);
        container.setBorder(new EmptyBorder(0, 10, 0, 0));
        container.add(c1);
        container.add(c2);
        container.add(c3);
        container.add(c4);
        container.add(c5);
        container.add(c6);
        lineBottomPanel.add(container);
        lineBottomPanel.setPreferredSize(new Dimension(700, 60));
        lineBottomPanel.setMaximumSize(new Dimension(2000, 70));
        return lineBottomPanel;
    }

    private void remove() {
//        Doesn't delete the file, instead moves it to another folder called 'Discarded'.
        try {
            File folder = new File(pathDiscarded.toString());
            if (folder.exists() || folder.mkdirs()) {
                Files.move(Path.of(entryItem.getFile().getAbsolutePath()), Path.of(pathDiscarded + File.separator + entryItem.getFile().getName()), StandardCopyOption.ATOMIC_MOVE);
            } else {
                JOptionPane.showMessageDialog(null, "ERROR: Folder does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println("Folder Not created");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void statChangeAction() {
//           Move file to the "Returned" folder if checkbox is checked.
        var returnedPath = pathReturned + File.separator + entryItem.getFile().getName();
        if (entryItem.isReturned()) {
            if (!Files.exists(Path.of(returnedPath))) {
                try {
                    File folder = new File(pathReturned.toString());
                    if (folder.exists() || folder.mkdirs()) {
                        Files.move(Path.of(entryItem.getFile().getAbsolutePath()), Path.of(pathReturned + File.separator + entryItem.getFile().getName()), StandardCopyOption.ATOMIC_MOVE);
                        System.out.println("File moved!");
                        return;
                    } else {
                        JOptionPane.showMessageDialog(null, "ERROR: Folder does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Folder Not created");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        var unreturnedPath = pathUnreturned + File.separator + entryItem.getFile().getName();
        if (!entryItem.isReturned()) {
            if (!Files.exists(Path.of(unreturnedPath))) {
                try {
                    File folder = new File(pathUnreturned.toString());
                    if (folder.exists() || folder.mkdirs()) {
                        Files.move(Path.of(entryItem.getFile().getAbsolutePath()), Path.of(pathUnreturned + File.separator + entryItem.getFile().getName()), StandardCopyOption.ATOMIC_MOVE);
                        System.out.println("File moved!");
                    } else {
                        JOptionPane.showMessageDialog(null, "ERROR: Folder does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Folder Not created");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void labelFormat(Component label) {
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Inter", Font.BOLD, 18));
    }

    private static void labelFormat(JLabel label1, JLabel label2) {
        label1.setForeground(Color.black);
        label1.setFont(new Font("Inter", Font.BOLD, 18));
        label2.setForeground(Color.black);
        label2.setFont(new Font("Inter", Font.PLAIN, 12));
    }

    private static void panelFormat(JPanel panel) {
        panel.setBorder(new EmptyBorder(10, 20, 10, 20));
        panel.setBackground(Color.white);
    }

    private static void buttonFormat(JButton button, ImageIcon icon) {
        button.setIcon(iconResize(icon, 34));
        button.setPreferredSize(new Dimension(34, 34));
        button.setBorder(new EmptyBorder(0, 0, 0, 0));
    }

    private static ImageIcon iconResize(ImageIcon icon, int size) {
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }

    public String getBookName() {
        return bookName;
    }

}
