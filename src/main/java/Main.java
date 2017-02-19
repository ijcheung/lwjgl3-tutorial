import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

/**
 * Created by tachyonflux on 2/19/2017.
 */

public class Main {
    public static void main(String[] args) {
        SharedLibraryLoader.load();
        if(glfwInit() == GL11.GL_FALSE) {
            throw new IllegalStateException("Failed to initialize GLFW");
        }
        glfwWindowHint(GLFW_VISIBLE, GL11.GL_FALSE);
        long window = glfwCreateWindow(640, 480, "MY LWJGL Program", 0, 0);
        if(window == 0) {
            throw new IllegalStateException("Failed to create window");
        }

        GLFWvidmode videoMode = new GLFWvidmode(glfwGetVideoMode(glfwGetPrimaryMonitor()));
        glfwSetWindowPos(window, (videoMode.getWidth() - 640) / 2, (videoMode.getHeight() - 480)/ 2);

        glfwShowWindow(window);

        while(glfwWindowShouldClose(window) == GL11.GL_FALSE) {
            glfwPollEvents();
        }

        glfwTerminate();
    }
}
