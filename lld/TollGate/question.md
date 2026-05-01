The Prompt: Digital Toll Booth System
Scenario:
You need to design a system for a highway toll booth that handles different types of vehicles and calculates fares based on specific rules.

Requirements:
Vehicle Diversity: The system must handle Cars, Trucks, and Motorcycles.

Fare Calculation: * Cars pay a flat fee of $50.

Motorcycles pay $20.

Trucks pay based on weight: $50 + ($10 per 100kg).

Payment Processing: The system should support multiple payment methods: Cash and Electronic Pass (RFID).

Collection Tracking: The system must keep track of the total number of vehicles passed and the total revenue collected using a Collection (like a List or Map).

Safety: If a vehicle tries to pass without enough balance in their RFID pass, the system should throw a Custom Exception called InsufficientBalanceException.

Technical Constraints:
Use Inheritance for Vehicle types.

Use an Interface for the Payment Strategy.

Use Encapsulation to protect vehicle and payment data.

Implement a functional approach (Stream API) to calculate the total revenue from the list of passed vehicles at the end.