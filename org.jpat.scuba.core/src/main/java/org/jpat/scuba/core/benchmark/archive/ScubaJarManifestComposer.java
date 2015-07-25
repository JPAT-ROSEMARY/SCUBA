package org.jpat.scuba.core.benchmark.archive;

import java.io.IOException;
import java.util.jar.Attributes;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;

import org.jpat.scuba.foundation.io.SimpleLogger;
import org.jpat.scuba.foundation.io.SimpleStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.schlichtherle.truezip.file.TFile;

final class ScubaJarManifestComposer
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ScubaJarManifestComposer.class);

    private ScubaJarManifestComposer()
    {
        super();
    }

    public static Manifest composeManifest(final String archivePathOriginal)
    {
        assert null != archivePathOriginal && !archivePathOriginal.isEmpty() : "Parameter 'archivePathOriginal' of method 'composeManifest' must not be empty";

        try (final JarInputStream jis = new JarInputStream(SimpleStreamReader.read(new TFile(archivePathOriginal)));)
        {
            final Manifest manifest = jis.getManifest();
            /**
             * if manifest is null that means we either have a different archive like Zip or a Jar without manifest
             * TODO treatment required
             */
            assert null != manifest : "'manifest' of method 'composeManifest' must not be null";

            final Attributes atts = manifest.getMainAttributes();
            atts.put(Attributes.Name.CLASS_PATH, ScubaJarJarConfiguration.CLASS_PATH_VALUE);
            return manifest;
        }
        catch (final IOException e)
        {
            SimpleLogger.error(LOGGER, e, ScubaJarManifestComposer.class, "composeManifest", archivePathOriginal);
        }
        return null;
    }
}
