package com.birdRun543.code0.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import sun.font.FontDesignMetrics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hanbing
 * @date 2021/10/5 14:29
 */

@Slf4j
public class ImageUtil {

    public static BufferedImage getBufferedImage(String resourcePath) {
        BufferedImage image = null;
        Resource resource = new ClassPathResource(resourcePath);
        try (InputStream in = resource.getInputStream()) {
            image = ImageIO.read(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }


    public static List<String> dealRows(String text, Font font, int width) {

        int currentLineWidth = 0;
        StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            Character c = text.charAt(i);
            currentLineWidth += getFontWidth(font, c);
            sb.append(c);
            if (width <= currentLineWidth) {
                list.add(sb.toString());
                sb = new StringBuilder();
                currentLineWidth = 0;
            }
        }
        if (sb.length() > 0) {
            list.add(sb.toString());
        }
        return list;
    }

    public static int getFontHeight(Font font) {
        FontDesignMetrics metrics = FontDesignMetrics.getMetrics(font);
        return metrics.getHeight();
    }

    public static int getFontWidth(Font font, Character c) {
        FontDesignMetrics metrics = FontDesignMetrics.getMetrics(font);
        return metrics.charWidth(c);
    }

    public static int drawString(String text, Graphics2D outg, Font plainFont, int x, int y, int textWidth) {

        outg.setFont(plainFont);

        List<String> textList = ImageUtil.dealRows(text, plainFont, textWidth);
        int fontHeight = ImageUtil.getFontHeight(plainFont);
        int height = 0;
        for (String t : textList) {
            outg.drawString(t, x, y + height);
            height += fontHeight;
        }
        return y + height + fontHeight;
    }
}