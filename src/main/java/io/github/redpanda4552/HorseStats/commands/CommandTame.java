/**
 * This file is part of HorseStats, licensed under the MIT License (MIT)
 * 
 * Copyright (c) 2015 Brian Wood
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.github.redpanda4552.HorseStats.commands;

import io.github.redpanda4552.HorseStats.Main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.Player;


public class CommandTame extends AbstractCommand {
    
    public CommandTame(Main main) {
        super(main);
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            AbstractHorse h = null;
            
            if (p.isInsideVehicle()) {
                if (p.getVehicle() instanceof AbstractHorse) {
                    h = (AbstractHorse) p.getVehicle();
                }
            }
            
            run(p, h);
        } else {
            sender.sendMessage(lang.get("generic.console"));
        }
        return true;
    }
    
    public void run(Player p, AbstractHorse h) {
        if (h == null) {
            p.sendMessage(lang.tag + lang.r + lang.get("generic.riding"));
            return;
        }
        
        if (h.getOwner() == null) {
            h.setOwner(p);
            p.sendMessage(lang.tag + lang.get("tame.now-own") + " " + friendlyName(h));
        } else if (h.getOwner() == p) {
            p.sendMessage(lang.tag + lang.get("tame.already-own") + " " + friendlyName(h));
        } else {
            p.sendMessage(lang.tag + lang.r + lang.get("generic.owner"));
        }
    }
}
