package org.jpat.scuba.analysisprovider.statica.asm.scala.foundation;

import org.jpat.scuba.common.util.IPathResolver;
import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.IClassParser;
import org.jpat.scuba.core.model.IMethodAnalyser;
import org.jpat.scuba.core.model.benchmark.BytecodeArtifact;

import scala.tools.asm.ClassReader;
import scala.tools.asm.ClassVisitor;
import scala.tools.asm.MethodVisitor;
import scala.tools.asm.Opcodes;

public final class ASMScalaClassParser extends ClassVisitor implements IClassParser, Opcodes
{
    private final IMethodAnalyser methodAnalyser;
    private String className;
    private boolean isInterface;
    private final FANChallenge benchmark;

    public ASMScalaClassParser(final FANChallenge benchmark, final IMethodAnalyser methodAnalyser)
    {
        super(ASM5);

        assert null != benchmark : "Parameter 'benchmark' of 'ASMScalaClassParser''s ctor must not be null";
        assert methodAnalyser != null : "Parameter 'methodAnalyser' of 'ASMScalaClassParser''s ctor must not be null";

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
