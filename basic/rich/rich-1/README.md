##TASKS

###Command

1. handler in player should change depends on result of command executed  -10min 12min

3. waitCommandHandler status should return waitResponseHandler after execute roll to empty  -10min -15min

4. waitCommandHandler status should return waitResponseHandler after execute roll to owned estate  -10min 10min

5. waitCommandHandler status should return null after execute roll to other estate  -10min 9min

6. refactor: when need response, return detail response handler  -7min 4min

    change waitResponseHandler to waitBuyResponseHandler after execute roll to empty
    
    change waitResponseHandler to waitBuildResponseHandler after execute roll to owned empty
    
7. refactor: move return logic for handler after roll command into place  -10min 5min

8. implement estate class  - 10min 10min

7. waitCommandHandler status should return waitGiftResponseHandler after execute roll to gift house  -5min 6min

8. waitCommandHandler status should return waitToolResponseHandler after execute roll to tool house  -5min 3min

12. waitCommandHandler status should return waitMagicResponseHandler after execute roll to magic house  -5min 3min

9. waitCommandHandler status should return null after execute roll to mine  -5min 2min

10. waitCommandHandler status should return null after execute roll to prison  -5min 2min

11. waitCommandHandler status should return null after execute roll to hospital  -5min 2min

12. buy empty -7min 10min

5. waitBuyResponseHandler should return null after execute yes at empty -5min -7min

6. waitBuyResponseHandler should return null after execute no at empty  -5min -2min

13. build estate -7min 15min

5. waitBuildResponseHandler should return null after execute yes at empty -5min 4min

6. waitBuildResponseHandler should return null after execute no at empty  -5min 3min

14. pay fee when go to other estate -7min 10min

15. should pay fee when roll to other estate -5min 3min

13. waitCommandHandler status should return waitCommandHandler after execute sell command  -7min 15min

14. waitGiftResponseHandler should return null and get gift after executed -10min 7min

15. waitToolResponseHandler should return waitToolResponseHandler and get tool -15min 12min
    (get tool, F- quite tool house, 10 tools quite)

16. with 10 tools/without enough points, quite tool when roll to tool house - 10min 5min

17. do not pay fee with free time - 5min 3min

14. waitCommandHandler status should return waitCommandHandler after execute sellTool command  -7min 10min

15. waitCommandHandler status should return waitCommandHandler after execute block command  -7min 10min

16. waitCommandHandler status should return waitCommandHandler after execute bomb command  -7min 5min

17. implement map -10min 7min

17. waitCommandHandler status should return waitCommandHandler after execute robot command  -7min 7min

18. roll pass by block/bomb -10min 6min

18. waitCommandHandler status should return waitCommandHandler after execute query command  -7min

19. place record player who is here - 5min 5min

20. can not set tool at place with player - 5min 3min

19. game control - find next player  -7min
                   player start turn -5min
                   find winner  -5min


