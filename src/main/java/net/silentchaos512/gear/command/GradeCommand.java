package net.silentchaos512.gear.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.silentchaos512.gear.api.material.IMaterialInstance;
import net.silentchaos512.gear.api.material.MaterialList;
import net.silentchaos512.gear.api.part.MaterialGrade;
import net.silentchaos512.gear.gear.material.MaterialInstance;
import net.silentchaos512.gear.item.MainPartItem;
import net.silentchaos512.gear.util.TextUtil;

public final class GradeCommand {
    private GradeCommand() {}

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("sgear_grade")
                .requires(source -> source.hasPermission(2))
                .then(Commands.literal("set")
                        .then(Commands.argument("grade", StringArgumentType.string())
                                .executes(context ->
                                        runSet(context, StringArgumentType.getString(context, "grade"))
                                )
                        )
                )
        );
    }

    private static int runSet(CommandContext<CommandSourceStack> context, String gradeStr) throws CommandSyntaxException {
        MaterialGrade grade = MaterialGrade.fromString(gradeStr);
        ServerPlayer player = context.getSource().getPlayerOrException();
        ItemStack stack = player.getMainHandItem();

        if (stack.getItem() instanceof MainPartItem) {
            MaterialList materials = MainPartItem.getMaterials(stack);
            for(IMaterialInstance material : materials) {
                grade.setGradeOnStack(material.getItem());
            }
            
            MainPartItem part = (MainPartItem) stack.getItem();
            ItemStack result = part.createOne(materials.stream().map(material -> MaterialInstance.from(material.getItem())).toList());
            player.setItemInHand(InteractionHand.MAIN_HAND, result.copy());
            context.getSource().sendSuccess(TextUtil.translate("command", "grade.success", grade.name(), stack.getHoverName()), false);
            return 1;
        } else {
            context.getSource().sendFailure(TextUtil.translate("command", "grade.notMaterial", stack.getHoverName()));
            return 0;
        }
    }
}
