package de.voomdoon.tool.map.kml.bryton;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import de.voomdoon.testing.logging.tests.LoggingCheckingTestBase;

/**
 * DOCME add JavaDoc for
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
class CleanBrytonDataProgramTest extends LoggingCheckingTestBase {

	/**
	 * DOCME add JavaDoc for method test
	 * 
	 * @throws IOException
	 * @since DOCME add inception version number
	 */
	@Test
	void test() throws IOException {
		logTestStart();

		CleanBrytonDataTestUtil.run(fileName -> CleanBrytonDataProgram.main(new String[] { fileName }), getTempDirectory());
	}
}
