/*
 * Copyright (c) 2020.
 *
 * Készült Táskai Dominik - AM6JEK által az IP alapú rendszerek és alkalmazások (IVB369MNMI) tantárgy keretein belül.
 */

package com.company;

import javax.swing.*;
import java.net.*;
import javax.swing.text.DefaultCaret;

public class Logger {
    private JFrame jFrame;
    private JPanel jPanel;
    private JTextArea jTextAreaLog;


    public Logger(){
        setElements();
    }

    private void setElements(){
        jFrame = new JFrame();
        jPanel = new JPanel();
        jTextAreaLog = new JTextArea();
        jFrame.setSize(535,300);
        jFrame.setTitle("Mail Log");
        jFrame.add(jPanel);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jPanel.setLayout(null);
        jTextAreaLog.setSize(320,250);
        jFrame.add(jTextAreaLog);

    }

    public void showLog(boolean var){
        if(jFrame.isVisible()==true){
            jFrame.setVisible(false);
        }else{
            jFrame.setVisible(true);
        }
    }

    public void addText(String text){
        jTextAreaLog.append(text);
    }

    public void clearLog(){
        jTextAreaLog.setText("");
    }
}
