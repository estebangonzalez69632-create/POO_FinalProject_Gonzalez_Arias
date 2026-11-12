# POO_FinalProject_Gonzalez_Arias
# Restaurant Order Management

### Integrantes
- Esteban Gonzalez Garcia - Código: (69632)
- David Fernando Arias - Código: (70297)

### Descripción general del sistema
Sistema para el personal de un restaurante que permite registrar pedidos, calcular cuentas y gestionar mesas.

### Diagrama de clases UML

```mermaid
classDiagram
    direction TB

    class Restaurant {
        -name: String
        -adress: String
        -tables: ArrayList~Table~
        -orders: ArrayList~Order~
        -employees: ArrayList~Employee~
        -menu:ArrayList~MenuItem~
        +addTable(table: Table) void
        +registerOrder(order: Order) void
        +findActiveOrders() ArrayList~Order~
        +addMenuItem(item: MenuItem) void
        +calculateDailySales() double
    }

    class MenuItem {
        <<abstract>>
        -id: int
        -name: String
        -basePrice: double
        +calculatePrice() double*
    }
    class Dish {
        -preparationTimeMin: int
        +calculatePrice() double
    }
    class Beverage {
        -sizeMl: int
        +calculatePrice() double
    }

    class Employee {
        <<abstract>>
        -id: int
        -fullName: String
        +getRole() String*
    }
    class Waiter {
        -assignedTables: ArrayList~Table~
        +takeOrder(order: Order) void
        +getRole() String
    }
    class Manager {
        +generateReport(restaurant: Restaurant) String
        +getRole() String
    }

    class Table {
        -number: int
        -capacity: int
        -status: TableStatus
        +occupy() void
        +free() void
    }
    class Order {
        -id: int
        -status: OrderStatus
        -items: ArrayList~OrderItem~
        +addItem(item: OrderItem) void
        +calculateTotal() double
    }
    class OrderItem {
        -quantity: int
        +getSubtotal() double
    }

    %% HERENCIA
    MenuItem <|-- Dish
    MenuItem <|-- Beverage
    Employee <|-- Waiter
    Employee <|-- Manager

    %% COMPOSICIÓN
    Restaurant "1" *-- "1..*" Table 
    Restaurant "1" *-- "0..*" Order 
    Order "1" *-- "1..*" OrderItem 

    %% AGREGACIÓN
    Restaurant "1" o-- "0..*" MenuItem 
    Restaurant "1" o-- "1..*" Employee 

    %% ASOCIACIONES
    OrderItem "*" --> "1" MenuItem 
    Waiter "1" --> "0..*" Order 
    Table "1" --> "0..1" Order
    Waiter "*" -- "*" Table 

    %% DEPENDENCIA
    Manager ..> Restaurant 
```
