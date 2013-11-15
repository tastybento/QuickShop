package com.wasteofplastic.QuickShopMW.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.dthielke.herochat.ChannelChatEvent;
import com.dthielke.herochat.Chatter.Result;
import com.wasteofplastic.QuickShopMW.QuickShop;

/**
 * 
 * @author Netherfoam
 *
 */
public class HeroChatListener implements Listener{
	QuickShop plugin;
	
	public HeroChatListener(QuickShop plugin){
		this.plugin = plugin;
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
	public void onHeroChat(ChannelChatEvent e){
		if(!plugin.getShopManager().getActions().containsKey(e.getSender().getName())) return;
		plugin.getShopManager().handleChat(e.getSender().getPlayer(), e.getMessage());
		e.setResult(Result.FAIL);
	}
}
