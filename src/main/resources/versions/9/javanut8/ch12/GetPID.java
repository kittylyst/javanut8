package javanut8.ch12;

/**
 * Java 9 GetPID class
 * 
 * @author ben
 */
//tag::JAVA9[]
public class GetPID {
    public static long getPid() {
        // Use new Java 9 Process API...
        ProcessHandle processHandle = ProcessHandle.current();
        return processHandle.getPid();
    }
}
//end::JAVA9[]