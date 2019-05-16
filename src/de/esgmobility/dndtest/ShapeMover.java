package de.esgmobility.dndtest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShapeMover {

    public ShapeMover() {
        JFrame frame = new JFrame("DND Tests");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Surface());
        frame.add(new DragAndDrop());
        frame.pack();
        frame.setVisible(true);
    }
}

class DragAndDrop extends JPanel{
    
    private final int WIDTH_W = 600;
    private final int HEIGHT_W = 600;  
    private final Dimension dim = new Dimension(WIDTH_W, HEIGHT_W);
    
    private int preX;
    private int preY;
    
    private boolean isFirstTime = true;
    private boolean pressOut = false;
    
    private Rectangle area;
    private final Rectangle rec = new Rectangle(50, 50, 100, 100);

    public Rectangle getRec() {
        return rec;
    }
    
    
    
    public DragAndDrop() {
       setBackground(Color.LIGHT_GRAY);
       addMouseMotionListener(new MyMouseAdapter());
       addMouseListener(new MyMouseAdapter());
    }

    @Override
    public Dimension getPreferredSize() {
        return dim;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        if(isFirstTime) {
            area = new Rectangle(dim);
            rec.setLocation(50,50);
            isFirstTime = false;
        }
        g2d.setColor(Color.red);
        g2d.fill(rec);
    }
    
    private boolean checkRect(Rectangle rec) {
        if(area == null) {
            return false;
        }
        if(area.contains(rec.x, rec.y, rec.getWidth(), rec.getHeight())) {
            return true;
        }
        
        int newX = rec.x;
        int newY= rec.y;
        
        if((rec.x+rec.getWidth()) > area.getWidth()) {
            newX = (int) area.getWidth() - (int)(rec.getWidth()-1);
        }
        if(rec.x < 0) {
            newX = -1;
        }
        if((rec.y+rec.getHeight()) > area.getHeight()) {
            newY = (int) area.getHeight()- (int)(rec.getHeight()-1);
        }
        if(rec.y < 0) {
            newY = -1;
        }
        
        rec.setLocation(newX, newY);
        return false;
    }
    
    private class MyMouseAdapter extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            preX = rec.x - e.getX();
            preY = rec.y - e.getY();
            
            if(rec.contains(e.getX(),e.getY())) {
                updateLocation(e);
            } else {}
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if(!pressOut) {
                updateLocation(e);
            } else {}
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            if(rec.contains(e.getX(), e.getY())) {
                updateLocation(e);
            } else {
                pressOut = false;
            }
        }
        
        public void updateLocation(MouseEvent e) {
            rec.setLocation(preX+e.getX(), preY+e.getY());
            checkRect(rec);
            repaint();
        }
    }
}

class Surface extends JPanel {
    private Point2D[] points;
    private final int SIZE = 8;
    private int pos;

    public Surface() {
        initUI();
    }
    
    private void initUI() {
        addMouseListener(new ShapeTestAdapter());
        addMouseMotionListener(new ShapeTestAdapter());
        pos = -1;
        
        points = new Point2D[2];
        points[0] = new Point2D.Double(50, 50);
        points[1] = new Point2D.Double(150, 100);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.

        Graphics2D g2d = (Graphics2D) g;
        
        for(Point2D g2 : points) {
            double x = g2.getX() - SIZE/2;
            double y = g2.getY()- SIZE/2;
            g2d.fill(new Rectangle2D.Double(x, y, SIZE, SIZE));
        }
        
        Rectangle2D r = new Rectangle2D.Double();
        r.setFrameFromDiagonal(points[0], points[1]);
        
        g2d.draw(r);
    }
    
    private class ShapeTestAdapter extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            Point p = e.getPoint();
            
            for(int i = 0; i < points.length; i++) {
                double x = points[i].getX() - SIZE/2;
                double y = points[i].getY() - SIZE/2;

                Rectangle2D r = new Rectangle2D.Double(x, y, SIZE, SIZE);
                
                if(r.contains(p)) {
                    pos = i;
                    return;
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            pos = -1;
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if(pos == -1) {
                return;
            }
            points[pos] = e.getPoint();
            repaint();
        }
    }
}

