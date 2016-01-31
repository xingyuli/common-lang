/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Vic Lau
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.swordess.common.lang.java.io;

import org.junit.Test;
import org.swordess.common.lang.io.ResourcesKt;

import java.io.InputStream;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class ResourcesTest {

    @Test
    public void testResourceNameAsStreamCanFindFilesystemFile() {
        InputStream stream = ResourcesKt.resourceNameAsStream("src/test/resources/message.properties");
        assertNotNull(stream);
    }

    @Test
    public void testResourceNameAsStreamCanFindClasspathFile() {
        InputStream stream = ResourcesKt.resourceNameAsStream("message.properties");
        assertNotNull(stream);
    }

    @Test
    public void testResourceNameAsSteamShouldThrowExceptionIfNotFound() {
        try {
            ResourcesKt.resourceNameAsStream("not_exist.properties");
            fail("RuntimeException is expected");
        } catch (RuntimeException e) {
            assertEquals("resource not found: not_exist.properties", e.getMessage());
        }
    }

    @Test
    public void testResourceNameAsURLCanFindFilesystemFile() {
        URL url = ResourcesKt.resourceNameAsURL("src/test/resources/message.properties");
        assertNotNull(url);
    }

    @Test
    public void testResourceNameAsURLCanFindClasspathFile() {
        URL url = ResourcesKt.resourceNameAsURL("message.properties");
        assertNotNull(url);
    }

    @Test
    public void testResourceNameAsURLShouldThrowExceptionIfNotFound() {
        try {
            ResourcesKt.resourceNameAsURL("not_exist.properties");
            fail("RuntimeException is expected");
        } catch (RuntimeException e) {
            assertEquals("resource not found: not_exist.properties", e.getMessage());
        }
    }

}
