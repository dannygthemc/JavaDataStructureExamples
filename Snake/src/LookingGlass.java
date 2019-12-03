/*
 * LookingGlass.java -- A simple 2D canvas to animate a set of polyhedra.
 * 
 * Copyright (C) 2012 Stephen M. Watt
 */


import java.awt.*;

@SuppressWarnings("serial")
public class LookingGlass extends Frame {

	Box box;
	
	public LookingGlass() {
		setTitle("LookingGlass");
		this.box = null;
		setSize(500,500);
		setVisible(true);
	}
	
	public void paint(Graphics g) {
		if (box != null) showVolume(g, box.size(), 100, 400);
	}
	public void repaint(Box b) {
		box = b;
		repaint();
	}
	public void repaint(Graphics g) {
		super.repaint();
		if (box != null) showVolume(g, box.size(), 100, 400);
	}
	
	
	public static final int EdgeLength = 42;
	public static final int GridLength = 70;
	
	
	public void showVolume(Graphics g, int n, int x0, int y0) {
		for (int i = n-1; i >= 0; i--)
			showPlane(g, i, n, x0 + i*GridLength/2, y0 - i*GridLength/2);
	}
	public void showPlane(Graphics g, int i, int n, int x0, int y0) {
		for (int j = 0; j < n; j++) {
			for (int k = 0; k < n; k++) {
				int x00 = x0 + j*GridLength;
				int y00 = y0 - k*GridLength;
				showCube(g,box.get(i,j,k) != 0, x00, y00);
			}
		}
	}
	public void showCube(Graphics g, boolean doFill, int x0, int y0) {
		Color edgeColor = Color.black;
		showPoly(g, doFill, front(x0, y0), edgeColor, Color.yellow);
		showPoly(g, doFill, top  (x0, y0), edgeColor, Color.red);
		showPoly(g, doFill, side (x0, y0), edgeColor, Color.blue);
	}
	public void showPoly(Graphics g, boolean doFill, Polygon p, Color edge, Color face) {
		if (doFill) {
			g.setColor(face);
			g.fillPolygon(p);
		}
		g.setColor(edge);
		g.drawPolygon(p);
	}
	
	
	public Polygon front(int x0, int y0) {
		Polygon p = new Polygon();
		p.addPoint(x0,              y0 - EdgeLength);
		p.addPoint(x0,              y0);
		p.addPoint(x0 + EdgeLength, y0);
		p.addPoint(x0 + EdgeLength, y0 - 2* EdgeLength/2);
		
		return p;
	}
	public Polygon top(int x0, int y0) {
		Polygon p = new Polygon();
		p.addPoint(x0,                  y0 - EdgeLength);
		p.addPoint(x0 + EdgeLength/2,   y0 - 3*EdgeLength/2);
		p.addPoint(x0 + 3*EdgeLength/2, y0 - 3*EdgeLength/2);
		p.addPoint(x0 + EdgeLength,     y0 - EdgeLength);
		return p;
	}
	public Polygon side(int x0, int y0) {
		Polygon p = new Polygon();
		p.addPoint(x0 + EdgeLength,     y0 - EdgeLength);
		p.addPoint(x0 + 3*EdgeLength/2, y0 - 3*EdgeLength/2);
		p.addPoint(x0 + 3*EdgeLength/2, y0 - EdgeLength/2);
		p.addPoint(x0 + EdgeLength,     y0);
		return p;
	}
}
