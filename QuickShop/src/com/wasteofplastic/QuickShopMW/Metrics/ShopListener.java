package com.wasteofplastic.QuickShopMW.Metrics;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.wasteofplastic.QuickShopMW.QuickShop;
import com.wasteofplastic.QuickShopMW.Metrics.Metrics.Graph;
import com.wasteofplastic.QuickShopMW.Shop.ShopPurchaseEvent;

public class ShopListener implements Listener{
	int sales = 0;
	int purchases = 0;
	
	public ShopListener(){
		Metrics metrics = QuickShop.instance.getMetrics();
		
		Graph graph = metrics.createGraph("Sales vs Purchases");

		graph.addPlotter(new Metrics.Plotter("Sales") {
			@Override
			public int getValue() {
				//Returns the current sales # and sets it back to 0
				int oldsales = sales;
				sales = 0;
				return oldsales;
			}
		});
		
		graph.addPlotter(new Metrics.Plotter("Purchases") {
			@Override
			public int getValue() {
				//Returns the current purchases # and sets it back to 0
				int oldpurchases = purchases;
				purchases = 0;
				return oldpurchases;
			}
		});
	}
	
	@EventHandler
	public void onPurchase(ShopPurchaseEvent e){
		if(e.getShop().isSelling()) sales += e.getAmount();
		else purchases += e.getAmount();
	}
}