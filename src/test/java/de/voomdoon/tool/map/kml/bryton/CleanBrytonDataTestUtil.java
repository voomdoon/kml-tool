package de.voomdoon.tool.map.kml.bryton;

import static org.assertj.core.api.Assertions.assertThat;

import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import lombok.experimental.UtilityClass;

/**
 * DOCME add JavaDoc for
 *
 * @author André Schulz
 *
 * @since DOCME add inception version number
 */
@UtilityClass
public class CleanBrytonDataTestUtil {

	/**
	 * DOCME add JavaDoc for method assertPlacemark
	 * 
	 * @param actual
	 * @since 0.1.0
	 */
	public static void assertPlacemark(Kml actual) {
		assertThat(actual.getFeature()).isInstanceOf(Document.class);
		Document document = (Document) actual.getFeature();
		assertThat(document.getFeature()).hasSize(1);
		assertThat(document.getFeature().get(0)).isInstanceOf(Placemark.class);
	}
}
