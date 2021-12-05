package com.lms.gui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

public class ScrollPane {
    private final JPanel contRows = new JPanel();
    private final JScrollPane scrollPane;

    ScrollPane() {
        UIManager.put("ScrollBar.width", ((int) UIManager.get("ScrollBar.width") - 10));
        contRows.setLayout(new BoxLayout(contRows, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(contRows);
        scrollPane.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.DARK_GRAY));
        customScrollBar(scrollPane);
    }

    public void add(Component component) {
        contRows.add(component);
    }

    public Component getScrollPane() {
        return scrollPane;
    }

    public void refresh() {
        contRows.revalidate();
        contRows.repaint();
    }

    public void removeAll() {
        contRows.removeAll();
    }

    private static void customScrollBar(JScrollPane scroll_pane) {
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
                this.thumbColor = Color.LIGHT_GRAY;
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
                this.thumbColor = Color.LIGHT_GRAY;
            }
        });
    }

}
