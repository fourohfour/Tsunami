package io.github.fourohfour.tsunami.minigame.rotation;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SimpleRotation extends Rotation{
	private List<String> rotation;
	private int point = -1;
	
	public SimpleRotation(String path, String[] maps) {
		super(path, maps);
	}

	@Override
	protected void onLoad() {
		for(String map : this.maps.keySet()){
			if(this.mapExists(map)){
				rotation.add(map);
			}
		}
		rotate();
		
	}
	
	@Override
	protected void onUnload() {
		// TODO Auto-generated method stub
		
	}
	
	public RotationMap rotate(){
		point++;
		try{
			return maps.get(rotation.get(point));
		}
		catch (IndexOutOfBoundsException e){
			this.unloadMaps();
		}
		return null;
	}

	@Override
	protected void removePlayer(Player p) {
		p.kickPlayer("Server Closed");
		
	}

}
