package simpleplayer4;

import java.util.Arrays;
import java.util.List;

import battlecode.common.*;
<<<<<<< HEAD
//import java.util.*;
import common.DirectionUtils;
=======
>>>>>>> 01e182063ebffafe1dab4f437b5497029c82224d

class Slanderer extends Pawn {
    Politician successor;
    private MapLocation slandCenter = null;

    Slanderer(RobotController rcin) throws GameActionException {
        super(rcin); // Don't remove this.
    }

    void run() throws GameActionException {
<<<<<<< HEAD
		while (rc.getType().equals(RobotType.SLANDERER)) {
			turnCount++;
			if (rc.isReady()) {
				if (!runToSlandCenter()) {
					if (rc.canGetFlag(hqID)) {
						parseHQFlag(rc.getFlag(hqID));
					} else {
						runSimpleCode();
					}
				}
			} else {
				if (slandCenter == null && rc.canGetFlag(hqID)) {
					parseHQFlag(rc.getFlag(hqID));
				}
			}
			//setNearbyHQFlag();

			Clock.yield();
		}
		
=======
        while (rc.getType().equals(RobotType.SLANDERER)) {
            turnCount++;
            if (rc.isReady()) {
                if(distanceSquaredTo(hqLocation) < 5){
                    dirTarget = directionTo(hqLocation).opposite();
                } else {
                    dirTarget = Direction.CENTER;
                }
                tryDirForward180(awayFromEnemyMuckrakers());
            }
            Clock.yield();
        }
>>>>>>> 01e182063ebffafe1dab4f437b5497029c82224d
        if (successor == null) {
            successor = new Politician(this);
        }
        successor.run();
    }
    
	private void parseHQFlag(int flag) throws GameActionException {
		MapLocation tempLocation = Encoding.getLocationFromFlag(rc, flag);
		switch (Encoding.getInfoFromFlag(flag)) {
			case 6:
				slandCenter = tempLocation;
				runToSlandCenter();
				break;
			default:
				runSimpleCode();
				break;
		}
	}
	
	private boolean runToSlandCenter() throws GameActionException {
		if (!rc.isReady()) {
			return false;
		}
		
		if (slandCenter == null || hqLocation == null) {
			return false;
		}

		if (!tryDirForward180(awayFromEnemyMuckrakers())) {
			tryDirForward180(directionTo(slandCenter));
		}
		return true;
	}

	private boolean runSimpleCode() throws GameActionException {
		if (!rc.isReady()) {
			return false;
		}
		
		return tryDirForward180(awayFromEnemyMuckrakers()) || tryDirForward180(DirectionUtils.randomDirection());
	}

    protected Direction awayFromEnemyMuckrakers() throws GameActionException {
        List<RobotInfo> robots = Arrays.asList(rc.senseNearbyRobots(-1, enemyTeam));
        robots.removeIf(r -> (r.type != RobotType.MUCKRAKER));
        return awayFromRobots(robots);
    }
}