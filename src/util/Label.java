package util;

import java.awt.Font;

import javax.swing.JLabel;

import frames.AbstractFRM;

public class Label extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4680146173308218070L;

 
	public Label(String text) {
		setFont(new Font(Util.FONT, 1, Integer.parseInt(Util.FONT_LABEL_SIZE)));
		setText(text);
	}
}
