
/*
 * Copyright (c) 2020.
 *
 * Készült Táskai Dominik - AM6JEK által az IP alapú rendszerek és alkalmazások (IVB369MNMI) tantárgy keretein belül.
 */

package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.UnknownHostException;

import static javax.swing.JOptionPane.showMessageDialog;

public class View {
    private JFrame jFrame;
    private JPanel jPanel;
    private JButton jButtonSend;
    private JButton jButtonClose;
    private JButton jButtonLog;
    private JLabel jLabelFrom;
    private JLabel jLabelTo;
    private JLabel jLabelContent;
    private JLabel jLabelSubject;
    private JTextField jTextFieldFrom;
    private JTextField jTextFieldTo;
    private JTextField jTextFieldSubject;
    private JTextArea jTextAreaContent;
    private JFileChooser jFileChooser;
    private Logger log;

    private SmtpImplementation SMTP;

    public View() throws UnknownHostException {
        setBackground();
        setLabels();
        setTextfields();
        setTextArea();
        setButtons();
        showPanel(true);
        SMTP = new SmtpImplementation();
    }

    private void setBackground(){
        jFrame = new JFrame();
        jPanel = new JPanel();
        jFrame.setSize(280,320);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setTitle("IPA Mail");
        jFrame.add(jPanel);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jPanel.setLayout(null);
    }

    private void showPanel(boolean bool){
        jFrame.setVisible(bool);
    }

    private void setLabels(){
        jLabelFrom = new JLabel("Feladó:");
        jLabelFrom.setBounds(10,20,80,25);
        jPanel.add(jLabelFrom);

        jLabelTo = new JLabel("Címzett:");
        jLabelTo.setBounds(10,50,80,25);
        jPanel.add(jLabelTo);

        jLabelSubject = new JLabel("Tárgy:");
        jLabelSubject.setBounds(10,80,80,25);
        jPanel.add(jLabelSubject);

        jLabelContent = new JLabel("Tartalom:");
        jLabelContent.setBounds(10,110,80,25);
        jPanel.add(jLabelContent);


    }

    private void setTextfields(){
        jTextFieldFrom = new JTextField(20);
        jTextFieldFrom.setBounds(80,20,165,25);
        jPanel.add(jTextFieldFrom);

        jTextFieldTo = new JTextField(20);
        jTextFieldTo.setBounds(80,50,165,25);
        jPanel.add(jTextFieldTo);

        jTextFieldSubject = new JTextField(20);
        jTextFieldSubject.setBounds(80,80,165,25);
        jPanel.add(jTextFieldSubject);
    }

    private void setTextArea(){
        jTextAreaContent = new JTextArea();
        jTextAreaContent.setBounds(10,140,245,100);
        jPanel.add(jTextAreaContent);
    }

    private void setButtons(){
        jButtonSend = new JButton("Küldés");
        jButtonSend.setBounds(10,250,80,20);
        jPanel.add(jButtonSend);

        jButtonSend.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SMTP.setFrom(jTextFieldFrom.getText());
                SMTP.setTo(jTextFieldTo.getText());
                SMTP.setSubject(jTextFieldSubject.getText());
                SMTP.setContent(jTextAreaContent.getText());
                try {
                    if(SMTP.send()==1){
                        showMessageDialog(null, "Üzenet elküldve!");
                    }else showMessageDialog(null, "Hiba az üzenet elküldésekor!");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        jButtonClose = new JButton("Bezárás");
        jButtonClose.setBounds(175,250,80,20);
        jPanel.add(jButtonClose);

        jButtonClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(1);
            }
        });

        jButtonLog = new JButton("Log");
        jButtonLog.setBounds(93,250,80,20);
        jPanel.add(jButtonLog);

        jButtonLog.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SMTP.getLog().showLog(false);
            }
        });
    }


}
