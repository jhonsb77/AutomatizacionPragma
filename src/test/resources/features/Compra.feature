@carritoCompra
Feature: como usuario del portal web https://www.demoblaze.com/
  quiero añadir productos al carrito de compra
  para realizar la compra de los productos deseados

  @compraVarios
  Scenario Outline: Agregar mas de un producto al carrito de compra
    Given ingreso al portal demoblaze <fila>
      | Ruta Excel                         | Pestana         |
      | src/test/resources/data/data.xlsx  | carritoCompra   |
    When agrego los productos al carrito de compra
    And visualizo el carrito con los productos agregados
    And completo el formulario de compra
    Then verifico que la compra se haya realizado correctamente

    Examples:
    | fila |
    | 1    |

  @compraUno
  Scenario Outline: Agregar un producto al carrito de compra
    Given ingreso al portal demoblaze <fila>
      | Ruta Excel                         | Pestana         |
      | src/test/resources/data/data.xlsx  | carritoCompra   |
    When agrego los productos al carrito de compra
    And visualizo el carrito con los productos agregados
    And completo el formulario de compra
    Then verifico que la compra se haya realizado correctamente

    Examples:
      | fila |
      | 2    |

  @eliminarUno
  Scenario Outline: Agregar un producto al carrito de compra y eliminarlo
    Given ingreso al portal demoblaze <fila>
      | Ruta Excel                         | Pestana         |
      | src/test/resources/data/data.xlsx  | carritoCompra   |
    When agrego los productos al carrito de compra
    And visualizo el carrito con los productos agregados
    Then elimino el producto deseado del carrito

    Examples:
    | fila |
    | 3    |

  @eliminarDos
  Scenario Outline: Agregar 3 productos al carrito de compra y eliminar 2
    Given ingreso al portal demoblaze <fila>
      | Ruta Excel                         | Pestana         |
      | src/test/resources/data/data.xlsx  | carritoCompra   |
    When agrego los productos al carrito de compra
    And visualizo el carrito con los productos agregados
    Then elimino el producto deseado del carrito

    Examples:
      | fila |
      | 4    |

  @agregarNext
  Scenario Outline: Agregar un producto de la pestaña "Next"
    Given ingreso al portal demoblaze <fila>
      | Ruta Excel                         | Pestana         |
      | src/test/resources/data/data.xlsx  | carritoCompra   |
    When agrego los productos al carrito de compra
    And visualizo el carrito con los productos agregados
    And completo el formulario de compra
    Then verifico que la compra se haya realizado correctamente

    Examples:
      | fila |
      | 5    |

  @productoCategoria
  Scenario Outline: Agregar un producto de una categoria especifica
    Given ingreso al portal demoblaze <fila>
      | Ruta Excel                         | Pestana         |
      | src/test/resources/data/data.xlsx  | carritoCompra   |
    When selecciono la categoria
    When agrego los productos al carrito de compra
    And visualizo el carrito con los productos agregados
    And completo el formulario de compra
    Then verifico que la compra se haya realizado correctamente

    Examples:
      | fila |
      | 6    |



