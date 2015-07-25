package org.jpat.scuba.analysisprovider.dynamic.asm.foundation;

import org.jpat.scuba.common.util.IPathResolver;
import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.IClassParser;
import org.jpat.scuba.core.model.IMethodAnalyser;
import org.jpat.scuba.core.model.benchmark.BytecodeArtifact;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public final class ASMClassInstrumentor extends ClassVisitor implements IClassParser, Opcodes
{
    protected final IMethodAnalyser methodAnalyser;
    protected String className;
    protected boolean isInterface;
    private final FANChallenge benchmark;

    public ASMClassInstrumentor(final FANChallenge benchmark, final IMethodAnalyser methodAnalyser)
    {
        super(ASM5);

        assert null != benchmark : "Parameter 'benchmark' of 'ASMClassInstrumentor'' ctor must not be null";
        assert null != methodAnalyser : "Parameter 'methodAnalyser' of 'ASMClassInstrumentor'' ctor must not be null";

        this.benchmark = benchmark;
        this.methodAnalyser = methodAnalyser;
    }

    protected ASMClassInstrumentor(final FANChallenge benchmark, final ClassVisitor cvPassed, final IMethodAnalyser methodInstrumentor)
    {
        super(ASM5, cvPassed);

        assert null != benchmark : "Parameter 'benchmark' of method 'ASMClassInstrumentor' must not be null";
        assert methodInstrumentor != null : "Parameter 'methodInstrumentor' of 'ASMInstrumentAdapter''s ctor must not be null";

        this.benchmark = benchmark;
        this.methodAnalyser = methodInstrumentor;
    }

    @Override
    public void parse(final BytecodeArtifact bytecodeArtifact, final IPathResolver directoryResolver)
    {
        assert null != bytecodeArtifact : "Parameter 'bytecodeArtifact' of method 'parse' must not be null";

        /** (1) */
        final ClassReader cr = new ClassReader(bytecodeArtifact.getBytesOriginal());

        /** (2) */
        //cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
        final ClassWriter cw = new ClassWriter(cr, 0);

        /** (3) */
        final ASMClassInstrumentor ca = constructSelf(cw);

        /** (4) */
        cr.accept(ca, ClassReader.EXPAND_FRAMES);

        assert null != ca : "'ca' of method 'parse' must not be null";
        assert null != cw : "'cw' of method 'parse' must not be null";

        final String fileAbsolutePath = bytecodeArtifact.absolutePath();
        this.benchmark.addBytecodeArtificatAnalysed(ca.className, cw.toByteArray(), fileAbsolutePath, directoryResolver,
                bytecodeArtifact.isClassFile(), this.isInterface);
    }

    protected ASMClassInstrumentor constructSelf(final ClassWriter cw)
    {
        return new ASMClassInstrumentor(this.benchmark, cw, this.methodAnalyser);
    }

    @Override
    public void visit(final int version, final int access, final String name, final String signature, final String superName,
            final String[] interfaces)
    {
        this.cv.visit(version, access, name, signature, superName, interfaces);
        this.className = name;
        this.isInterface = 0 != (access & Opcodes.ACC_INTERFACE);
    }

    @Override
    public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions)
    {
        final MethodVisitor mv = this.cv.visitMethod(access, name, desc, signature, exceptions);
        return (MethodVisitor) this.methodAnalyser.execute(mv, name);
    }

    @Override
    public void visitEnd()
    {
        this.cv.visitEnd();
    }
}
