package com.raoulvdberge.refinedstorage.block.advancedCrafter;

import com.raoulvdberge.refinedstorage.RS;
import com.raoulvdberge.refinedstorage.RSGui;
import com.raoulvdberge.refinedstorage.block.BlockNode;
import com.raoulvdberge.refinedstorage.block.info.BlockDirection;
import com.raoulvdberge.refinedstorage.block.info.BlockInfoBuilder;
import com.raoulvdberge.refinedstorage.render.IModelRegistration;
import com.raoulvdberge.refinedstorage.render.model.baked.BakedModelFullbright;
import com.raoulvdberge.refinedstorage.tile.advancedCrafter.TileDiamondCrafter;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class BlockDiamondCrafter extends BlockNode {
    public BlockDiamondCrafter() {
        super(BlockInfoBuilder.forId("diamondcrafter").tileEntity(TileDiamondCrafter::new).create());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerModels(IModelRegistration modelRegistration) {
        modelRegistration.setModel(this, 0, new ModelResourceLocation(info.getId(), "connected=false,direction=north"));

        modelRegistration.addBakedModelOverride(info.getId(), base -> new BakedModelFullbright(
            base,
            RS.ID + ":blocks/diamondcrafter/cutouts/side_connected",
            RS.ID + ":blocks/diamondcrafter/cutouts/side_connected_90",
            RS.ID + ":blocks/diamondcrafter/cutouts/side_connected_180",
            RS.ID + ":blocks/diamondcrafter/cutouts/side_connected_270",
            RS.ID + ":blocks/diamondcrafter/cutouts/front_connected"
        ));
    }

    @Nonnull
    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    @Nullable
    public BlockDirection getDirection() {
        return BlockDirection.ANY_FACE_PLAYER;
    }

    @Override
    public void onBlockPlacedBy(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nonnull EntityLivingBase placer, @Nonnull ItemStack stack) {
        super.onBlockPlacedBy(world, pos, state, placer, stack);

        if (!world.isRemote) {
            TileEntity tile = world.getTileEntity(pos);

            if (tile instanceof TileDiamondCrafter && stack.hasDisplayName()) {
                ((TileDiamondCrafter) tile).getNode().setDisplayName(stack.getDisplayName());
                ((TileDiamondCrafter) tile).getNode().markNetworkNodeDirty();
            }
        }
    }

    @Override
    public boolean onBlockActivated(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nonnull EntityPlayer player, @Nonnull EnumHand hand, @Nonnull EnumFacing side, float hitX, float hitY, float hitZ) {
        return openNetworkGui(RSGui.DIAMONDCRAFTER, player, world, pos, side);
    }

    @Override
    public void getDrops(@Nonnull NonNullList<ItemStack> drops, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull IBlockState state, int fortune) {
        super.getDrops(drops, world, pos, state, fortune);

        String displayName = ((TileDiamondCrafter) world.getTileEntity(pos)).getNode().getDisplayName();

        if (displayName != null) {
            for (ItemStack drop : drops) {
                if (drop.getItem() == Item.getItemFromBlock(this)) {
                    drop.setStackDisplayName(displayName);
                }
            }
        }
    }

    @Override
    public boolean hasConnectedState() {
        return true;
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable World world, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);
        tooltip.add(I18n.format("block.refinedstorage:crafterslot.tooltip", TextFormatting.WHITE + I18n.format("6") + I18n.format("3") + TextFormatting.GREEN));
        tooltip.add(I18n.format("block.refinedstorage:crafterspeed.tooltip",TextFormatting.WHITE + I18n.format("2") + I18n.format("5")+ TextFormatting.GREEN));
    }
}
