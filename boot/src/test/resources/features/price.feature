Feature: Test Price API Functionality

Background:
    * url karate.properties['karate.base.url']

Scenario: Get a specific price for a product - Happy Path
  Given path '/api/v1/product-price'
  And param productId = 35455
  And param brandId = 1
  And param applicationDate = '2020-06-14T10:00:00'
  When method GET
  Then status 200
  And match response == { "productId": 35455, "brandId": 1, "priceList": 1, "startDate": "2020-06-14T00:00:00", "endDate": "2020-12-31T23:59:59", "price": 35.50, "currency": "EUR" }

Scenario: Get a specific price for a product - Happy Path
  Given path '/api/v1/product-price'
  And param productId = 35455
  And param brandId = 1
  And param applicationDate = '2020-06-14T16:00:00'
  When method GET
  Then status 200
  And match response == { "productId": 35455, "brandId": 1, "priceList": 2, "startDate": "2020-06-14T15:00:00", "endDate": "2020-06-14T18:30:00", "price": 25.45, "currency": "EUR" }

Scenario: Get a specific price for a product - Happy Path
  Given path '/api/v1/product-price'
  And param productId = 35455
  And param brandId = 1
  And param applicationDate = '2020-06-14T21:00:00'
  When method GET
  Then status 200
  And match response == { "productId": 35455, "brandId": 1, "priceList": 1, "startDate": "2020-06-14T00:00:00", "endDate": "2020-12-31T23:59:59", "price": 35.50, "currency": "EUR" }

Scenario: Get a specific price for a product - Happy Path
  Given path '/api/v1/product-price'
  And param productId = 35455
  And param brandId = 1
  And param applicationDate = '2020-06-15T10:00:00'
  When method GET
  Then status 200
  And match response == { "productId": 35455, "brandId": 1, "priceList": 3, "startDate": "2020-06-15T00:00:00", "endDate": "2020-06-15T11:00:00", "price": 30.50, "currency": "EUR" }

Scenario: Get a specific price for a product - Happy Path
  Given path '/api/v1/product-price'
  And param productId = 35455
  And param brandId = 1
  And param applicationDate = '2020-06-16T21:00:00'
  When method GET
  Then status 200
  And match response == { "productId": 35455, "brandId": 1, "priceList": 4, "startDate": "2020-06-15T16:00:00", "endDate": "2020-12-31T23:59:59", "price": 38.95, "currency": "EUR" }

Scenario: Error - Missing a required parameter
  Given path '/api/v1/product-price'
  And param productId = 35455
  And param brandId = 1
  # applicationDate is intentionally missing
  When method GET
  Then status 400
  And match response.detail contains "Required request parameter applicationDate is missing"

Scenario: Error - Product price not found
  Given path '/api/v1/product-price'
  And param productId = 99999
  And param brandId = 1
  And param applicationDate = '2020-06-16T21:00:00'
  When method GET
  Then status 404
  And match response.detail contains 'No applicable price found'

Scenario: Error - Invalid business logic (productId = 0)
  Given path '/api/v1/product-price'
  And param productId = 0
  And param brandId = 1
  And param applicationDate = '2020-06-16T21:00:00'
  When method GET
  Then status 400
  And match response.detail contains 'Product ID cannot be zero or negative'

Scenario: Error - Invalid business logic (productId = -1)
  Given path '/api/v1/product-price'
  And param productId = -1
  And param brandId = 1
  And param applicationDate = '2020-06-16T21:00:00'
  When method GET
  Then status 400
  And match response.detail contains 'Product ID cannot be zero or negative'

Scenario: Error - Invalid business logic (brandId = 0)
  Given path '/api/v1/product-price'
  And param productId = 35455
  And param brandId = 0
  And param applicationDate = '2020-06-16T21:00:00'
  When method GET
  Then status 400
  And match response.detail contains 'Brand ID cannot be zero or negative'

Scenario: Error - Invalid business logic (brandId = -1)
  Given path '/api/v1/product-price'
  And param productId = 35455
  And param brandId = -1
  And param applicationDate = '2020-06-16T21:00:00'
  When method GET
  Then status 400
  And match response.detail contains 'Brand ID cannot be zero or negative'