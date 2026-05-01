Vehicle Interface
- double rfidbalance;
- paytollgate();
Car Class Implements Vehicle
Motorcycles Class Implements Vehicle
Trucks Class Implements Vehicle
- double weight
Payment Interface
- collectpayment()
Cash Class Implements Payment
Electronic Pass Class Implements Payment throws InsufficientBalanceExc≠≠eption
- collectpayment(Vehicle vehicle)
TollCollection Class(Singleton)
- hashmap<string,List<Integer>> vehicle as key and toll cost as values