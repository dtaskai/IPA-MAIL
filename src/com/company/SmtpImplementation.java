
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

    public SmtpImplementation() throws UnknownHostException {
        mailHost = InetAddress.getByName("127.0.0.1");
        localHost = InetAddress.getLocalHost();
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

        String initialID = bufferedReaderIn.readLine();
        System.out.println(initialID);
        System.out.println("HELO "+localHost.getHostName());
        printWriterOut.println("HELO "+localHost.getHostName());
        String welcome = bufferedReaderIn.readLine();
        System.out.println(welcome);
        System.out.println("MAIL From:<"+from+">");
        printWriterOut.println("MAIL From:<"+from+">");
        String senderOK = bufferedReaderIn.readLine();
        System.out.println(senderOK);
        System.out.println("RCPT TO:<"+to+">");
        printWriterOut.println("RCPT TO:<"+to+">");
        String recipientOK = bufferedReaderIn.readLine();
        System.out.println(recipientOK);
        System.out.println("DATA");
        printWriterOut.println("DATA");
        printWriterOut.println("Subject: "+subject+"\r\n");
        printWriterOut.println(content);
        printWriterOut.println(".");
        String acceptedOK = bufferedReaderIn.readLine();
        System.out.println(acceptedOK);
        System.out.println("QUIT");
        printWriterOut.println("QUIT");
        return 1;
    }


}
