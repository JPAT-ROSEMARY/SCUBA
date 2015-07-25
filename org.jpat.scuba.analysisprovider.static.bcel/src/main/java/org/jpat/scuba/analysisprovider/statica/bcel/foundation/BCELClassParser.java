package org.jpat.scuba.analysisprovider.statica.bcel.foundation;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.bcel.classfile.ClassFormatException;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.ClassGen;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.MethodGen;
import org.jpat.scuba.common.util.FileUtil;
import org.jpat.scuba.common.util.IPathResolver;
import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.IMethodAnalyser;
import org.jpat.scuba.core.model.benchmark.BytecodeArtifact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BCELClassParser extends AbstractBCELClassParser
{
    private static final Logger LOGGER = LoggerFactory.getLogger(BCELClassParser.class);

    public BCELClassParser(final FANChallenge benchmark, final IMethodAnalyser methodAnalyser)
    {
        super(benchmark, methodAnalyser);
    }

    @Override
    public final void parse(final BytecodeArtifact bytecodeArtifact, final IPathResolver directoryResolver)
    {
        assert null != bytecodeArtifact : "'bytecodeArtifact' of method 'parse' must not be null";

        try
        {
            final ClassParser cp = new ClassParser(bytecodeArtifact.getArtifactSystemFile().getAbsolutePath());

            assert null != cp : "'cp' of method 'parse' must not be null";

            final JavaClass clazz = cp.parse();

            final ClassGen classGen = new ClassGen(clazz);

            final ConstantPoolGen cnstntPoolGen = classGen.getConstantPool();

            final List<Method> methodList = Arrays.asList(clazz.getMethods());
            methodList.forEach(t -> analyseNextMethod(clazz, classGen, cnstntPoolGen, t));

            assert null != classGen : "'classGen' of method 'parse' must not be null";

            final String fileAbsolutePath = bytecodeArtifact.absolutePath();
            this.benchmark.addBytecodeArtificatAnalysed(FileUtil.replaceDotWithFileSeparator(clazz.getClassName()), classGen.getJavaClass()
                    .getBytes(), fileAbsolutePath, directoryResolver, bytecodeArtifact.isClassFile(), classGen.isInterface());
        }
        catch (IOException | ClassFormatException e)
        {
            LOGGER.error("Error while parsing '{}'", bytecodeArtifact);
            LOGGER.error("Error while parsing a bytecode artifact", e);
        }
    }

    private void analyseNextMethod(final JavaClass clazz, final ClassGen classGen, final ConstantPoolGen cnstntPoolGen, final Method nextMethod)
    {
        final MethodGen nextMethodGen = new MethodGen(nextMethod, clazz.getClassName(), cnstntPoolGen);
        this.methodAnalyser.execute(nextMethodGen, nextMethod.getName());
        pareseInternal(classGen, nextMethod, nextMethodGen);
    }

    @Override
    protected void pareseInternal(final ClassGen classGen, final Method nextMethod, final MethodGen nextMethodGen)
    {
        /** Nothing to do so far*/
    }
}
