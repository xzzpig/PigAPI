package com.github.xzzpig.pigapi.bukkit;

import org.bukkit.entity.Player;

public class TExp
{
  public static int ExpToLevel(int exp) {
	int level = 0;
	int tlevel = 0;
	if (exp <= 352) {
		for (tlevel = 0; tlevel <= 16; tlevel++) {
			if (tlevel * tlevel + 6 * tlevel > exp) {
				level = tlevel - 1;
				break;
			}
		}
	} else if (exp <= 1507) {
		for (tlevel = 17; tlevel <= 31; tlevel++) {
			if (2.5 * tlevel * tlevel - 40.5 * tlevel + 360 > exp) {
				level = tlevel - 1;
				break;
			}
		}
	} else {
		for (tlevel = 32;; tlevel++) {
			if (4.5 * tlevel * tlevel - 162.5 * tlevel + 2220 > exp) {
				level = tlevel - 1;
				break;
			}
		}
	}
	return level;
}

  public static int getExpAtLevel(int level)
  {
    if (level > 29)
    {
      return 62 + (level - 30) * 7;
    }
    if (level > 15)
    {
      return 17 + (level - 15) * 3;
    }
    return 17;
  }

  private static int getExpAtLevel(Player player)
  {
    return getExpAtLevel(player.getLevel());
  }

  public static int getExpToLevel(int level)
  {
    int currentLevel = 0;
    int exp = 0;

    while (currentLevel < level)
    {
      exp += getExpAtLevel(currentLevel);
      currentLevel++;
    }
    if (exp < 0)
    {
      exp = 2147483647;
    }
    return exp;
  }

  public static int getExpUntilNextLevel(Player player)
  {
    int exp = Math.round(getExpAtLevel(player) * player.getExp());
    int nextLevel = player.getLevel();
    return getExpAtLevel(nextLevel) - exp;
  }

	public static int getTotalExperience(Player player)
	  {
	    int exp = Math.round(getExpAtLevel(player) * player.getExp());
	    int currentLevel = player.getLevel();
	
	    while (currentLevel > 0)
	    {
	      currentLevel--;
	      exp += getExpAtLevel(currentLevel);
	    }
	    if (exp < 0)
	    {
	      exp = 2147483647;
	    }
	    return exp;
	  }
  
  public static void setTotalExperience(Player player, int exp)
  {
    if (exp < 0)
    {
      throw new IllegalArgumentException("Experience is negative!");
    }
    player.setExp(0.0F);
    player.setLevel(0);
    player.setTotalExperience(0);

    int amount = exp;
    while (amount > 0)
    {
      int expToLevel = getExpAtLevel(player);
      amount -= expToLevel;
      if (amount >= 0)
      {
        player.giveExp(expToLevel);
      }
      else
      {
        amount += expToLevel;
        player.giveExp(amount);
        amount = 0;
      }
    }
  }
}