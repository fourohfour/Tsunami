package io.github.fourohfour.tsunami.minigame.rotation;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import io.github.fourohfour.tsunami.utils.ZipUtils;

public abstract class Rotation {
	protected String path;
	protected Map<String, RotationMap> maps;

	public Rotation (String path, String[] maps){
		this.path = path;
		Map<String, RotationMap> mapmap = new HashMap<>(); //geddit ;)
		for (String map : maps){
			mapmap.put(map, null);
		}
		this.maps = mapmap;
	}
	
	protected abstract void onLoad();
	protected abstract void onUnload();
	protected abstract void removePlayer(Player p);
	
	public String getPath(){
		return path;
	}
	
	public Map<String, RotationMap> getMaps(){
		return maps;
	}
	
	public void loadMaps(){
		for(String map : maps.keySet()){
			if (ZipUtils.unzip(path + "/" + map + ".zip", "./" + map)) {
				WorldCreator creator = new WorldCreator(map);
				Bukkit.createWorld(creator);
				System.out.println("Loaded Map: " + map);
			}
			else {
				System.out.println("Could Not Load Map: " + map);
			}
		}
		this.onLoad();
	}
	
	public void unloadMaps(){
		for (String map : maps.keySet()){
			if(this.mapExists(map)){
				for(Player p : Bukkit.getWorld(map).getPlayers()){
					this.removePlayer(p);
				}
				Bukkit.unloadWorld(map, false);
				try {
					FileUtils.deleteDirectory(new File("./" + map));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		this.onUnload();
	}
	
	public RotationMap getMap(String s){
		return maps.get(s);
	}
	
	public boolean mapExists(String s){
		if(maps.get(s) == null){
			return false;
		}
		return true;
	}
	
}
