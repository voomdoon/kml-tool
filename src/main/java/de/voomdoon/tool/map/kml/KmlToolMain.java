package de.voomdoon.tool.map.kml;

import java.util.Map;

import de.voomdoon.tool.map.kml.bryton.BrytonMain;
import de.voomdoon.util.cli.MainBase;

/**
 * DOCME add JavaDoc for
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
public class KmlToolMain extends MainBase {

	/**
	 * DOCME add JavaDoc for method main
	 * 
	 * @param args
	 * @since 0.1.0
	 */
	public static void main(String[] args) {
		new KmlToolMain(args, Map.of("bryton", BrytonMain.class)).run();
	}

	/**
	 * DOCME add JavaDoc for constructor KmlToolMain
	 * 
	 * @param args
	 * @param subMains
	 * @since 0.1.0
	 */
	protected KmlToolMain(String[] args, Map<String, Class<?>> subMains) {
		super(args, subMains);
	}

	/**
	 * @since 0.1.0
	 */
	@Override
	protected String getName() {
		return "KML-Tool";
	}
}
