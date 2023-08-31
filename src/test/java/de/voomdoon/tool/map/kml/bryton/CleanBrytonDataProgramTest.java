package de.voomdoon.tool.map.kml.bryton;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import de.voomdoon.testing.logging.tests.LoggingCheckingTestBase;
import de.voomdoon.testing.tests.FileTestingUtil;
import de.voomdoon.util.kml.KmlUtil;

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
	 * @since 0.1.0
	 */
	@Test
	void test_directory() throws IOException {
		logTestStart();

		FileTestingUtil.provideResourceAsFile("kml/Bryton/default.kml", getTempDirectory() + "/1.kml");
		FileTestingUtil.provideResourceAsFile("kml/Bryton/default.kml", getTempDirectory() + "/2.kml");

		CleanBrytonDataProgram.main(new String[] { getTempDirectory().toString() });

		CleanBrytonDataTestUtil.assertPlacemark(KmlUtil.readKml(getTempDirectory() + "/1.kml"));
		CleanBrytonDataTestUtil.assertPlacemark(KmlUtil.readKml(getTempDirectory() + "/2.kml"));
	}

	/**
	 * DOCME add JavaDoc for method test
	 * 
	 * @throws IOException
	 * @since 0.1.0
	 */
	@Test
	void test_file() throws IOException {
		logTestStart();

		CleanBrytonDataTestUtil.run(fileName -> CleanBrytonDataProgram.main(new String[] { fileName }),
				getTempDirectory());
	}
}
