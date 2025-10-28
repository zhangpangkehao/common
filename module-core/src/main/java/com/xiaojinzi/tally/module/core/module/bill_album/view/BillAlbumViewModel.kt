package com.xiaojinzi.tally.module.core.module.bill_album.view

import androidx.lifecycle.viewModelScope
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.StateHotObservable
import com.xiaojinzi.support.annotation.ViewLayer
import com.xiaojinzi.support.ktx.orNull
import com.xiaojinzi.support.ktx.sharedStateIn
import com.xiaojinzi.support.ktx.toStringItemDto
import com.xiaojinzi.tally.lib.res.model.support.toLocalImageItemDto
import com.xiaojinzi.tally.module.base.support.AppServices
import com.xiaojinzi.tally.module.core.module.bill_album.domain.BillAlbumUseCase
import com.xiaojinzi.tally.module.core.module.bill_album.domain.BillAlbumUseCaseImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@ViewLayer
class BillAlbumViewModel(
    private val useCase: BillAlbumUseCase = BillAlbumUseCaseImpl(),
) : BaseViewModel(),
    BillAlbumUseCase by useCase {

    @StateHotObservable
    val billImageListStateObVo: Flow<List<BillAlbumItemVo>> = useCase
        .billImageListStateOb
        .map { billImageList ->
            buildList {
                billImageList.forEach { itemPair ->
                    val billDetail = itemPair.first
                    this.add(
                        element = BillAlbumItemHeaderVo(
                            billId = billDetail.core.id,
                            billType = billDetail.core.type,
                            billTime = billDetail.core.time,
                            categoryIcon = AppServices.iconMappingSpi[billDetail.category?.getAdapter?.iconName]?.toLocalImageItemDto(),
                            categoryName = billDetail.category?.getAdapter?.name?.toStringItemDto(),
                            billAmount = billDetail.core.amount.toYuan(),
                        )
                    )
                    itemPair.second.forEach { billImageItem ->
                        this.add(
                            element = BillAlbumItemNormalVo(
                                billImageId = billImageItem.id,
                                url = billImageItem.url.orNull(),
                            ),
                        )
                    }
                }
            }
        }.sharedStateIn(
            scope = viewModelScope,
        )

}