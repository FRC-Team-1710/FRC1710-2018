# 2018 Robot

Github repository for Team 1710's 2018 robot.

## Auto Handling

This year autonomous is really something special, there is a random element to the game and every match you are required to go somewhere different, even if your starting position did not change. To handle this random element we put all of our autons into a HashMap (in the [AutoHandler](https://github.com/FRC-Team-1710/FRC1710-2018/blob/master/FRC1710-2018/src/org/usfirst/frc/team1710/robot/AutoHandler.java) class). When auton begins, a string is constructed ("223LR" for example) that defines the amount of cubes we are placing, where we started (left, middle, right) and where we want to go (switch, scale or both) and then, it is completed with the position of our switch and scale which is retrieved from the driverstation. This string is just a key in a hashmap so each of these keys ("223LR" for example) correlates to a CommandGroup object. Figuring out what auto to run is as simple as `map.get(key)`.

## Auto Control

At the beginning of our auton development process, we were using [Jaci's Pathfinder](https://github.com/JacisNonsense/Pathfinder) however we found this to work rather inconsistently between our competition and practice bot and was difficult to tune so that it'd utilize our robot's speedy capabilities. So we developed our own, [DriveToPosition](https://github.com/FRC-Team-1710/FRC1710-2018/blob/master/FRC1710-2018/src/commands/DriveToPosition.java) command that drives for a certain amount of inches at a specific heading. By using several of these commands in sequence, changing the heading between each sequential, consistent curved paths can be developed that can be followed in reverse, at a constant speed, or with a smooth accel/deccel. We are currently working on a higher level program that allows us to input a path, then figure out how follow that using this command.
