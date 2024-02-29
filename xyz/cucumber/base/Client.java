package xyz.cucumber.base;

import org.lwjgl.opengl.Display;

import de.florianmichael.viamcp.ViaMCP;
import xyz.cucumber.base.commands.CommandManager;
import xyz.cucumber.base.events.EventBus;
import xyz.cucumber.base.file.FileManager;
import xyz.cucumber.base.interf.config.ConfigManager;
import xyz.cucumber.base.module.ModuleManager;
import xyz.cucumber.base.script.ScriptManager;
import xyz.cucumber.base.utils.cfgs.ConfigFileUtils;
import xyz.cucumber.base.utils.packet.ClientSession;
import xyz.cucumber.base.utils.packet.HWIDUtils;
import xyz.cucumber.base.utils.render.Fonts;

public enum Client {
	
	INSTANCE;
	
	private ModuleManager moduleManager;
	private EventBus eventBus;
	private Fonts fonts;
	private CommandManager commandManager;
	private ScriptManager scriptManager;
	private ConfigManager configManager;
	
	private FileManager fileManager;
	
	//private SpotifyHook spotify;
	
	public String version = "3.01b Alpha";
	
	public int startTime;
	
	private ClientSession session = new ClientSession("0", "0", "0");
	
	public void onEnable() {
		
		Display.setTitle("Gothaj" + " " + version);


		moduleManager = new ModuleManager();
		eventBus = new EventBus();
		fonts = new Fonts();
		commandManager = new CommandManager();
		//scriptManager = new ScriptManager();
		fileManager = new FileManager();
		
		try {
			  ViaMCP.create();
			  ViaMCP.INSTANCE.initAsyncSlider();
			} catch (Exception e) {
			  e.printStackTrace();
			}
				
		//spotify = new SpotifyHook();
		HWIDUtils.setSessionNon();
		configManager = new ConfigManager();
		
		fileManager.load();
		ConfigFileUtils.load(ConfigFileUtils.file, false, true);
		
		startTime = (int) System.currentTimeMillis();
		
	}

	public void onDisable() {
		fileManager.save();
		ConfigFileUtils.save(ConfigFileUtils.file, false);
	}

	public ModuleManager getModuleManager() {
		return moduleManager;
	}

	public EventBus getEventBus() {
		return eventBus;
	}
	public CommandManager getCommandManager() {
		return commandManager;
	}

	public FileManager getFileManager() {
		return fileManager;
	}

	public ClientSession getSession() {
		return session;
	}

	public void setSession(ClientSession session) {
		this.session = session;
	}

	public ConfigManager getConfigManager() {
		return configManager;
	}

	public void setConfigManager(ConfigManager configManager) {
		this.configManager = configManager;
	}

	/*public SpotifyHook getSpotify() {
		return spotify;
	}*/

}
