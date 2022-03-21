package com.jab125.thonkutil;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public final class Main {
    public static void main(String[] args) {
        final ResourceBundle warningApi = ResourceBundle.getBundle("lang/ThonkUtil", Locale.getDefault(), new ResourceBundle.Control() {
            @Override
            public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload) throws IllegalAccessException, InstantiationException, IOException {
                final String bundleName = toBundleName(baseName, locale);
                final String resourceName = toResourceName(bundleName, "properties");

                try (InputStream stream = loader.getResourceAsStream(resourceName)) {
                    if (stream != null) {
                        try (InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
                            return new PropertyResourceBundle(reader);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return super.newBundle(baseName, locale, format, loader, reload);
            }
        });
        String message = warningApi.getString("thonkutil.warning");

        if (GraphicsEnvironment.isHeadless()) {
            System.err.println(message);
        } else {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            } catch (ReflectiveOperationException | UnsupportedLookAndFeelException ignored) {
                // Ignored
            }

            JOptionPane.showMessageDialog(null, message);
            System.exit(-1);
        }
    }
}