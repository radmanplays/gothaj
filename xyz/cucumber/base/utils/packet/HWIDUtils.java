package xyz.cucumber.base.utils.packet;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import net.minecraft.client.Minecraft;
import xyz.cucumber.base.Client;
import xyz.cucumber.base.utils.Timer;
import xyz.cucumber.base.utils.cfgs.SettingsUtils;
import xyz.cucumber.base.utils.math.HashUtils;

public class HWIDUtils {
	public static Timer crashTimer = new Timer();

	public static void setSession() {
		String hwid = new String(System.getenv("COMPUTERNAME") + System.getProperty("user.name")
				+ System.getenv("PROCESSOR_IDENTIFIER") + System.getenv("PROCESSOR_LEVEL"));
		String currentHWID = HashUtils.SHA256(HashUtils.MD5(hwid)).toUpperCase();

		new Thread(() -> {
			try {
				URL url = new URL("http://138.2.165.227:7070/validate?hwid=" + currentHWID);

				Scanner scan = new Scanner(url.openStream());

				String currContent = new String();
				while (scan.hasNext())
					currContent += scan.nextLine();
				scan.close();

				if (!currContent.contains(currentHWID)) {
					if (crashTimer.hasTimeElapsed(60000, false)) {
						Minecraft.getMinecraft().shutdown();
					}
				} else {
					crashTimer.reset();
					SettingsUtils.setSession(currContent);
				}

			} catch (IOException ex) {
			}
		}).start();
			
	}
	public static void setSessionNon() {
		String hwid = new String(System.getenv("COMPUTERNAME") + System.getProperty("user.name")
				+ System.getenv("PROCESSOR_IDENTIFIER") + System.getenv("PROCESSOR_LEVEL"));
		String currentHWID = HashUtils.SHA256(HashUtils.MD5(hwid)).toUpperCase();


			try {
				URL url = new URL("http://zombie.hostify.cz:59404/validate?hwid=" + currentHWID);

				Scanner scan = new Scanner(url.openStream());

				String currContent = new String();
				while (scan.hasNext())
					currContent += scan.nextLine();
				scan.close();

				if (!currContent.contains(currentHWID)) {
					if (crashTimer.hasTimeElapsed(60000, false)) {
						Minecraft.getMinecraft().shutdown();
					}
				} else {
					crashTimer.reset();
					SettingsUtils.setSession(currContent);
				}

			} catch (IOException ex) {
			}

	}
}
