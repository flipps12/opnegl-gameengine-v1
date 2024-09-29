package org.example.entities;

import lombok.Getter;
import lombok.Setter;
import org.example.ShaderUtils;
import org.example.TextureLoader;

import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL30C.glGenerateMipmap;

@Setter
@Getter
public class EntityNoMove {
    private Long id;
    private float x, y;
    private float width, height;
    private float r, g, b;

    private int shaderProgram;
    private int textureID;

    public EntityNoMove(Long id ,float x, float y, float width, float height, float r, float g, float b) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public void init() throws IOException {
        BufferedImage image = TextureLoader.loadImage("/shaders/oak.png");
        if (image == null) {
            throw new IOException("Failed to load image");
        } else {
            System.out.println("Image loaded successfully: " + image.getWidth() + "x" + image.getHeight());
        }
        textureID = TextureLoader.loadTexture(image);

        // Configurar parámetros de la textura
        glBindTexture(GL_TEXTURE_2D, textureID);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        glGenerateMipmap(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public void render() throws IOException {
        glBindTexture(GL_TEXTURE_2D, textureID);

        glBegin(GL_QUADS);
        glTexCoord2f(0, 0); glVertex2f(x, y);
        glTexCoord2f(1, 0); glVertex2f(x + width, y);
        glTexCoord2f(1, 1); glVertex2f(x + width, y + height);
        glTexCoord2f(0, 1); glVertex2f(x, y + height);
        glEnd();

        glBindTexture(GL_TEXTURE_2D, 0);
    }



}
