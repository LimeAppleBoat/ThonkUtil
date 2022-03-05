package com.jab125.thonkutil.unittests;

import com.jab125.thonkutil.api.annotations.SubscribeEvent;
import com.jab125.thonkutil.api.events.EventTaxi;
import com.jab125.thonkutil.api.events.EventTaxiEvent;
import com.jab125.thonkutil.api.events.EventTaxiReturnableEvent;
import com.jab125.thonkutil.api.events.misc.PingMapEvent;
import com.jab125.thonkutil.util.PingMap;

import java.rmi.UnexpectedException;
import java.util.ArrayList;

public class UnitTests {
    private static int q = 0;
    private static int r = 0;
    private static int d = 0;
    private static final ArrayList<Object> errs = new ArrayList<>();
    private static final PingMap<String, String> pingMap = new PingMap<>();
    public static void main(String[] args) {
        System.out.println("Started Unit Tests");
        //System.out.println(new SubscribeEventTest("TEST", 15).getClass());
        EventTaxi.registerEventTaxiSubscriber(UnitTests.class);
        logTestStart("Subscribe Event Test");
        EventTaxi.executeEventTaxi(new SubscribeEventTest("TEST", 15));
        checkResults(1, q);
        checkResults(1, d);
        logTestEnd();

        logTestStart("Subscribe Event Returnable Test");
        var a = EventTaxi.executeEventTaxi(new SubscribeEventReturnableTest("another test", 74));
        checkResults(true, a);
        logTestEnd();
        logTestStart("Ping Map Test");
        pingMap.put("cheese", "crackers");
        logTestEnd();
        logTestStart("Subscribe Event Target Test");
        EventTaxi.executeEventTaxi(new SubscribeEventTargetTest(), "target");
        checkResults(1, r);
        logTestEnd();
    }

    @SubscribeEvent
    public static void testSubscribeEvent(SubscribeEventTest test) {
        checkResults("TEST", test.str);
        checkResults(15, test.i);
        q = 1;
    }

    @SubscribeEvent
    public static void testSubscribeEvent1(SubscribeEventTest test) {
        checkResults("TEST", test.str);
        checkResults(15, test.i);
        d = 1;
    }
    @SubscribeEvent
    public static void testSubscribeEvent(PingMapEvent event) {
        checkResults(pingMap, event.map());
        checkResults("cheese", event.key());
        checkResults("crackers", event.value());
    }

    @SubscribeEvent
    public static void testReturnableSubscribeEvent(SubscribeEventReturnableTest test) {
        checkResults("another test", test.str);
        checkResults(74, test.i);
        test.setBoolean(true);
        checkResults(true, test.getResult());
    }

    @SubscribeEvent(target = "target")
    public static void testSubscribeEvent(SubscribeEventTargetTest test) {
        r++;
        //System.out.println("Target \"target\"");
    }

    @SubscribeEvent(target = "not target")
    public static void testSubscribeEvent2(SubscribeEventTargetTest test) {
        //System.out.println("Target \"not target\"");
        errs.add("Target \"not target\" fired even though target was \"target\"");
    }

    private static void checkResults(Object expectedResult, Object result) {
        if (expectedResult != result) {
            errs.add("Expected {" + expectedResult + "}, got {" + result + "}");
        }
    }

    private static String e;
    private static void logTestStart(String str) {
        System.out.println("Starting test {" + str + "}");
        e = str;
    }

    private static void logTestEnd() {
        if (errs.size() > 0) {
            System.err.println("Test {" + e + "} finished with " + errs.size() + " unexpected results:");
            for (var err: errs) {
                System.err.println(err);
            }
        } else {
            //System.out.println("Test {" + e + "} finished with no unexpected results.");
        }
        errs.clear();
    }
    public static class SubscribeEventTest extends EventTaxiEvent {
        private final String str;
        private final int i;
        public SubscribeEventTest(String str, int i) {
            this.str = str;
            this.i = i;
        }
    }

    public static class SubscribeEventReturnableTest extends EventTaxiReturnableEvent<Boolean> {
        private final String str;
        private final int i;
        private boolean b = false;
        public SubscribeEventReturnableTest(String str, int i) {
            this.str = str;
            this.i = i;
        }

        @Override
        public Boolean getResult() {
            return b;
        }
        public void setBoolean(boolean b) {
            this.b = b;
        }
    }

    public static class SubscribeEventTargetTest extends EventTaxiEvent {

    }
}
