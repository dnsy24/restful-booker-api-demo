@testAll
Feature: Restful Booker CRUD features

  Agile story: User should be able to create booking with POST method,
  update booking with PUT method and delete booking with DELETE method

  @test1
  Scenario: Create random booking on RestfulBooker with post method at http://restful-booker.herokuapp.com/booking
    When User upload random data on given end-point
    Then Server should handle it and return success status

  @test2
  Scenario: Create booking on RestfulBooker with POST method at http://restful-booker.herokuapp.com/booking
    When User creates request body with parameters to send with POST method
      | firstname | lastname | totalprice | depositpaid | bookingdates | additionalneeds |
      | Ivan      | Petrov   | 555        | true        |              | Breakfast       |

    And User adds to the body booking dates with parameters
      | checkin  | 2020-12-20 |
      | checkout | 2021-01-30 |

    And User makes api call with POST method to create booking
    Then User verifies following data in Json body
      | JsonPath | bookingid |
      | Value    | notNull   |







