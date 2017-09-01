package com.hiveworkshop.wc3.gui.modeledit.useractions;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import javax.swing.SwingUtilities;

import com.hiveworkshop.wc3.gui.modeledit.CoordinateSystem;
import com.hiveworkshop.wc3.gui.modeledit.manipulator.activity.Manipulator;
import com.hiveworkshop.wc3.gui.modeledit.selection.SelectionView;

public final class MultiManipulatorActivity implements ViewportActivity {
	private final ManipulatorBuilder manipulatorBuilder;
	private final UndoActionListener undoActionListener;
	private Manipulator manipulator;
	private CoordinateSystem coordinateSystem;
	private CursorManager cursorManager;
	private Double mouseStartPoint;
	private Double lastDragPoint;

	public MultiManipulatorActivity(final ManipulatorBuilder manipulatorBuilder,
			final UndoActionListener undoActionListener) {
		this.manipulatorBuilder = manipulatorBuilder;
		this.undoActionListener = undoActionListener;
	}

	@Override
	public void onSelectionChanged(final SelectionView newSelection) {

	}

	@Override
	public void modelChanged() {

	}

	@Override
	public void viewportChanged(final CursorManager cursorManager, final CoordinateSystem coordinateSystem) {
		this.cursorManager = cursorManager;
		this.coordinateSystem = coordinateSystem;
	}

	@Override
	public void mousePressed(final MouseEvent e) {
		final ButtonType buttonType;
		if (SwingUtilities.isRightMouseButton(e)) {
			buttonType = ButtonType.RIGHT_MOUSE;
		} else if (SwingUtilities.isMiddleMouseButton(e)) {
			buttonType = ButtonType.MIDDLE_MOUSE;
		} else {
			buttonType = ButtonType.LEFT_MOUSE;
		}
		manipulator = manipulatorBuilder.buildActivityListener(e.getX(), e.getY(), buttonType, coordinateSystem);
		if (manipulator != null) {
			mouseStartPoint = new Point2D.Double(coordinateSystem.geomX(e.getPoint().getX()),
					coordinateSystem.geomY(e.getPoint().getY()));
			manipulator.start(mouseStartPoint, coordinateSystem.getPortFirstXYZ(), coordinateSystem.getPortSecondXYZ());
		}
	}

	@Override
	public void mouseReleased(final MouseEvent e) {
		if (manipulator != null) {
			final Point2D.Double mouseEnd = new Point2D.Double(coordinateSystem.geomX(e.getPoint().getX()),
					coordinateSystem.geomY(e.getPoint().getY()));
			undoActionListener.pushAction(manipulator.finish(lastDragPoint, mouseEnd,
					coordinateSystem.getPortFirstXYZ(), coordinateSystem.getPortSecondXYZ()));
			mouseStartPoint = null;
			lastDragPoint = null;
			manipulator = null;
		}
	}

	@Override
	public void mouseMoved(final MouseEvent e) {
		cursorManager.setCursor(manipulatorBuilder.getCursorAt(e.getX(), e.getY(), coordinateSystem));
	}

	@Override
	public void mouseDragged(final MouseEvent e) {
		if (manipulator != null) {
			final Point2D.Double mouseEnd = new Point2D.Double(coordinateSystem.geomX(e.getPoint().getX()),
					coordinateSystem.geomY(e.getPoint().getY()));
			manipulator.update(lastDragPoint, mouseEnd, coordinateSystem.getPortFirstXYZ(),
					coordinateSystem.getPortSecondXYZ());
			lastDragPoint = mouseEnd;
		}
	}

	@Override
	public void render(final Graphics2D graphics) {
		manipulatorBuilder.render(graphics, coordinateSystem);
		if (manipulator != null) {
			manipulator.render(graphics, coordinateSystem);
		}
	}

	@Override
	public boolean isEditing() {
		return manipulator != null;
	}

}