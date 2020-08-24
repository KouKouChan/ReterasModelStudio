package com.hiveworkshop.rms.ui.gui.modeledit.components.material;

import com.hiveworkshop.rms.editor.model.Layer;
import com.hiveworkshop.rms.editor.model.util.ModelUtils;
import com.hiveworkshop.rms.filesystem.sources.DataSource;
import com.hiveworkshop.rms.parsers.mdlx.MdlxLayer.FilterMode;
import com.hiveworkshop.rms.parsers.blp.BLPHandler;
import com.hiveworkshop.rms.ui.application.actions.model.material.SetLayerFilterModeAction;
import com.hiveworkshop.rms.ui.application.edit.ModelStructureChangeListener;
import com.hiveworkshop.rms.ui.application.edit.mesh.activity.UndoActionListener;
import com.hiveworkshop.rms.ui.gui.modeledit.components.editors.ColorValuePanel;
import com.hiveworkshop.rms.ui.gui.modeledit.components.editors.ComponentEditorJSpinner;
import com.hiveworkshop.rms.ui.gui.modeledit.components.editors.FloatValuePanel;
import com.hiveworkshop.rms.ui.icons.IconUtils;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComponentLayerPanel extends JPanel {
	private final JComboBox<FilterMode> filterModeDropdown;
	private final JButton textureButton;
	private final LayerFlagsPanel layerFlagsPanel;
	private final JButton tVertexAnimButton;
	private final ComponentEditorJSpinner coordIdSpinner;
	private final FloatValuePanel alphaPanel;
	private final FloatValuePanel emissiveGainPanel;
	private Layer layer;
	private final FloatValuePanel fresnelOpacityPanel;
	private final FloatValuePanel fresnelTeamColor;
	private final ColorValuePanel fresnelColorPanel;
	private UndoActionListener undoActionListener;
	private ModelStructureChangeListener modelStructureChangeListener;
	private boolean listenersEnabled = true;

	public ComponentLayerPanel() {
		setLayout(new MigLayout("fill", "", "[fill][fill]"));
		final JPanel leftHandSettingsPanel = new JPanel();
		layerFlagsPanel = new LayerFlagsPanel();
		layerFlagsPanel.setBorder(BorderFactory.createTitledBorder("Flags"));
		add(leftHandSettingsPanel);
		add(layerFlagsPanel);
		leftHandSettingsPanel.setLayout(new MigLayout());
		leftHandSettingsPanel.add(new JLabel("Filter Mode:"));
		filterModeDropdown = new JComboBox<FilterMode>(FilterMode.values());
		filterModeDropdown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				if (listenersEnabled) {
					final SetLayerFilterModeAction setLayerFilterModeAction = new SetLayerFilterModeAction(
							layer, layer.getFilterMode(),
							((FilterMode) filterModeDropdown.getSelectedItem()),
							modelStructureChangeListener);
					setLayerFilterModeAction.redo();
					undoActionListener.pushAction(setLayerFilterModeAction);
				}
			}
		});
		leftHandSettingsPanel.add(filterModeDropdown, "wrap, growx");
		leftHandSettingsPanel.add(new JLabel("Texture:"));
		textureButton = new JButton("Choose Texture");
		textureButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {

			}
		});
		leftHandSettingsPanel.add(textureButton, "wrap, growx");
		coordIdSpinner = new ComponentEditorJSpinner(
				new SpinnerNumberModel(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1));
		leftHandSettingsPanel.add(new JLabel("TVertex Anim:"));
		tVertexAnimButton = new JButton("Choose TVertex Anim");
		leftHandSettingsPanel.add(tVertexAnimButton, "wrap, growx");
		leftHandSettingsPanel.add(new JLabel("CoordID:"));
		leftHandSettingsPanel.add(coordIdSpinner, "wrap, growx");

		alphaPanel = new FloatValuePanel("Alpha");
		leftHandSettingsPanel.add(alphaPanel, "wrap, span 2");
		emissiveGainPanel = new FloatValuePanel("Emissive Gain");
		leftHandSettingsPanel.add(emissiveGainPanel, "wrap, span 2, hidemode 2");
		fresnelColorPanel = new ColorValuePanel("Fresnel Color");
		leftHandSettingsPanel.add(fresnelColorPanel, "wrap, span 2, hidemode 2");
		fresnelOpacityPanel = new FloatValuePanel("Fresnel Opacity");
		leftHandSettingsPanel.add(fresnelOpacityPanel, "wrap, span 2, hidemode 2");
		fresnelTeamColor = new FloatValuePanel("Fresnel Team Color");
		leftHandSettingsPanel.add(fresnelTeamColor, "wrap, span 2, hidemode 2");

	}

	public void setLayer(final DataSource workingDirectory, final Layer layer, final int formatVersion,
						 final boolean hdShader, final UndoActionListener undoActionListener,
						 final ModelStructureChangeListener modelStructureChangeListener) {
		listenersEnabled = false;
		this.layer = layer;
		this.undoActionListener = undoActionListener;
		this.modelStructureChangeListener = modelStructureChangeListener;
		layerFlagsPanel.setLayer(layer);
		filterModeDropdown.setSelectedItem(layer.getFilterMode());
		textureButton.setIcon(new ImageIcon(
				IconUtils.worldEditStyleIcon(BLPHandler.getImage(layer.getTextureBitmap(), workingDirectory))));
		coordIdSpinner.reloadNewValue(layer.getCoordId());
		tVertexAnimButton.setText(layer.getTextureAnim() == null ? "None" : layer.getTextureAnim().toString());
		alphaPanel.reloadNewValue((float) layer.getStaticAlpha(), layer.find("Alpha"));
		emissiveGainPanel.setVisible(ModelUtils.isEmissiveLayerSupported(formatVersion) && hdShader);
		emissiveGainPanel.reloadNewValue((float) layer.getEmissive(), layer.find("EmissiveGain"));
		final boolean fresnelColorLayerSupported = ModelUtils.isFresnelColorLayerSupported(formatVersion) && hdShader;
		fresnelColorPanel.setVisible(fresnelColorLayerSupported);
		fresnelColorPanel.reloadNewValue(layer.getFresnelColor(), layer.find("FresnelColor"));
		fresnelOpacityPanel.setVisible(fresnelColorLayerSupported);
		fresnelOpacityPanel.reloadNewValue((float) layer.getFresnelOpacity(), layer.find("FresnelOpacity"));
		fresnelTeamColor.setVisible(fresnelColorLayerSupported);
		fresnelTeamColor.reloadNewValue((float) layer.getFresnelTeamColor(), layer.find("FresnelTeamColor"));
		listenersEnabled = true;
	}
}
