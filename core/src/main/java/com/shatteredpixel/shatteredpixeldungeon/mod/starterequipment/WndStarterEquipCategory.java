package com.shatteredpixel.shatteredpixeldungeon.mod.starterequipment;

import com.shatteredpixel.shatteredpixeldungeon.mod.ModOption;
import com.shatteredpixel.shatteredpixeldungeon.mod.ModRegistry;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.Artifact;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ClothArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.LeatherArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.MailArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.PlateArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ScaleArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.AlchemistsToolkit;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.ChaliceOfBlood;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.CloakOfShadows;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.DriedRose;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.EtherealChains;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.HornOfPlenty;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.MasterThievesArmband;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.SandalsOfNature;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.TalismanOfForesight;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.TimekeepersHourglass;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.UnstableSpellbook;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfAccuracy;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfArcana;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfElements;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfEnergy;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfEvasion;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfForce;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfFuror;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfHaste;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfMight;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfSharpshooting;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfTenacity;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfWealth;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfBlastWave;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfCorrosion;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfCorruption;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfDisintegration;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfFireblast;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfFrost;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfLightning;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfLivingEarth;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfMagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfPrismaticLight;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfRegrowth;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfTransfusion;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfWarding;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.BattleAxe;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Crossbow;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Dagger;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Flail;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Glaive;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Greataxe;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Greatshield;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Greatsword;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Spear;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.WarHammer;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.WarScythe;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Whip;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.RedButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;

/**
 * 开局装备分类与自选弹窗（纯正原生中文文本）
 */
public class WndStarterEquipCategory extends Window implements ModOption {
	public String id(){ return "starter-equipment"; }
	public String title(){ return "开局自选装备"; }
	public void onStart(){ GameScene.show(new WndStarterEquipCategory()); }

	private static final int WIDTH      = 120;
	private static final int TTL_HEIGHT = 16;
	private static final int BTN_HEIGHT = 16;
	private static final int GAP        = 2;

	public WndStarterEquipCategory() {
		super();

		RenderedTextBlock title = PixelScene.renderTextBlock("选择装备类别", 12);
		title.hardlight(TITLE_COLOR);
		title.setPos((WIDTH - title.width()) / 2, (TTL_HEIGHT - title.height()) / 2);
		PixelScene.align(title);
		add(title);

		float pos = TTL_HEIGHT + GAP;

		// 1. 近战/远程武器 (包含5阶及长鞭、长矛、一阶匕首)
		RedButton btnWeapon = new RedButton("武器") {
			@Override
			protected void onClick() {
				hide();
				GameScene.show(new WndItemChoice("选择武器", new ItemEntry[]{
						new ItemEntry(WarScythe.class),
						new ItemEntry(Greatsword.class),
						new ItemEntry(WarHammer.class),
						new ItemEntry(Greataxe.class),
						new ItemEntry(Glaive.class),
						new ItemEntry(BattleAxe.class),
						new ItemEntry(Flail.class),
						new ItemEntry(Greatshield.class),
						new ItemEntry(Crossbow.class),
						new ItemEntry(Whip.class),
						new ItemEntry(Spear.class),
						new ItemEntry(Dagger.class)
				}));
			}
		};
		btnWeapon.setRect(0, pos, WIDTH, BTN_HEIGHT);
		add(btnWeapon);
		pos = btnWeapon.bottom() + GAP;

		// 2. 全阶防具列表 (布甲、皮甲、锁甲、鳞甲、板甲)
		RedButton btnArmor = new RedButton("防具") {
			@Override
			protected void onClick() {
				hide();
				GameScene.show(new WndItemChoice("选择防具", new ItemEntry[]{
						new ItemEntry(ClothArmor.class),
						new ItemEntry(LeatherArmor.class),
						new ItemEntry(MailArmor.class),
						new ItemEntry(ScaleArmor.class),
						new ItemEntry(PlateArmor.class)
				}));
			}
		};
		btnArmor.setRect(0, pos, WIDTH, BTN_HEIGHT);
		add(btnArmor);
		pos = btnArmor.bottom() + GAP;

		// 3. 全法杖列表
		RedButton btnWand = new RedButton("法杖") {
			@Override
			protected void onClick() {
				hide();
				GameScene.show(new WndItemChoice("选择法杖", new ItemEntry[]{
						new ItemEntry(WandOfFireblast.class),
						new ItemEntry(WandOfLightning.class),
						new ItemEntry(WandOfDisintegration.class),
						new ItemEntry(WandOfFrost.class),
						new ItemEntry(WandOfCorrosion.class),
						new ItemEntry(WandOfBlastWave.class),
						new ItemEntry(WandOfCorruption.class),
						new ItemEntry(WandOfLivingEarth.class),
						new ItemEntry(WandOfPrismaticLight.class),
						new ItemEntry(WandOfRegrowth.class),
						new ItemEntry(WandOfTransfusion.class),
						new ItemEntry(WandOfWarding.class),
						new ItemEntry(WandOfMagicMissile.class)
				}));
			}
		};
		btnWand.setRect(0, pos, WIDTH, BTN_HEIGHT);
		add(btnWand);
		pos = btnWand.bottom() + GAP;

		// 4. 全神器列表
		RedButton btnArtifact = new RedButton("神器") {
			@Override
			protected void onClick() {
				hide();
				GameScene.show(new WndItemChoice("选择神器", new ItemEntry[]{
						new ItemEntry(ChaliceOfBlood.class),
						new ItemEntry(CloakOfShadows.class),
						new ItemEntry(TimekeepersHourglass.class),
						new ItemEntry(MasterThievesArmband.class),
						new ItemEntry(EtherealChains.class),
						new ItemEntry(HornOfPlenty.class),
						new ItemEntry(TalismanOfForesight.class),
						new ItemEntry(UnstableSpellbook.class),
						new ItemEntry(DriedRose.class),
						new ItemEntry(AlchemistsToolkit.class),
						new ItemEntry(SandalsOfNature.class)
				}));
			}
		};
		btnArtifact.setRect(0, pos, WIDTH, BTN_HEIGHT);
		add(btnArtifact);
		pos = btnArtifact.bottom() + GAP;

		// 5. 全戒指列表
		RedButton btnRing = new RedButton("戒指") {
			@Override
			protected void onClick() {
				hide();
				GameScene.show(new WndItemChoice("选择戒指", new ItemEntry[]{
						new ItemEntry(RingOfMight.class),
						new ItemEntry(RingOfForce.class),
						new ItemEntry(RingOfHaste.class),
						new ItemEntry(RingOfFuror.class),
						new ItemEntry(RingOfEvasion.class),
						new ItemEntry(RingOfWealth.class),
						new ItemEntry(RingOfAccuracy.class),
						new ItemEntry(RingOfArcana.class),
						new ItemEntry(RingOfElements.class),
						new ItemEntry(RingOfEnergy.class),
						new ItemEntry(RingOfSharpshooting.class),
						new ItemEntry(RingOfTenacity.class)
				}));
			}
		};
		btnRing.setRect(0, pos, WIDTH, BTN_HEIGHT);
		add(btnRing);
		pos = btnRing.bottom() + GAP;

		resize(WIDTH, (int) pos);
	}

	/**
	 * 对应的具体装备选择子窗口
	 */
	private static class WndItemChoice extends Window {

		public WndItemChoice(String categoryTitle, ItemEntry[] entries) {
			super();

			RenderedTextBlock title = PixelScene.renderTextBlock(categoryTitle, 12);
			title.hardlight(TITLE_COLOR);
			title.setPos((WIDTH - title.width()) / 2, (TTL_HEIGHT - title.height()) / 2);
			PixelScene.align(title);
			add(title);

			float pos = TTL_HEIGHT + GAP;

			for (final ItemEntry entry : entries) {
				RedButton btn = new RedButton(entry.displayName) {
					@Override
					protected void onClick() {
						hide();
						try {
							Item item = entry.itemClass.newInstance();
							giveStarterItem(item);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				};
				btn.setRect(0, pos, WIDTH, BTN_HEIGHT);
				add(btn);
				pos = btn.bottom() + GAP;
			}

			resize(WIDTH, (int) pos);
		}
	}

	private static class ItemEntry {
		String displayName;
		Class<? extends Item> itemClass;

		ItemEntry(Class<? extends Item> itemClass) {
			this.itemClass = itemClass;
			this.displayName = Messages.titleCase(Messages.get(itemClass, "name"));
		}

		ItemEntry(String displayName, Class<? extends Item> itemClass) {
			this.displayName = displayName;
			this.itemClass = itemClass;
		}
	}

	/**
	 * 赋予玩家所选装备并固定赋予 +3 强化等级
	 */
	public static void giveStarterItem(Item item) {
		if (item == null || Dungeon.hero == null) return;

		item.upgrade(3);
		item.identify();
		if (item instanceof Artifact) Generator.removeArtifact((Class<? extends Artifact>) item.getClass());
		item.collect(Dungeon.hero.belongings.backpack);
		GLog.p("开局奖励：" + item.name() + " (+" + item.level() + ")");
	}
}
