package de.voomdoon.tool.map.kml.bryton;

import java.io.IOException;
import java.nio.file.Path;

import de.voomdoon.logging.LogManager;
import de.voomdoon.logging.Logger;
import de.voomdoon.util.commons.FileUtil;

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
	 * @since 0.1.0
	 */
	private final Logger logger = LogManager.getLogger(getClass());

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
		FileUtil.listFiles(Path.of(fileName), null, f -> f.getName().endsWith(".kml")).forEach(f -> {
			try {
				logger.info("running " + f.toString() + "...");
				CleanBrytonData.run(f.toString());
				// TODO write into temp file to not destroy it if interrupted in between
			} catch (IOException e) {
				// TODO implement error handling
				throw new RuntimeException("Error at " + f + ": " + e.getMessage(), e);
			} catch (RuntimeException e) {
				throw new RuntimeException("Failed to run " + f + ": " + e.getMessage(), e);
			}
		});
	}
}
