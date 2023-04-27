package quizAppliication;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollBar;

public class MyScrollBar extends JScrollBar{

	public MyScrollBar() {
		super.setUI(new ModernScrollBarUI());
		super.setPreferredSize(new Dimension(4, 4));
		super.setUnitIncrement(8);
		super.setBackground(new Color(245, 245, 245));
		super.setForeground(new Color(244, 244, 244));
	}
}
