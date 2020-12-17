package restful.booker.training;


import java.util.LinkedHashMap;
import java.util.Map;

public class Training {


    public static Map<String, Object> mapFromTraining() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("firstname", "Name");
        map.put("lastname", " Last Name");
        map.put("totalprice", 100500);
        map.put("depositpaid", true);
        map.put("bookingdates", null);
        map.put("additionalneeds", "breakfast");


        Map<String, Object> bookingDates = new LinkedHashMap<String, Object>() {{
            put("checkin", "2020-20-22");
            put("checkout", "2020-20-33");
        }};

        System.out.println(bookingDates);

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getKey().equals("bookingdates")) {
                map.put("bookingdates", bookingDates);
            }
        }

        System.out.println(map);

        return map;
    }
}
