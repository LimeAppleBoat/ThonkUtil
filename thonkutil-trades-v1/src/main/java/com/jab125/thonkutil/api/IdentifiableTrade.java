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
package com.jab125.thonkutil.api;

import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffer;

@SuppressWarnings("unused")
public interface IdentifiableTrade {
    Identifier getId();

    default boolean isSameType(IdentifiableTrade trade) {
        return trade.getId().equals(getId());
    }

    static boolean isSameType(IdentifiableTrade trade, IdentifiableTrade trade2) {
        return trade.getId().equals(trade2.getId());
    }

    static Identifier getIdOf(TradeOffer tradeOffer) {
        try {
            return tradeOffer.getId();
        } catch (ClassCastException e) {
            return new Identifier("minecraft", "trade_offer");
        }
    }
}
