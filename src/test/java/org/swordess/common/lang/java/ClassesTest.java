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

import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.KClass;
import org.junit.Test;
import org.swordess.common.lang.Classes;
import org.swordess.common.lang.java.test.Marker;
import org.swordess.common.lang.java.test.foo.MyFoo;
import org.swordess.common.lang.java.test.foo.bar.MyBar;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClassesTest {

    @Test
    public void testUnderPackageShouldFindMatchesIncludingNestedPackages() {
        List<KClass<?>> found = Classes.underPackage("org.swordess.common.lang.java.test.foo", new Function1<KClass<?>, Boolean>() {
            @Override
            public Boolean invoke(KClass<?> kClass) {
                return Marker.class.isAssignableFrom(JvmClassMappingKt.getJavaClass(kClass));
            }
        });
        assertEquals(2, found.size());
        assertTrue(found.contains(JvmClassMappingKt.getKotlinClass(MyFoo.class)) && found.contains(JvmClassMappingKt.getKotlinClass(MyBar.class)));
    }

}
