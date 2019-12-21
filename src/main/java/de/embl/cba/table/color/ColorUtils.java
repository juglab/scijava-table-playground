package de.embl.cba.table.color;

import net.imglib2.type.numeric.ARGBType;

import java.awt.*;

public abstract class ColorUtils
{
	public static Color getColor( ARGBType argbType )
	{
		final int colorIndex = argbType.get();

		return new Color(
				ARGBType.red( colorIndex ),
				ARGBType.green( colorIndex ),
				ARGBType.blue( colorIndex ),
				ARGBType.alpha( colorIndex ));
	}
}
