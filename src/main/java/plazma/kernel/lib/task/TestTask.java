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

package plazma.kernel.lib.task;

import static plazma.kernel.lib.sys.SysLib.*;

//
// Intel Core i5, 1.4 GHz, 8GB, Mac OS X
// ========================================
// Java:         386 ms
// C++:          930 ms
// C++ (-O2):    365 ms
// Swift:      3 497 ms
// Python:     8 450 ms
// 

//
// Intel Core i5, 2.6 GHz, 16GB, Windows 10
// ========================================
// Java:         477 ms
// C++:          639 ms
// C++ (-O2)     316 ms
// Swift:            -
// Python:    13 418 ms
// 

// Java vs C++ vs PHP vs Python vs Perl vs Ruby
// https://habr.com/ru/post/66562/

public class TestTask extends BaseTask {

    public static final String TASK_TEST = "test";

    @Override
    public void execute(TaskContext ctx) throws Exception {

        //printStartTask(TASK_TEST);
        //println();
        //printProcessing();

        //long time = SysLib.getTimeInMilliseconds();

        int r = 0;
        int COUNT = 10000;
        for (int i = 0; i < COUNT; i++) {
            for (int j = 0; j < COUNT; j++) {
                r = (r + (i * j) % 100) % 47;
            }
        }

        //time = SysLib.getTimeInMilliseconds() - time;

        println("Answer: " + r);
        //printTotalTime(time);

    }

}
