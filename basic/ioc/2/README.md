Implement a simple container with annotation in java

##tasks


factory：
    constructor，field, method
     -> method call -> field setting   -> method call
     
     interface Injection {
        Class<?>[] required();
        Object execute(Object target, Object[] injected);
     }
     Injection[] resovle(Class type);
   1. create Injections based on class
    1.1 constructor -> Injection
        1.1.1 single 
      1.2 field
      1.3 method
     2. constructor injection execution, field, method
    3. container get required compoennts 
     

  1. inject class with no field, method and constructor. -10min 40min
 
2. bind implement and interface -10min 7min

3. should not bind class to interface which it not implement -10min  30min

4. refactor 
    - record map relationship in bind, remove new instance logic back to resolve -10min 13min
    - resolve a class container not instantiate -7min 7min
    - resolve a class container instantiated -7min
 
5. inject class with injected field - 15min 20min

6. inject constructor 
    - parameter has injected -10min 30min
    - more than one constructor with inject annotation -> exception -7min 7min

7. inject method
    - setter, parameter has been inject -15min -23min
    - method is not setter -15min

8. @Named  register different implement of same interface