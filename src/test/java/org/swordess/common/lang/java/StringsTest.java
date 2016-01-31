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

package org.swordess.common.lang.java;

import org.junit.Test;
import org.swordess.common.lang.StringsKt;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class StringsTest {

    @Test
    public void testWithPrefix() {
        String prefix = "/usr";
        assertEquals("/usr", StringsKt.withPrefix(null, prefix));
        assertEquals("/usr", StringsKt.withPrefix("", prefix));
        assertEquals("/usr", StringsKt.withPrefix("/usr", prefix));
        assertEquals("/usr/bin", StringsKt.withPrefix("/bin", prefix));
    }

    @Test
    public void testWithoutPrefix() {
        String prefix = "/usr";
        assertEquals("", StringsKt.withoutPrefix(null, prefix));
        assertEquals("", StringsKt.withoutPrefix("", prefix));
        assertEquals("/local", StringsKt.withoutPrefix("/usr/local", prefix));
        assertEquals("/opt/local", StringsKt.withoutPrefix("/opt/local", prefix));
    }

    @Test
    public void testWithSuffix() {
        String suffix = "/bin";
        assertEquals("/bin", StringsKt.withSuffix(null, suffix));
        assertEquals("/bin", StringsKt.withSuffix("", suffix));
        assertEquals("/bin", StringsKt.withSuffix("/bin", suffix));
        assertEquals("/usr/bin", StringsKt.withSuffix("/usr", suffix));
    }

    @Test
    public void testWithoutSuffix() {
        String suffix = "/local";
        assertEquals("", StringsKt.withoutSuffix(null, suffix));
        assertEquals("", StringsKt.withoutSuffix("", suffix));
        assertEquals("/usr", StringsKt.withoutSuffix("/usr/local", suffix));
        assertEquals("/usr/doc", StringsKt.withoutSuffix("/usr/doc", suffix));
    }

    @Test
    public void testFilterNotEmpty() {
        assertArrayEquals(new String[0], StringsKt.filterNotEmpty(new String[0]));
        assertArrayEquals(new String[] { "a" }, StringsKt.filterNotEmpty(new String[] { "a", "" }));
    }

}
