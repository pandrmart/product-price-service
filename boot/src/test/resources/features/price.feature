Feature: Test Price API Functionality

Background:
    * url 'http://localhost:8081'

Scenario: Get a specific price for a product - Happy Path
  Given path '/prices/search'
  And request
    """
    {
      "productId": 35455,
      "brandId": 1,
      "applicationDate": "2020-06-14T10:00:00"
    }
    """
  When method POST
  Then status 200
  And match response == { "productId": 35455, "brandId": 1, "priceList": 1, "startDate": "2020-06-14T00:00:00", "endDate": "2020-12-31T23:59:59", "price": 35.50, "currency": "EUR" }

Scenario: Get a specific price for a product - Happy Path
  Given path '/prices/search'
  And request
    """
    {
      "productId": 35455,
      "brandId": 1,
      "applicationDate": "2020-06-14T16:00:00"
    }
    """
  When method POST
  Then status 200
  And match response == { "productId": 35455, "brandId": 1, "priceList": 2, "startDate": "2020-06-14T15:00:00", "endDate": "2020-06-14T18:30:00", "price": 25.45, "currency": "EUR" }

Scenario: Get a specific price for a product - Happy Path
  Given path '/prices/search'
  And request
    """
    {
      "productId": 35455,
      "brandId": 1,
      "applicationDate": "2020-06-14T21:00:00"
    }
    """
  When method POST
  Then status 200
  And match response == { "productId": 35455, "brandId": 1, "priceList": 1, "startDate": "2020-06-14T00:00:00", "endDate": "2020-12-31T23:59:59", "price": 35.50, "currency": "EUR" }

Scenario: Get a specific price for a product - Happy Path
  Given path '/prices/search'
  And request
    """
    {
      "productId": 35455,
      "brandId": 1,
      "applicationDate": "2020-06-15T10:00:00"
    }
    """
  When method POST
  Then status 200
  And match response == { "productId": 35455, "brandId": 1, "priceList": 3, "startDate": "2020-06-15T00:00:00", "endDate": "2020-06-15T11:00:00", "price": 30.50, "currency": "EUR" }

Scenario: Get a specific price for a product - Happy Path
  Given path '/prices/search'
  And request
    """
    {
      "productId": 35455,
      "brandId": 1,
      "applicationDate": "2020-06-16T21:00:00"
    }
    """
  When method POST
  Then status 200
  And match response == { "productId": 35455, "brandId": 1, "priceList": 4, "startDate": "2020-06-15T16:00:00", "endDate": "2020-12-31T23:59:59", "price": 38.95, "currency": "EUR" }
