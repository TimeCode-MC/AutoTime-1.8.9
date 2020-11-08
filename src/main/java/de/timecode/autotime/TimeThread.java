package de.timecode.autotime;

import net.labymod.main.LabyMod;
import net.minecraft.client.Minecraft;

public class TimeThread extends Thread{

    @Override
    public void run() {
        for(int i = 0;;i++){



                    if (AutoTime.mode == TimeMode.DAY) {
                        if (AutoTime.enabled) {
                            if (LabyMod.getInstance().isInGame()) {
                                Minecraft.getMinecraft().theWorld.setWorldTime(1000);
                                Minecraft.getMinecraft().theWorld.setTotalWorldTime(1000);
                            }
                        }
                    } else if (AutoTime.mode == TimeMode.NOON) {
                            if (AutoTime.enabled) {
                                if (LabyMod.getInstance().isInGame()) {
                                    Minecraft.getMinecraft().theWorld.setWorldTime(6000);
                                    Minecraft.getMinecraft().theWorld.setTotalWorldTime(6000);
                                }
                            }
                    } else if (AutoTime.mode == TimeMode.NIGHT) {
                                if (AutoTime.enabled) {
                                    if (LabyMod.getInstance().isInGame()) {
                                        Minecraft.getMinecraft().theWorld.setWorldTime(13000);
                                        Minecraft.getMinecraft().theWorld.setTotalWorldTime(13000);
                                    }
                                }
                    } else if (AutoTime.mode == TimeMode.MIDNIGHT) {
                                    if (AutoTime.enabled) {
                                        if (LabyMod.getInstance().isInGame()) {
                                            Minecraft.getMinecraft().theWorld.setWorldTime(18000);
                                            Minecraft.getMinecraft().theWorld.setTotalWorldTime(18000);
                                        }
                                    }
                    } else if (AutoTime.mode == TimeMode.OWN) {
                                        if (AutoTime.enabled) {
                                            if (LabyMod.getInstance().isInGame()) {
                                                Minecraft.getMinecraft().theWorld.setWorldTime(AutoTime.owntime);
                                                Minecraft.getMinecraft().theWorld.setTotalWorldTime(AutoTime.owntime);
                                            }
                                        }


            }
        }
    }

}
