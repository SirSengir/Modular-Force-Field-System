package mffs.common.multitool;

import mffs.api.ISwitchabel;
import mffs.common.Functions;
import mffs.common.SecurityHelper;
import mffs.common.SecurityRight;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ItemSwitch extends ItemMultitool
{
	public ItemSwitch(int id)
	{
		super(id, 1);
	}

	public boolean onItemUseFirst(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		if (world.isRemote)
		{
			return false;
		}

		TileEntity tileentity = world.getBlockTileEntity(x, y, z);

		if ((tileentity instanceof ISwitchabel))
		{
			if (SecurityHelper.isAccessGranted(tileentity, entityplayer, world, SecurityRight.EB))
			{
				if (((ISwitchabel) tileentity).isSwitchabel())
				{
					if (consumePower(itemstack, 1000, true))
					{
						consumePower(itemstack, 1000, false);
						((ISwitchabel) tileentity).toggelSwitchValue();
						return true;
					}

					Functions.ChattoPlayer(entityplayer, "[MultiTool] Fail: not enough FP please charge");
					return false;
				}

				Functions.ChattoPlayer(entityplayer, "[MultiTool] Fail: Object not in switch enable mode");
				return false;
			}

		}

		return false;
	}

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		return super.onItemRightClick(itemstack, world, entityplayer);
	}
}