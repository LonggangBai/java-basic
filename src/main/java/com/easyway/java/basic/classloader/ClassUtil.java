/*
 * This file is part of the QuickServer library 
 * Copyright (C) QuickServer.org
 *
 * Use, modification, copying and distribution of this software is subject to
 * the terms and conditions of the GNU Lesser General Public License. 
 * You should have received a copy of the GNU LGP License along with this 
 * library; if not, you can download a copy from <http://www.quickserver.org/>.
 *
 * For questions, suggestions, bug-reports, enhancement-requests etc.
 * visit http://www.quickserver.org
 *
 */

package com.easyway.java.basic.classloader; 

import java.util.*;
import java.io.*;
import java.net.*;
import java.util.logging.*;

/**
 * A utility class to load class.
 * @author Akshathkumar Shetty
 * @since 1.3.2
 */
public class ClassUtil {
	private static Logger logger = Logger.getLogger(ClassUtil.class.getName());

	/**
	 * Tries to load the classes present in the array 
	 * passed has second parameter from
	 * the ClassLoader passed has first parameter.
	 * Returns the HashMap of all the classed successfully loaded.
	 * @param classLoader ClassLoader used to find the class
	 * @param classNames array of classes to load. 
	 */
	public static Map loadClass(ClassLoader classLoader, String classNames[]) 
			throws Exception {
		Class classloded = null;
		HashMap classHash = new HashMap();
		for(int i=0;i<classNames.length;i++) {
			try	{
				classloded = classLoader.loadClass(classNames[i]);	
				classHash.put(classNames[i], classloded);
			} catch(Exception e) {
				logger.warning("Could not load classes : "+e);
			}						
		}
		return classHash;
	}



	/**
	 * Returns the ClassLoader to a jar
	 * @since 1.3.3
	 */
	public static ClassLoader getClassLoaderFromJar(String jarPath) 
			throws Exception {
		File file = new File(jarPath);
		logger.fine("Getting ClassLoader for "+file.getCanonicalPath());		
		URL jarurl[] = {file.toURL()};
		URLClassLoader classLoader = URLClassLoader.newInstance(jarurl);
		return classLoader;
	}


}
