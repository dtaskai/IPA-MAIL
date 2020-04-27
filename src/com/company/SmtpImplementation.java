
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

        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
        bufferedReaderIn = new BufferedReader(new InputStreamReader(inputStream));
        printWriterOut = new PrintWriter(new OutputStreamWriter(outputStream), true);

        String initialMsg = bufferedReaderIn.readLine();
        System.out.println(initialMsg);
        log.addText(initialMsg+"\n");
        System.out.println("HELO "+localHost.getHostName());
        log.addText("HELO "+localHost.getHostName()+"\n");
        printWriterOut.println("HELO "+localHost.getHostName());
        String welcomeMsg = bufferedReaderIn.readLine();
        System.out.println(welcomeMsg);
        log.addText(welcomeMsg+"\n");
        System.out.println("MAIL From:<"+from+">");
        log.addText("MAIL From:<"+from+">"+"\n");
        printWriterOut.println("MAIL From:<"+from+">");
        String senderMsg = bufferedReaderIn.readLine();
        System.out.println(senderMsg);
        log.addText(senderMsg+"\n");
        System.out.println("RCPT TO:<"+to+">");
        log.addText("RCPT TO:<"+to+">"+"\n");
        printWriterOut.println("RCPT TO:<"+to+">");
        String recipientMsg = bufferedReaderIn.readLine();
        System.out.println(recipientMsg);
        log.addText(recipientMsg+"\n");
        System.out.println("DATA");
        log.addText("DATA"+"\n");
        printWriterOut.println("DATA");
        printWriterOut.println("Subject: "+subject+"\r\n");
        printWriterOut.println(content);
        printWriterOut.println(".");
        String acceptedMsg = bufferedReaderIn.readLine();
        System.out.println(acceptedMsg);
        log.addText(acceptedMsg+"\n");
        System.out.println("QUIT");
        log.addText("QUIT"+"\n");
        printWriterOut.println("QUIT");
        return 1;
    }


}
