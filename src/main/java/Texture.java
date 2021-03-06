import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

public class Texture {
    private int id;
    private int width;
    private int height;

    public Texture(String filepath) {
        BufferedImage bi;
        try {
            bi = ImageIO.read(new File(filepath));
            width = bi.getWidth();
            height = bi.getHeight();
            int[] raw = bi.getRGB(0, 0, width, height, null, 0, width);

            ByteBuffer pixels = BufferUtils.createByteBuffer(width * height * 4);
            for(int i = 0; i < width; i++) {
                for(int j = 0; j < height; j++) {
                    int pixel = raw[i * width + j];
                    pixels.put((byte)((pixel >> 16) & 0xFF)); // R
                    pixels.put((byte)((pixel >>  8) & 0xFF)); // G
                    pixels.put((byte)((pixel      ) & 0xFF)); // B
                    pixels.put((byte)((pixel >> 24) & 0xFF)); // A
                }
            }
            pixels.flip();
            id = GL11.glGenTextures();
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
            GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
            GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, pixels);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bind() {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
    }
}
