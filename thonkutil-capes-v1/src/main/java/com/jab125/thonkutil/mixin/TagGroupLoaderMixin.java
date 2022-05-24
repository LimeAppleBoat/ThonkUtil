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

import com.jab125.thonkutil.api.CapeItem;
import net.minecraft.resource.ResourceManager;
import net.minecraft.tag.Tag;
import net.minecraft.tag.TagGroupLoader;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(TagGroupLoader.class)
public class TagGroupLoaderMixin {

    @Shadow
    @Final
    private String dataType;

    @Inject(method = "loadTags", at = @At("RETURN"), cancellable = true)
    private void loadTagInject(ResourceManager manager, CallbackInfoReturnable<Map<Identifier, Tag.Builder>> cir) {
        if (!this.dataType.equals("tags/items")) return;
        Tag.Builder a = Tag.Builder.create();
        Registry.ITEM.forEach((item -> {
            if (item instanceof CapeItem) a.add(Registry.ITEM.getId(item), "thonkutil");
        }));
        Map<Identifier, Tag.Builder> b = cir.getReturnValue();
        var n = b.get(Identifier.tryParse("trinkets:chest/cape"));
        if (n != null) {
            Registry.ITEM.forEach((item -> {
                if (item instanceof CapeItem)
                    n.add(TrackedEntryAccessor.createTrackedEntry((Tag.Entry) (new Tag.ObjectEntry(Registry.ITEM.getId(item))), "thonkutil"));
            }));
            b.put(Identifier.tryParse("trinkets:chest/cape"), n);
        } else {
            b.put(Identifier.tryParse("trinkets:chest/cape"), a);
        }
        cir.setReturnValue(b);
        cir.getReturnValue().forEach(((identifier, builder) -> {
            //System.out.println(identifier.toString() + ", " +  builder.toJson().toString() + ", " + this.dataType);
        }));
    }
}
