/*
 * Copyright (c) 2019-2020, ganom <https://github.com/Ganom>
 * All rights reserved.
 * Licensed under GPL3, see LICENSE for the full scope.
 */
package net.runelite.client.plugins.externals.adamautoclicker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

class A1CAutoClickerOverlay extends Overlay
{
	private static final Color FLASH_COLOR = new Color(255, 0, 0, 70);
	private final Client client;
	private final A1CAutoClick plugin;
	private final A1CAutoClickConfig config;
	private int timeout;

	@Inject
	A1CAutoClickerOverlay(Client client, A1CAutoClick plugin, A1CAutoClickConfig config)
	{
		setPosition(OverlayPosition.DYNAMIC);
		setLayer(OverlayLayer.ALWAYS_ON_TOP);
		this.client = client;
		this.plugin = plugin;
		this.config = config;
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (plugin.isFlash() && config.flash())
		{
			final Color flash = graphics.getColor();
			graphics.setColor(FLASH_COLOR);
			graphics.fill(new Rectangle(client.getCanvas().getSize()));
			graphics.setColor(flash);
			timeout++;
			if (timeout >= 50)
			{
				timeout = 0;
				plugin.setFlash(false);
			}
		}
		return null;
	}

}