Feature: add an item
  Scenario: choose and add an item
    Given url 'https://www.mvideo.ru/'
    Then  find an item 'холодильник'
    Then add first item
    Then waiting
    Then follow to cart
    And assert that item added 'В корзине 1 товар'

  Scenario Outline: chose absent city
    Given url 'https://www.mvideo.ru/'
    Then choose the city 'фывапр'
    And assert that user got message 'Такого города не найдено'