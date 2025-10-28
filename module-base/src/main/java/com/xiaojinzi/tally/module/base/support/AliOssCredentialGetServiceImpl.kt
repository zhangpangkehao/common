package com.xiaojinzi.tally.module.base.support

import com.xiaojinzi.component.anno.ServiceAnno
import com.xiaojinzi.lib.common.res.tx.cos.AliOssCredentialInfoDto
import com.xiaojinzi.module.common.base.spi.AliOssCredentialGetService

@ServiceAnno(AliOssCredentialGetService::class)
class AliOssCredentialGetServiceImpl: AliOssCredentialGetService {

    override suspend fun getInfo(): AliOssCredentialInfoDto {
        return AppServices.appNetworkSpi.getAliOssStsToken().let {
            AliOssCredentialInfoDto(
                accessKeyId = it.accessKeyId,
                accessKeySecret = it.accessKeySecret,
                securityToken = it.securityToken,
                expiration = it.expiration,
            )
        }
    }

}