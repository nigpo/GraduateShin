package emu.grasscutter.server.packet.recv;

import emu.grasscutter.game.managers.SotSManager;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.net.packet.Opcodes;
import emu.grasscutter.net.packet.PacketHandler;
import emu.grasscutter.net.packet.PacketOpcodes;
import emu.grasscutter.server.game.GameSession;

@Opcodes(PacketOpcodes.EnterTransPointRegionNotify)
public class HandlerEnterTransPointRegionNotify extends PacketHandler {
    @Override
    public void handle(GameSession session, byte[] header, byte[] payload) throws Exception{
        Player player = session.getPlayer();
        SotSManager sotsManager = player.getSotSManager();

        sotsManager.refillSpringVolume();
        sotsManager.autoRevive(session);
        sotsManager.scheduleAutoRecover(session);
        // TODO: allow interaction with the SotS?
    }
}
