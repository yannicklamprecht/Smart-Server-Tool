package com.ysl3000.utils;

import lib.ResourceYamlConfigLoader;

import org.bukkit.plugin.java.JavaPlugin;

public class Config extends ResourceYamlConfigLoader{
		
		public Config(JavaPlugin plugin) {
			super("SmartServerTool/", "config.yml", true, plugin);
		}
		
		public boolean isXpsave() {
			return this.config.getBoolean("world-setting.xpsave");
		}

		public boolean isDiamondDrop() {
			return this.config.getBoolean("drops.diamond-ore-drop");
		}

		public boolean isGlassPaneDrop() {
			return this.config.getBoolean("drops.glassPane-drop");
		}

		public boolean isGlassSandDrop() {
			return this.config.getBoolean("drops.glass-sand-drop");
		}

		public boolean isAppleShear() {
			return this.config.getBoolean("drops.goldenapple-shear");
		}
		// dropchance
		public int getGlassSandDropChance() {

				return this.config.getInt("chance.glass-sand-drop-rate");
			}
		public int getDiamondDropChance() {
				return this.config.getInt("chance.diamond-drop-rate");
			}
		public int getGlassPaneDropChance() {
				return this.config.getInt("chance.glassPane-drop-rate");
			}
			
		public String getFirstJoinMessage() {
				return this.config.getString("message.player.firstjoin");
			}

		public String getLeftmessage() {
				return this.config.getString("message.player.leftmessage");
			}
		
		public String getTimeFormat() {
			return this.config.getString("message.timeformat");
		}
		
		public String getJoinmessage() {
			return this.config.getString("message.player.joinmessage");
		}

		public String getPrivatJoinMessage() {
			return this.config.getString("message.player.privatejoinmessage");
		}
		
		public String getAdminpassword() {

			return this.config.getString("Misc.adminchat-password");
		}
		
		public boolean getPhysicsSand() {
			return this.config.getBoolean("disable-physics.sand");
		}

		public boolean getPhysicsTorch() {
			return this.config.getBoolean("disable-physics.torch");
		}

		public boolean getPhysicsTrapdoor() {
			return this.config.getBoolean("disable-physics.trapdoor");
		}

		public boolean getPhysicsGravel() {
			return this.config.getBoolean("disable-physics.gravel");
		}

		public boolean getNonPermission() {
			return this.config.getBoolean("disable.permission");
		}
		// spread
		public boolean isFireSpead() {
			return this.config.getBoolean("world-setting.prevent-firespread");
		}

		public boolean isLavaspread() {
			return this.config.getBoolean("world-setting.prevent-lava-spread");
		}

		public boolean isNormalspread() {
			return this.config.getBoolean("world-setting.general-spread");
		}

		public boolean isLightning_spread() {
			return this.config.getBoolean("world-setting.trike-spread");
		}
		
		// no connect message
		public String getWhitelistmessage() {
				return this.config.getString("message.service.whitelist");
			}

		public String getBanmessage() {
				return this.config.getString("message.service.ban");
			}

		public String getFullmessage() {
				return this.config.getString("message.service.serverfull");
			}

		public boolean getMaintenance() {
				return this.config.getBoolean("message.service.is-under-construction");
			}

		public String getMaintenanceMessage() {
				return this.config.getString("message.service.construction");
			}
		
		public boolean isMessaging() {
			return this.config.getBoolean("message.enable");
		}
		// advertising
		public boolean getadvert() {
			return this.config.getBoolean("advertising.enable");
		}
		// nice feature Misc
		public boolean isPlayerPressPlate() {
			return this.config.getBoolean("Misc.Save-Player-PressPlate");
		}
		
		public boolean isSleepingIgnored() {
			return this.config.getBoolean("Misc.Sleeping-Ignored");
		}
		
		public boolean getRandomColor() {
			return this.config.getBoolean("message.enable-random-chatcolor");
		}
				// messaging
		public int getDefaultStack() {
			return this.config.getInt("Misc.item-amount");
		}
		public long getAdvertTime() {
			return this.config.getLong("advertising.time-between-adverts");
		}
		
		public int getSaveTimeInterval() {
			return this.config.getInt("config-save-interval");
		}
		
		public String getAnswer() {
			return this.config.getString("question.answer");
		}

		public String getDestinationGroup() {
			return this.config.getString("question.unlockGroupName");
		}

		public String getQuestion() {
			return this.config.getString("question.answer");
		}

		public String getAdvertMessage() {
			return this.config.getString("advertising.advert-message");
		}

		public String getAdvertPrefix() {
			return this.config.getString("advertising.advert-prefix");
		}
		
		public boolean isBcreeper() {
			return this.config.getBoolean("world-setting.block-creeper");
		}

		public boolean isBender() {
			return this.config.getBoolean("world-setting.block-ender");
		}

		public boolean isTntsave() {
			return this.config.getBoolean("world-setting.prevent-tnt");
		}
	}

 