package org.jpat.scuba.common.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.jpat.scuba.foundation.io.SimpleLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.schlichtherle.truezip.file.TFile;

public final class FileUtil
{
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    private static final char PATH_SEPARATOR_CHAR_2_BE_CONVERTED = '\\';
    private static final char PATH_SEPARATOR_CHAR = '/';
    private static final String CLASSFILE_EXTENSION = ".class";
    private static final String USER_HOME_DIR = System.getProperty("user.home");
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");
    private static final String JAR_EXTENSION = "jar";
    private static final String SLASH = "/";
    private static final String DOT = ".";
    private static final String EMPTY_STRING = "";

    private FileUtil()
    {
        super();
    }

    public static String convertPath2UniversalForm(final String path)
    {
        assert null != path : "Parameter 'path' of method 'convertPath2UniversalForm' must not be null";

        return path.replace(PATH_SEPARATOR_CHAR_2_BE_CONVERTED, PATH_SEPARATOR_CHAR);
    }

    public static void prepareCleanAbsPathDirectory(final String absolutePath)
    {
        assert null != absolutePath && !absolutePath.isEmpty() : "Parameter 'absolutePath' of method 'prepareCleanAbsPathDirectory' must not be empty";

        final TFile absoluteFile = new TFile(absolutePath);
        /** Case (1) if file exists but it is not a directory */
        if (absoluteFile.exists() && !absoluteFile.isDirectory())
        {
            /** delete the file and create a directory */
            deleteFileAndCreateDirectory(absoluteFile);
        }
        else if (absoluteFile.exists() && absoluteFile.isDirectory())
        {
            cleanDirectory(absoluteFile);
        }
        else
        {
            forceMkdir(absoluteFile);
        }
    }

    private static void forceMkdir(final TFile absoluteFile)
    {
        try
        {
            FileUtils.forceMkdir(absoluteFile);
        }
        catch (IOException e)
        {
            LOGGER.error(e.getMessage());
        }
    }

    private static void cleanDirectory(final TFile absoluteFile)
    {
        try
        {
            FileUtils.cleanDirectory(absoluteFile);
        }
        catch (IOException e)
        {
            SimpleLogger.error(FileUtil.LOGGER, e, FileUtil.class, "; Method 'cleanUpAbsPathDirectory'");
        }
    }

    private static void deleteFileAndCreateDirectory(final TFile absoluteFile)
    {
        try
        {
            absoluteFile.rm();

            absoluteFile.mkdir();
        }
        catch (IOException e)
        {
            LOGGER.error(e.getMessage());
        }
    }

    public static String convertRelativeToAbs(final String filePath)
    {
        assert filePath != null && filePath.length() > 0 : "Parameter 'filePath' of method 'convertRelativeToAbs' must not be empty"; //$NON-NLS-1$

        final TFile file = new TFile(filePath);

        assert file != null : "'file' of method 'convertRelativeToAbs' must not be null"; //$NON-NLS-1$

        if (!file.isAbsolute())
        {
            final TFile relativePath = new TFile(filePath);
            return relativePath.getCanOrAbsPath();
        }
        return filePath;
    }

    public static TFile mkdir(final String absPath)
    {
        assert null != absPath : "Parameter 'absPath' of 'mkdir''s ctor must not be null";

        final TFile absDirectory = new TFile(absPath);
        absDirectory.mkdir();
        return absDirectory;
    }

    public static boolean isArchive(final TFile absFile)
    {
        return null != absFile && absFile.isArchive();
    }

    /** @param file
     *            Bytecode class file (type in Java or Scala)
     * @return whether this is a .class file TODO Still impl. is naiive. */
    public static boolean isClassFile(final TFile file)
    {
        assert null != file : "Parameter 'file' of method 'isClassFile' must not be null";

        return !file.isDirectory() && file.isFile() && file.getName().endsWith(FileUtil.CLASSFILE_EXTENSION);
    }

    public static String composeAbsPathFromRelativePath(final String relativeFilePath, final String targetDirectory)
    {
        assert null != relativeFilePath && !relativeFilePath.isEmpty() : "Parameter 'relativeFilePath' of method 'composeAbsPathFromRelativePath' must not be empty";
        assert !new TFile(relativeFilePath).isAbsolute() : "Parameter 'relativeFilePath' of method 'composeAbsPathFromRelativePath' must not be absolute";
        assert null != targetDirectory && !targetDirectory.isEmpty() : "Parameter 'targetDirectory' of method 'composeAbsPathFromRelativePath' must not be empty";

        final StringBuilder targetAbsolutePath = new StringBuilder();
        targetAbsolutePath.append(FileUtil.USER_HOME_DIR);
        targetAbsolutePath.append(FileUtil.FILE_SEPARATOR);
        targetAbsolutePath.append(targetDirectory);
        targetAbsolutePath.append(FileUtil.FILE_SEPARATOR);
        targetAbsolutePath.append(FileUtil.fixFileSeparator(relativeFilePath));
        targetAbsolutePath.trimToSize();
        return targetAbsolutePath.toString();
    }

    private static String fixFileSeparator(final String filePath)
    {
        return filePath.replace(FileUtil.SLASH, FileUtil.FILE_SEPARATOR);
    }

    private static String extractFileName(final String filePath)
    {
        assert null != filePath && !filePath.isEmpty() : "Parameter 'filePath' of method 'extractFileName' must not be empty";
        final File file = new File(filePath);
        if (file.isAbsolute())
        {
            final File parent = file.getParentFile();
            return parent.toURI().relativize(file.toURI()).getPath();
        }
        return filePath;
    }

    public static String extractFileNameWithoutDotExtension(final TFile file)
    {
        assert null != file : "Parameter 'file' of method 'extractFileNameWithoutDotExtension' must not be null";

        final String fileName = FileUtil.extractFileName(file.getAbsolutePath());
        return removeDotExtension(fileName);
    }

    public static String removeDotExtension(final String nextName)
    {
        assert null != nextName && !nextName.isEmpty() : "Parameter 'nextName' of method 'removeDotExtension' must not be empty";

        if (StringUtil.assertNotEmpty(nextName))
        {
            final int lastIndexOfDOT = nextName.lastIndexOf(FileUtil.DOT);
            if (-1 < lastIndexOfDOT)
            {
                return nextName.substring(0, lastIndexOfDOT);
            }
        }
        return nextName;
    }

    public static String postFixFileName(final TFile file, final String postFix)
    {
        assert null != file : "'file' of method 'postFixFileName' must not be null";
        assert null != postFix && !postFix.isEmpty() : "Parameter 'postFix' of method 'postFixFileName' must not be empty";

        final String fileName = FileUtil.extractFileName(file.getAbsolutePath());
        if (StringUtil.assertNotEmpty(fileName))
        {
            final int lastIndexOfDOT = fileName.lastIndexOf(FileUtil.DOT);
            if (-1 < lastIndexOfDOT)
            {
                final String fileExtension = fileName.substring(lastIndexOfDOT + 1);
                final String everyThingUntilLastDot = fileName.substring(0, lastIndexOfDOT);
                final StringBuilder strBldr = new StringBuilder();
                strBldr.append(everyThingUntilLastDot);
                strBldr.append(postFix);
                strBldr.append(DOT);
                strBldr.append(fileExtension);
                strBldr.trimToSize();
                return strBldr.toString();
            }
        }
        return file.getCanOrAbsPath();
    }

    public static String relativise(final String absPath, final String parentAbsPath)
    {
        assert null != absPath && !absPath.isEmpty() : "Parameter 'absPath' of method 'relativise' must not be empty";
        assert null != parentAbsPath && !parentAbsPath.isEmpty() : "Parameter 'parentAbsPath' of method 'relativise' must not be empty";

        final File file = new File(absPath);
        final File parent = new File(parentAbsPath);
        final String fileName = parent.toURI().relativize(file.toURI()).getPath();
        if (fileName.isEmpty())
        {
            return absPath;
        }
        return fileName;
    }

    public static String replaceDotWithFileSeparator(final String value)
    {
        assert null != value && !value.isEmpty() : "Parameter 'value' of method 'replaceDotWithFileSeparator' must not be empty";
        return value.replace(DOT, FILE_SEPARATOR);
    }

    /** Should not be used for system file - file separator is not system independent
     * 
     * @param parent
     *            first parent directory
     * @param nextName
     *            subject (relative) path name
     * @return a new path composed of the parent and the subject path with a slash ('/') in between */
    public static String gluePathNameAndParentWithSlash(final String parent, final String nextName)
    {
        assert null != parent && !parent.isEmpty() : "Parameter 'parent' of method 'composeAbsPathFromParentPathAndFileName' must not be empty";
        assert null != nextName && !nextName.isEmpty() : "Parameter 'nextName' of method 'composeAbsPathFromParentPathAndFileName' must not be empty";

        /*
         * result is: parent + FileUtil.SLASH + nextName
         */
        final StringBuilder strBldr = new StringBuilder();
        strBldr.append(parent);
        strBldr.append(SLASH);
        strBldr.append(nextName);
        strBldr.trimToSize();
        return strBldr.toString();
    }

    public static String gluePathAndParentWithSystemFileSeparator(final String parent, final String path)
    {
        assert null != parent && !parent.isEmpty() : "Parameter 'parent' of method 'gluePathAndParentWithSystemFileSeparator' must not be empty";
        assert null != path && !path.isEmpty() : "Parameter 'path' of method 'gluePathAndParentWithSystemFileSeparator' must not be empty";

        final StringBuilder strBldr = new StringBuilder();
        strBldr.append(parent);
        strBldr.append(FILE_SEPARATOR);
        strBldr.append(path);
        strBldr.trimToSize();
        return strBldr.toString();
    }

    public static String composeSystemCompatiblePath(final String someParent, final String specificDirName)
    {
        assert null != someParent && !someParent.isEmpty() : "Parameter 'someParent' of method 'composeSystemCompatiblePath' must not be empty";
        assert null != specificDirName && !specificDirName.isEmpty() : "Parameter 'specificDirName' of method 'composeSystemCompatiblePath' must not be empty";

        final StringBuilder strBldr = new StringBuilder();
        strBldr.append(someParent);
        strBldr.append(FileUtil.FILE_SEPARATOR);
        strBldr.append(specificDirName);
        strBldr.trimToSize();
        return strBldr.toString();
    }

    public static String composePathInUserHomeDirectory(final String relativePath)
    {
        assert null != relativePath && !relativePath.isEmpty() : "Parameter 'relativePath' of method 'composePathInUserHomeDirectory' must not be empty";

        final StringBuilder strBldr = new StringBuilder();
        strBldr.append(FileUtil.USER_HOME_DIR);
        strBldr.append(FileUtil.FILE_SEPARATOR);
        strBldr.append(relativePath);
        strBldr.trimToSize();
        return strBldr.toString();
    }

    public static String postFixPathWithSystemCompatibleFileSeparator(final String path)
    {
        assert null != path && !path.isEmpty() : "Parameter 'path' of method 'postFixPathWithSystemCompatibleFileSeparator' must not be empty";

        final StringBuilder strBldr = new StringBuilder();
        strBldr.append(path);
        strBldr.append(FileUtil.FILE_SEPARATOR);
        strBldr.trimToSize();
        return strBldr.toString();
    }

    public static String postFixTypeInternalNameWithClassExtension(final String typeInternalName)
    {
        assert null != typeInternalName && !typeInternalName.isEmpty() : "Parameter 'typeInternalName' of method 'postFixTypeInternalNameWithClassExtension' must not be empty";

        final StringBuilder strBldr = new StringBuilder();
        strBldr.append(typeInternalName);
        strBldr.append(CLASSFILE_EXTENSION);
        strBldr.trimToSize();
        return strBldr.toString();
    }

    public static byte[] readInputStream2Bytes(final InputStream inputStream) throws IOException
    {
        assert null != inputStream : "Parameter 'inputStream' of method 'readInputStream2Bytes' must not be null";

        return IOUtils.toByteArray(inputStream);
    }

    public static byte[] readFileToByteArray(final TFile nextFile)
    {
        assert null != nextFile : "Parameter 'nextFile' of method 'readFileToByteArray' must not be null";

        try
        {
            return FileUtils.readFileToByteArray(nextFile);
        }
        catch (IOException e)
        {
            LOGGER.error(e.getMessage());
        }

        return null;
    }

    /** Naiive impl. TODO fix the bug when there are more than '-' and only the last one follows the build number
     * 
     * @param file
     *            the file (most probably the archive which usually comes with a release/build version)
     * @return the name without the trailing build version number with the dash */
    public static String removeTrailingBuildVersion(final TFile file)
    {
        assert null != file : "Parameter 'file' of method 'removeTrailingBuildVersion' must not be null";

        final String fileName = file.getName();
        final int indexOfFirstChar = fileName.indexOf('-');
        if (0 < indexOfFirstChar && fileName.length() - 1 > indexOfFirstChar)
        {
            return fileName.substring(0, indexOfFirstChar);
        }
        return fileName;
    }

    public static String removeBuildVersionAndDotExtension(final TFile file)
    {
        assert null != file : "Parameter 'file' of method 'removeBuildVersionAndDotExtension' must not be null";

        final String fileName = file.getName();
        final String finalNamewithoutBuildVersion = FileUtil.removeTrailingBuildVersion(new TFile(fileName));
        return FileUtil.extractFileNameWithoutDotExtension(new TFile(finalNamewithoutBuildVersion));
    }

    public static String extractFileExtension(final TFile file)
    {
        assert null != file : "Parameter 'file' of method 'extractFileExtension' must not be null";

        final String fileName = file.getName();
        final int lastIndexOfDOT = fileName.lastIndexOf(FileUtil.DOT);
        if (-1 == lastIndexOfDOT)
        {
            /** this file has no extension */
            return EMPTY_STRING;
        }
        final int lastIndexOfFileSeparator = fileName.lastIndexOf(FILE_SEPARATOR);
        if (lastIndexOfFileSeparator > lastIndexOfDOT)
        {
            /** means file name is still after the path separator */
            final String fileExactName = fileName.substring(lastIndexOfFileSeparator);
            return extractFileExtension(new TFile(fileExactName));
        }
        return fileName.substring(lastIndexOfDOT + 1);
    }

    public static boolean isJar(final String filePath)
    {
        assert null != filePath && !filePath.isEmpty() : "Parameter 'filePath' of method 'isJar' must not be empty";

        final TFile file = new TFile(filePath);
        return isArchive(file) && 0 == JAR_EXTENSION.compareTo(extractFileExtension(file));
    }

    public static TFile create(final String absPath)
    {
        assert null != absPath && !absPath.isEmpty() : "Parameter 'absPath' of method 'create' must not be empty";

        final TFile file = new TFile(absPath);

        return file;
    }

    public static void create(final TFile file)
    {
        assert file.isAbsolute() : "Path '" + file.getAbsolutePath() + "' should be absolute";

        try
        {
            file.createNewFile();
        }
        catch (IOException exception)
        {
            LOGGER.debug("Error while creating a new file in 'create' method.", exception);
        }
    }
}
