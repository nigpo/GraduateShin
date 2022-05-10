package emu.grasscutter.server.packet.send;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.data.def.TowerScheduleData;
import emu.grasscutter.game.tower.TowerManager;
import emu.grasscutter.game.tower.TowerScheduleManager;
import emu.grasscutter.net.packet.BasePacket;
import emu.grasscutter.net.packet.PacketOpcodes;
import emu.grasscutter.net.proto.TowerAllDataRspOuterClass.TowerAllDataRsp;
import emu.grasscutter.net.proto.TowerCurLevelRecordOuterClass.TowerCurLevelRecord;
import emu.grasscutter.net.proto.TowerFloorRecordOuterClass.TowerFloorRecord;
import emu.grasscutter.net.proto.TowerLevelRecordOuterClass;
import emu.grasscutter.utils.DateHelper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PacketTowerAllDataRsp extends BasePacket {
	
	public PacketTowerAllDataRsp(TowerScheduleManager towerScheduleManager, TowerManager towerManager) {
		super(PacketOpcodes.TowerAllDataRsp);

		var recordList = towerManager.getRecordMap().values().stream()
				.map(rec -> TowerFloorRecord.newBuilder()
						.setFloorId(rec.getFloorId())
						.setFloorStarRewardProgress(rec.getFloorStarRewardProgress())
						.putAllPassedLevelMap(rec.getPassedLevelMap())
						.addAllPassedLevelRecordList(buildFromPassedLevelMap(rec.getPassedLevelMap()))
						.build()
				)
				.collect(Collectors.toList());

		var openTimeMap = towerScheduleManager.getScheduleFloors().stream()
				.collect(Collectors.toMap(x -> x,
						y -> DateHelper.getUnixTime(towerScheduleManager.getTowerScheduleConfig()
						.getScheduleStartTime()))
				);

		TowerScheduleData data = towerScheduleManager.getCurrentTowerScheduleData();
		TowerAllDataRsp.Builder protoBuilder = TowerAllDataRsp.newBuilder();
		if(data != null)
		{
			protoBuilder.setTowerScheduleId(data.getScheduleId());
		}
		else
		{
			protoBuilder.setTowerScheduleId(0);
		}
		try{
			protoBuilder.addAllTowerFloorRecordList(recordList);
		}catch (Exception e)
		{
			Grasscutter.getLogger().error("Fatal -> ", e);
		}
		try{
			protoBuilder.setCurLevelRecord(TowerCurLevelRecord.newBuilder().setIsEmpty(true));
		}catch (Exception e)
		{
			Grasscutter.getLogger().error("Fatal -> ", e);
		}
		try{
			protoBuilder.setScheduleStartTime(DateHelper.getUnixTime(towerScheduleManager.getTowerScheduleConfig()
					.getScheduleStartTime()));
		}catch (Exception e)
		{
			Grasscutter.getLogger().error("Fatal -> ", e);
		}
		try{
			protoBuilder.setNextScheduleChangeTime(DateHelper.getUnixTime(towerScheduleManager.getTowerScheduleConfig()
					.getNextScheduleChangeTime()));
		}catch (Exception e)
		{
			Grasscutter.getLogger().error("Fatal -> ", e);
		}
		try{
			protoBuilder.putAllFloorOpenTimeMap(openTimeMap);
		}catch (Exception e)
		{
			Grasscutter.getLogger().error("Fatal -> ", e);
		}
		try{
			protoBuilder.setIsFinishedEntranceFloor(towerManager.canEnterScheduleFloor());
		}catch (Exception e)
		{
			Grasscutter.getLogger().error("Fatal -> ", e);
		}
		this.setData(protoBuilder.build());
	}

	private List<TowerLevelRecordOuterClass.TowerLevelRecord> buildFromPassedLevelMap(Map<Integer, Integer> map){
		return map.entrySet().stream()
				.map(item -> TowerLevelRecordOuterClass.TowerLevelRecord.newBuilder()
						.setLevelId(item.getKey())
						.addAllSatisfiedCondList(IntStream.range(1, item.getValue() + 1).boxed().collect(Collectors.toList()))
						.build())
				.collect(Collectors.toList());

	}

}
