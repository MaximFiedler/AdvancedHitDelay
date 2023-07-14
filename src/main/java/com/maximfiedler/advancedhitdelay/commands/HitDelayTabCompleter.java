package com.maximfiedler.advancedhitdelay.commands;

import com.maximfiedler.advancedhitdelay.AdvancedHitDelay;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HitDelayTabCompleter implements TabCompleter {
    private AdvancedHitDelay advancedHitDelay;
    public HitDelayTabCompleter(AdvancedHitDelay advancedHitDelay) {
        this.advancedHitDelay = advancedHitDelay;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();
        if (args.length == 1) {
            commands.add("reload");
            commands.add("nohitdelay");
            commands.add("nodamagedelay");
            commands.add("damagemultiplier");
            commands.add("nodamageticks");
            commands.add("maxnodamageticks");
            StringUtil.copyPartialMatches(args[0], commands, completions);
        }
        if (args.length == 2) {
            switch (args[0]) {
                case "nohitdelay", "nodamagedelay" -> {
                    commands.add("true");
                    commands.add("false");
                }
                case "damagemultiplier" -> {
                    commands.add(advancedHitDelay.config.getDamageMultiplier()+"");
                }
                case "nodamageticks" -> {
                    commands.add(advancedHitDelay.config.getNoDamageTicks()+"");
                }
                case "maxnodamageticks" -> {
                    commands.add(advancedHitDelay.config.getMaxNoDamageTicks()+"");
                }
            }
            StringUtil.copyPartialMatches(args[1], commands, completions);
        }
        Collections.sort(completions);
        return completions;
    }

}
