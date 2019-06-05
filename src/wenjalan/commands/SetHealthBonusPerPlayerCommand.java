package wenjalan.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import wenjalan.SharedHealth;
import wenjalan.listeners.SharedHealthListener;

public class SetHealthBonusPerPlayerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        try {
            // if no value, quit
            if (strings.length < 1) {
                return false;
            }

            // get the amount
            double newAmount = Double.parseDouble(strings[0]);

            // check the amount is valid
            if (newAmount < 1) {
                throw new IllegalArgumentException("Value must be at least 1");
            }

            // set the new amount
            SharedHealth.health_bonus_per_player = newAmount;

            // sync players
            SharedHealthListener.syncPlayers();

            // return
            return true;
        } catch (IllegalArgumentException e) {
            commandSender.sendMessage("Error: " + e.getMessage());
            return false;
        }
    }

}
