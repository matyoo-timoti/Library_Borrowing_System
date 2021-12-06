package com.lms.gui;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DatePicker {
    int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
    int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
    JLabel l = new JLabel("", JLabel.CENTER);
    String day = "";
    JDialog datePickerDialog;
    JButton[] button = new JButton[49];

    public DatePicker(Container parent) {
        datePickerDialog = new JDialog();
        datePickerDialog.setModal(true);
        datePickerDialog.setResizable(false);
        String[] header = {"Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat"};
        JPanel p1 = new JPanel(new GridLayout(7, 7));
        p1.setPreferredSize(new Dimension(500, 170));
        for (int x = 0; x < button.length; x++) {
            final int selection = x;
            button[x] = new JButton();
            calendarBtnFormat(button[x]);
            if (x > 6){
                button[x].addActionListener(ae -> {
                    day = button[selection].getActionCommand();
                    datePickerDialog.dispose();
                });
                int finalX = x;
                button[x].addMouseListener(new MouseAdapter() {
                    public void mouseEntered(MouseEvent evt) {
                        button[finalX].setBackground(Color.yellow);
                    }
                    public void mouseExited(MouseEvent evt) {
                        button[finalX].setBackground(Color.white);
                    }
                });
            }
            if (x < 7) {
                button[x].setText(header[x]);
                button[x].setForeground(Color.black);
                button[x].setBackground(Color.decode("#E1FAF9"));
                button[x].setFont(new Font("Inter", Font.BOLD, 13));
                button[x].setBorder(BorderFactory.createCompoundBorder(
                        new MatteBorder(0, 0, 0, 1, Color.lightGray),
                        new MatteBorder(0, 0, 1, 0, Color.lightGray)));
            }
            p1.add(button[x]);
        }
        JPanel p2 = new JPanel(new GridLayout(1, 3));
        JButton previous = new JButton("◄"+"  Previous");
        prevNextBtnFormat(previous);
        previous.addActionListener(ae -> {
            month--;
            displayCalendar();
        });
        p2.add(previous);
        p2.add(l);
        JButton next = new JButton("Next  "+ "►");
        prevNextBtnFormat(next);
        next.addActionListener(ae -> {
            month++;
            displayCalendar();
        });
        p2.add(next);
        datePickerDialog.add(p1, BorderLayout.CENTER);
        datePickerDialog.add(p2, BorderLayout.SOUTH);
        datePickerDialog.pack();
        datePickerDialog.setLocationRelativeTo(parent);
        displayCalendar();
        datePickerDialog.setVisible(true);
    }

    private void calendarBtnFormat(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(Color.white);
//        Theme 1
//        button.setBorder(BorderFactory.createCompoundBorder(
//                BorderFactory.createEmptyBorder(2,2,2,2),
//                new MatteBorder(1, 1, 1, 1, Color.lightGray)));

//        Theme 2
        button.setBorder(BorderFactory.createCompoundBorder(
                new MatteBorder(0,0,1,0, Color.lightGray),
                new MatteBorder(0, 0, 0, 1, Color.lightGray)));
        button.setFont(new Font("Inter", Font.BOLD, 12));
    }

    private void prevNextBtnFormat(JButton button) {
        button.setFont(new Font("Inter", Font.BOLD, 12));
        button.setFocusable(false);
        button.setBackground(Color.decode("#58A4B0"));
        button.setForeground(Color.WHITE);
    }

    public void displayCalendar() {
        for (int x = 7; x < button.length; x++)
            button[x].setText("");
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "MMMM yyyy");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(year, month, 1);
        int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
        int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
        for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++)
            button[x].setText("" + day);
        l.setText(sdf.format(cal.getTime()));
        l.setFont(new Font("Inter", Font.BOLD, 15));
        datePickerDialog.setTitle("Date Picker");
    }

    public String getPickedDate() {
        if (day.equals(""))
            return day;
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "MM-dd-yyyy");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(year, month, Integer.parseInt(day));
        return sdf.format(cal.getTime());
    }
}
