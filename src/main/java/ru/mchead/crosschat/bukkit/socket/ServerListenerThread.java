package ru.mchead.crosschat.bukkit.socket;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.bukkit.Bukkit;

public class ServerListenerThread implements Runnable {
	
	private InputStream is;
	//Принимаем стрим
	public ServerListenerThread(InputStream is) {
		this.is = is;
	}
	//Все что ниже - читаем стрим, печатаем в чат.
	@Override
	public void run() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(this.is))){
			String str = null;
			while ((str = br.readLine()) != null) {
				Bukkit.broadcastMessage(str);
			}
		} catch (Exception e) {
			System.out.println("Disconnected");
		}
		
	}

}
