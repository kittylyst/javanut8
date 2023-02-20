package javanut8.ch12;

import java.lang.management.ManagementFactory;

/**
 * Java 8 GetPID class
 * 
 * @author ben
 */
public class GetPID {
    public static long getPid() {
        // This rather clunky call uses JMX to return the name that
        // represents the currently running JVM. This name is in the
        // format <pid>@<hostname>—on OpenJDK and Oracle VMs only—there
        // is no guaranteed portable solution for this on Java 8
        final String jvmName = 
            ManagementFactory.getRuntimeMXBean().getName();
        final int index = jvmName.indexOf('@');
        if (index < 1)
            return -1;

        try {
            return Long.parseLong(jvmName.substring(0, index));
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }
}
