package com.arcee.parkit.common

import com.arcee.parkit.R

enum class VehicleTypeEnum(val type: String) {
    CAR("CAR"),
    LORRY("LORRY"),
    SCOOTER("SCOOTER"),
    BUS("BUS");

    companion object {
        fun getVehicleTypeIcon(type: VehicleTypeEnum): Int {
            return when (type) {
                CAR -> R.drawable.car_24
                LORRY -> R.drawable.lorry_24
                SCOOTER -> R.drawable.scooter_24
                BUS -> R.drawable.bus_24
            }
        }
    }
}