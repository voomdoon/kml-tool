package de.voomdoon.tool.map.kml.bryton;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.TimeSpan;
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
class CleanBrytonDataTest extends LoggingCheckingTestBase {

	/**
	 * @throws IOException
	 * @since 0.1.0
	 */
	@Test
	void test_Document_LookAt() throws IOException {
		logTestStart();

		Kml kml = run("kml/Bryton.xml");
		Document actual = (Document) kml.getFeature();

		assertThat(actual).extracting(Document::getAbstractView).isNull();
	}

	/**
	 * @throws IOException
	 * @since 0.1.0
	 */
	@Test
	void test_Document_name() throws IOException {
		logTestStart();

		Kml kml = run("kml/Bryton.xml");
		Document actual = (Document) kml.getFeature();

		assertThat(actual).extracting(Document::getName).isEqualTo("2023-07-26T07:47:59Z");
	}

	/**
	 * @throws IOException
	 * @since 0.1.0
	 */
	@Test
	void test_Document_snippet() throws IOException {
		logTestStart();

		Kml kml = run("kml/Bryton.xml");
		Document actual = (Document) kml.getFeature();

		assertThat(actual).extracting(Document::getSnippetd).isNull();
	}

	/**
	 * @throws IOException
	 * @since 0.1.0
	 */
	@Test
	void test_Document_StyleSelector() throws IOException {
		logTestStart();

		Kml kml = run("kml/Bryton.xml");
		Document actual = (Document) kml.getFeature();

		assertThat(actual.getStyleSelector()).hasSize(1);
		assertThat(actual.getStyleSelector().get(0).getId()).isEqualTo("default-path");
	}

	/**
	 * DOCME add JavaDoc for method test_name_startTime
	 * 
	 * @throws IOException
	 * 
	 * @since 0.1.0
	 */
	@Test
	void test_noFolders() throws IOException {
		logTestStart();

		Kml actual = run("kml/Bryton.xml");

		assertPlacemark(actual);
	}

	/**
	 * DOCME add JavaDoc for method test_Placemark_name
	 * 
	 * @throws IOException
	 * @since 0.1.0
	 */
	@Test
	void test_Placemark_name() throws IOException {
		logTestStart();

		Kml kml = run("kml/Bryton.xml");
		Placemark actual = assumePlacemark(kml);

		assertThat(actual).extracting(Placemark::getName).isEqualTo("2023-07-26T07:47:59Z");
	}

	/**
	 * DOCME add JavaDoc for method test_Placemark_name
	 * 
	 * @throws IOException
	 * @since 0.1.0
	 */
	@Test
	void test_Placemark_style() throws IOException {
		logTestStart();

		Kml kml = run("kml/Bryton.xml");
		Placemark actual = assumePlacemark(kml);

		assertThat(actual.getStyleUrl()).isEqualTo("#default-path");
	}

	/**
	 * DOCME add JavaDoc for method test_placememark
	 * 
	 * @throws IOException
	 * 
	 * @since 0.1.0
	 */
	@Test
	void test_Placemark_TimeSpan() throws IOException {
		logTestStart();

		Kml kml = run("kml/Bryton.xml");
		Placemark actual = assumePlacemark(kml);

		assertThat(actual.getTimePrimitive()).isInstanceOf(TimeSpan.class);
		TimeSpan timeSpan = (TimeSpan) actual.getTimePrimitive();
		assertThat(timeSpan.getBegin()).isEqualTo("2023-07-26T07:47:59Z");
	}

	/**
	 * DOCME add JavaDoc for method assertPlacemark
	 * 
	 * @param actual
	 * @since 0.1.0
	 */
	private void assertPlacemark(Kml actual) {
		assertThat(actual.getFeature()).isInstanceOf(Document.class);
		Document document = (Document) actual.getFeature();
		assertThat(document.getFeature()).hasSize(1);
		assertThat(document.getFeature().get(0)).isInstanceOf(Placemark.class);
	}

	/**
	 * DOCME add JavaDoc for method assumePlacemark
	 * 
	 * @param actual
	 * @return
	 * @since 0.1.0
	 */
	private Placemark assumePlacemark(Kml actual) {
		try {
			assertPlacemark(actual);
		} catch (AssertionError e) {
			Assumptions.abort(e.getMessage());
		}

		return (Placemark) ((Document) actual.getFeature()).getFeature().get(0);
	}

	/**
	 * DOCME add JavaDoc for method run
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 * @since 0.1.0
	 */
	private Kml run(String file) throws IOException {
		String fileName = getTempDirectory() + "/file.kml";

		FileTestingUtil.provideResourceAsFile(file, fileName);

		CleanBrytonData.run(fileName);

		try {
			return KmlUtil.readKml(fileName);
		} catch (IOException e) {
			// TODO implement error handling
			throw new RuntimeException("Error at 'run': " + e.getMessage(), e);
		}
	}
}
