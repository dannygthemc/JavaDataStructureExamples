/*
 * Shower.java -- Don't get wet.  This is a show-er, not a bathroom shower.
 * 
 * This class provides debugging output and formatting of the basic objects.
 * A simple alteration (done) allows it to animate the solving process.
 * 
 * Copyright (C) 2012 Stephen M. Watt
 */


import java.io.*;

public class Shower {
	private LookingGlass lg;
	private PrintStream  out;
	
	public Shower(PrintStream outStream) {
		lg = new LookingGlass();
		out = outStream;
	}
	public void done() {
		lg.dispose();
	}
	
	public void trace(BoxIndex[] path, Box box) {
		show(path);
		draw(box);
	}
	
	public void show(Direction d) {
		out.print("<" + d.delta(0) + d.delta(1) + d.delta(2) + ">");
	}
	public void show(BoxIndex[] path) {
		for (int i = 0; i < path.length; i++) show(path[i]);
		System.out.println();
	}
	public void show(BoxIndex bix) {
		out.print("[" + bix.coord(0) + bix.coord(1) + bix.coord(2) + "]");
	}
	public void show(Box box) {
		for (int i = 0; i < box.size(); i++) {
			for (int j = 0; j < box.size(); j++) {
				if (j > 0) out.print("    ");
				for (int k = 0; k < box.size(); k++) {
					int val = box.get(i,j,k);
					out.print((val < 10 ? " " : "") + val + " ");
				}		
			}
			out.println();
		}
	}
	public void draw(Box box) {
		lg.repaint(box);
		try {
			Thread.sleep(600);
		} catch (InterruptedException e) {
			out.println("Graphics interrupted.");
		}
	}
}
