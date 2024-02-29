package xyz.cucumber.base.interf.config.ext;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Map.Entry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.client.Minecraft;
import xyz.cucumber.base.Client;
import xyz.cucumber.base.interf.config.ConfigManager;
import xyz.cucumber.base.module.Mod;
import xyz.cucumber.base.module.settings.BooleanSettings;
import xyz.cucumber.base.module.settings.ColorSettings;
import xyz.cucumber.base.module.settings.ModeSettings;
import xyz.cucumber.base.module.settings.ModuleSettings;
import xyz.cucumber.base.module.settings.NumberSettings;
import xyz.cucumber.base.module.settings.StringSettings;
import xyz.cucumber.base.utils.FileUtils;
import xyz.cucumber.base.utils.RenderUtils;
import xyz.cucumber.base.utils.cfgs.ConfigFileUtils;
import xyz.cucumber.base.utils.cfgs.SettingsUtils;
import xyz.cucumber.base.utils.position.PositionUtils;
import xyz.cucumber.base.utils.render.ColorUtils;
import xyz.cucumber.base.utils.render.Fonts;

public class ConfigNavButton extends ConfigButton{

	private String name;
	private ConfigManager configManager;
	private double animation;
	
	public ArrayList<ConfigPanel> configs = new ArrayList();
	
	public ConfigNavButton(ConfigManager configManager, int id, String name,double x, double y) {
		this.position = new PositionUtils(x,y,50,13,1);
		this.id = id;
		this.name = name;
		this.configManager = configManager;
		this.updateValues();
	}

	@Override
	public void draw(int mouseX, int mouseY) {
    
		
		
		if(configManager.active == this) {
			animation = (animation*9+ 100) /10;
		}else {
			animation = (animation*9) /10;
		}
		
		RenderUtils.drawRoundedRect(this.position.getX(), this.position.getY(), this.position.getX2(), this.position.getY2(), ColorUtils.getAlphaColor(0xff525ff7, (int) animation), 3f);
		
		Fonts.getFont("rb-m-13").drawString(name, this.position.getX()+this.position.getWidth()/2-Fonts.getFont("rb-m-13").getWidth(name)/2, this.position.getY()+5, -1);
	}

	@Override
	public void onClick(int mouseX, int mouseY, int b) {
		if(position.isInside(mouseX, mouseY)) {
			configManager.active = this;
		}
	}
	
	public void updateValues() {
		configs.clear();
		if(id == 0) {
			for(File f : new File("Gothaj/configs").listFiles()) {
				if(!f.getName().endsWith(".json")) continue;
				configs.add(new ConfigPanel(f, Client.INSTANCE.getSession().getName(), ConfigFileUtils.getConfigDate(f), ""));
			}
		}
	}
}
