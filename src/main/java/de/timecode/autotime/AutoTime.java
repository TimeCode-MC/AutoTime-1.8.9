package de.timecode.autotime;

import net.labymod.api.LabyModAddon;
import net.labymod.gui.elements.DropDownMenu;
import net.labymod.main.LabyMod;
import net.labymod.main.lang.LanguageManager;
import net.labymod.settings.elements.*;
import net.labymod.utils.Consumer;
import net.labymod.utils.Material;
import net.labymod.utils.ServerData;

import java.util.List;

public class AutoTime extends LabyModAddon {

    public static boolean enabled;
    public static boolean onServer;
    public static TimeMode mode;
    public static Integer owntime;
    public static TimeThread t;


    @Override
    public void onEnable() {
        System.out.println("TimeCode's AutoTime LabyMod Addon is now enabled!");
        t = new TimeThread();
        t.start();
        getApi().getEventManager().registerOnJoin(new Consumer<ServerData>() {
            @Override
            public void accept(ServerData data) {

                    onServer = true;
                    t = new TimeThread();
                    t.start();

            }
        });

        getApi().getEventManager().registerOnQuit(new Consumer<ServerData>() {
            @Override
            public void accept(ServerData data) {

                    onServer = false;
                    t.stop();

            }
        });
    }

    @Override
    public void loadConfig() {
        enabled = getConfig().has("Enabled") ? getConfig().get("Enabled").getAsBoolean() : false;
        mode = getConfig().has("Mode") ? null : TimeMode.DAY;
        if(getConfig().has("Mode")) {
            String m = getConfig().get("Mode").getAsString();
            if (m.equalsIgnoreCase("NOON")) {
                mode = TimeMode.NOON;
            } else if (m.equalsIgnoreCase("DAY")) {
                mode = TimeMode.DAY;
            } else if (m.equalsIgnoreCase("NIGHT")) {
                mode = TimeMode.NIGHT;
            } else if (m.equalsIgnoreCase("MIDNIGHT")) {
                mode = TimeMode.MIDNIGHT;
            } else if (m.equalsIgnoreCase("OWN")) {
                mode = TimeMode.OWN;
            }else{
                mode = TimeMode.DAY;
            }
        }
        owntime = getConfig().has("Own-Time") ? getConfig().get("Own-Time").getAsInt() : 0;
    }

    @Override
    protected void fillSettings(List<SettingsElement> settings) {
        settings.add( new BooleanElement( "Enabled", new ControlElement.IconData( Material.REDSTONE_TORCH_ON ), new Consumer<Boolean>() {
            @Override
            public void accept( Boolean value ) {
                System.out.println( "[AutoTime] Enabled: " + value );
                enabled = value;
                getConfig().addProperty("Enabled", value);
                saveConfig();
            }
        } ,enabled));

        final DropDownMenu<TimeMode> addm = new DropDownMenu<TimeMode>( "TimeModes", 0, 0, 0, 0 )
                .fill( TimeMode.values() );
        DropDownElement<TimeMode> add = new DropDownElement<TimeMode>( "TimeModes", addm );

        addm.setSelected(mode);

        add.setChangeListener( new Consumer<TimeMode>() {
            @Override
            public void accept( TimeMode alignment ) {
                System.out.println( "New TimeMode: " + alignment.name() );
                mode = alignment;
                getConfig().addProperty("Mode", alignment.name());
                saveConfig();
            }
        } );

        settings.add(add);


        NumberElement ot = new NumberElement( "Own Time (MC-Ticks)",
                new ControlElement.IconData( Material.WATCH ), owntime);

        ot.addCallback( new Consumer<Integer>() {
            @Override
            public void accept( Integer value ) {
                System.out.println( "Own Time: " + value );
                owntime = value;
                getConfig().addProperty("Own-Time", value);
                saveConfig();
            }
        } );
        ot.setMaxValue(24000);
        ot.setMinValue(0);

        settings.add(ot);

    }
}
