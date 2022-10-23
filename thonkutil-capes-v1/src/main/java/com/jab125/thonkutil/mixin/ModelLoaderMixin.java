/*
 * Copyright (c) 2021, 2022 Jab125 & LimeAppleBoat
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jab125.thonkutil.mixin;

import com.jab125.thonkutil.ThonkUtilCapes;
import com.jab125.thonkutil.api.CapeItem;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
@Mixin(ModelLoader.class)
public class ModelLoaderMixin {
    @Inject(method = "loadModelFromJson", at = @At(value = "HEAD"), cancellable = true)
    public void loadModelFromJson(Identifier id, CallbackInfoReturnable<JsonUnbakedModel> cir) {
        // System.out.println(id.getNamespace() + ":" + id.getPath().split("/")[1]);
        if (!id.getPath().startsWith("item/") || !(Registry.ITEM.get(Identifier.tryParse(id.getNamespace() + ":" + id.getPath().split("/")[1])) instanceof CapeItem))
            return;
        String b = id.getNamespace() + ":" + "cape/" + id.getPath().split("/")[1];
        //Here, we can do different checks to see if the current item is a block-item, a tool, or other.
        //This can be done in a lot of different ways, like putting all our items in a Set or a List and checking if the current item is contained inside.
        //For this tutorial, we only have 1 item, so we will not be doing that, and we will be going with "generated" as default item type.
        String modelJson = ThonkUtilCapes.createItemModelJson(Identifier.tryParse(b), "cape");
        if ("".equals(modelJson)) return;
        //We check if the json string we get is valid before continuing.
        JsonUnbakedModel model = JsonUnbakedModel.deserialize(modelJson);
        model.id = id.toString();
        cir.setReturnValue(model);
    }
}
