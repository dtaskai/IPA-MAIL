
/*
 * Copyright (c) 2020.
 *
 * Készült Táskai Dominik - AM6JEK által az IP alapú rendszerek és alkalmazások (IVB369MNMI) tantárgy keretein belül.
 */

package com.company;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SmtpImplementation {
    private final int PORT = 25;
    private InetAddress mailHost;
    private InetAddress localHost;

    private String from;
    private String to;
    private String content;
    private String subject;

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    private InetAddress host;

    private BufferedReader bufferedReaderIn;
    private PrintWriter printWriterOut;

    private Logger log;

    public Logger getLog() {
        return log;
    }

    public SmtpImplementation() throws UnknownHostException {
        mailHost = InetAddress.getByName("127.0.0.1");
        localHost = InetAddress.getLocalHost();
        log = new Logger();
        log.clearLog();
    }


    public int send() throws IOException {
        Socket socket;
        BufferedReader bufferedReader;
        InputStream inputStream;
        OutputStream outputStream;


        socket = new Socket(mailHost,PORT);
        if(socket == null){
            return -1;
        }

        log.clearLog();

        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
        bufferedReaderIn = new BufferedReader(new InputStreamReader(inputStream));
        printWriterOut = new PrintWriter(new OutputStreamWriter(outputStream), true);

        String initialMsg = bufferedReaderIn.readLine();
        System.out.println(initialMsg);
        log.addText("S: "+initialMsg+"\n");
        System.out.println("HELO "+localHost.getHostName());
        log.addText("C: HELO "+localHost.getHostName()+"\n");
        printWriterOut.println("HELO "+localHost.getHostName());
        String welcomeMsg = bufferedReaderIn.readLine();
        System.out.println(welcomeMsg);
        log.addText("S: "+welcomeMsg+"\n");
        System.out.println("MAIL From:<"+from+">");
        log.addText("C: MAIL From:<"+from+">"+"\n");
        printWriterOut.println("MAIL From:<"+from+">");
        String senderMsg = bufferedReaderIn.readLine();
        System.out.println(senderMsg);
        log.addText("S: "+senderMsg+"\n");
        System.out.println("RCPT TO:<"+to+">");
        log.addText("C: RCPT TO:<"+to+">"+"\n");
        printWriterOut.println("RCPT TO:<"+to+">");
        String recipientMsg = bufferedReaderIn.readLine();
        System.out.println(recipientMsg);
        log.addText("S: "+recipientMsg+"\n");
        System.out.println("DATA");
        log.addText("C: DATA"+"\n");
        printWriterOut.println("DATA");
        String acceptedMsg = bufferedReaderIn.readLine();
        System.out.println(acceptedMsg);
        log.addText("S: "+acceptedMsg+"\n");
        printWriterOut.println("Subject: "+subject+"\n");
        printWriterOut.println(content);
        log.addText("C: Subject: "+subject+"\n");
        log.addText("C: "+content+"\n");
        System.out.println(".");
        log.addText("C: ."+"\n");
        printWriterOut.println(".");
        String deliveryMsg = bufferedReaderIn.readLine();
        System.out.println(deliveryMsg);
        log.addText("S: "+deliveryMsg+"\n");
        System.out.println("QUIT");
        log.addText("C: QUIT"+"\n");
        printWriterOut.println("QUIT");
        String byeMsg = bufferedReaderIn.readLine();
        System.out.println(byeMsg);
        log.addText("S: "+byeMsg+"\n");
        log.addText("\n\n\n\n\n");
        return 1;
    }


}
