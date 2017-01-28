package ru.mchead.crosschat.bukkit.socket;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ServerListenerThread implements Runnable {
	
	private InputStream is;
	//Принимаем стрим
	public ServerListenerThread(InputStream is) {
		this.is = is;
	}
	//Все что ниже - читаем стрим, печатаем в консоль.
	//Думаю обьяснять почему этот код в отдельном потоке - не нужно
	@Override
	public void run() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(this.is))){
			String str = null;
			while ((str = br.readLine()) != null) {
				for (Player player : Bukkit.getOnlinePlayers()) {
					if(player.isOnline()){
						player.sendMessage(str);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Disconnected");
		}
		
	}

}
