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

package org.swordess.common.lang.io

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.fail

class ResourcesTest {

    @Test
    fun testResourceNameAsStreamCanFindFilesystemFile() {
        val stream = "src/test/resources/message.properties".resourceNameAsStream()
        assertNotNull(stream)
    }

    @Test
    fun testResourceNameAsStreamCanFindClasspathFile() {
        val stream = "message.properties".resourceNameAsStream()
        assertNotNull(stream)
    }

    @Test
    fun testResourceNameAsSteamShouldThrowExceptionIfNotFound() {
        try {
            "not_exist.properties".resourceNameAsStream()
            fail("RuntimeException is expected")
        } catch (e: RuntimeException) {
            assertEquals("resource not found: not_exist.properties", e.message)
        }
    }

    @Test
    fun testResourceNameAsURLCanFindFilesystemFile() {
        val url = "src/test/resources/message.properties".resourceNameAsURL()
        assertNotNull(url)
    }

    @Test
    fun testResourceNameAsURLCanFindClasspathFile() {
        val url = "message.properties".resourceNameAsURL()
        assertNotNull(url)
    }

    @Test
    fun testResourceNameAsURLShouldThrowExceptionIfNotFound() {
        try {
            "not_exist.properties".resourceNameAsURL()
            fail("RuntimeException is expected")
        } catch (e: RuntimeException) {
            assertEquals("resource not found: not_exist.properties", e.message)
        }
    }

}