package eighty4.akimbo.compile.source;

import eighty4.akimbo.compile.ProcessorContext;
import eighty4.akimbo.compile.source.application.AkimboApplicationMainFile;

import javax.annotation.processing.Filer;
import javax.lang.model.element.TypeElement;
import java.util.HashSet;
import java.util.Set;

import static eighty4.akimbo.compile.util.AkimboProcessorUtils.compactFqn;

public class SourceWriter {

    private final ProcessorContext processorContext;

    private final Filer filer;

    private final Set<String> writtenSources = new HashSet<>();

    private boolean wiringWritten = false;

    public SourceWriter(ProcessorContext processorContext, Filer filer) {
        this.processorContext = processorContext;
        this.filer = filer;
    }

    public boolean isWrittenSourceFile(TypeElement typeElement) {
        return writtenSources.contains(typeElement.getQualifiedName().toString());
    }

    public void writeAkimboWiring() {
        if (wiringWritten) {
            return;
        }
        String akimboSourcesPackageName = processorContext.getAkimboSourcesPackageName();
        writeSourceFile(new AkimboApplicationMainFile(akimboSourcesPackageName));
        wiringWritten = true;
    }

    public void writeSourceFile(SourceFile sourceFile) {
        writtenSources.add(sourceFile.getType().reflectionName());
        sourceFile.writeTo(processorContext, filer);
        System.out.println("wrote source file " + compactFqn(sourceFile.getType().canonicalName()));
    }
}
