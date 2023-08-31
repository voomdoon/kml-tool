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
class BrytonMainTest extends LoggingCheckingTestBase {

	/**
	 * DOCME add JavaDoc for method testClean
	 * 
	 * @throws IOException
	 * 
	 * @since 0.1.0
	 */
	@Test
	void test_clean() throws IOException {
		logTestStart();

		CleanBrytonDataTestUtil.run(fileName -> BrytonMain.main(new String[] { "clean", fileName }),
				getTempDirectory());
	}
}
