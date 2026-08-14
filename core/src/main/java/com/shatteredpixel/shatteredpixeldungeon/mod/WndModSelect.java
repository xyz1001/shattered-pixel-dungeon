package com.shatteredpixel.shatteredpixeldungeon.mod;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.CheckBox;
import com.shatteredpixel.shatteredpixeldungeon.ui.RedButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WndModSelect extends Window {

 public WndModSelect(){this(false);}

 public WndModSelect(boolean onlyNew){
  final int width=135;
  final Set<String> selected=new HashSet<>();
  final List<ModOption> options=onlyNew?ModRegistry.pendingOptions():ModRegistry.options();
  float y=18;
  RenderedTextBlock title=PixelScene.renderTextBlock(onlyNew?"New Mods Available":"开局 MOD 选项",12);
  title.hardlight(TITLE_COLOR);title.setPos(4,2);add(title);
  if (onlyNew){
   RenderedTextBlock description=PixelScene.renderTextBlock(6);
   description.text("These mods were added after this save was created. Choose any you want to enable. This is optional.",width-8);
   description.setPos(4,title.bottom()+2);add(description);y=description.bottom()+4;
  }
  for(final ModOption o:options){
   CheckBox box=new CheckBox(o.title()){
    protected void onClick(){super.onClick();if(checked())selected.add(o.id());else selected.remove(o.id());}
   };
   if (!onlyNew){
    boolean checked=ModSettings.hasDefault(o.id())?ModSettings.defaultEnabled(o.id()):o.defaultEnabled();
    box.checked(checked);if(checked)selected.add(o.id());
   }
   box.setRect(0,y,width,16);add(box);y+=18;
  }
  RedButton confirm=new RedButton(onlyNew?"Continue":"确定进入游戏"){
   protected void onClick(){
    hide();
    if (onlyNew){
     ModRegistry.confirmPending(selected);
     try { Dungeon.saveAll(); } catch (IOException e) { ShatteredPixelDungeon.reportException(e); }
    } else {
     ModRegistry.start(selected);
    }
   }
  };
  confirm.setRect(0,y,width,18);add(confirm);resize(width,(int)confirm.bottom());
 }
}
