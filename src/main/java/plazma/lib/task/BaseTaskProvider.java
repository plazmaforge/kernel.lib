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

package plazma.lib.task;


import static plazma.lib.task.sys.SysTask.TASK_PRINT_DATE;
import static plazma.lib.task.sys.SysTask.TASK_PRINT_DATE_TIME;
import static plazma.lib.task.sys.SysTask.TASK_PRINT_FACT;
import static plazma.lib.task.sys.SysTask.TASK_PRINT_FIB;
import static plazma.lib.task.sys.SysTask.TASK_PRINT_SYSTEM_INFO;
import static plazma.lib.task.sys.SysTask.TASK_PRINT_TIME;


import static plazma.lib.task.sys.CalendarTask.TASK_PRINT_CALENDAR;
import static plazma.lib.task.sys.CalendarTask.TASK_PRINT_YEAR_CALENDAR;
import static plazma.lib.task.sys.CalendarTask.TASK_PRINT_MONTH_CALENDAR;

import static plazma.lib.task.xml.XmlFormatTask.TASK_FORMAT_XML;
import static plazma.lib.task.json.JsonFormatTask.TASK_FORMAT_JSON;
import static plazma.lib.task.yaml.YamlFormatTask.TASK_FORMAT_YAML;

import static plazma.lib.task.xml.Xml2JsonConvertTask.TASK_CONVERT_XML2JSON;
import static plazma.lib.task.xml.Xml2YamlConvertTask.TASK_CONVERT_XML2YAML;
import static plazma.lib.task.json.Json2XmlConvertTask.TASK_CONVERT_JSON2XML;
import static plazma.lib.task.json.Json2YamlConvertTask.TASK_CONVERT_JSON2YAML;
import static plazma.lib.task.yaml.Yaml2XmlConvertTask.TASK_CONVERT_YAML2XML;
import static plazma.lib.task.yaml.Yaml2JsonConvertTask.TASK_CONVERT_YAML2JSON;

import static plazma.lib.task.TestTask.TASK_TEST;
import static plazma.lib.task.array.ArrayGenTask.TASK_GENERATE_FLOAT_ARRAY;
import static plazma.lib.task.array.ArrayReadTask.TASK_READ_FLOAT_ARRAY;

import java.util.Arrays;
import java.util.List;

import plazma.lib.array.ArrayLib;

import plazma.lib.task.sys.SysTask;
import plazma.lib.task.array.ArrayGenTask;
import plazma.lib.task.array.ArrayReadTask;
import plazma.lib.task.sys.CalendarTask;

import plazma.lib.task.xml.XmlFormatTask;
import plazma.lib.task.json.JsonFormatTask;
import plazma.lib.task.yaml.YamlFormatTask;

import plazma.lib.task.xml.Xml2JsonConvertTask;
import plazma.lib.task.xml.Xml2YamlConvertTask;
import plazma.lib.task.json.Json2XmlConvertTask;
import plazma.lib.task.json.Json2YamlConvertTask;
import plazma.lib.task.yaml.Yaml2XmlConvertTask;
import plazma.lib.task.yaml.Yaml2JsonConvertTask;

public class BaseTaskProvider implements TaskProvider {

    public static final String[] TASK_LIST = new String[] { 
            TASK_PRINT_SYSTEM_INFO, 
            TASK_PRINT_DATE, TASK_PRINT_TIME,
            TASK_PRINT_DATE_TIME,

            TASK_PRINT_FACT, 
            TASK_PRINT_FIB,

            TASK_PRINT_CALENDAR,
            TASK_PRINT_YEAR_CALENDAR,
            TASK_PRINT_MONTH_CALENDAR,

            // TASK_FORMAT_CSV,
            TASK_FORMAT_XML,            
            TASK_FORMAT_JSON,
            TASK_FORMAT_YAML,
            
            TASK_CONVERT_XML2JSON,
            TASK_CONVERT_XML2YAML,

            TASK_CONVERT_JSON2XML,
            TASK_CONVERT_JSON2YAML,

            TASK_CONVERT_YAML2JSON,
            TASK_CONVERT_YAML2XML,

            TASK_READ_FLOAT_ARRAY,
            TASK_GENERATE_FLOAT_ARRAY,

            TASK_TEST 
            };

    @Override
    public boolean hasTask(String taskName) {
        return ArrayLib.contains(TASK_LIST, taskName);
    }
    
    @Override
    public Task getTask(String taskName) {
        if (taskName == null) {
            return null;
        }

        // print
        if (TASK_PRINT_SYSTEM_INFO.equals(taskName)) {
            return new SysTask();
        } else if (TASK_PRINT_DATE.equals(taskName)) {
            return new SysTask();
        } else if (TASK_PRINT_TIME.equals(taskName)) {
            return new SysTask();
        } else if (TASK_PRINT_DATE_TIME.equals(taskName)) {
            return new SysTask();

        } else if (TASK_PRINT_FACT.equals(taskName)) {
            return new SysTask();
        } else if (TASK_PRINT_FIB.equals(taskName)) {
            return new SysTask();

        } else if (TASK_PRINT_CALENDAR.equals(taskName)) {
            return new CalendarTask();
        } else if (TASK_PRINT_YEAR_CALENDAR.equals(taskName)) {
            return new CalendarTask();
        } else if (TASK_PRINT_MONTH_CALENDAR.equals(taskName)) {
            return new CalendarTask();

        // format
        } else if (TASK_FORMAT_XML.equals(taskName)) {
            return new XmlFormatTask();
        } else if (TASK_FORMAT_JSON.equals(taskName)) {
            return new JsonFormatTask();
        } else if (TASK_FORMAT_YAML.equals(taskName)) {
            return new YamlFormatTask();

        // convert
        } else if (TASK_CONVERT_XML2JSON.equals(taskName)) {
            return new Xml2JsonConvertTask();
        } else if (TASK_CONVERT_XML2YAML.equals(taskName)) {
            return new Xml2YamlConvertTask();

        } else if (TASK_CONVERT_JSON2XML.equals(taskName)) {
            return new Json2XmlConvertTask();
        } else if (TASK_CONVERT_JSON2YAML.equals(taskName)) {
            return new Json2YamlConvertTask();

        } else if (TASK_CONVERT_YAML2XML.equals(taskName)) {
            return new Yaml2XmlConvertTask();
        } else if (TASK_CONVERT_YAML2JSON.equals(taskName)) {
            return new Yaml2JsonConvertTask();
            
        // array
        } else if (TASK_READ_FLOAT_ARRAY.equals(taskName)) {
            return new ArrayReadTask();
        } else if (TASK_GENERATE_FLOAT_ARRAY.equals(taskName)) {
            return new ArrayGenTask();

        // test
        } else if (TASK_TEST.equals(taskName)) {
            return new TestTask();
        }

        return null;
    }

    @Override
    public List<String> getTaskNames() {
        return Arrays.asList(TASK_LIST);
    }

}
