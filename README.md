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
    namespace ui {
        class Main {
            -Restaurant restaurant$
            -PersistenceManager persistence$
            -CsvExporter csvExporter$
            -Scanner scanner$
            +main(String[] args)$ void
        }
    }

    namespace data {
        class PersistenceManager {
            +saveRestaurant(Restaurant restaurant, String filePath) void
            +loadRestaurant(String filePath) Restaurant
        }

        class CsvExporter {
            +exportOrdersReport(Restaurant restaurant, String filePath) void
        }
    }

    namespace domain {
        class Restaurant {
            -String name
            -ArrayList~Table~ tables
            -ArrayList~Order~ orders
            -ArrayList~Employee~ employees
            -ArrayList~MenuItem~ menu
            +recommendTable(int guests) Table
            +registerOrder(Order order) void
            +updateOrderStatus(int orderId, OrderStatus status) void
            +cancelOrder(int orderId) void
            +findActiveOrders() ArrayList~Order~
            +calculateDailySales() double
        }

        class Table {
            -int number
            -int capacity
            -TableStatus status
        }

        class TableStatus {
            <<enumeration>>
            FREE
            OCCUPIED
        }

        class Order {
            -int id
            -Table table
            -Waiter waiter
            -OrderStatus status
            -ArrayList~OrderItem~ items
            +addItem(OrderItem item) void
            +calculateTotal() double
        }

        class OrderStatus {
            <<enumeration>>
            OPEN
            SERVED
            PAID
            CANCELLED
        }

        class OrderItem {
            -int quantity
            -MenuItem menuItem
            +getSubtotal() double
        }

        class MenuItem {
            <<abstract>>
            -int id
            -String name
            -double basePrice
            +calculatePrice()* double
        }

        class Dish {
            -int preparationTimeMin
            +calculatePrice() double
        }

        class Beverage {
            -int sizeMl
            +calculatePrice() double
        }

        class Employee {
            <<abstract>>
            -int id
            -String fullName
            +getRole()* String
        }

        class Waiter {
            -ArrayList~Table~ assignedTables
            +getRole() String
        }

        class Manager {
            +getRole() String
            +generateReport(Restaurant restaurant) String
        }
    }

    Main --> Restaurant
    Main --> PersistenceManager
    Main --> CsvExporter
    PersistenceManager ..> Restaurant
    CsvExporter ..> Restaurant
    CsvExporter ..> Order

    Restaurant "1" *-- "*" Table
    Restaurant "1" *-- "*" Order
    Restaurant "1" --> "*" Employee
    Restaurant "1" --> "*" MenuItem

    Table --> TableStatus
    Order --> OrderStatus
    Order --> Table
    Order --> Waiter
    Order "1" *-- "*" OrderItem
    OrderItem --> MenuItem

    MenuItem <|-- Dish
    MenuItem <|-- Beverage
    Employee <|-- Waiter
    Employee <|-- Manager
    Manager ..> Restaurant

    MenuItem <|-- Dish
    MenuItem <|-- Beverage
    Employee <|-- Waiter
    Employee <|-- Manager
    Manager ..> Restaurant 
