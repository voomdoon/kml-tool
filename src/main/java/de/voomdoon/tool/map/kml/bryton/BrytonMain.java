package de.voomdoon.tool.map.kml.bryton;

import java.util.Map;

import de.voomdoon.util.cli.MainBase;

/**
 * DOCME add JavaDoc for
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
public class BrytonMain extends MainBase {

	/**
	 * DOCME add JavaDoc for method main
	 * 
	 * @param args
	 * @since 0.1.0
	 */
	public static void main(String[] args) {
		new BrytonMain(args, Map.of("clean", CleanBrytonData.class));
	}

	/**
	 * DOCME add JavaDoc for constructor BrytonMain
	 * 
	 * @param args
	 * @param subMains
	 * @since 0.1.0
	 */
	protected BrytonMain(String[] args, Map<String, Class<?>> subMains) {
		super(args, subMains);
	}

	/**
	 * @since 0.1.0
	 */
	@Override
	protected String getName() {
		return "Bryton";
	}
}
