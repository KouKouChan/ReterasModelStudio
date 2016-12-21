package com.hiveworkshop.wc3.gui.modeledit.actions.newsys;

import java.util.List;

import com.hiveworkshop.wc3.gui.modeledit.UndoAction;
import com.hiveworkshop.wc3.gui.modeledit.selection.SelectionItem;
import com.hiveworkshop.wc3.gui.modeledit.selection.SelectionManager;

public final class SelectComponentAction implements UndoAction {
	private final SelectionManager manager;
	private final List<SelectionItem> items;
	private final List<SelectionItem> previousSelection;

	public SelectComponentAction(final SelectionManager manager, final List<SelectionItem> items,
			final List<SelectionItem> previousSelection) {
		this.manager = manager;
		this.items = items;
		this.previousSelection = previousSelection;
	}

	@Override
	public void undo() {
		manager.setSelection(previousSelection);
	}

	@Override
	public void redo() {
		manager.setSelection(items);
	}

	@Override
	public String actionName() {
		return "selection: select";
	}

}
