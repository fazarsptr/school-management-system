package com.mtsarridho.pallate;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;
import java.io.Serializable;

/**
 * RoundedPanel - Panel dengan sudut bulat dan support gradasi.
 * Siap digunakan di GUI Builder (drag and drop).
 * 
 * @author
 */
public class RoundedPanel extends JPanel implements Serializable {

    private int arcWidth = 20;
    private int arcHeight = 20;
    private boolean useGradient = true;
    private Color gradientStart = new Color(255, 255, 255);
    private Color gradientEnd = new Color(220, 220, 220);
    private boolean drawBorder = false;
    private Color borderColor = Color.GRAY;
    private int borderThickness = 1;

    public RoundedPanel() {
        setOpaque(false);
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        RoundRectangle2D.Float shape = new RoundRectangle2D.Float(
            0, 0, width - 1, height - 1, arcWidth, arcHeight);

        if (useGradient) {
            GradientPaint gp = new GradientPaint(
                0, 0, gradientStart,
                width, 0, gradientEnd
            );
            g2.setPaint(gp);
        } else {
            g2.setColor(getBackground());
        }

        g2.fill(shape);

        if (drawBorder) {
            g2.setColor(borderColor);
            for (int i = 0; i < borderThickness; i++) {
                g2.draw(new RoundRectangle2D.Float(
                    i, i, width - 1 - (i * 2), height - 1 - (i * 2),
                    arcWidth, arcHeight
                ));
            }
        }

        g2.dispose();
    }

    // Getter dan Setter properti (biar muncul di Properties di GUI Designer)

    public int getArcWidth() {
        return arcWidth;
    }

    public void setArcWidth(int arcWidth) {
        this.arcWidth = arcWidth;
        repaint();
    }

    public int getArcHeight() {
        return arcHeight;
    }

    public void setArcHeight(int arcHeight) {
        this.arcHeight = arcHeight;
        repaint();
    }

    public boolean isUseGradient() {
        return useGradient;
    }

    public void setUseGradient(boolean useGradient) {
        this.useGradient = useGradient;
        repaint();
    }

    public Color getGradientStart() {
        return gradientStart;
    }

    public void setGradientStart(Color gradientStart) {
        this.gradientStart = gradientStart;
        repaint();
    }

    public Color getGradientEnd() {
        return gradientEnd;
    }

    public void setGradientEnd(Color gradientEnd) {
        this.gradientEnd = gradientEnd;
        repaint();
    }

    public boolean isDrawBorder() {
        return drawBorder;
    }

    public void setDrawBorder(boolean drawBorder) {
        this.drawBorder = drawBorder;
        repaint();
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        repaint();
    }

    public int getBorderThickness() {
        return borderThickness;
    }

    public void setBorderThickness(int borderThickness) {
        this.borderThickness = borderThickness;
        repaint();
    }
}
