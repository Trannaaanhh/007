package TCP;
import java.io.*;
import java.net.*;

public class TCPClientData {

    public static void main(String[] args) throws Exception {
        String serverIP = "203.162.10.109"; 
        int serverPort = 2207;
        String studentCode = "B22DCVT034"; 
        String qCode = "rghN9t18";
        int timeout = 5000; 
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(serverIP, serverPort), timeout);
            socket.setSoTimeout(timeout);
            DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataIn = new DataInputStream(socket.getInputStream());
            String request = studentCode + ";" + qCode;
            dataOut.writeUTF(request); // Gửi chuỗi
            dataOut.flush();
            System.out.println("Sent: " + request);
            int receivedNumber = dataIn.readInt(); 
            System.out.println("Received: " + receivedNumber);
            String binaryString = Integer.toBinaryString(receivedNumber);
            String hexString = Integer.toHexString(receivedNumber).toUpperCase();
            String result = binaryString + ";" + hexString;
            dataOut.writeUTF(result); 
            dataOut.flush();
            System.out.println("Sent result: " + result);
        }
    }
}
