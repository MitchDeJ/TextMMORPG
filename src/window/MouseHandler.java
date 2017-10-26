package window;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import window.others.TextBox;
import window.screens.ScreenComponent;

public class MouseHandler implements MouseListener {

	private Game game;

	public MouseHandler(Game game) {
		this.game = game;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		List<ScreenComponent> comps = game.getCurrentScreen().getComponents();
		List<TextBox> tboxes = new ArrayList<TextBox>();
		boolean clickedSomething = false;
		for (int i = 0; i < comps.size(); i++) {
			ScreenComponent comp = comps.get(i);
			Rectangle r = comp.getClickBox();
			if (comp instanceof TextBox)
				tboxes.add((TextBox) comp);
			if (e.getX() > r.getX() && e.getY() > r.getY() && e.getX() < r.getX() + r.getWidth()
					&& e.getY() < r.getY() + r.getHeight()) {
				comp.onClick(game);
				clickedSomething = true;
			}
		}
		if (clickedSomething == false) {
			for (int i = 0; i < tboxes.size(); i++) {
				tboxes.get(i).setSelected(false);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
