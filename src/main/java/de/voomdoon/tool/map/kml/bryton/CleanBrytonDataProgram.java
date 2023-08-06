package de.voomdoon.tool.map.kml.bryton;

import java.io.IOException;

/**
 * DOCME add JavaDoc for
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
public class CleanBrytonDataProgram {

	/**
	 * DOCME add JavaDoc for method main
	 * 
	 * @param args
	 * @throws IOException
	 * @since DOCME add inception version number
	 */
	public static void main(String[] args) throws IOException {
		new CleanBrytonDataProgram(args).run();
	}

	/**
	 * @since DOCME add inception version number
	 */
	private String fileName;

	/**
	 * DOCME add JavaDoc for constructor CleanBrytonDataProgram
	 * 
	 * @param args
	 * @since DOCME add inception version number
	 */
	public CleanBrytonDataProgram(String[] args) {
		fileName = args[0];
	}

	/**
	 * DOCME add JavaDoc for method run
	 * 
	 * @throws IOException
	 * 
	 * @since DOCME add inception version number
	 */
	private void run() throws IOException {
		CleanBrytonData.run(fileName);
	}
}
