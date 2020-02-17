package me.zeroeightsix.kami.command.commands;

import me.zeroeightsix.kami.command.Command;
import me.zeroeightsix.kami.command.syntax.ChunkBuilder;
import me.zeroeightsix.kami.module.ModuleManager;
import me.zeroeightsix.kami.module.modules.render.XRay;
import net.minecraft.block.Block;

/**
 * Created by 20kdc on 17/02/2020.
 * Updated by S-B99 on 17/02/20
 */
public class XRayCommand extends Command {
    public XRayCommand() {
        super("xray", new ChunkBuilder().append("help <command>").append("list|clear|+block|-block").build(), "wallhack", "x-ray");
        setDescription("Allows you to add or remove blocks from the &7xray &8module");
    }

    @Override
    public void call(String[] args) {
        XRay xr = (XRay) ModuleManager.getModuleByName("XRay");
        if (xr == null) {
            Command.sendErrorMessage("&cThe XRay module is not available for some reason. Make sure the name you're calling is correct and that you have the module installed!!");
            return;
        }
        if (!xr.isEnabled()) {
            Command.sendWarningMessage("&cWarning: The XRay module is not enabled!");
            Command.sendWarningMessage("&cThese commands will still have effects, but will not visibly do anything.");
        }
        for (String s : args) {
            if (s == null)
                continue;
            if (s.equalsIgnoreCase("help")) {
                Command.sendChatMessage("The XRay module has a list of blocks");
                Command.sendChatMessage("Normally, the XRay module hides these blocks");
                Command.sendChatMessage("When the Invert setting is on, the XRay only shows these blocks");
                Command.sendChatMessage("This command is a convenient way to quickly edit the list");
                Command.sendChatMessage("XRay Subcommands:");
                Command.sendChatMessage("clear: Removes all blocks from the XRay block list");
                Command.sendChatMessage("list: Prints the list of selected blocks");
                Command.sendChatMessage("default: Resets the list to the default list");
                Command.sendChatMessage("+<block>: Adds a block to the list");
                Command.sendChatMessage("-<block>: Removes a block from the list");
            } else if (s.equalsIgnoreCase("clear")) {
                xr.extClear();
                Command.sendWarningMessage("Cleared the XRay block list");
            } else if (s.equalsIgnoreCase("default")) {
                xr.extDefault();
                Command.sendChatMessage("Reset the XRay block list to default");
            } else if (s.equalsIgnoreCase("list")) {
                Command.sendChatMessage("\n" + xr.extGet());
            } else if (s.startsWith("+") || s.startsWith("-")) {
                String name = s.substring(1);
                Block b = Block.getBlockFromName(name);
                if (b == null) {
                    Command.sendChatMessage("&cInvalid block name <" + name + ">");
                } else {
                    if (s.startsWith("+")) {
                        xr.extAdd(name);
                    } else {
                        xr.extRemove(name);
                    }
                }
            } else {
                Command.sendChatMessage("&cInvalid subcommand <" + s + ">");
            }
        }
    }
}