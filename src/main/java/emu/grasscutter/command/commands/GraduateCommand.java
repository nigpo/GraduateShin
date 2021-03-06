package emu.grasscutter.command.commands;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.utils.Language;
import java.util.*;

@Command(label = "graduate", usage = "graduate userid", aliases = {"grad"}, permission = "player.graduate", permissionTargeted = "player.graduate.others")
public class GraduateCommand extends GiveArtifactCommand {
    @Override
    public void execute(Player sender, Player targetPlayer, List<String> args) {
        try {
            Player toWho = null;
            if(targetPlayer != null)
            {
                toWho = targetPlayer;
            }
            else if(sender != null)
            {
                toWho = sender;
            }
            else
            {
                CommandHandler.sendMessage(null, Language.translate("commands.execution.need_target"));
                return;
            }
            String[][] graduateData = new String[][]{
// Soulscent Bloom
                    new String[]{"98544", "10001", "501054,1", "501064,1", "501204,1", "501224,6", "21"},
                    new String[]{"98524", "10003", "501064,1", "501244,1", "501204,1", "501224,6", "21"},
                    new String[]{"98554", "10004", "501054,1", "501244,1", "501204,1", "501224,6", "21"},
                    new String[]{"98514", "15011", "501054,1", "501064,1", "501204,1", "501224,6", "21"},
                    new String[]{"98534", "30950", "501054,1", "501064,1", "501244,1", "501204,6", "21"},
// In Remembrance of Viridescent Fields/Flowering Life
                    new String[]{"97544", "10001", "501054,1", "501064,1", "501204,1", "501224,6", "21"},
                    new String[]{"97524", "10003", "501064,1", "501244,1", "501204,1", "501224,6", "21"},
                    new String[]{"97554", "10004", "501054,1", "501244,1", "501204,1", "501224,6", "21"},
                    new String[]{"97514", "15012", "501054,1", "501064,1", "501204,1", "501224,6", "21"},
                    new String[]{"97534", "30950", "501054,1", "501064,1", "501244,1", "501204,6", "21"},
// In Remembrance of Viridescent Fields
                    new String[]{"24654", "10001", "501054,1", "501064,1", "501204,6", "501224,1", "21"},
                    new String[]{"24652", "10003", "501064,1", "501244,1", "501204,1", "501224,6", "21"},
                    new String[]{"24655", "10004", "501054,1", "501244,1", "501204,1", "501224,6", "21"},
                    new String[]{"24651", "15012", "501054,1", "501064,1", "501204,1", "501224,6", "21"},
                    new String[]{"24653", "30950", "501054,1", "501064,1", "501244,1", "501204,6", "21"},
// Bloom Times
                    new String[]{"24804", "10001", "501054,1", "501064,1", "501204,6", "501224,1", "21"},
                    new String[]{"94524", "10003", "501064,1", "501234,1", "501204,1", "501224,6", "21"},
                    new String[]{"94554", "10007", "501054,1", "501064,1", "501204,1", "501224,6", "21"},
                    new String[]{"94514", "15009", "501054,1", "501064,1", "501204,1", "501224,6", "21"},
                    new String[]{"94534", "30950", "501054,1", "501064,1", "501234,1", "501204,6", "21"},
// ???????????????????????????
                    new String[]{"95544", "10001", "501054,1", "501064,1", "501204,1", "501224,6", "21"},
                    new String[]{"95524", "10003", "501064,1", "501244,1", "501204,1", "501224,6", "21"},
                    new String[]{"95554", "10006", "501054,1", "501064,1", "501204,1", "501224,6", "21"},
                    new String[]{"95514", "15013", "501054,1", "501064,1", "501204,1", "501224,6", "21"},
                    new String[]{"95534", "30950", "501054,1", "501064,1", "501244,1", "501204,6", "21"},
// Thundersoother's Heart
                    new String[]{"23444", "10001", "501054,1", "501064,1", "501204,1", "501224,6", "21"},
                    new String[]{"23442", "10003", "501064,1", "501244,1", "501204,1", "501224,6", "21"},
                    new String[]{"23445", "10004", "501054,1", "501244,1", "501204,1", "501224,6", "21"},
                    new String[]{"23441", "15009", "501054,1", "501064,1", "501204,1", "501224,6", "21"},
                    new String[]{"23443", "30950", "501054,1", "501064,1", "501244,1", "501204,6", "21"},
// Stainless Bloom
                    new String[]{"23554", "10001", "501054,1", "501064,1", "501204,6", "501224,1", "21"},
                    new String[]{"24662", "10003", "501064,1", "501244,1", "501204,1", "501224,6", "21"},
                    new String[]{"24665", "10004", "501054,1", "501244,1", "501204,1", "501224,6", "21"},
                    new String[]{"92514", "15015", "501054,1", "501064,1", "501204,1", "501224,6", "21"},
                    new String[]{"92534", "30950", "501054,1", "501064,1", "501244,1", "501204,6", "21"},
// Thundersoother's Heart
                    new String[]{"23444", "10001", "501054,1", "501064,1", "501204,6", "501224,1", "21"},
                    new String[]{"23442", "10003", "501064,1", "501244,1", "501204,1", "501224,6", "21"},
                    new String[]{"23445", "10004", "501054,1", "501244,1", "501204,1", "501224,6", "21"},
                    new String[]{"23441", "15009", "501054,1", "501064,1", "501204,1", "501224,6", "21"},
                    new String[]{"23443", "30950", "501054,1", "501064,1", "501244,1", "501204,6", "21"},
// Bloom Times
                    new String[]{"95544", "10001", "501054,1", "501064,1", "501204,6", "501224,1", "21"},
                    new String[]{"95524", "10003", "501064,1", "501244,1", "501204,1", "501224,6", "21"},
                    new String[]{"95554", "10006", "501054,1", "501064,1", "501204,1", "501224,6", "21"},
                    new String[]{"95514", "15013", "501054,1", "501064,1", "501204,1", "501224,6", "21"},
                    new String[]{"95534", "30950", "501054,1", "501064,1", "501244,1", "501204,6", "21"},
// Flower of Accolades
                    new String[]{"24224", "10001", "501054,1", "501064,1", "501204,6", "501224,1", "21"},
                    new String[]{"24222", "10003", "501064,1", "501244,1", "501204,1", "501224,6", "21"},
                    new String[]{"24225", "10002", "501054,1", "501064,1", "501204,1", "501224,6", "21"},
                    new String[]{"24221", "15013", "501054,1", "501064,1", "501204,1", "501224,6", "21"},
                    new String[]{"24223", "30950", "501054,1", "501064,1", "501244,1", "501204,6", "21"},
// In Remembrance of Viridescent Fields
                    new String[]{"24654", "10001", "501054,1", "501064,1", "501204,6", "501224,1", "21"},
                    new String[]{"24652", "10003", "501064,1", "501244,1", "501204,1", "501224,6", "21"},
                    new String[]{"24655", "10004", "501054,1", "501244,1", "501204,1", "501224,6", "21"},
                    new String[]{"24651", "15012", "501054,1", "501064,1", "501204,1", "501224,6", "21"},
                    new String[]{"24653", "30950", "501054,1", "501064,1", "501244,1", "501204,6", "21"},
// In Remembrance of Viridescent Fields
                    new String[]{"24654", "10001", "501064,1", "501244,6", "501204,1", "501224,1", "21"},
                    new String[]{"24652", "10003", "501064,1", "501244,6", "501204,1", "501224,1", "21"},
                    new String[]{"24655", "10008", "501054,1", "501064,1", "501204,6", "501224,1", "21"},
                    new String[]{"24651", "10008", "501054,1", "501064,1", "501204,6", "501224,1", "21"},
                    new String[]{"24653", "10008", "501054,1", "501064,1", "501204,6", "501224,1", "21"},
// Deal
                    new String[]{"93544", "10001", "501054,1", "501064,1", "501204,6", "501224,1", "21"},
                    new String[]{"93524", "10003", "501064,1", "501244,1", "501204,6", "501224,1", "21"},
                    new String[]{"24765", "10004", "501054,1", "501244,1", "501204,1", "501224,6", "21"},
                    new String[]{"24761", "10004", "501054,1", "501244,1", "501204,1", "501224,6", "21"},
                    new String[]{"24763", "10004", "501054,1", "501244,1", "501204,1", "501224,6", "21"},
// Snowswept Memory
                    new String[]{"23454", "10001", "501054,1", "501064,1", "501204,1", "501224,6", "21"},
                    new String[]{"23452", "10003", "501064,1", "501244,1", "501204,1", "501224,6", "21"},
                    new String[]{"23455", "10004", "501054,1", "501244,1", "501204,1", "501224,6", "21"},
                    new String[]{"23451", "15010", "501054,1", "501064,1", "501204,1", "501224,6", "21"},
                    new String[]{"23453", "30950", "501054,1", "501064,1", "501244,1", "501204,6", "21"},
// Sea-Dyed Blossom
                    new String[]{"96544", "10001", "501054,1", "501064,1", "501244,1", "501034,6", "21"},
                    new String[]{"96524", "10003", "501064,1", "501244,1", "501024,1", "501034,6", "21"},
                    new String[]{"96554", "10002", "501054,1", "501064,1", "501244,1", "501024,6", "21"},
                    new String[]{"96514", "15011", "501054,1", "501064,1", "501244,1", "501034,6", "21"},
                    new String[]{"96534", "10002", "501054,1", "501064,1", "501244,1", "501024,6", "21"},
// Soulscent Bloom
                    new String[]{"98544", "10001", "501054,1", "501064,1", "501204,1", "501224,6", "21"},
                    new String[]{"98524", "10003", "501064,1", "501244,1", "501204,1", "501224,6", "21"},
                    new String[]{"98554", "10004", "501054,1", "501244,1", "501204,1", "501224,6", "21"},
                    new String[]{"98514", "15008", "501054,1", "501064,1", "501204,1", "501224,6", "21"},
                    new String[]{"98534", "30950", "501054,1", "501064,1", "501244,1", "501204,6", "21"},
// Fire
                    new String[]{"24674", "10001", "501054,1", "501064,1", "501204,6", "501224,1", "21"},
                    new String[]{"24672", "10003", "501064,1", "501244,1", "501204,1", "501224,6", "21"},
                    new String[]{"24675", "10004", "501054,1", "501244,1", "501204,1", "501224,6", "21"},
                    new String[]{"24671", "15008", "501054,1", "501064,1", "501204,1", "501224,6", "21"},
                    new String[]{"24673", "30950", "501054,1", "501064,1", "501244,1", "501204,6", "21"},
// Fire
                    new String[]{"24684", "10001", "501244,1", "501064,1", "501204,6", "501224,1", "21"},
                    new String[]{"24682", "10003", "501064,1", "501244,1", "501204,1", "501224,6", "21"},
                    new String[]{"24685", "10002", "501054,1", "501244,1", "501204,1", "501224,6", "21"},
                    new String[]{"24681", "15008", "501244,6", "501064,1", "501204,1", "501224,1", "21"},
                    new String[]{"24683", "30950", "501054,1", "501064,1", "501244,6", "501204,1", "21"},
// Fire
                    new String[]{"24684", "10001", "501244,2", "501064,1", "501204,1", "501224,4", "21"},
                    new String[]{"24682", "10003", "501064,1", "501244,1", "501204,1", "501224,6", "21"},
                    new String[]{"24685", "10004", "501054,1", "501244,1", "501204,1", "501224,6", "21"},
                    new String[]{"24681", "15008", "501244,1", "501064,1", "501204,1", "501224,6", "21"},
                    new String[]{"24683", "30950", "501054,1", "501064,1", "501244,1", "501204,6", "21"},
// Fire
                    new String[]{"24684", "10001", "501244,2", "501064,1", "501204,1", "501224,4", "21"},
                    new String[]{"24682", "10003", "501064,1", "501244,1", "501204,1", "501224,6", "21"},
                    new String[]{"24685", "10004", "501054,1", "501244,1", "501204,1", "501224,6", "21"},
                    new String[]{"24681", "15008", "501244,1", "501064,1", "501204,1", "501224,6", "21"},
                    new String[]{"24683", "30950", "501054,1", "501064,1", "501244,1", "501204,6", "21"}
            };
            for(String[] item : graduateData)
            {
                ArrayList<String> passingList = new ArrayList<>(Arrays.asList(item));
                if(!executeInternal(sender, toWho, passingList, false))
                {
                    CommandHandler.sendMessage(sender, "???????????????" + item[0] + " ???????????????");
                }
            }
            CommandHandler.sendMessage(sender, "??????????????????????????????");
        } catch (Exception e) {
            Grasscutter.getLogger().debug("You can not be graduated...", e);
        }
    }
}