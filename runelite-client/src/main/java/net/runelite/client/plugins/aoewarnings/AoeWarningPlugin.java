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
package net.runelite.client.plugins.aoewarnings;

import com.google.common.collect.Lists;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Binder;
import com.google.inject.Provides;
import net.runelite.api.Actor;
import net.runelite.api.Projectile;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.events.AnimationChanged;
import net.runelite.api.events.GameObjectSpawned;
import net.runelite.api.events.GraphicsObjectCreated;
import net.runelite.api.events.ProjectileMoved;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.aoewarnings.info.AOEAnimationInfo;
import net.runelite.client.plugins.aoewarnings.info.AOEGraphicsInfo;
import net.runelite.client.plugins.aoewarnings.info.AOEObjectInfo;
import net.runelite.client.plugins.aoewarnings.info.AOEProjectileInfo;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.time.Instant;
import java.util.List;

@PluginDescriptor(
	name = "AoE projectile warning plugin"
)
public class AoeWarningPlugin extends Plugin
{
	@Inject
	AoeWarningOverlay overlay;

	@Inject
    AoeWarningConfig config;

	@Inject
	private OverlayManager overlayManager;

	private final List<AoeData> aoeData = Lists.newArrayList();

	@Override
	public void configure(Binder binder)
	{
		binder.bind(AoeWarningOverlay.class);
	}

	@Provides
    AoeWarningConfig getConfig(ConfigManager configManager)
	{
		return configManager.getConfig(AoeWarningConfig.class);
	}

	public List<AoeData> getAoeData()
	{
		return aoeData;
	}

	@Override
	public void startUp() {
		overlayManager.add(overlay);
	}

	@Override
	public void shutDown() {
		overlayManager.remove(overlay);
	}

	/**
	 * Called when a projectile is set to move towards a point. For
	 * aoeData that target the ground, like AoE aoeData from
	 * Lizardman Shamans, this is only called once
	 */
	@Subscribe
	public void onProjectileMoved(ProjectileMoved event) {
		Projectile projectile = event.getProjectile();

		// AoE aoeData do not target anything
		if (projectile.getInteracting() != null) {
			return;
		}

		int projectileId = projectile.getId();
		AOEProjectileInfo aoeProjectileInfo = AOEProjectileInfo.getById(projectileId);

		if (aoeProjectileInfo != null && aoeProjectileInfo.isEnabled(config)) {
			LocalPoint targetPoint = event.getPosition();
			AoeData aoeData = new AoeData(Instant.now(), targetPoint, aoeProjectileInfo);
			this.aoeData.add(aoeData);
		}

	}

	/**
	 * This is called whenever an NPC performs an animation.
	 */
	@Subscribe
	public void onNPCAnimation(AnimationChanged event) {
		Actor actor = event.getActor();

		if(actor == null) {
			return;
		}

		int animationID = actor.getAnimation();
		AOEAnimationInfo info = AOEAnimationInfo.getFromAnimationID(animationID);

		if(info != null && info.isEnabled(config)) {
			LocalPoint location = actor.getLocalLocation();
			AoeData aoeData = new AoeData(Instant.now(), location, info);
			this.aoeData.add(aoeData);
		}
	}

	/**
	 * Detects the creation of graphics objects, then adds a AOE warning to them.
	 * For example, olm falling crystals.
	 */
	@Subscribe
	public void graphicsChanged(GraphicsObjectCreated event) {

		int id = event.getGraphicsObject().getId();
		AOEGraphicsInfo info = AOEGraphicsInfo.getFromID(id);

		if(info != null && info.isEnabled(config)) {
			LocalPoint location = event.getGraphicsObject().getLocation();
			AoeData aoeData = new AoeData(Instant.now(), location, info);
			this.aoeData.add(aoeData);
		}
	}

	/**
	 * Whenever an object is spawned in the world, check if the object has an AOE.
	 */
	@Subscribe
	public void onObjectCreate(GameObjectSpawned event) {

		int objectID = event.getGameObject().getId();
		AOEObjectInfo info = AOEObjectInfo.getFromID(objectID);

		if(info != null && info.isEnabled(config)) {
			LocalPoint location = event.getGameObject().getLocalLocation();
			AoeData aoeData = new AoeData(Instant.now(), location, info);
			this.aoeData.add(aoeData);
		}
	}
}
