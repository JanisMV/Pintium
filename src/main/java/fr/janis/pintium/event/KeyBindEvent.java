package fr.janis.pintium.event;

import fr.janis.pintium.keybind.KeyBinds;
import fr.janis.pintium.network.Network;
import fr.janis.pintium.network.packet.LaunchFireBall;
import fr.janis.pintium.network.packet.SpawnABoatPacket;
import fr.janis.pintium.network.packet.SpawnSomeZombiesPacket;
import fr.janis.pintium.network.packet.TransformToABlockPacket;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class KeyBindEvent {
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent e){
        if (KeyBinds.SPAWN_BOAT.isDown()){
            Network.CHANNEL.sendToServer(new SpawnABoatPacket());
        }
        if (KeyBinds.HIDE_BLOCK.isDown()){
            Network.CHANNEL.sendToServer(new TransformToABlockPacket());
        }
        if (KeyBinds.SPAWN_ZOMBIE.isDown()){
            Network.CHANNEL.sendToServer(new SpawnSomeZombiesPacket());
        }
        if (KeyBinds.LAUNCH_FIREBALL.isDown()){
            Network.CHANNEL.sendToServer(new LaunchFireBall());
        }

    }
}
