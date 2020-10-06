package eighty4.akimbo.compile;

import com.squareup.javapoet.ClassName;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

public class ProcessorContext {

    private static final String AKIMBO_SOURCES_PACKAGE_SUFFIX = ".a_k_i_m_b_o";

    private String packageName;

    private String akimboSourcesPackageName;

    private int round = 0;

    public void initialize(Element akimboAppElement) {
        packageName = ClassName.get((TypeElement) akimboAppElement).packageName();
        akimboSourcesPackageName = packageName + AKIMBO_SOURCES_PACKAGE_SUFFIX;
    }

    public void incrementRound() {
        round++;
    }

    public int getRound() {
        return round;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getAkimboSourcesPackageName() {
        return akimboSourcesPackageName;
    }
}
