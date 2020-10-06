package eighty4.akimbo.compile;

import eighty4.akimbo.compile.source.SourceWriter;

import javax.lang.model.element.TypeElement;
import java.util.Set;

import static eighty4.akimbo.compile.util.AkimboElementUtils.getAkimboAppElement;

public class ApplicationResolver {

    private final ProcessorContext processorContext;

    private final SourceWriter sourceWriter;

    public ApplicationResolver(ProcessorContext processorContext, SourceWriter sourceWriter) {
        this.processorContext = processorContext;
        this.sourceWriter = sourceWriter;
    }

    public void addSources(Set<TypeElement> elements) {
        if (isNotInitialized()) {
            processorContext.initialize(getAkimboAppElement(elements));
        }
        sourceWriter.writeAkimboWiring();
    }

    private boolean isNotInitialized() {
        return processorContext.getPackageName() == null;
    }
}
