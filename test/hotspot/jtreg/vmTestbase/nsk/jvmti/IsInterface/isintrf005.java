/*
 * Copyright (c) 2003, 2018, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package nsk.jvmti.IsInterface;

import java.io.PrintStream;

public class isintrf005 {

    final static int JCK_STATUS_BASE = 95;

    static {
        try {
            System.loadLibrary("isintrf005");
        } catch (UnsatisfiedLinkError ule) {
            System.err.println("Could not load isintrf005 library");
            System.err.println("java.library.path:"
                + System.getProperty("java.library.path"));
            throw ule;
        }
    }

    native static void check(Class cls, boolean flag);
    native static int getRes();

    public static void main(String args[]) {
        args = nsk.share.jvmti.JVMTITest.commonInit(args);

        // produce JCK-like exit status.
        System.exit(run(args, System.out) + JCK_STATUS_BASE);
    }

    public static int run(String args[], PrintStream out) {
        check(isintrf005.class, false);
        check(InnerInterface.class, true);
        check(InnerClass.class, false);
        check(OuterInterface.class, true);
        check(OuterClass1.class, false);
        check(OuterClass2.class, false);
        return getRes();
    }

    interface InnerInterface {
    }

    class InnerClass implements InnerInterface {
    }
}

interface OuterInterface {
}

abstract class OuterClass1 {
}

class OuterClass2 implements OuterInterface {
}
