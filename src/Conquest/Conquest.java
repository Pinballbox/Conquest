package conquest;

import arc.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;
import mindustry.ui.dialogs.PlanetDialog;
import mindustry.ui.dialogs.BaseDialog;
import content.CPlanets;

public class Conquest extends Mod{

    public Conquest(){
        Log.info("Loaded Conquest constructor.");

        //Game load event listener
        Events.on(ClientLoadEvent.class, e -> {
            //show dialog
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("Notice");
                dialog.cont.add("This mod is still in early development. It has no working content whatsoever");
                dialog.cont.button("I understand", dialog::hide).size(100f, 50f);
                dialog.show();
            });
        });
    }

    public void init() {

        CPlanets.load();
    }

}
