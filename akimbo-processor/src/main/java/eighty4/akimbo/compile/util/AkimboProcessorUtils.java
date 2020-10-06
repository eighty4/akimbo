package eighty4.akimbo.compile.util;

import eighty4.akimbo.compile.source.SourceFile;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AkimboProcessorUtils {

    private static final int COMPACTED_PACKAGE_LENGTH = 2;

    public static String compactFqn(String fqn) {
        int lastPeriodIndex = fqn.lastIndexOf(".");
        if (lastPeriodIndex == -1) {
            return fqn;
        } else {
            String[] splitFqn = fqn.substring(0, lastPeriodIndex - 1).split("\\.");
            String packageName = Stream.of(splitFqn).map(sf -> sf.length() < (COMPACTED_PACKAGE_LENGTH + 1) ? sf : sf.substring(0, COMPACTED_PACKAGE_LENGTH)).collect(Collectors.joining("."));
            String typeName = fqn.substring(lastPeriodIndex + 1);
            return packageName + "." + typeName;
        }
    }

    public static String compactFqn(SourceFile sourceFile) {
        return compactFqn(sourceFile.getType().simpleName());
    }
}
