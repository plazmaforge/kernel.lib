/*
 * Copyright (C) 2012-2023 Oleh Hapon ohapon@users.sourceforge.net
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * Oleh Hapon
 * Kyiv, UKRAINE
 * ohapon@users.sourceforge.net
 */

package plazma.lib.fs;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

import plazma.lib.str.StrLib;

public class FSLib {
    
    public static final String EMPTY_STRING = StrLib.EMPTY_STRING;

    public static final LinkOption DEFAULT_LINK_OPTION = LinkOption.NOFOLLOW_LINKS;


    // Functions:

    /////////////////////////////////////////////////////////////////////////////////
    // 1.1
    // - exists(fileName)
    // - exists(file)
    // - exists(path)

    // 1.2
    // - isDirectory(fileName)
    // - isDirectory(file)
    // - isDirectory(path)

    // 1.3
    // - isFile(fileName)
    // - isFile(file)
    // - isFile(path)

    // 1.4
    // - isRelativePath(fileName)
    // - isRelativePath(file)
    // - isRelativePath(path)

    // 1.4
    // - isAbsolutePath(fileName)
    // - isAbsolutePath(file)
    // - isAbsolutePath(path)

    // 1.5
    // - getFileSize(fileName)
    // - getFileSize(file)

    // 1.6
    // - getFileLastModified(fileName)
    // - getFileLastModified(file)

    /////////////////////////////////////////////////////////////////////////////////
    // 2.1
    // - getDirectory(fileName)
    // - getDirectoryName(fileName)
    // - getParentPath(fileName)

    // 2.2
    // - getFileName(fileName) - simple file name
    // - getFileExt(fileName)
    // - getFileNameWithoutExt(fileName)
    // - getFileNameWithoutSuffix(fileName)

    /////////////////////////////////////////////////////////////////////////////////
    
    // 3.1
    // - createDirectory(path)
    // - createDirectory(path, deep)
    // - createDirectories(path)
    
    // 3.2
    // - deleteFile(path)
    // - deleteDirectory(path)
    // - deleteDirectory(path, deep)
    // - deleteDirectories(path)
    
    // 3.3
    // - copyFile(source, target)
    // - moveFile(source, target)   
    
    /// UNUX ORIENTED

    // 4.1
    // - mkdir(path)
    // - mkdir(path, deep)
    // - mkdirs(path)

    // 4.2
    // - rmdir(path)
    // - rmdir(path, deep)
    // - rmdirs(path)

    // - rm(path)

    // 4.3
    // - cp(source, target)
    // - mv(source, target)   
    

    /////////////////////////////////////////////////////////////////////////////////
    // 5.1
    // - toFile(path)
    // - toPath(path)
    // - toURI(url)

    private FSLib() {
    }

    /**
     * Returns true if the file is found
     * 
     * @param fileName
     * @return
     */        
    public static boolean exists(String fileName) {
        fileName = normalizeString(fileName);
        if (fileName == null) {
            return false;
        }
        return exists(toFile(fileName));
    }

    public static boolean exists(File file) {
        return file == null ? false : file.exists();
    }

    public static boolean exists(Path path) {
        return path == null ? false : Files.exists(path, DEFAULT_LINK_OPTION);
    }
    
    //

    public static boolean isDirectory(String fileName) {
        fileName = normalizeString(fileName);
        if (fileName == null) {
            return false;
        }
        return isDirectory(toFile(fileName));
    }

    public static boolean isDirectory(File file) {
        return file == null ? false : file.isDirectory();
    }

    public static boolean isDirectory(Path path) {
        return path == null ? false : Files.isDirectory(path, DEFAULT_LINK_OPTION);
    }

    //

    public static boolean isFile(String fileName) {
        fileName = normalizeString(fileName);
        if (fileName == null) {
            return false;
        }
        return isFile(toFile(fileName));
    }

    public static boolean isFile(File file) {
        return file == null ? false : file.isFile();
    }

    public static boolean isFile(Path path) {
        return path == null ? false : Files.isRegularFile(path, DEFAULT_LINK_OPTION); // TODO: isRegularFile = isFile ?
    }

    //

    public static boolean isRelativePath(String path) {
        return !isAbsolutePath(path);
    }

    public static boolean isRelativePath(File path) {
        return !isAbsolutePath(path);
    }

    public static boolean isRelativePath(Path path) {
        return !isAbsolutePath(path);
    }

    /**
     * Returns true if the file path is absolute
     * 
     * @param path
     * @return
     */
    public static boolean isAbsolutePath(String path) {
        path = normalizeString(path);
        if (path == null) {
            return false;
        }
        return isAbsolutePath(toFile(path));
    }

    public static boolean isAbsolutePath(File path) {
        return path == null ? null : path.isAbsolute();
    }

    public static boolean isAbsolutePath(Path path) {
        return path == null ? null : path.isAbsolute();
    }

    //

    public static long getFileSize(String fileName) {
        fileName = normalizeString(fileName);
        if (fileName == null) {
            return 0L;
        }
        return getFileSize(toFile(fileName));
    }

    public static long getFileSize(File file) {
        return file == null ? 0L : file.length();
    }

    // TODO: Not implemented yet
    /*
     * public static long size(Path file) { Files.readAttributes(path, type, options) }
     */

    //

    public static long getFileLastModified(String fileName) {
        fileName = normalizeString(fileName);
        if (fileName == null) {
            return 0L;
        }
        return getFileSize(toFile(fileName));
    }

    public static long getFileLastModified(File file) {
        return file == null ? 0L : file.lastModified();
    }

    //

    /**
     * Returns parent directory of the path
     * 
     * @param path
     * @return <code>File</code>
     */
    public static File getDirectory(String path) {
        Path pathObj = getParentPath(path);
        return pathObj == null ? null : pathObj.toFile();
    }

    /**
     * Returns name of parent directory of the path
     * 
     * @param path
     * @return
     */
    public static String getDirectoryName(String path) {
        Path pathObj = getParentPath(path);
        return pathObj == null ? null : pathObj.toString();
    }

    /**
     * Returns parent of path
     * 
     * @param path
     * @return
     */
    public static Path getParentPath(String path) {
        path = normalizeString(path);
        if (path == null) {
            return null;
        }
        Path pathObj = Paths.get(path);
        return pathObj == null ? null : pathObj.getParent();
    }

    /**
     * Returns simple file name
     * 
     * For example: File Name - 'C:\\directory\\myfile.txt' Result - 'myfile.txt'
     * 
     * @param fileName
     * @return
     */
    public static String getFileName(String fileName) { // TODO: maybe getFileName -> getSimpleFileName
        fileName = normalizeString(fileName);
        if (fileName == null) {
            return null;
        }
        Path path = Paths.get(fileName);
        if (path == null) {
            return null;
        }
        path = path.getFileName();
        return path == null ? null : path.toString();
    }

    /**
     * Returns index of file extension. It is position of '.'
     * 
     * @param fileName
     * @return
     */
    public static int getIndexOfFileExt(String fileName) {
        // Get simple file name
        fileName = getFileName(fileName);
        return indexIndexOfSimpleFileExt(fileName);
    }

    private static int indexIndexOfSimpleFileExt(String fileName) {
        if (fileName == null) {
            return -1;
        }
        // LAST INDEX - LAST EXTENSION: TODO: Why last? fileName is simple!
        int index = fileName.lastIndexOf(".");
        return index;
    }

    /**
     * Returns file extension
     * 
     * For example: File Name - 'C:\\directory\\myfile.txt' Result - 'txt'
     * 
     * @param fileName
     * @return
     */
    public static String getFileExt(String fileName) {
        if (fileName == null) {
            return null;
        }
        fileName = getFileName(fileName);
        int index = indexIndexOfSimpleFileExt(fileName);

        if (index < 0) {
            return EMPTY_STRING; // TODO: maybe null
        }
        index++;
        if (index > fileName.length() - 1) {
            return EMPTY_STRING; // TODO: maybe null
        }
        return fileName.substring(index);
    }

    /**
     * Returns simple file name without extension.
     * 
     * For example: File Name - 'C:\\directory\\myfile.txt' Result - 'myfile'
     * 
     * @param fileName
     * @return
     */
    public static String getFileNameWithoutExt(String fileName) {
        if (fileName == null) {
            return null;
        }
        fileName = getFileName(fileName); // get simple file name (non absolute file name)
        int index = indexIndexOfSimpleFileExt(fileName);

        if (index < 0) {
            return fileName;
        }
        return fileName.substring(0, index);
    }

    public static String getFileNameWithoutSuffix(String fileName, String[] suffixes) {
        // remove suffixes from file name
        return StrLib.removeSuffixes(fileName, suffixes);
    }

    // FS commands cp, mv, rm, mkdir, rmdir ...

    ////

    public static boolean createDirectory(String path) {
        checkDirectoryName(path);
        return toFile(path).mkdir();

    }

    public static boolean createDirectory(String path, boolean deep) {
        return deep ? createDirectories(path) : createDirectory(path);

    }

    public static boolean createDirectories(String path) {
        checkDirectoryName(path);
        return toFile(path).mkdirs();  
    }

    ////

    public static boolean deleteFile(String path) {
        checkFileName(path);
        return toFile(path).delete();
        //return Files.delete(toPath(path));
    }

    public static boolean deleteDirectory(String path) {
        checkDirectoryName(path);
        return toFile(path).delete();
    }

    public static boolean deleteDirectory(String path, boolean deep) throws IOException {
        checkDirectoryName(path);
        return deep ? deleteRecursively(path) : deleteDirectory(path);
    }

    public static boolean deleteDirectories(String path) throws IOException {
        return deleteDirectory(path, true);
    }

    public static boolean deleteRecursively(String path) throws IOException {
        return deleteRecursively(toPath(path));
    }

    public static boolean deleteRecursively(Path root) throws IOException {
        if (root == null) {
            return false;
        }
        if (!Files.exists(root)) {
            return false;
        }

        Files.walkFileTree(root, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
        return true;
    }
    
    //    
    
    public static void copyFile(String source, String target) throws IOException {
        checkFileName(source);
        checkFileName(target);
        
        Files.copy(toPath(source), toPath(target), StandardCopyOption.REPLACE_EXISTING);
        
    }

    public static void moveFile(String source, String target) throws IOException {
        checkFileName(source);
        checkFileName(target);
        
        Files.move(toPath(source), toPath(target), StandardCopyOption.REPLACE_EXISTING);
        
    }

    //// UNIX ORIENTED ///////////////////////////////////////////////////////////////////

    public static boolean mkdir(String path) {
        return createDirectory(path);
    }

    public static boolean mkdir(String path, boolean deep) {
        return createDirectory(path, deep); 
    }

    public static boolean mkdirs(String path) {
        return createDirectories(path);
    }

    //
    
    public static boolean rmdir(String path) {
        return deleteDirectory(path);
    }

    public static boolean rmdir(String path, boolean deep) throws IOException {
        return deleteDirectory(path, deep); 
    }

    public static boolean rmdirs(String path) throws IOException {
        return deleteDirectories(path);
    }
    
    //
    
    public static boolean rm(String path) {
        return deleteFile(path);
    }
    
    //

    public static void cp(String source, String target) throws IOException {
        copyFile(source, target); 
    }

    public static void mv(String source, String target) throws IOException {
        moveFile(source, target); 
    }

    //

    public static File toFile(String path) {
        return path == null ? null : new File(path);
    }

    public static Path toPath(String path) {
        return path == null ? null : toFile(path).toPath();
    }

    public static URI toURI(URL url) throws URISyntaxException {
        return url == null ? null : toURI(url.toString());
    }

    public static URI toURI(String path) throws URISyntaxException {
        return path == null ? null : new URI(StrLib.replaceAll(path, " ", "%20"));
    }


    /**
     * Return optional path
     * 
     * @param path1
     * @param path2
     * @return
     */
    public static String getOptionalPath(String path1, String path2) {

        if (path1 == null && path2 == null) {
            return null;
        }

        if (path1 == null) {
            return path2;
        }

        if (path2 == null) {
            return path1;
        }

        if (path1.isEmpty() && path2.isEmpty()) {
            return "";
        }

        return path1 + File.separator + path2;
    }

    /**
     * Build file path by elements
     * 
     * @param elements
     * @return
     */
    public static String getPath(String... elements) {
        if (elements == null || elements.length == 0) {
            return null;
        }
        if (elements.length == 1) {
            return elements[0];
        }
        if (elements.length == 2) {
            return elements[0] + File.separator + elements[1];
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < elements.length; i++) {
            if (i > 0) {
                builder.append(File.separator);
            }
            builder.append(elements[i]);
        }
        return builder.toString();
    }
    
    public static String generateFileName(String dir, String filePrefix, int number, String fileExt) {
        return generateFileName(getOptionalPath(dir, filePrefix), number, fileExt);
    }

    public static String generateFileName(String filePrefix, int number, String fileExt) {
        return StrLib.emptyIfNull(filePrefix) + number + StrLib.emptyIfNull(fileExt);
    }
    
    
    // UTILS ///////////////////////////////////////////////////////////////////////////////////////////////
    
    private static String normalizeString(String str) {
        return StrLib.normalize(str);
    }
    
    //
    
    private static void checkFileName(String path) {
        checkPath(path, "File name");
    }

    private static void checkDirectoryName(String path) {
        checkPath(path, "Directory name");
    }

    private static void checkPath(String path, String type) {
        if (path == null) {
            throw new IllegalArgumentException(type + " is null");
        }
        if (path.isEmpty()) {
            throw new IllegalArgumentException(type + " is empty");
        }
    }


}
