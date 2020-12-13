Feature: Restful Booker CRUD features

  Agile story: User should be able to create booking with POST method,
  update booking with PUT method and delete booking with DELETE method

  Scenario: Create random booking on RestfulBooker with post method at http://restful-booker.herokuapp.com/booking
    When User upload random data on given end-point
    Then Server should handle it and return success status

  # TODO: transfer data table to nested json!!!

  Scenario: Create booking on RestfulBooker with post method at http://restful-booker.herokuapp.com/booking
    When User makes api call to create booking
      | firstname | lastname | totalprice | depositpaid | bookingdates | additionalneeds |
      | Michael   | Smith    | 555        | true        |              | Breakfast       |

    Then User verifies following data in Json body
      | JsonPath  | Value   |
      | bookingid | notNull |






#    {
#    "bookingid": 1,
#    "booking": {
#    "firstname": "Jim",
#    "lastname": "Brown",
#    "totalprice": 111,
#    "depositpaid": true,
#    "bookingdates": {
#            "checkin": "2018-01-01",
#            "checkout": "2019-01-01"
#    },
#    "additionalneeds": "Breakfast"
#    }


