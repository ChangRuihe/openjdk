/*
 * Copyright (c) 2014, 2015, Oracle and/or its affiliates. All rights reserved.
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

package common;

import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.testng.Assert;
import org.testng.annotations.Test;

/*
 * @bug 6350682
 * @summary Test SAXParserFactory and TransformerFactory can newInstance when setContextClassLoader(null).
 */
public class Bug6350682 {

    @Test
    public void testSAXParserFactory() {
        try {
            Thread.currentThread().setContextClassLoader(null);
            if (Bug6350682.class.getClassLoader() == null)
                System.out.println("this class loader is NULL");
            else
                System.out.println("this class loader is NOT NULL");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            Assert.assertTrue(factory != null, "Failed to get an instance of a SAXParserFactory");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception occured: " + e.getMessage());
        }
    }

    @Test
    public void testTransformerFactory() {
        try {
            Thread.currentThread().setContextClassLoader(null);
            TransformerFactory factory = TransformerFactory.newInstance();
            Assert.assertTrue(factory != null, "Failed to get an instance of a TransformerFactory");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception occured: " + e.getMessage());
        } catch (TransformerFactoryConfigurationError error) {
            error.printStackTrace();
            Assert.fail(error.toString());
        }
    }
}
