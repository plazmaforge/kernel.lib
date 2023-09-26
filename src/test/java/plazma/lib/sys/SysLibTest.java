package plazma.lib.sys;

import plazma.lib.AbstractTestCase;

public class SysLibTest extends AbstractTestCase {

    public void testSystemInfo() {

        long timeMs = SysLib.getTimeInMilliseconds();
        long timeS = SysLib.getTimeInSeconds();
        long timeNs = SysLib.getTimeInNanoseconds();

        println("Current Time (ms): " + timeMs);
        println("Current Time (s) : " + timeS);
        println("Current Time (ns): " + timeNs);
    }

}
