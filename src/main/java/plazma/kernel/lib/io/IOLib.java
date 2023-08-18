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

package plazma.kernel.lib.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import plazma.kernel.lib.str.StrLib;

public class IOLib {
    
    
    public static final int MAX_BUFFER_SIZE = Integer.MAX_VALUE - 8;

    public static final int DEFAULT_BUFFER_SIZE = 8192;

    // Functions:
    
    /////////////////////////////////////////////////////////////////////////////////
    //
    // Input
    //
    // 1.1
    // - createFileInputStream(fileName)
    // - createFileInputStream(file)
    //
    // 1.2
    // - createInputStream(fileName)
    // - createInputStream(file)
    //
    // 1.3
    // - createReader(fileName)
    // - createReader(fileName, fileEncoding)
    // - createReader(file)
    // - createReader(file, fileEncoding)
    // - createReader(inputStream, fileEncoding)
    //
    // 1.4
    // - createBufferedReader(fileName)
    // - createBufferedReader(fileName, fileEncoding)
    // - createBufferedReader(file)
    // - createBufferedReader(file, fileEncoding)
    // - createBufferedReader(inputStream, fileEncoding)
    // - createBufferedReader(reader)
    
    /////////////////////////////////////////////////////////////////////////////////
    //
    // Output
    //
    // 2.1
    // - createFileOutputStream(fileName)
    // - createFileOutputStream(file)
    //
    // 2.2
    // - createOutputStream(fileName)
    // - createOutputStream(file)
    //
    // 2.3
    // - createWriter(fileName)
    // - createWriter(fileName, fileEncoding)
    // - createWriter(file)
    // - createWriter(file, fileEncoding)
    // - createWriter(outputStream, fileEncoding)
    //
    // 2.4
    // - createBufferedWriter(fileName)
    // - createBufferedWriter(fileName, fileEncoding)
    // - createBufferedWriter(file)
    // - createBufferedWriter(file, fileEncoding)
    // - createBufferedWriter(inputStream, fileEncoding)
    // - createBufferedWriter(reader)
    
    /////////////////////////////////////////////////////////////////////////////////
    //
    // Read
    //
    // 3.1
    // - readLines(fileName)
    // - readLines(fileName, encoding)
    // - readLines(file)
    // - readLines(file, encoding)
    //
    // 3.2    
    // - readText(fileName)
    // - readText(fileName, encoding)
    // - readText(file)
    // - readText(file, encoding)
    //
    // 3.3
    // - readChars(fileName)
    // - readChars(fileName, encoding)
    // - readChars(file)
    // - readChars(file, encoding)
    //
    // 3.4
    // - readBytes(fileName)
    // - readBytes(file)

    /////////////////////////////////////////////////////////////////////////////////
    //
    // Write
    //
    // 4.1
    // - writeLines(fileName, lines)
    // - writeLines(fileName, lines, encoding)
    // - writeLines(file, lines)
    // - writeLines(file, lines, encoding)
    //
    // 4.2
    // - writeText(fileName, text)
    // - writeText(fileName, text, encoding)
    // - writeText(text, file)
    // - writeText(file, text, encoding)
    //
    // 4.3
    // - writeChars(fileName, chars)
    // - writeChars(fileName, chars, encoding)
    // - writeChars(file, chars)
    // - writeChars(file, chars, encoding)
    // 4.4
    // - writeChars(fileName, chars)
    // - writeChars(file, chars)
    
    /////////////////////////////////////////////////////////////////////////////////
    //
    // copyFile/copyTextFile
    //
    // 5.1
    // - copyFile(source, target)
    // - copyFile(source, target)    
    //
    // 5.2
    // - copyTextFile(source, target, encodingSource, encodingTarget)
    // - copyTextFile(source, target, encodingSource, encodingTarget)    
    
    /////////////////////////////////////////////////////////////////////////////////
    //
    // 6.1 transferStream
    // - transferStream(inputStream, outputStream)
    // - transferStream(inputStream, outputStream, bufferSize)
       
    /////////////////////////////////////////////////////////////////////////////////
    //    
    // 7.1 close
    // - close(inputStream)
    // - close(reader)
    // - close(outputStream)
    // - close(writer) 
   
    
    private IOLib() {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////
    //// 1. Input
    ////
    ////////////////////////////////////////////////////////////////////////////////////////////

    //// 1.1 FileInputStream

    public static FileInputStream createFileInputStream(String fileName) throws IOException {
        return createFileInputStream(toFile(fileName));
    }

    public static FileInputStream createFileInputStream(File file) throws IOException {
        return new FileInputStream(file);
    }

    //// 1.2 InputStream

    public static InputStream createInputStream(String fileName) throws IOException {
        return createInputStream(toFile(fileName));
    }

    public static InputStream createInputStream(File file) throws IOException {
        return createFileInputStream(file);
    }

    //// BufferedInputStream

    public static BufferedInputStream createBufferedInputStream(String fileName) throws IOException {
        return createBufferedInputStream(toFile(fileName));
    }

    public static BufferedInputStream createBufferedInputStream(File file) throws IOException {
        InputStream is = createFileInputStream(file);
        return createBufferedInputStream(is);
    }

    public static BufferedInputStream createBufferedInputStream(InputStream is) throws IOException {
        return new BufferedInputStream(is);
    }

    //// 1.3 Reader

    public static FileReader createFileReader(String fileName) throws IOException {
        return createFileReader(toFile(fileName));
    }

    public static FileReader createFileReader(File file) throws IOException {
        return new FileReader(file);
    }

    ////

    public static Reader createReader(String fileName) throws IOException {
        return createReader(toFile(fileName));
    }

    public static Reader createReader(String fileName, String fileEncoding) throws IOException {
        return createReader(toFile(fileName), fileEncoding);
    }

    public static Reader createReader(File file) throws IOException {
        return createReader(file, null); // Maybe: createFileReader(file) - See createReader
    }

    public static Reader createReader(File file, String fileEncoding) throws IOException {
        InputStream is = createInputStream(file);
        return createReader(is, fileEncoding);
    }

    public static Reader createReader(InputStream os, String fileEncoding) throws IOException {
        return fileEncoding == null ? new InputStreamReader(os) : new InputStreamReader(os, fileEncoding);
    }

    //// 1.4 BufferedReader

    public static BufferedReader createBufferedReader(String fileName) throws IOException {
        return createBufferedReader(toFile(fileName));
    }

    public static BufferedReader createBufferedReader(String fileName, String fileEncoding) throws IOException {
        return createBufferedReader(toFile(fileName), fileEncoding);
    }

    public static BufferedReader createBufferedReader(File file) throws IOException {
        FileReader fr = createFileReader(file); // WHY? Maybe: (file, null)
        return createBufferedReader(fr);
    }

    public static BufferedReader createBufferedReader(File file, String fileEncoding) throws IOException {
        InputStream is = createInputStream(file);
        return createBufferedReader(is, fileEncoding);
    }

    public static BufferedReader createBufferedReader(InputStream os, String fileEncoding) throws IOException {
        return createBufferedReader(
                fileEncoding == null ? new InputStreamReader(os) : new InputStreamReader(os, fileEncoding));
    }

    public static BufferedReader createBufferedReader(Reader reader) throws IOException {
        return new BufferedReader(reader);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////
    //// 2. Output
    ////
    ////////////////////////////////////////////////////////////////////////////////////////////

    //// 2.1 FileOutputStream

    public static FileOutputStream createFileOutputStream(String fileName) throws IOException {
        return createFileOutputStream(toFile(fileName));
    }

    public static FileOutputStream createFileOutputStream(File file) throws IOException {
        return new FileOutputStream(file);
    }

    //// 2.2 OutputStream

    public static OutputStream createOutputStream(String fileName) throws IOException {
        return createFileOutputStream(toFile(fileName));
    }

    public static OutputStream createOutputStream(File file) throws IOException {
        return createFileOutputStream(file);
    }

    ////

    public static BufferedOutputStream createBufferedOutputStream(String fileName) throws IOException {
        return createBufferedOutputStream(toFile(fileName));
    }

    public static BufferedOutputStream createBufferedOutputStream(File file) throws IOException {
        OutputStream os = createFileOutputStream(file);
        return createBufferedOutputStream(os);
    }

    public static BufferedOutputStream createBufferedOutputStream(OutputStream os) throws IOException {
        return new BufferedOutputStream(os);
    }

    //// 2.3 Writer

    public static FileWriter createFileWriter(String fileName) throws IOException {
        return createFileWriter(toFile(fileName));
    }

    public static FileWriter createFileWriter(File file) throws IOException {
        return new FileWriter(file);
    }

    //

    public static Writer createWriter(String fileName) throws IOException {
        return createWriter(toFile(fileName));
    }

    public static Writer createWriter(String fileName, String fileEncoding) throws IOException {
        return createWriter(toFile(fileName), fileEncoding);
    }

    public static Writer createWriter(File file) throws IOException {
        return createFileWriter(file); // Maybe: createWriter(file, null) - See createReader s
    }

    public static Writer createWriter(File file, String fileEncoding) throws IOException {
        OutputStream os = createOutputStream(file);
        return createWriter(os, fileEncoding);
    }

    public static Writer createWriter(OutputStream os, String fileEncoding) throws IOException {
        return fileEncoding == null ? new OutputStreamWriter(os) : new OutputStreamWriter(os, fileEncoding);
    }

    //// 2.4 BufferedWriter

    public static BufferedWriter createBufferedWriter(String fileName) throws IOException {
        return createBufferedWriter(toFile(fileName));
    }

    public static BufferedWriter createBufferedWriter(String fileName, String fileEncoding) throws IOException {
        return createBufferedWriter(toFile(fileName), fileEncoding);
    }

    public static BufferedWriter createBufferedWriter(File file) throws IOException {
        FileWriter fw = createFileWriter(file); // WHY? Maybe: (file, null)
        return createBufferedWriter(fw);
    }

    public static BufferedWriter createBufferedWriter(File file, String fileEncoding) throws IOException {
        OutputStream os = createOutputStream(file);
        return createBufferedWriter(os, fileEncoding);
    }

    public static BufferedWriter createBufferedWriter(OutputStream os, String fileEncoding) throws IOException {
        return createBufferedWriter(
                fileEncoding == null ? new OutputStreamWriter(os) : new OutputStreamWriter(os, fileEncoding));
    }

    public static BufferedWriter createBufferedWriter(Writer writer) throws IOException {
        return new BufferedWriter(writer);
    }

    //// 3.1 readLines

    public static List<String> readLines(String fileName) throws IOException {
        return readLines(toFile(fileName), null);
    }

    public static List<String> readLines(String fileName, String encoding) throws IOException {
        return readLines(toFile(fileName), encoding);
    }

    public static List<String> readLines(File file) throws IOException {
        return readLines(file, null);
    }

    public static List<String> readLines(File file, String encoding) throws IOException {
        BufferedReader reader = null;
        try {
            reader = createBufferedReader(file, encoding);
            return readLines(reader);
        } finally {
            close(reader);
        }
    }

    ////

    private static List<String> readLines(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        if (line == null) {
            return null;
        }
        List<String> lines = new ArrayList<String>();
        while (line != null) {
            lines.add(line);

            // read next line
            line = reader.readLine();
        }
        return lines;
    }

    //// 3.2 readText

    public static String readText(String fileName) throws IOException {
        return readText(toFile(fileName), null);
    }

    public static String readText(String fileName, String encoding) throws IOException {
        return readText(toFile(fileName), encoding);
    }

    public static String readText(File file) throws IOException {
        return readText(file, null);
    }

    public static String readText(File file, String encoding) throws IOException {
        BufferedReader reader = null;
        try {
            reader = createBufferedReader(file, encoding);
            return readText(reader);
        } finally {
            close(reader);
        }

    }

    ////

    public static String readText(InputStream input) throws IOException {
        return readText(input, null);
    }

    public static String readText(InputStream input, String encoding) throws IOException {
        return readText_A(input, toCharset(encoding));
    }

    ////

    private static String readText(Reader reader) throws IOException {
        // return readText_J(reader); // J-Code
        return readText_A(reader); // A-Code
    }

    // V1: O-Code
    private static String readText_O(Reader reader) throws IOException {
        StringBuilder builder = new StringBuilder();
        char[] buf = new char[DEFAULT_BUFFER_SIZE];
        int numRead = 0;
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            builder.append(readData);
            buf = new char[DEFAULT_BUFFER_SIZE];
        }
        return builder.toString();
    }

    // V2: J-Code
    private static String readText_J(Reader reader) throws IOException {
        char[] chars = readChars_J(reader, DEFAULT_BUFFER_SIZE);
        return new String(chars);
    }

    // V3: A-Code
    private static String readText_A(final Reader reader) throws IOException {
        try (StringWriter writer = new StringWriter()) {
            copyData(reader, writer);
            return writer.toString();
        }
    }

    //

    private static String readText_A(final InputStream input) throws IOException {
        return readText_A(input, Charset.defaultCharset());
    }

    private static String readText_A(final InputStream input, final Charset charset) throws IOException {
        // try (StringBuilderWriter sw = new StringBuilderWriter()) {

        try (StringWriter writer = new StringWriter()) {
            copyData(input, writer, charset);
            return writer.toString();
        }
    }

    // V4
    // https://www.techiedelight.com/read-text-file-using-filereader-java/
    private static String readText3(File file) throws IOException {
        try (FileReader fr = new FileReader(file)) {
            char[] chars = new char[(int) file.length()];

            int offset = 0;
            while (offset < chars.length) {
                int result = fr.read(chars, offset, chars.length - offset);
                if (result == -1) {
                    break;
                }
                offset += result;
            }
            return new String(chars);
        }
    }

    //// 3.3 readChars

    public static char[] readChars(String fileName) throws IOException {
        return readChars(toFile(fileName), null);
    }

    public static char[] readChars(String fileName, String encoding) throws IOException {
        return readChars(toFile(fileName), encoding);
    }

    public static char[] readChars(File file) throws IOException {
        return readChars(file, null);
    }

    public static char[] readChars(File file, String encoding) throws IOException {
        BufferedReader reader = null;
        try {
            reader = createBufferedReader(file, encoding);
            return readChars(reader);
        } finally {
            close(reader);
        }
    }

    ////

    public static char[] readChars(Reader source) throws IOException {
        // return readChars_J(source); // J-Code
        return readChars_A(source); // A-Code
    }

    // V2: J-Code
    private static char[] readChars_J(Reader source) throws IOException {
        return readChars_J(source, DEFAULT_BUFFER_SIZE);
    }

    private static char[] readChars_J(Reader source, int initialSize) throws IOException {
        int capacity = initialSize;
        char[] buf = new char[capacity];
        int nread = 0;
        int n;
        for (;;) {
            // read to EOF which may read more or less than initialSize (eg: file
            // is truncated while we are reading)
            while ((n = source.read(buf, nread, capacity - nread)) > 0)
                nread += n;

            // if last call to source.read() returned -1, we are done
            // otherwise, try to read one more byte; if that failed we're done too
            if (n < 0 || (n = source.read()) < 0)
                break;

            // one more byte was read; need to allocate a larger buffer
            if (capacity <= MAX_BUFFER_SIZE - capacity) {
                capacity = Math.max(capacity << 1, DEFAULT_BUFFER_SIZE);
            } else {
                if (capacity == MAX_BUFFER_SIZE)
                    throw new OutOfMemoryError("Required array size too large");
                capacity = MAX_BUFFER_SIZE;
            }
            buf = Arrays.copyOf(buf, capacity);
            buf[nread++] = (char) n;
        }
        return (capacity == nread) ? buf : Arrays.copyOf(buf, nread);
    }

    // V3: A-Code
    public static char[] readChars_A(final Reader reader) throws IOException {
        try (CharArrayWriter writer = new CharArrayWriter()) {
            copyData(reader, writer);
            return writer.toCharArray();
        }
    }

    public static char[] readChars_A(final InputStream input) throws IOException {
        return readChars_A(input, Charset.defaultCharset());
    }

    public static char[] readChars_A(final InputStream input, final Charset charset) throws IOException {
        try (CharArrayWriter writer = new CharArrayWriter()) {
            copyData(input, writer, charset);
            return writer.toCharArray();
        }
    }

    //// 3.4 readBytes

    public static byte[] readBytes(String fileName) throws IOException {
        return readBytes(toFile(fileName));
    }

    public static byte[] readBytes(File file) throws IOException {
        InputStream is = createFileInputStream(file);
        return readBytes(is);
    }

    ////

    private static byte[] readBytes(InputStream source) throws IOException {
        return readBytes_J(source); // J-Code
    }

    // V2: J-Code

    private static byte[] readBytes_J(InputStream source) throws IOException {
        return readBytes_J(source, DEFAULT_BUFFER_SIZE);
    }

    private static byte[] readBytes_J(InputStream source, int initialSize) throws IOException {
        int capacity = initialSize;
        byte[] buf = new byte[capacity];
        int nread = 0;
        int n;
        for (;;) {
            // read to EOF which may read more or less than initialSize (eg: file
            // is truncated while we are reading)
            while ((n = source.read(buf, nread, capacity - nread)) > 0)
                nread += n;

            // if last call to source.read() returned -1, we are done
            // otherwise, try to read one more byte; if that failed we're done too
            if (n < 0 || (n = source.read()) < 0)
                break;

            // one more byte was read; need to allocate a larger buffer
            if (capacity <= MAX_BUFFER_SIZE - capacity) {
                capacity = Math.max(capacity << 1, DEFAULT_BUFFER_SIZE);
            } else {
                if (capacity == MAX_BUFFER_SIZE)
                    throw new OutOfMemoryError("Required array size too large");
                capacity = MAX_BUFFER_SIZE;
            }
            buf = Arrays.copyOf(buf, capacity);
            buf[nread++] = (byte) n;
        }
        return (capacity == nread) ? buf : Arrays.copyOf(buf, nread);
    }

    //// 4.1 writeLines

    public static void writeLines(String fileName, List<String> lines) throws IOException {
        writeLines(toFile(fileName), lines, null);
    }

    public static void writeLines(String fileName, List<String> lines, String encoding) throws IOException {
        writeLines(toFile(fileName), lines, encoding);
    }

    public static void writeLines(File file, List<String> lines) throws IOException {
        writeLines(file, lines, null);
    }

    public static void writeLines(File file, List<String> lines, String encoding) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = createBufferedWriter(file, encoding);
            writeLines(writer, lines);
            writer.flush();
        } finally {
            close(writer);
        }
    }

    ////

    private static void writeLines(BufferedWriter writer, List<String> lines) throws IOException {
        if (lines == null || lines.isEmpty()) {
            return;
        }
        String lineSeparator = System.lineSeparator();
        for (int i = 0; i < lines.size(); i++) {
            writer.write(lines.get(i) + lineSeparator); // \n
        }
    }

    //// 4.2 writeText

    public static void writeText(String fileName, String text) throws IOException {
        writeText(toFile(fileName), text, null);
    }

    public static void writeText(String fileName, String text, String encoding) throws IOException {
        writeText(toFile(fileName), text, null);
    }

    public static void writeText(File file, String text) throws IOException {
        writeText(file, text, null);
    }

    public static void writeText(File file, String text, String encoding) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = createBufferedWriter(file, encoding);
            writeText(writer, text);
            writer.flush();
        } finally {
            close(writer);
        }
    }

    //

    private static void writeText(Writer writer, String text) throws IOException {
        if (text == null) {
            return;
        }
        // writer.write(text); // Standard
        writeText_A(text, writer); // A-Code
    }

    // V3: A-Code
    private static void writeText_A(final String data, final Writer writer) throws IOException {
        if (data == null) {
            return;
        }
        writeChars_A(data.toCharArray(), writer);
    }

    //// 4.3 writeChars

    public static void writeChars(String fileName, char[] chars) throws IOException {
        writeChars(toFile(fileName), chars, null);
    }

    public static void writeChars(String fileName, char[] chars, String encoding) throws IOException {
        writeChars(toFile(fileName), chars, null);
    }

    public static void writeChars(File file, char[] chars) throws IOException {
        writeChars(file, chars, null);
    }

    public static void writeChars(File file, char[] chars, String encoding) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = createBufferedWriter(file, encoding);
            writeChars(writer, chars);
            // writer.flush();
        } finally {
            close(writer);
        }
    }

    ////

    private static void writeChars(Writer writer, char[] chars) throws IOException {
        if (chars == null) {
            return;
        }
        writeChars_A(chars, writer); // A-Code
    }

    // V3: A-Code
    private static void writeChars_A(final char[] data, final Writer writer) throws IOException {
        if (data == null || data.length == 0) {
            return;
        }
        int size = data.length;
        int offset = 0;
        int n = 0; // chunk
        while (size > 0) {
            n = Math.min(size, DEFAULT_BUFFER_SIZE);
            writer.write(data, offset, n);
            size -= n;
            offset += n;
        }
    }

    //// 4.4 writeBytes

    public static void writeBytes(String fileName, byte[] bytes) throws IOException {
        writeBytes(toFile(fileName), bytes);
    }

    public static void writeBytes(File file, byte[] bytes) throws IOException {
        OutputStream output = null;
        try {
            output = createOutputStream(file);
            writeBytes(output, bytes);
            // writer.flush();
        } finally {
            close(output);
        }
    }

    ////

    private static void writeBytes(OutputStream output, byte[] bytes) throws IOException {
        if (bytes == null) {
            return;
        }
        writeBytes_A(bytes, output); // A-Code
    }

    // V3: A-Code
    public static void writeBytes_A(final byte[] data, final OutputStream output) throws IOException {
        if (data == null || data.length == 0) {
            return;
        }
        int size = data.length;
        int offset = 0;
        int n = 0; // chunk
        while (size > 0) {
            n = Math.min(size, DEFAULT_BUFFER_SIZE);
            output.write(data, offset, n);
            size -= n;
            offset += n;
        }
    }

    //// 5.1 copyFile

    public static void copyFile(String source, String target) throws IOException {
        if (source == null) {
            throw new IllegalArgumentException("Source file must be not null");
        }
        if (target == null) {
            throw new IllegalArgumentException("Target file must be not null");
        }

        source = normalizeString(source);
        if (source == null) {
            throw new IllegalArgumentException("Source file must be not empty");
        }
        target = normalizeString(target);
        if (target == null) {
            throw new IllegalArgumentException("Target file must be not empty");
        }

        copyFile(toFile(source), toFile(target));
    }

    public static void copyFile(File source, File target) throws IOException {
        if (source == null) {
            throw new IllegalArgumentException("Source file must be not null");
        }
        if (target == null) {
            throw new IllegalArgumentException("Target file must be not null");
        }
        if (!source.exists()) {
            throw new IllegalArgumentException("Source file not found");
        }
        BufferedInputStream is = createBufferedInputStream(source);
        BufferedOutputStream os = createBufferedOutputStream(target);
        transferStream(is, os);
    }

    //// 5.2 copyTextFile

    public static void copyTextFile(String source, String target, String encodingSource, String encodingTarget)
            throws IOException {

        if (source == null) {
            throw new IllegalArgumentException("Source file must be not null");
        }
        if (target == null) {
            throw new IllegalArgumentException("Target file must be not null");
        }

        source = normalizeString(source);
        if (source == null) {
            throw new IllegalArgumentException("Source file must be not empty");
        }
        target = normalizeString(target);
        if (target == null) {
            throw new IllegalArgumentException("Target file must be not empty");
        }
        
        copyTextFile(toFile(source), toFile(target), encodingSource, encodingTarget);

//        if (encodingSource == null && encodingTarget == null) {
//            copyFile(source, target);
//            return;
//        }
//
//        // read
//        String text = readText(source, encodingSource);
//
//        // write
//        writeText(target, text, encodingTarget);

    }

    public static void copyTextFile(File source, File target, String encodingSource, String encodingTraget)
            throws IOException {

        if (source == null) {
            throw new IllegalArgumentException("Source file must be not null");
        }
        if (target == null) {
            throw new IllegalArgumentException("Target file must be not null");
        }
        if (!source.exists()) {
            throw new IllegalArgumentException("Source file not found");
        }

        if (encodingSource == null && encodingTraget == null) {
            copyFile(source, target);
            return;
        }

        // read
        String text = readText(source, encodingSource);

        // write
        writeText(target, text, encodingTraget);

    }

    //// 6.1 transferStream

    public static void transferStream(InputStream is, OutputStream os) throws IOException {
        transferStream(is, os, DEFAULT_BUFFER_SIZE);
    }

    public static void transferStream(InputStream is, OutputStream os, int bufferSize) throws IOException {
        if (is == null) {
            throw new IllegalArgumentException("Input stream must be not null");
        }
        if (os == null) {
            throw new IllegalArgumentException("Output stream must be not null");
        }
        if (bufferSize < 1) {
            throw new IllegalArgumentException("BufferSize must be > 0");
        }

        byte data[] = new byte[bufferSize];

        while (true) {
            int i = is.read(data);
            if (i == -1) {
                break;
            }
            os.write(data, 0, i);
        }

        close(is);
        close(os);

        return;
    }

    ////

    public static final int EOF = -1;

    ////

    ////

    public static void copyData(final InputStream input, final Writer writer, final String charsetName)
            throws IOException {
        copyData(input, writer, toCharset(charsetName));
    }

    public static void copyData(final InputStream input, final Writer writer, final Charset charset)
            throws IOException {
        final InputStreamReader reader = new InputStreamReader(input, charset);
        copyData(reader, writer);
    }

    // public static int copy(final InputStream inputStream, final OutputStream
    // outputStream) throws IOException {
    // final long count = copyLarge(inputStream, outputStream);
    // return count > Integer.MAX_VALUE ? EOF : (int) count;
    // }

    // public static void copy(final Reader reader, final Writer writer) throws
    // IOException {
    // copyData(reader, writer);
    // }

    ////

    public static void copyData(final InputStream input, final OutputStream output) throws IOException {
        copyData(input, output, byteArray(DEFAULT_BUFFER_SIZE));
    }

    public static void copyData(final InputStream input, final OutputStream output, final int size) throws IOException {
        copyData(input, output, byteArray(size));
    }

    public static void copyData(final InputStream input, final OutputStream output, final byte[] buf)
            throws IOException {
        // Objects.requireNonNull(inputStream, "input");
        // Objects.requireNonNull(outputStream, "output");
        // long count = 0;
        int n = 0;
        while (EOF != (n = input.read(buf))) {
            output.write(buf, 0, n);
            // count += n;
        }
        // return count;
    }

    ////

    public static void copyData(final Reader reader, final Writer writer) throws IOException {
        copyData(reader, writer, DEFAULT_BUFFER_SIZE);
    }

    public static void copyData(final Reader reader, final Writer writer, final int size) throws IOException {
        copyData(reader, writer, charArray(size));
    }

    public static void copyData(final Reader reader, final Writer writer, final char[] buf) throws IOException {
        // long count = 0;
        int n = 0;
        while (EOF != (n = reader.read(buf))) {
            writer.write(buf, 0, n);
            // count += n;
        }
        // return count;
    }

    //// 7.1 close

    public static void close(InputStream is) {
        if (is == null) {
            return;
        }
        try {
            is.close();
        } catch (IOException e) {
        }
    }

    public static void close(Reader reader) {
        if (reader == null) {
            return;
        }
        try {
            reader.close();
        } catch (IOException e) {
        }
    }

    public static void close(OutputStream os) {
        if (os == null) {
            return;
        }
        try {
            os.close();
        } catch (IOException e) {
        }
    }

    public static void close(Writer writer) {
        if (writer == null) {
            return;
        }
        try {
            writer.close();
        } catch (IOException e) {
        }
    }

    ////

    private static File toFile(String fileName) {
        return fileName == null ? null : new File(fileName);
    }

    private static String normalizeString(String str) {
        return StrLib.normalize(str);
    }

    ////

    public static byte[] byteArray(final int size) {
        return new byte[size];
    }

    private static char[] charArray(final int size) {
        return new char[size];
    }

    private static Charset toCharset(String charsetName) {
        if (charsetName == null) {
            return Charset.defaultCharset();
        }
        return Charset.forName(charsetName);
    }
    
    
}
