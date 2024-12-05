package ca.openbox.process.service.components;

import ca.openbox.process.entities.LeaveApplication;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ApplicationStatusChangeMessageQueue {
    private static final BlockingQueue<LeaveApplication> queue = new LinkedBlockingQueue<>();

    public static void put(LeaveApplication leaveApplication) throws InterruptedException {
        queue.put(leaveApplication);
    }

    public static LeaveApplication take() throws InterruptedException {
        return queue.take();
    }
}
