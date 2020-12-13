package restful.booker.utilities.testdata;

import com.github.javafaker.Faker;
import restful.booker.utilities.testdata.td.TestData;

public class TestDataImpl implements TestData {
    Faker faker = new Faker();

    @Override
    public String getFirstName() {
        return faker.name().firstName();
    }

    @Override
    public String getLastName() {
        return faker.name().lastName();
    }

    @Override
    public String getAdditionalNeeds() {
        return Math.random() < 0.5 ? "Breakfast" : "Nothing";
    }

    @Override
    public int getRandomPrice() {
        return Integer.parseInt(faker.number().digits(3));
    }
}
