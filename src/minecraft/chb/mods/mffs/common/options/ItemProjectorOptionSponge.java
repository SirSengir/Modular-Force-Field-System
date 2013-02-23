package chb.mods.mffs.common.options;

import net.minecraft.world.World;
import chb.mods.mffs.api.PointXYZ;
import chb.mods.mffs.common.ModularForceFieldSystem;
import chb.mods.mffs.common.tileentity.TileEntityProjector;

public class ItemProjectorOptionSponge extends ItemProjectorOptionBase
  implements IInteriorCheck
{
  public ItemProjectorOptionSponge(int i)
  {
    super(i);
    setIconIndex(35);
  }

  public void checkInteriorBlock(PointXYZ png, World world, TileEntityProjector Projector)
  {
    if (world.getBlockMaterial(png.X, png.Y, png.Z).isLiquid())
      if (!ModularForceFieldSystem.forcefieldremoveonlywaterandlava.booleanValue())
        world.setBlockWithNotify(png.X, png.Y, png.Z, 0);
      else if ((world.getBlockId(png.X, png.Y, png.Z) == 8) || (world.getBlockId(png.X, png.Y, png.Z) == 9) || (world.getBlockId(png.X, png.Y, png.Z) == 10) || (world.getBlockId(png.X, png.Y, png.Z) == 11))
      {
        world.setBlockWithNotify(png.X, png.Y, png.Z, 0);
      }
  }
}