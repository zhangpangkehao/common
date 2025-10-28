package com.xiaojinzi.tally.module.base.spi

import androidx.annotation.DrawableRes
import androidx.annotation.Keep
import com.xiaojinzi.component.anno.ServiceAnno
import com.xiaojinzi.tally.lib.res.R

interface IconMappingSpi {

    @Keep
    data class IconMappingDto(
        val name: String,
        @DrawableRes
        val rsd: Int,
    )

    object IconMapping {

        val Book1 = IconMappingDto(
            name = "book1",
            rsd = R.drawable.res_book1,
        )

        val Book2 = IconMappingDto(
            name = "book2",
            rsd = R.drawable.res_book2,
        )

        val Shopping1 = IconMappingDto(
            name = "shopping1",
            rsd = R.drawable.res_shopping1,
        )

        val Nightstand1 = IconMappingDto(
            name = "nightstand1",
            rsd = R.drawable.res_nightstand1,
        )

        val Lipstick1 = IconMappingDto(
            name = "lipstick1",
            rsd = R.drawable.res_lipstick1,
        )

        val Airpods1 = IconMappingDto(
            name = "airpods1",
            rsd = R.drawable.res_airpods1,
        )

        val Alipay1 = IconMappingDto(
            name = "alipay1",
            rsd = R.drawable.res_alipay1,
        )

        val Money1 = IconMappingDto(
            name = "money1",
            rsd = R.drawable.res_money1,
        )

        val Dollar1 = IconMappingDto(
            name = "dollar1",
            rsd = R.drawable.res_dollar1,
        )

        val WashingMachine1 = IconMappingDto(
            name = "washingMachine1",
            rsd = R.drawable.res_washing_machine1,
        )

        val Watch1 = IconMappingDto(
            name = "watch1",
            rsd = R.drawable.res_watch1,
        )

        val BabyBottle1 = IconMappingDto(
            name = "babyBottle1",
            rsd = R.drawable.res_baby_bottle1,
        )

        val Cardigan1 = IconMappingDto(
            name = "cardigan1",
            rsd = R.drawable.res_cardigan1,
        )

        val Dog1 = IconMappingDto(
            name = "dog1",
            rsd = R.drawable.res_dog1,
        )

        val Bird1 = IconMappingDto(
            name = "bird1",
            rsd = R.drawable.res_bird1,
        )

        val Cattle1 = IconMappingDto(
            name = "cattle1",
            rsd = R.drawable.res_cattle1,
        )

        val Dolphin1 = IconMappingDto(
            name = "dolphin1",
            rsd = R.drawable.res_dolphin1,
        )

        val Duck1 = IconMappingDto(
            name = "duck1",
            rsd = R.drawable.res_duck1,
        )

        val Elephant1 = IconMappingDto(
            name = "elephant1",
            rsd = R.drawable.res_elephant1,
        )

        val Fish1 = IconMappingDto(
            name = "fish1",
            rsd = R.drawable.res_fish1,
        )

        val Frog1 = IconMappingDto(
            name = "frog1",
            rsd = R.drawable.res_frog1,
        )

        val Hippo1 = IconMappingDto(
            name = "hippo1",
            rsd = R.drawable.res_hippo1,
        )

        val Monkey1 = IconMappingDto(
            name = "monkey1",
            rsd = R.drawable.res_monkey1,
        )

        val Panda1 = IconMappingDto(
            name = "panda1",
            rsd = R.drawable.res_panda1,
        )

        val Whale1 = IconMappingDto(
            name = "whale1",
            rsd = R.drawable.res_whale1,
        )

        val Printer1 = IconMappingDto(
            name = "printer1",
            rsd = R.drawable.res_printer1,
        )

        val Brush1 = IconMappingDto(
            name = "brush1",
            rsd = R.drawable.res_brush1,
        )

        val ChopsticksFork1 = IconMappingDto(
            name = "chopsticksFork1",
            rsd = R.drawable.res_chopsticks_fork1,
        )

        val Bread1 = IconMappingDto(
            name = "bread1",
            rsd = R.drawable.res_bread1,
        )

        val Rice1 = IconMappingDto(
            name = "rice1",
            rsd = R.drawable.res_rice1,
        )

        val Noodles1 = IconMappingDto(
            name = "noodles1",
            rsd = R.drawable.res_noodles1,
        )

        val Cocktail1 = IconMappingDto(
            name = "cocktail1",
            rsd = R.drawable.res_cocktail1,
        )

        val Candy1 = IconMappingDto(
            name = "candy1",
            rsd = R.drawable.res_candy1,
        )

        val Croissant1 = IconMappingDto(
            name = "croissant1",
            rsd = R.drawable.res_croissant1,
        )

        val CookingPot1 = IconMappingDto(
            name = "cookingPot1",
            rsd = R.drawable.res_cooking_pot1,
        )

        val Bottle1 = IconMappingDto(
            name = "bottle1",
            rsd = R.drawable.res_bottle1,
        )

        val Coupon1 = IconMappingDto(
            name = "coupon1",
            rsd = R.drawable.res_coupon1,
        )

        val BankCard1 = IconMappingDto(
            name = "bankCard1",
            rsd = R.drawable.res_bank_card1,
        )

        val BankCard2 = IconMappingDto(
            name = "bankCard2",
            rsd = R.drawable.res_bank_card2,
        )

        val Taobao1 = IconMappingDto(
            name = "taobao1",
            rsd = R.drawable.res_taobao1,
        )

        val Wechat1 = IconMappingDto(
            name = "wechat1",
            rsd = R.drawable.res_wechat1,
        )

        val HuaBei1 = IconMappingDto(
            name = "huabei1",
            rsd = R.drawable.res_hua_bei1,
        )

        val JD1 = IconMappingDto(
            name = "jd1",
            rsd = R.drawable.res_jd1,
        )

        val JD2 = IconMappingDto(
            name = "jd2",
            rsd = R.drawable.res_jd2,
        )

        val More1 = IconMappingDto(
            name = "more1",
            rsd = R.drawable.res_more1,
        )

        val More2 = IconMappingDto(
            name = "more2",
            rsd = R.drawable.res_more2,
        )

        val More3 = IconMappingDto(
            name = "more3",
            rsd = R.drawable.res_more3,
        )

        val Bank1 = IconMappingDto(
            name = "bank1",
            rsd = R.drawable.res_bank1,
        )

        val FirstAidKit1 = IconMappingDto(
            name = "firstAidKit1",
            rsd = R.drawable.res_first_aid_kit1,
        )

        val Bus1 = IconMappingDto(
            name = "bus1",
            rsd = R.drawable.res_bus1,
        )

        val School1 = IconMappingDto(
            name = "school1",
            rsd = R.drawable.res_school1,
        )

        val Vip1 = IconMappingDto(
            name = "vip1",
            rsd = R.drawable.res_vip1,
        )

        val ShoppingCart1 = IconMappingDto(
            name = "shoppingCart1",
            rsd = R.drawable.res_shopping_cart1,
        )

        val ChartStock1 = IconMappingDto(
            name = "chartStock1",
            rsd = R.drawable.res_chart_stock1,
        )

        val Funds1 = IconMappingDto(
            name = "funds1",
            rsd = R.drawable.res_funds1,
        )

        val Income1 = IconMappingDto(
            name = "income1",
            rsd = R.drawable.res_income1,
        )

        val Expenses1 = IconMappingDto(
            name = "expenses1",
            rsd = R.drawable.res_expenses1,
        )

        val Road1 = IconMappingDto(
            name = "road1",
            rsd = R.drawable.res_road1,
        )

        val Taxi1 = IconMappingDto(
            name = "taxi1",
            rsd = R.drawable.res_taxi1,
        )

        val Subway1 = IconMappingDto(
            name = "subway1",
            rsd = R.drawable.res_subway1,
        )

        val Parking1 = IconMappingDto(
            name = "parking1",
            rsd = R.drawable.res_parking1,
        )

        val GasStation1 = IconMappingDto(
            name = "gasStation1",
            rsd = R.drawable.res_gas_station1,
        )

        val Repair1 = IconMappingDto(
            name = "repair1",
            rsd = R.drawable.res_repair1,
        )

        val GameConsole1 = IconMappingDto(
            name = "gameConsole1",
            rsd = R.drawable.res_game_console1,
        )

        val GamePad1 = IconMappingDto(
            name = "gamePad1",
            rsd = R.drawable.res_game_pad1,
        )

        val Microphone1 = IconMappingDto(
            name = "microphone1",
            rsd = R.drawable.res_microphone1,
        )

        val Movie1 = IconMappingDto(
            name = "movie1",
            rsd = R.drawable.res_movie1,
        )

        val Dumbbell1 = IconMappingDto(
            name = "dumbbell1",
            rsd = R.drawable.res_dumbbell1,
        )

        val Fitness1 = IconMappingDto(
            name = "fitness1",
            rsd = R.drawable.res_fitness1,
        )

        val Chess1 = IconMappingDto(
            name = "chess1",
            rsd = R.drawable.res_chess1,
        )

        val Gift1 = IconMappingDto(
            name = "gift1",
            rsd = R.drawable.res_gift1,
        )

        val Gift2 = IconMappingDto(
            name = "gift2",
            rsd = R.drawable.res_gift2,
        )

        val RedPacket1 = IconMappingDto(
            name = "redPacket1",
            rsd = R.drawable.res_red_packet1,
        )

        val Stethoscope1 = IconMappingDto(
            name = "stethoscope1",
            rsd = R.drawable.res_stethoscope1,
        )

        val Hospital1 = IconMappingDto(
            name = "hospital1",
            rsd = R.drawable.res_hospital1,
        )

        val House1 = IconMappingDto(
            name = "house1",
            rsd = R.drawable.res_house1,
        )

        val WaterElectricityCharge1 = IconMappingDto(
            name = "waterElectricityCharge1",
            rsd = R.drawable.res_water_electricity_charge1,
        )

        val Wage1 = IconMappingDto(
            name = "wage1",
            rsd = R.drawable.res_wage1,
        )

        val PartTimeJob1 = IconMappingDto(
            name = "partTimeJob1",
            rsd = R.drawable.res_clock1,
        )

        val Bonus1 = IconMappingDto(
            name = "bonus1",
            rsd = R.drawable.res_bonus1,
        )

        val Market1 = IconMappingDto(
            name = "market1",
            rsd = R.drawable.res_market1,
        )

        val Restock1 = IconMappingDto(
            name = "restock1",
            rsd = R.drawable.res_restock1,
        )

        val UserMoney1 = IconMappingDto(
            name = "userMoney1",
            rsd = R.drawable.res_user_money1,
        )

        val Transporter1 = IconMappingDto(
            name = "transporter1",
            rsd = R.drawable.res_transporter1,
        )

        val Ad1 = IconMappingDto(
            name = "ad1",
            rsd = R.drawable.res_ad1,
        )

        val Interest1 = IconMappingDto(
            name = "interest1",
            rsd = R.drawable.res_interest1,
        )

        val Invest1 = IconMappingDto(
            name = "invest1",
            rsd = R.drawable.res_invest1,
        )

        val Refund1 = IconMappingDto(
            name = "refund1",
            rsd = R.drawable.res_refund1,
        )

        val Bookmark1 = IconMappingDto(
            name = "bookmark1",
            rsd = R.drawable.res_bookmark1,
        )

        val Bookmark2 = IconMappingDto(
            name = "bookmark2",
            rsd = R.drawable.res_bookmark2,
        )

        val Camera1 = IconMappingDto(
            name = "camera1",
            rsd = R.drawable.res_camera1,
        )

        val Image1 = IconMappingDto(
            name = "image1",
            rsd = R.drawable.res_image1,
        )

        val Image2 = IconMappingDto(
            name = "image2",
            rsd = R.drawable.res_image2,
        )

        val Label1 = IconMappingDto(
            name = "label1",
            rsd = R.drawable.res_label1,
        )

        val Label2 = IconMappingDto(
            name = "label2",
            rsd = R.drawable.res_label2,
        )

        val Refresh1 = IconMappingDto(
            name = "refresh1",
            rsd = R.drawable.res_refresh1,
        )

        val Folder1 = IconMappingDto(
            name = "folder1",
            rsd = R.drawable.res_folder1,
        )

        val Ambulance1 = IconMappingDto(
            name = "ambulance1",
            rsd = R.drawable.res_ambulance1,
        )

        val CardioElectric1 = IconMappingDto(
            name = "cardioElectric1",
            rsd = R.drawable.res_cardio_electric1,
        )

        val TraditionalChineseMedicine1 = IconMappingDto(
            name = "traditionalChineseMedicine1",
            rsd = R.drawable.res_traditional_chinese_medicine1,
        )

        val Infusion1 = IconMappingDto(
            name = "infusion1",
            rsd = R.drawable.res_infusion1,
        )

        val Needle1 = IconMappingDto(
            name = "needle1",
            rsd = R.drawable.res_needle1,
        )

        val Teeth1 = IconMappingDto(
            name = "teeth1",
            rsd = R.drawable.res_teeth1,
        )

        val AppStore1 = IconMappingDto(
            name = "appStore1",
            rsd = R.drawable.res_app_store1,
        )

        val Apple1 = IconMappingDto(
            name = "apple1",
            rsd = R.drawable.res_apple1,
        )

        val Android1 = IconMappingDto(
            name = "android1",
            rsd = R.drawable.res_android1,
        )

        val Clock1 = IconMappingDto(
            name = "clock1",
            rsd = R.drawable.res_clock1,
        )

        val Calendar1 = IconMappingDto(
            name = "calendar1",
            rsd = R.drawable.res_calendar1,
        )

        val Briefs1 = IconMappingDto(
            name = "briefs1",
            rsd = R.drawable.res_briefs1,
        )

        val PantsShort1 = IconMappingDto(
            name = "pantsShort1",
            rsd = R.drawable.res_pants_short1,
        )

        val Glasses1 = IconMappingDto(
            name = "glasses1",
            rsd = R.drawable.res_glasses1,
        )

        val Pants1 = IconMappingDto(
            name = "pants1",
            rsd = R.drawable.res_pants1,
        )

        val Slippers1 = IconMappingDto(
            name = "slippers1",
            rsd = R.drawable.res_slippers1,
        )

        val Socks1 = IconMappingDto(
            name = "socks1",
            rsd = R.drawable.res_socks1,
        )

        val TShirt1 = IconMappingDto(
            name = "tShirt1",
            rsd = R.drawable.res_t_shirt1,
        )

        val Wifi1 = IconMappingDto(
            name = "wifi1",
            rsd = R.drawable.res_wifi1,
        )

        val Telephone1 = IconMappingDto(
            name = "telephone1",
            rsd = R.drawable.res_telephone1,
        )

        val Journey1 = IconMappingDto(
            name = "journey1",
            rsd = R.drawable.res_journey1,
        )

        val Business1 = IconMappingDto(
            name = "business1",
            rsd = R.drawable.res_business1,
        )

        val Home1 = IconMappingDto(
            name = "home1",
            rsd = R.drawable.res_home1,
        )

        val Toy1 = IconMappingDto(
            name = "toy1",
            rsd = R.drawable.res_toy1,
        )

        val Food1 = IconMappingDto(
            name = "food1",
            rsd = R.drawable.res_food1,
        )

        val CatFood1 = IconMappingDto(
            name = "catFood1",
            rsd = R.drawable.res_cat_food1,
        )

        val Help1 = IconMappingDto(
            name = "help1",
            rsd = R.drawable.res_help1,
        )

        val BreakRules1 = IconMappingDto(
            name = "breakRules1",
            rsd = R.drawable.res_break_rules1,
        )

        val Airplane1 = IconMappingDto(
            name = "airplane1",
            rsd = R.drawable.res_airplane1,
        )

        val MonitorCamera1 = IconMappingDto(
            name = "monitorCamera1",
            rsd = R.drawable.res_monitor_camera1,
        )

        val Bike1 = IconMappingDto(
            name = "bike1",
            rsd = R.drawable.res_bike1,
        )

        val ChargingTreasure1 = IconMappingDto(
            name = "chargingTreasure1",
            rsd = R.drawable.res_charging_treasure1,
        )

        val Calculator1 = IconMappingDto(
            name = "calculator1",
            rsd = R.drawable.res_calculator1,
        )

        val Music1 = IconMappingDto(
            name = "music1",
            rsd = R.drawable.res_music1,
        )

        val Headset1 = IconMappingDto(
            name = "headset1",
            rsd = R.drawable.res_headset1,
        )

        val Piano1 = IconMappingDto(
            name = "piano1",
            rsd = R.drawable.res_piano1,
        )

        val ZhongguoBank1 = IconMappingDto(
            name = "zhongguoBank1",
            rsd = R.drawable.res_zhongguo_bank1,
        )

        val JiansheBank1 = IconMappingDto(
            name = "jiansheBank1",
            rsd = R.drawable.res_jianshe_bank1,
        )

        val GongshangBank1 = IconMappingDto(
            name = "gongshangBank1",
            rsd = R.drawable.res_gongshang_bank1,
        )

        val NongyeBank1 = IconMappingDto(
            name = "nongyeBank1",
            rsd = R.drawable.res_nongye_bank1,
        )

        val JiaotongBank1 = IconMappingDto(
            name = "jiaotongBank1",
            rsd = R.drawable.res_jiaotong_bank1,
        )

        val ZhaoshangBank1 = IconMappingDto(
            name = "zhaoshangBank1",
            rsd = R.drawable.res_zhaoshang_bank1,
        )

        val ShanghaiBank1 = IconMappingDto(
            name = "shanghaiBank1",
            rsd = R.drawable.res_shanghai_bank1,
        )

        val JiangsuBank1 = IconMappingDto(
            name = "jiangsuBank1",
            rsd = R.drawable.res_jiangsu_bank1,
        )

        val Google1 = IconMappingDto(
            name = "google1",
            rsd = R.drawable.res_google1,
        )

        val Github1 = IconMappingDto(
            name = "github1",
            rsd = R.drawable.res_github1,
        )

        val QQ1 = IconMappingDto(
            name = "qq1",
            rsd = R.drawable.res_qq1,
        )

        val Aquarius1 = IconMappingDto(
            name = "aquarius1",
            rsd = R.drawable.res_aquarius1,
        )

        val Aries1 = IconMappingDto(
            name = "aries1",
            rsd = R.drawable.res_aries1,
        )

        val Cancer1 = IconMappingDto(
            name = "cancer1",
            rsd = R.drawable.res_cancer1,
        )

        val Capricornus1 = IconMappingDto(
            name = "capricornus1",
            rsd = R.drawable.res_capricornus1,
        )

        val Gemini1 = IconMappingDto(
            name = "gemini1",
            rsd = R.drawable.res_gemini1,
        )

        val Leo1 = IconMappingDto(
            name = "leo1",
            rsd = R.drawable.res_leo1,
        )

        val Libra1 = IconMappingDto(
            name = "libra1",
            rsd = R.drawable.res_libra1,
        )

        val Pisces1 = IconMappingDto(
            name = "pisces1",
            rsd = R.drawable.res_pisces1,
        )

        val Sagittarius1 = IconMappingDto(
            name = "sagittarius1",
            rsd = R.drawable.res_sagittarius1,
        )

        val Scorpio1 = IconMappingDto(
            name = "scorpio1",
            rsd = R.drawable.res_scorpio1,
        )

        val Taurus1 = IconMappingDto(
            name = "taurus1",
            rsd = R.drawable.res_taurus1,
        )

        val Virgo1 = IconMappingDto(
            name = "virgo1",
            rsd = R.drawable.res_virgo1,
        )

        val VegetableBasket1 = IconMappingDto(
            name = "vegetableBasket1",
            rsd = R.drawable.res_vegetable_basket1,
        )

        val BarberClippers1 = IconMappingDto(
            name = "barberClippers1",
            rsd = R.drawable.res_barber_clippers1,
        )

        val Makeups1 = IconMappingDto(
            name = "makeups1",
            rsd = R.drawable.res_makeups1,
        )

        val Watermelon1 = IconMappingDto(
            name = "watermelon1",
            rsd = R.drawable.res_watermelon1,
        )

        val Paint1 = IconMappingDto(
            name = "paint1",
            rsd = R.drawable.res_paint1,
        )

        val Tissue1 = IconMappingDto(
            name = "tissue1",
            rsd = R.drawable.res_tissue1,
        )

        val Tissue2 = IconMappingDto(
            name = "tissue2",
            rsd = R.drawable.res_tissue2,
        )

        /**
         * Key 是 [IconMappingDto.name]
         * value 是 [IconMappingDto]
         */
        val AllMap = listOf(
            Book1,
            Book2,
            Shopping1,
            Nightstand1,
            Lipstick1,
            Airpods1,
            Alipay1,
            Money1,
            Dollar1,
            WashingMachine1,
            Watch1,
            BabyBottle1,
            Cardigan1,
            Dog1,
            Bird1,
            Cattle1,
            Dolphin1,
            Duck1,
            Elephant1,
            Fish1,
            Frog1,
            Hippo1,
            Monkey1,
            Panda1,
            Whale1,
            Printer1,
            Brush1,
            ChopsticksFork1,
            Bread1,
            Rice1,
            Noodles1,
            Cocktail1,
            Candy1,
            Croissant1,
            CookingPot1,
            Bottle1,
            Coupon1,
            BankCard1,
            BankCard2,
            Taobao1,
            Wechat1,
            HuaBei1,
            JD1,
            JD2,
            More1,
            More2,
            More3,
            Bank1,
            FirstAidKit1,
            Bus1,
            School1,
            Vip1,
            ShoppingCart1,
            ChartStock1,
            Funds1,
            Income1,
            Expenses1,
            Road1,
            Taxi1,
            Subway1,
            Parking1,
            GasStation1,
            Repair1,
            GameConsole1,
            GamePad1,
            Microphone1,
            Movie1,
            Dumbbell1,
            Fitness1,
            Chess1,
            Gift1,
            Gift2,
            RedPacket1,
            Stethoscope1,
            Hospital1,
            House1,
            WaterElectricityCharge1,
            Wage1,
            PartTimeJob1,
            Bonus1,
            Market1,
            Restock1,
            UserMoney1,
            Transporter1,
            Ad1,
            Interest1,
            Invest1,
            Refund1,
            Bookmark1,
            Bookmark2,
            Camera1,
            Image1,
            Image2,
            Label1,
            Label2,
            Refresh1,
            Folder1,
            Ambulance1,
            CardioElectric1,
            TraditionalChineseMedicine1,
            Infusion1,
            Needle1,
            Teeth1,
            AppStore1,
            Apple1,
            Android1,
            Clock1,
            Calendar1,
            Briefs1,
            PantsShort1,
            Glasses1,
            Pants1,
            Slippers1,
            Socks1,
            TShirt1,
            Wifi1,
            Telephone1,
            Journey1,
            Business1,
            Home1,
            Toy1,
            Food1,
            CatFood1,
            Help1,
            BreakRules1,
            Airplane1,
            MonitorCamera1,
            Bike1,
            ChargingTreasure1,
            Calculator1,
            Music1,
            Headset1,
            Piano1,
            ZhongguoBank1,
            JiansheBank1,
            GongshangBank1,
            NongyeBank1,
            JiaotongBank1,
            ZhaoshangBank1,
            ShanghaiBank1,
            JiangsuBank1,
            Google1,
            Github1,
            QQ1,
            Aquarius1,
            Aries1,
            Cancer1,
            Capricornus1,
            Gemini1,
            Leo1,
            Libra1,
            Pisces1,
            Sagittarius1,
            Scorpio1,
            Taurus1,
            Virgo1,
            VegetableBasket1,
            BarberClippers1,
            Makeups1,
            Watermelon1,
            Paint1,
            Tissue1,
            Tissue2,
        ).associateBy { it.name }

    }

    @DrawableRes
    operator fun get(iconName: String?): Int?

}

@ServiceAnno(IconMappingSpi::class)
class IconMappingSpiImpl : IconMappingSpi {

    override fun get(iconName: String?): Int? {
        return iconName?.let {
            IconMappingSpi.IconMapping.AllMap[it]?.rsd
        }
    }

}