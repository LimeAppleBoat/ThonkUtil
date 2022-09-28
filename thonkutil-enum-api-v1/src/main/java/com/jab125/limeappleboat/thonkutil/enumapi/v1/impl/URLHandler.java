/*
 * Copyright (c) 2021, 2022 Jab125 & LimeAppleBoat
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jab125.limeappleboat.thonkutil.enumapi.v1.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.security.Permission;
import java.util.Map;

public class URLHandler extends URLStreamHandler {
    private final Map<String, byte[]> data;
    private static Provider byteDump;

    public static URL create(String name, byte[] stream) {
        return create(Map.of('/' + name.replace('.', '/') + ".class", stream));
    }

    public static URL create(Map<String, byte[]> mixins) {
        try {
            return new URL("thonkutil-generated", null, -1, "/", new URLHandler(mixins));
        } catch (MalformedURLException e) {
            throw new RuntimeException("Unexpected error creating URL", e);
        }
    }

    @Override
    protected URLConnection openConnection(URL url) {
        if (!data.containsKey(url.getPath())) return null;
        return new URLConnectionConk(url, data.get(url.getPath()));
    }

    public interface Provider {
        void get(String string, byte[] stream);
    }

    public URLHandler(Map<String, byte[]> data) {
        this.data = data;
    }

    private static final class URLConnectionConk extends URLConnection {
        private final byte[] bytes; // the actual bytes we will use instead


        protected URLConnectionConk(URL url, byte[] bytes) {
            super(url);
            this.bytes = bytes;
        }

        @Override
        public InputStream getInputStream() {
            if (URLHandler.byteDump != null) {
                URLHandler.byteDump.get(url.getPath().replaceAll("\\.class", ""), bytes);
            }
            return new ByteArrayInputStream(bytes);
        }

        @Override
        public Permission getPermission() {
            return null; // shut
        }

        @Override
        public void connect() throws IOException {
            throw new IOException();
        }
    }
}
