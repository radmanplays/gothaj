package xyz.cucumber.base.module.feat.player;

import java.util.Random;

import org.apache.commons.lang3.RandomUtils;

import xyz.cucumber.base.events.EventListener;
import xyz.cucumber.base.events.ext.EventTick;
import xyz.cucumber.base.module.Category;
import xyz.cucumber.base.module.Mod;
import xyz.cucumber.base.module.ModuleInfo;
import xyz.cucumber.base.utils.StringUtils;
import xyz.cucumber.base.utils.Timer;

@ModuleInfo(category = Category.PLAYER, description = "", name = "Spammer")
public class SpammerModule extends Mod{

	private Timer t = new Timer();
	
	public void onEnable() {
		t.reset();
	}
	
	@EventListener
	public void onTick(EventTick e) {
		String[] text = new String[] {
				"Co se tu deje? Cerez od Gothaje 3,0? Ja nevim, ale za�i si to sam.",
				"Nem� sal�mek? Kupuj Gothaj! 3,0 bude o chvili tady.",
				"Kveplej ma vypornej anticheat na cheatovani. Kupuj Gothaj",
				"Profik Buli jak mala holka. Jdi na Gothaj Discord"
			};
		if(t.hasTimeElapsed(35000, true)) {
			String chars = "";
			for(int i = 0; i < 6;i++) {
				chars+= StringUtils.generateRandomCharacterFromAlphaBet();
			}
			mc.thePlayer.sendChatMessage("!"+text[new Random().nextInt(text.length)]+" "+chars);
		}
	}
	
}
