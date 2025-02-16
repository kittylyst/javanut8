package javanut8.ch12;

/**
 * Java 9 GetPID class
 * 
 * @author ben
 */
public class GetPID {
    public static long getPid() {
        System.out.println("Portable version");
        // Use new Java 9 Process API...
        ProcessHandle processHandle = ProcessHandle.current();
        return processHandle.getPid();
    }
}
