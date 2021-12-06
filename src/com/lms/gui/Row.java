package com.lms.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
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
    private final JCheckBox checkBox = new JCheckBox("Returned");
    private final String bookName;

    Row(File file) {
        entryItem = new EntryItem(file);
        bookName = entryItem.getBook();
        checkBox.setSelected(entryItem.isReturned());
        checkBox.setFocusable(false);
        delButton.addActionListener(e -> remove());
        var mod = new ModifyEntryGUI(entryItem);
        editButton.addActionListener(e -> {
            if (!mod.isRunning()) {
                mod.showUI();
            }
            mod.focusable();
        });
        checkBox.addActionListener(e -> {
            entryItem.setReturned(checkBox.isSelected());
            moveToFolder();
        });
    }

    private void moveToFolder() {
//           Move file to the "Returned" folder if checkbox is checked.
        var returnedPath = pathReturned + File.separator + entryItem.getFile().getName();
        if (entryItem.isReturned()) {
            if (!Files.exists(Path.of(returnedPath))) {
                try {
                    File folder = new File(pathReturned.toString());
                    if (folder.exists() || folder.mkdirs()) {
                        Files.move(Path.of(entryItem.getFile().getAbsolutePath()), Path.of(pathReturned + File.separator + entryItem.getFile().getName()), StandardCopyOption.ATOMIC_MOVE);
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

        panelFormat(c5);
        checkBox.setBackground(Color.white);
        c5.add(checkBox, new GridBagConstraints());

        panelFormat(c6);
        editButton.setBackground(Color.white);
        delButton.setBackground(Color.white);
        buttonFormat(editButton, edIco);
        buttonFormat(delButton, delIco);
        iconResize(edIco, 40);
        c6.add(editButton, new GridBagConstraints());
        c6.add(createRigidArea(new Dimension(25, 0)));
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
