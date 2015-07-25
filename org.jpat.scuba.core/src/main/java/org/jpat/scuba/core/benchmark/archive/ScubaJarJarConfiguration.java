package org.jpat.scuba.core.benchmark.archive;

import org.jpat.scuba.common.util.ResourceBundleGrabber;
import org.jpat.scuba.common.util.StringUtil;

public enum ScubaJarJarConfiguration
{
    $inst;
    
    public static final String METAMANIFEST_PATH = "META-INF/MANIFEST.MF";
    public static final String LOGBACK_CORE_JAR = ResourceBundleGrabber.getString("JarPackager.LOGBACK_CORE_JAR");
    public static final String LOGBACK_CLASSIC_JAR = ResourceBundleGrabber.getString("JarPackager.LOGBACK_CLASSIC_JAR");
    public static final String SLF4J_API_JAR = ResourceBundleGrabber.getString("JarPackager.SLF4J_API_JAR");
    public static final String TRUEZIP_JAR = ResourceBundleGrabber.getString("JarPackager.TRUEZIP_JAR");
    public static final String COMMONS_IO_JAR = ResourceBundleGrabber.getString("JarPackager.COMMONS_IO");
    public static final String FANCHALLENGE_ANALYSER_JAR = ResourceBundleGrabber.getString("JarPackager.FANCHALLENGE_ANALYSER_JAR");
    public static final String DIR_UP = ResourceBundleGrabber.getString("JarPackager.DIR_UP");
    public static final String CLASS_PATH_VALUE = StringUtil.compose(DIR_UP, LOGBACK_CORE_JAR,
            LOGBACK_CLASSIC_JAR, SLF4J_API_JAR, TRUEZIP_JAR, COMMONS_IO_JAR,
            FANCHALLENGE_ANALYSER_JAR);
}
