package com.maximfiedler.advancedhitdelay.commands;

import com.maximfiedler.advancedhitdelay.AdvancedHitDelay;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class HitDelayCommand implements CommandExecutor  {
    private AdvancedHitDelay advancedHitDelay;
    public HitDelayCommand(AdvancedHitDelay advancedHitDelay) {
        this.advancedHitDelay = advancedHitDelay;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length < 1) {
            sendDefaultMessage(sender);
            return false;
        }
        if (args[0].equals("reload")) {
            try {
                this.advancedHitDelay.config.reload();
                sender.sendMessage("Success! Updated config.");
            } catch (Exception e) {
                e.printStackTrace();
                sender.sendMessage("An error occurred!");
            }
            return false;
        }
        if(args.length < 2) {
            sendDefaultMessage(sender);
            return false;
        }
        switch (args[0]) {
            case "nohitdelay" -> {
                try {
                    this.advancedHitDelay.config.updateValue("Options.NoHitDelay", Boolean.parseBoolean(args[1]));
                    sender.sendMessage(ChatColor.GREEN + "Success! NoHitDelay is now set to " + Boolean.parseBoolean(args[1]));
                } catch (Exception e) {
                    sender.sendMessage(ChatColor.RED + args[1] + " is not a valid boolean! Please use true or false.");
                }
            }
            case "nodamagedelay" -> {
                try {
                    this.advancedHitDelay.config.updateValue("Options.NoDamageDelay", Boolean.parseBoolean(args[1]));
                    sender.sendMessage(ChatColor.GREEN + "Success! NoDamageDelay is now set to " + Boolean.parseBoolean(args[1]));
                } catch (Exception e) {
                    sender.sendMessage(ChatColor.RED + args[1] + " is not a valid boolean! Please use true or false.");
                }
            }
            case "damagemultiplier" -> {
                try {
                    this.advancedHitDelay.config.updateValue("Options.DamageMultiplier", Double.parseDouble(args[1]));
                    sender.sendMessage(ChatColor.GREEN + "Success! DamageMultiplier is now set to " + Double.parseDouble(args[1]));
                } catch (Exception e) {
                    sender.sendMessage(ChatColor.RED + args[1] + " is not a valid number!");
                }
            }
            case "nodamageticks" -> {
                try {
                    this.advancedHitDelay.config.updateValue("Options.NoDamageTicks", Double.parseDouble(args[1]));
                    sender.sendMessage(ChatColor.GREEN + "Success! NoDamageTicks is now set to " + Double.parseDouble(args[1]));
                } catch (Exception e) {
                    sender.sendMessage(ChatColor.RED + args[1] + " is not a valid number!");
                }
            }
            case "maxnodamageticks" -> {
                try {
                    this.advancedHitDelay.config.updateValue("Options.MaxNoDamageTicks", Double.parseDouble(args[1]));
                    sender.sendMessage(ChatColor.GREEN + "Success! MaxNoDamageTicks is now set to " + Double.parseDouble(args[1]));
                } catch (Exception e) {
                    sender.sendMessage(ChatColor.RED + args[1] + " is not a valid number!");
                }
            }
            default -> {
                sendDefaultMessage(sender);
            }
        }
        return false;
    }

    private void sendDefaultMessage(CommandSender sender) {
        sender.sendMessage("--------------------");
        sender.sendMessage("-  /hitdelay reload");
        sender.sendMessage("-  /hitdelay nohitdelay <true/false>");
        sender.sendMessage("-  /hitdelay nodamagedelay <true/false>");
        sender.sendMessage("-  /hitdelay damagemultiplier <number>");
        sender.sendMessage("-  /hitdelay nodamageticks <number>");
        sender.sendMessage("-  /hitdelay maxnodamageticks <number>");
        sender.sendMessage("--------------------");
    }

}
