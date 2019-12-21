package de.embl.cba.table.color;

import de.embl.cba.table.lut.ARGBLut;
import de.embl.cba.table.tablerow.TableRow;
import net.imglib2.type.numeric.ARGBType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CategoryTableRowColumnColoringModel< T extends TableRow >
		extends AbstractColoringModel< T > implements CategoryColoringModel< T >
{
	private Map< Object, ARGBType > inputToFixedColor;
	private Map< Object, ARGBType > inputToRandomColor;
	private final String column;
	private ARGBLut argbLut;
	private int randomSeed;

	final static public double goldenRatio = 1.0 / ( 0.5 * Math.sqrt( 5 ) + 0.5 );

	/**
	 *
	 * @param argbLut
	 */
	public CategoryTableRowColumnColoringModel( String column, ARGBLut argbLut )
	{
		this.column = column;
		this.argbLut = argbLut;
		this.inputToRandomColor = new ConcurrentHashMap<>(  );
		this.inputToFixedColor = new ConcurrentHashMap<>(  );
		this.randomSeed = 50;
	}

	@Override
	public void convert( T input, ARGBType output )
	{
		final Object featureValue = input.getCell( column );

		if ( inputToFixedColor.keySet().contains( featureValue ) )
		{
			final int color = inputToFixedColor.get( featureValue ).get();
			output.set( color );
		}
 		else if ( inputToRandomColor.keySet().contains( featureValue ) )
		{
			final int color = inputToRandomColor.get( featureValue ).get();
			output.set( color );
		}
		else
		{
			final double random = createRandom( inputToRandomColor.size() + 1 );
			inputToRandomColor.put( featureValue, new ARGBType( argbLut.getARGB( random ) ) );
			final int color = inputToRandomColor.get( featureValue ).get();
			output.set( color );
		}
	}

	private double createRandom( double x )
	{
		double random = ( x * randomSeed ) * goldenRatio;
		random = random - ( long ) Math.floor( random );
		return random;
	}

	@Override
	public void incRandomSeed( )
	{
		inputToRandomColor.clear();
		this.randomSeed++;

		notifyColoringListeners();
	}

	public void addInputToFixedColor( Object input, ARGBType color )
	{
		inputToFixedColor.put( input, color );
	}

}
