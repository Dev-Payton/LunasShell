package com.example.lunasshell;

import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Networking {
    private String ipAddress;
    private TextArea terminalOutput;
    public Networking (String ipAddress, TextArea terminalOutput) {
        this.ipAddress = ipAddress;
        this.terminalOutput = terminalOutput;
    }


    public void pingIpAddress () throws UnknownHostException, IOException {
        InetAddress interstellar = InetAddress.getByName("127.0.0.1");
        terminalOutput.appendText("\nzsh: Sending ping request to: " + ipAddress);
        System.out.println("Attempting to reach host...");
        if (interstellar.isReachable(5000)){
            terminalOutput.appendText("\nzsh: The host is reachable");
        }
        else {
            terminalOutput.appendText("\nzzsh: Sorry we couldnt reach the host!");
        }


    }

}
