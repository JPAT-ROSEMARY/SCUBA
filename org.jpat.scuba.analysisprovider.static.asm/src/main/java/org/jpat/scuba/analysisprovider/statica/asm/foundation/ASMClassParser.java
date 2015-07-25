package org.jpat.scuba.analysisprovider.statica.asm.foundation;

import org.jpat.scuba.common.util.IPathResolver;
import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.IClassParser;
import org.jpat.scuba.core.model.IMethodAnalyser;
import org.jpat.scuba.core.model.benchmark.BytecodeArtifact;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public final class ASMClassParser extends ClassVisitor implements IClassParser, Opcodes
{
    private final IMethodAnalyser methodAnalyser;
    private String className;
    private boolean isInterface;
    private final FANChallenge benchmark;

    public ASMClassParser(final FANChallenge benchmark, final IMethodAnalyser methodAnalyser)
    {
        super(ASM5);

        assert null != benchmark : "Parameter 'benchmark' of 'ASMClassParser''s ctor must not be null";
        assert methodAnalyser != null : "Parameter 'methodAnalyser' of 'ASMAnalysisAdapter''s ctor must not be null";

        this.benchmark = benchmark;
        this.methodAnalyser = methodAnalyser;
    }

    @Override
    public void parse(final BytecodeArtifact nextBytecodeArtifact, final IPathResolver directoryResolver)
    {
        assert null != nextBytecodeArtifact : "Parameter 'nextBytecodeArtifact' of method 'parse' must not be null";

        final ClassReader cr = new ClassReader(nextBytecodeArtifact.getBytesOriginal());
        cr.accept(this, ClassReader.SKIP_DEBUG);

        assert null != cr : "'cr' of method 'parse' must not be null";

        final String fileAbsolutePath = nextBytecodeArtifact.absolutePath();
        //TODO this should be done by the controller and not here of course.
        this.benchmark.addBytecodeArtificatAnalysed(this.className, cr.b, fileAbsolutePath, directoryResolver,
                nextBytecodeArtifact.isClassFile(), this.isInterface);
    }

    @Override
    public void visit(final int version, final int access, final String name, final String signature, final String superName,
            final String[] interfaces)
    {
        this.className = name;
        this.isInterface = 0 != (access & Opcodes.ACC_INTERFACE);
    }

    @Override
    public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions)
    {
        /**
         * No need to check whether it is an interface
         * ASM framework 5.0.4 can handle this
         *  // if (!this.isInterface)
         */
        return (MethodVisitor) this.methodAnalyser.execute(null, name);
    }
}
