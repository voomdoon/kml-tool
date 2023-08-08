package de.voomdoon.tool.map.kml;

import org.junit.jupiter.api.Test;

import de.voomdoon.testing.logging.tests.LoggingCheckingTestBase;
import de.voomdoon.tool.map.kml.bryton.CleanBrytonDataTestUtil;

/**
 * DOCME add JavaDoc for
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
class KmlToolMainTest extends LoggingCheckingTestBase {

	/**
	 * DOCME add JavaDoc for method test_bryton_clean
	 * 
	 * @since DOCME add inception version number
	 */
	@Test
	void test_bryton_clean() throws Exception {
		logTestStart();

		CleanBrytonDataTestUtil.run(fileName -> KmlToolMain.main(new String[] { "bryton", "clean", fileName }),
				getTempDirectory());
	}
}
