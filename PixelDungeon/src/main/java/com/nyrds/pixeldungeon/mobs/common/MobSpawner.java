package com.nyrds.pixeldungeon.mobs.common;

import android.support.annotation.NonNull;

import com.nyrds.pixeldungeon.mobs.necropolis.JarOfSouls;
import com.watabou.pixeldungeon.actors.mobs.Bestiary;
import com.watabou.pixeldungeon.actors.mobs.Mob;
import com.watabou.pixeldungeon.levels.Level;

public class MobSpawner {

	@NonNull
	static public Mob spawnRandomMob(Level level, int position) {
		Mob mob = Bestiary.mutable();
		mob.setPos(position);
		mob.setState(mob.WANDERING);
		level.spawnMob(mob);
		return mob;
	}

	static public void spawnJarOfSouls(Level level, int position) {
		Mob mob = new JarOfSouls();
		mob.setPos(position);
		mob.setState(mob.WANDERING);
		level.spawnMob(mob);
	}

}
