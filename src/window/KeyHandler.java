package window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import window.others.TextBox;
import window.screens.ScreenComponent;

public class KeyHandler implements KeyListener {

	private Game game;

	public KeyHandler(Game game) {
		this.game = game;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		List<ScreenComponent> comps = game.getCurrentScreen().getComponents();
		/*
		 * Looping through all components that are on screen to see if a textbox
		 * is selected. all textbox input goes here
		 */
		for (int i = 0; i < comps.size(); i++) {
			ScreenComponent s = comps.get(i);
			if (s instanceof TextBox) {
				TextBox t = (TextBox) s;
				if (t.isSelected()) {
					if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
						t.backspace();
					} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
						t.addText(" ");
					} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						t.setSelected(false);
						if (t.getSubmitter() != null) {
							t.getSubmitter().onClick(game);
						} else {
							boolean passed = false;
							for (int c = 0; c < comps.size(); c++) {
								ScreenComponent next = comps.get(c);
								if (next instanceof TextBox && next != t && passed == true) {
									((TextBox) next).setSelected(true);
									return;
								} else if (next instanceof TextBox && next == t) {
									passed = true;
								}
							}
						}
					} else if (e.getKeyCode() == KeyEvent.VK_TAB) {
						t.setSelected(false);
						boolean passed = false;
						for (int c = 0; c < comps.size(); c++) {
							ScreenComponent next = comps.get(c);
							if (next instanceof TextBox && next != t && passed == true) {
								((TextBox) next).setSelected(true);
								return;
							} else if (next instanceof TextBox && next == t) {
								passed = true;
							}
						}
						/*special characters*/
					} else if (e.getKeyCode() == KeyEvent.VK_2 || e.getKeyCode() == KeyEvent.VK_PERIOD) {
						t.addText(""+e.getKeyChar());
					} else {
						if (Character.isDigit(e.getKeyChar()) || Character.isLetter(e.getKeyChar()))
							t.addText("" + e.getKeyChar());
					}
					return;
				}
			}
		}
		/* END OF TEXTBOXES*/

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
