package com.alex.graphics;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ToolPanel extends JPanel{

	public ToolPanel() {
		String[] images = {"select",
				"line",
				"square",
				"rectangle",
				"circle",
				"oval"
		};
		setLayout(new GridLayout(images.length, 1) );
		
		ImageIcon icon = null;
		for (String image : images) {
		      java.net.URL imgURL = ToolPanel.class.getResource("/buttons/" + image + ".png");
		      if (imgURL != null) {
		    	  icon = new ImageIcon(imgURL);
		      } else {
		         JOptionPane.showMessageDialog(this.getParent(), "Icon " + image + ".png image not found.");
		      }
			JButton button = new JButton(icon);
			add(button);
		}
	}
}
