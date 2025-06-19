package adengine;

import adengine.model.ContentType;
import adengine.model.TargetingCriteria;

import java.util.*;

public class Demo {
    public static void main(String[] args) {
        TargetingCriteria criteria = new TargetingCriteria(15, 45, "Bengaluru");
        AdvertisementEngine engine = new AdvertisementEngine(criteria);
        Calendar cal = Calendar.getInstance();
        cal.set(1995, Calendar.MARCH, 25);
        engine.showAdvertisements("Puma", 200.0, "12345",
                cal.getTime(), "Male", 10.0, "http://ad1.com", ContentType.IMAGE);
    }
}
