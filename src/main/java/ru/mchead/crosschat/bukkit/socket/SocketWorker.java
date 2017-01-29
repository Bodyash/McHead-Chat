package ru.mchead.crosschat.bukkit.socket;


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import ru.mchead.crosschat.bukkit.utils.ConfigUtil;

public class SocketWorker implements Listener{
	
	private Socket s = null;
	private BufferedWriter bw;
	
	public SocketWorker(ConfigUtil config) {
		try {
			s = new Socket(config.getConnectionIp(), config.getConnectionPort());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (s != null) {
			System.out.println("Enstablished connection with " + s.getRemoteSocketAddress());
			try {
				bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				new Thread(new ServerListenerThread(s.getInputStream())).start();
			} catch (IOException e2) {
				e2.printStackTrace();
			}

		}
		
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	private void onChatEvent(AsyncPlayerChatEvent e) {
		if (e.getMessage().startsWith("!")){
			sendToServer(e.getMessage().substring(1));
		}
	}

	private void sendToServer(String substring) {
			try {
					bw.write(substring);
					bw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

}
