package TCP;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.stream.Collectors;

public class TCPClientFilterString {

    public static void main(String[] args) throws Exception {
        String serverIP = "203.162.10.109"; 
        int serverPort = 2208;

        String studentCode = "B22DCVT034"; 
        String qCode = "OGCRM8Ni";
        int timeout = 5000; 

        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(serverIP, serverPort), timeout);
            socket.setSoTimeout(timeout);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String request = studentCode + ";" + qCode;
            writer.write(request);
            writer.newLine(); // Gửi ký tự xuống dòng để server có thể readLine()
            writer.flush();
            System.out.println("Sent: " + request);

            String receivedString = reader.readLine();
            if (receivedString == null) {
                System.out.println("Server did not send data.");
                return;
            }
            System.out.println("Received: " + receivedString);
            String processedString = receivedString.chars()
                    .filter(Character::isLetter)
                    .distinct()
                    .collect(StringBuilder::new, 
                             (sb, c) -> sb.append((char) c), 
                             StringBuilder::append)
                    .toString();

            writer.write(processedString);
            writer.newLine();
            writer.flush();
            System.out.println("Sent processed: " + processedString);
        }
    }
}
