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

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.swordess.common.lang.io.DirectoryEvent;
import org.swordess.common.lang.io.DirectoryWatcher;

import java.io.File;
import java.io.IOException;
import java.nio.file.StandardWatchEventKinds;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DirectoryWatcherTest {

    private File dir;
    private DirectoryWatcher watcher;

    @Before
    public void setUp() {
        dir = new File("build/tmp/test/${javaClass.simpleName}");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        watcher = new DirectoryWatcher(dir.getAbsolutePath(), StandardWatchEventKinds.ENTRY_CREATE);
        watcher.init();
    }

    @After
    public void tearDown() {
        watcher.destroy();
        for (File it : dir.listFiles()) {
            it.delete();
        }
    }

    @Test(timeout = 30000)
    public void testHandlerShouldBeInvokedWhenInterestedEventOccurred() throws IOException, InterruptedException {
        final ValueHolder<Boolean> invoked = new ValueHolder<>(false);
        watcher.addHandler(new Function1<DirectoryEvent, Unit>() {
            @Override
            public Unit invoke(DirectoryEvent directoryEvent) {
                invoked.value = true;
                return null;
            }
        });

        new File(dir, "testCreate").createNewFile();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!invoked.value) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        thread.start();
        thread.join();
    }

    @Test
    public void testHandlerShouldNotBeInvokedIfFilenameExtensionNotMatch() throws IOException, InterruptedException {
        watcher.setFilenameExtensionInclude(".json");

        final Set<String> changes = new HashSet<>();
        watcher.addHandler(new Function1<DirectoryEvent, Unit>() {
            @Override
            public Unit invoke(DirectoryEvent it) {
                changes.add(it.getChange().getFileName().toString());
                return null;
            }
        });

        new File(dir, "testCreate").createNewFile();
        new File(dir, "testCreate.json").createNewFile();

        final long waitFor = 30000;
        final ValueHolder<Long> wait = new ValueHolder<>(0L);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (wait.value < waitFor) {
                        Thread.sleep(1000);
                        wait.value += 1000;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
        thread.join();

        assertEquals(1, changes.size());
        assertTrue(changes.contains("testCreate.json"));
    }

    private static class ValueHolder<T> {

        T value;

        public ValueHolder(T value) {
            this.value = value;
        }

    }

}
