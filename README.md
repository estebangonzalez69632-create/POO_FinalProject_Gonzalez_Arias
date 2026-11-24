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
            +calculateSubtotal() double
        }

        class MenuItem {
            <<abstract>>
            -int id
            -String name
            -double basePrice
            +calculatePrice()* double
        }

        class Dish {
            -int preparationTime
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

    %% Relationships between layers
    Main --> Restaurant : manages
    Main --> PersistenceManager : uses
    Main --> CsvExporter : uses
    PersistenceManager ..> Restaurant : serializes
    CsvExporter ..> Restaurant : reads
    CsvExporter ..> Order : exports

    %% Domain relationships
    Restaurant "1" *-- "*" Table : owns
    Restaurant "1" *-- "*" Order : registers
    Restaurant "1" o-- "*" Employee : employs
    Restaurant "1" o-- "*" MenuItem : offers

    Table --> TableStatus : has
    Order --> OrderStatus : has
    Order --> Table : assigned to
    Order --> Waiter : attended by
    Order "1" *-- "*" OrderItem : contains
    OrderItem --> MenuItem : references

    MenuItem <|-- Dish
    MenuItem <|-- Beverage
    Employee <|-- Waiter
    Employee <|-- Manager
    Manager ..> Restaurant : reports on
