package eighty4.akimbo.compile;

import eighty4.akimbo.AkimboApp;
import eighty4.akimbo.compile.source.SourceWriter;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class AkimboProcessor extends AbstractProcessor {

    private ProcessorContext processorContext;

    private ApplicationResolver applicationResolver;

    @Override
    public synchronized final void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        processorContext = new ProcessorContext();
        applicationResolver = new ApplicationResolver(processorContext, new SourceWriter(processorContext, processingEnvironment.getFiler()));
    }

    @Override
    public final boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        try {
            Set<TypeElement> userSources = getUserSources(roundEnvironment);
            applicationResolver.addSources(userSources);
            processorContext.incrementRound();
        } catch (Exception e) {
            e.printStackTrace();
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Error running processor: " + Optional.ofNullable(e.getMessage()).orElse(e.getClass().toString()));
        }
        return false;
    }

    private Set<TypeElement> getUserSources(RoundEnvironment roundEnvironment) {
        return roundEnvironment.getRootElements().stream()
                .map(e -> (TypeElement) e)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Set.of(AkimboApp.class.getName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_11;
    }

    @Override
    public Set<String> getSupportedOptions() {
        return Set.of("akimbo.debug");
    }
}
