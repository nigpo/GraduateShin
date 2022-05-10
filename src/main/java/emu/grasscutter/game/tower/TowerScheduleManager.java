package emu.grasscutter.game.tower;

import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.data.GameData;
import emu.grasscutter.data.def.TowerScheduleData;
import emu.grasscutter.server.game.GameServer;

public class TowerScheduleManager {
    private final GameServer gameServer;

    public GameServer getGameServer() {
        return gameServer;
    }

    public TowerScheduleManager(GameServer gameServer) {
        this.gameServer = gameServer;
        this.load();
    }

    private TowerScheduleConfig towerScheduleConfig;

    public synchronized void load(){
        try (FileReader fileReader = new FileReader(Grasscutter.getConfig().DATA_FOLDER + "TowerSchedule.json")) {
            towerScheduleConfig = Grasscutter.getGsonFactory().fromJson(fileReader, TowerScheduleConfig.class);

        } catch (Exception e) {
            Grasscutter.getLogger().error("Unable to load tower schedule config.", e);
        }
    }

    public TowerScheduleConfig getTowerScheduleConfig() {
        return towerScheduleConfig;
    }

    public TowerScheduleData getCurrentTowerScheduleData(){
        var data = GameData.getTowerScheduleDataMap().get(towerScheduleConfig.getScheduleId());
        if(data == null){
            Grasscutter.getLogger().error("Could not get current tower schedule data by config:{}", towerScheduleConfig);
        }
        return data;
    }

    public List<Integer> getScheduleFloors() {
        TowerScheduleData data = getCurrentTowerScheduleData();
        if(data == null)
        {
            Grasscutter.getLogger().warn("Tower data may not installed, just return default floor.");
            return Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        }
        TowerScheduleData.ScheduleDetail detail = getCurrentTowerScheduleData().getSchedules().get(0);
        if(detail != null)
        {
            return detail.getFloorList();
        }
        Grasscutter.getLogger().warn("List may not installed, just return default floor.");
        return Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
    }

    public int getNextFloorId(int floorId){
        var entranceFloors = getCurrentTowerScheduleData().getEntranceFloorId();
        if(entranceFloors == null)
        {
            Grasscutter.getLogger().warn("List may not installed, rollback to default.");
            entranceFloors = Arrays.asList(1, 2, 3);
        }
        var scheduleFloors = getScheduleFloors();
        var nextId = 0;
        // find in entrance floors first
        for(int i=0;i<entranceFloors.size()-1;i++){
            if(floorId == entranceFloors.get(i)){
                nextId = entranceFloors.get(i+1);
            }
        }
        if(floorId == entranceFloors.get(entranceFloors.size()-1)){
            nextId = scheduleFloors.get(0);
        }
        if(nextId != 0){
            return nextId;
        }
        // find in schedule floors
        for(int i=0;i<scheduleFloors.size()-1;i++){
            if(floorId == scheduleFloors.get(i)){
                nextId = scheduleFloors.get(i+1);
            }
        }
        return nextId;
    }

    public Integer getLastEntranceFloor() {
        return getCurrentTowerScheduleData().getEntranceFloorId().get(getCurrentTowerScheduleData().getEntranceFloorId().size()-1);
    }
}
