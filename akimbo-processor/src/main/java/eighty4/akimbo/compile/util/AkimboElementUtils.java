package eighty4.akimbo.compile.util;

import eighty4.akimbo.AkimboApp;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.lang.annotation.Annotation;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class AkimboElementUtils {

    public static TypeElement getAkimboAppElement(Set<? extends Element> elements) {
        return elements.stream()
                .filter(e -> hasAnnotation(e, AkimboApp.class))
                .findFirst()
                .map(e -> (TypeElement) e)
                .orElseThrow();
    }

    private static Optional<AnnotationMirror> getAnnotation(Element element, Class<? extends Annotation> annotationType) {
        String annotationName = annotationType.getCanonicalName();
        for (AnnotationMirror annotation : element.getAnnotationMirrors()) {
            if (isSameAnnotation(annotation, annotationName)) {
                return Optional.of(annotation);
            }
        }
        return Optional.empty();
    }

    public static boolean hasAnnotation(Element element, Class<? extends Annotation> annotationType) {
        return getAnnotation(element, annotationType).isPresent();
    }

    private static boolean isSameAnnotation(AnnotationMirror annotationMirror, String annotationName) {
        return Objects.equals(annotationName, annotationMirror.getAnnotationType().toString());
    }
}
