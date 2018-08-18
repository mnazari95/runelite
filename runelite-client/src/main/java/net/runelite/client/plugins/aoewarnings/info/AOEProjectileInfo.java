/*
 * Copyright (c) 2017, Adam <Adam@sigterm.info>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.plugins.aoewarnings.info;

import net.runelite.api.ProjectileID;
import net.runelite.client.plugins.aoewarnings.AoeWarningConfig;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public enum AOEProjectileInfo implements AOEInfo
{
	/**
	 * OLM
	 */
	OLM_BURN_AOE(ProjectileID.OLM_BURNING_AOE, 2800, 3) {
		@Override
		public boolean isEnabled(AoeWarningConfig config) {
			return config.isOlmEnabled();
		}
	},
	//TODO find out bomb ID
	OLM_BOMB_AOE(ProjectileID.OLM_BOMB_AOE, 2200, 5) {
		@Override
		public boolean isEnabled(AoeWarningConfig config) {
			return config.isOlmEnabled();
		}
	},
	/**
	 * Other
	 */

	LIZARDMAN_SHAMAN_AOE(ProjectileID.LIZARDMAN_SHAMAN_AOE, 3000, 4) {
		@Override
		public boolean isEnabled(AoeWarningConfig config) {
			return config.isShamansEnabled();
		}
	},
	CRAZY_ARCHAEOLOGIST_AOE(ProjectileID.CRAZY_ARCHAEOLOGIST_AOE, 3000, 3) {
		@Override
		public boolean isEnabled(AoeWarningConfig config) {
			return config.isArchaeologistEnabled();
		}
	},
	ICE_DEMON_RANGED_AOE(ProjectileID.ICE_DEMON_RANGED_AOE, 3000, 3) {
		@Override
		public boolean isEnabled(AoeWarningConfig config) {
			return config.isIceDemonEnabled();
		}
	},
	/**
	 * When you don't have pray range on ice demon does an ice barrage
	 */
	ICE_DEMON_ICE_BARRAGE_AOE(ProjectileID.ICE_DEMON_ICE_BARRAGE_AOE, 3000, 3) {
		@Override
		public boolean isEnabled(AoeWarningConfig config) {
			return config.isIceDemonEnabled();
		}
	},
	/**
	 * The AOE when vasa first starts
	 */
	VASA_AWAKEN_AOE(ProjectileID.VASA_AWAKEN_AOE, 4500, 3) {
		@Override
		public boolean isEnabled(AoeWarningConfig config) {
			return config.isVasaEnabled();
		}
	},
	VASA_RANGED_AOE(ProjectileID.VASA_RANGED_AOE, 3000, 3) {
		@Override
		public boolean isEnabled(AoeWarningConfig config) {
			return config.isVasaEnabled();
		}
	},
	TEKTON_METEOR_AOE(ProjectileID.TEKTON_METEOR_AOE, 4000, 3) {
		@Override
		public boolean isEnabled(AoeWarningConfig config) {
			return config.isTektonEnabled();
		}
	},

	/**
	 * The AOEs of Vorkath
	 */
	VORKATH_BOMB(ProjectileID.VORKATH_BOMB_AOE, 2400, 3) {
		@Override
		public boolean isEnabled(AoeWarningConfig config) {
			return config.isVorkathEnabled();
		}
	},
	VORKATH_POISON_POOL(ProjectileID.VORKATH_POISON_POOL_AOE, 1800, 1) {
		@Override
		public boolean isEnabled(AoeWarningConfig config) {
			return config.isVorkathEnabled();
		}
	},
	VORKATH_SPAWN(ProjectileID.VORKATH_SPAWN_AOE, 3000, 1) {
		@Override
		public boolean isEnabled(AoeWarningConfig config) {
			return config.isVorkathEnabled();
		}
	}, //extra tick because hard to see otherwise
	VORKATH_TICK_FIRE(ProjectileID.VORKATH_TICK_FIRE_AOE, 600, 1) {
		@Override
		public boolean isEnabled(AoeWarningConfig config) {
			return config.isVorkathEnabled();
		}
	},

	/**
	 * the AOEs of Galvek
	 */
	GALVEK_MINE(ProjectileID.GALVEK_MINE, 3600, 3) {
		@Override
		public boolean isEnabled(AoeWarningConfig config) {
			return config.isGalvekEnabled();
		}
	},
	GALVEK_BOMB(ProjectileID.GALVEK_BOMB, 2400, 3) {
		@Override
		public boolean isEnabled(AoeWarningConfig config) {
			return config.isGalvekEnabled();
		}
	},

	/**
	 * the AOE of Vet'ion
	 */
	VETION_LIGHTNING(ProjectileID.VETION_LIGHTNING, 3000, 1) {
		@Override
		public boolean isEnabled(AoeWarningConfig config) {
			return config.isVetionEnabled();
		}
	},

	/**
	 * the AOE of Chaos Fanatic
	 */
	CHAOS_FANATIC(ProjectileID.CHAOS_FANATIC_AOE, 3000, 1) {
		@Override
		public boolean isEnabled(AoeWarningConfig config) {
			return config.isChaosFanaticEnabled();
		}
	},

	/**
	 * the AOE of the Corporeal Beast
	 */

	CORPOREAL_BEAST(ProjectileID.CORPOREAL_BEAST_AOE, 3000, 1) {
		@Override
		public boolean isEnabled(AoeWarningConfig config) {
			return config.isCorpEnabled();
		}
	},
	CORPOREAL_BEAST_DARK_CORE(ProjectileID.CORPOREAL_BEAST_DARK_CORE_AOE, 3000, 3) {
		@Override
		public boolean isEnabled(AoeWarningConfig config) {
			return config.isCorpEnabled();
		}
	},

	/**
	 * the AOE of the Wintertodt snow that falls
	 */
	WINTERTODT_SNOW_FALL(ProjectileID.WINTERTODT_SNOW_FALL_AOE, 4000, 3) {
		@Override
		public boolean isEnabled(AoeWarningConfig config) {
			return config.isWintertodtEnabled();
		}
	};


	/**
	 * The id of the projectile to trigger this AoE warning
	 */
	private final int id;

	/**
	 * How long the indicator should last for this AoE warning This might
	 * need to be a bit longer than the projectile actually takes to land as
	 * there is a fade effect on the warning
	 */
	private final Duration lifeTime;

	/**
	 * The size of the splash radius of the AoE warning Ex. Lizardman shaman
	 * AoE is a 3x3, so aoeSize = 3
	 */
	private final int aoeSize;

	private static final Map<Integer, AOEProjectileInfo> map = new HashMap<>();

	static
	{
		for (AOEProjectileInfo aoe : values())
		{
			map.put(aoe.id, aoe);
		}
	}

	AOEProjectileInfo(int id, int lifeTimeMillis, int aoeSize)
	{
		this.id = id;
		this.lifeTime = Duration.ofMillis(lifeTimeMillis);
		this.aoeSize = aoeSize;
	}

	public int getId()
	{
		return id;
	}

	public static AOEProjectileInfo getById(int id)
	{
		return map.get(id);
	}

	@Override
	public Duration getDuration() {
		return lifeTime;
	}

	@Override
	public int getSize() {
		return aoeSize;
	}
}
