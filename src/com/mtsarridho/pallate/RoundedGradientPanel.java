package com.mtsarridho.pallate;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

public class RoundedGradientPanel extends JPanel {
    
    private Color colorStart = Color.WHITE;
    private Color colorEnd = Color.LIGHT_GRAY;
    private int arcWidth = 20;
    private int arcHeight = 20;

    public RoundedGradientPanel() {
        setOpaque(false); // Penting supaya sudutnya transparan
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g.create();
        
        // Haluskan gambar
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Buat gradasi
        GradientPaint gradient = new GradientPaint(
            0, 0, colorStart, 
            getWidth(), 0, colorEnd
        );
        g2d.setPaint(gradient);
        
        // Gambar rounded rectangle
        g2d.fill(new RoundRectangle2D.Float(
            0, 0, getWidth(), getHeight(), arcWidth, arcHeight
        ));
        
        g2d.dispose();
    }

    // Getter dan Setter supaya properti bisa diatur dari GUI Designer

    public Color getColorStart() {
        return colorStart;
    }

    public void setColorStart(Color colorStart) {
        this.colorStart = colorStart;
        repaint();
    }

    public Color getColorEnd() {
        return colorEnd;
    }

    public void setColorEnd(Color colorEnd) {
        this.colorEnd = colorEnd;
        repaint();
    }

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
}
