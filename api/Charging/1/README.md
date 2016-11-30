##TASKS

1. /cards/{cid} 
   - get 
     200 -10min 20min
     404 -7min 9min
     403 -7min 5min
                    
2. /cards/{cid}/plans/{pid} 
   - get 
     404 -5min 5min
     403 -5min 2min
     200 -10min 12min
                                
3. /cards/{cid}/plans 
   - get
     200 -7min 5min
     403 -5min 3min

4. /cards/{cid}/plans/{pid}/payments/{pid}
   - get 
     200 -10min 17min
     403 -5min 2min
     404 -5min 3min

5. /cards/{cid}/plans/{pid}/payments 
   - get
     200 -7min 4min
     403 -3min 3min
     
   - post 
     201 -10min -7min
     400 -7min -3min
     403 -3min -7min

6. /cards/{cid}/recharges/{rid}
   - get 
     200 -7min 10min
     404 -3min 3min
     403 -3min 2min

7. /cards/{cid}/recharges
   - get
     200 -7min 5min
     403 -3min 3min
     
   - post 
     201 -10min 7min
     400 -7min 5min
     403 -3min 3min

8. /cards/{cid}/consumption-records/{rid}
   - get 
     200 -7min 30min
     404 -3min 2min
     403 -3min 2min

9. /cards/{cid}/consumption-records
   - get
     200 -5min 3min
     403 -5min 3min

10. /cards/{cid}/consumption-records/call-records
   - get
     200 -7min 12min
     403 -3min 2min
     
   - post 
     201 -10min 7min
     400 -7min 4min
     403 -3min 2min

11. /cards/{cid}/consumption-records/data-records
   - get
     200 -7min 4min
     403 -3min 2min
     
   - post 
     201 -10min 5min
     400 -5min 3min
     403 -3min 3min

12. /cards/{cid}/consumption-records/sms-records
   - get
     200 -7min 3min
     403 -3min 1min
     
   - post 
     201 -10min 7min 
     400 -5min 5min
     403 -3min 3min
                                