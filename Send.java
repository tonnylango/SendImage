package mine;

import java.awt.image.BufferedImage;
import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;

public class Send {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("127.0.0.1", 13085);
        OutputStream outputStream = socket.getOutputStream();
        
        BufferedImage image = ImageIO.read(new File("C:\\users\\deguzman\\desktop\\Capture.png"));
        
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);
        
        byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
        outputStream.write(size);
        outputStream.write(byteArrayOutputStream.toByteArray());
        outputStream.flush();
        System.out.println("Flushed: "+System.currentTimeMillis());
        
        Thread.sleep(120000);
        System.out.println("Closing: "+System.currentTimeMillis());
        socket.close();
    }
}
