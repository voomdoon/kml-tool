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
	 * @since 0.1.0
	 */
	public static void main(String[] args) throws IOException {
		new CleanBrytonDataProgram(args).run();
	}

	/**
	 * @since 0.1.0
	 */
	private String fileName;

	/**
	 * DOCME add JavaDoc for constructor CleanBrytonDataProgram
	 * 
	 * @param args
	 * @since 0.1.0
	 */
	public CleanBrytonDataProgram(String[] args) {
		fileName = args[0];
	}

	/**
	 * DOCME add JavaDoc for method run
	 * 
	 * @throws IOException
	 * 
	 * @since 0.1.0
	 */
	private void run() throws IOException {
		CleanBrytonData.run(fileName);
	}
}
