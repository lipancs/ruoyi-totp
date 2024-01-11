package com.ruoyi.framework.web.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.exception.user.TcodeNotMatchException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.sign.Base64;

import dev.samstevens.totp.code.CodeVerifier;
import dev.samstevens.totp.code.HashingAlgorithm;
import dev.samstevens.totp.exceptions.QrGenerationException;
import dev.samstevens.totp.qr.QrData;
import dev.samstevens.totp.qr.QrDataFactory;
import dev.samstevens.totp.qr.QrGenerator;
import dev.samstevens.totp.secret.SecretGenerator;


@Service
public class MfaSetup {

    //注入双因子生成相应工具类
    @Autowired
    private SecretGenerator secretGenerator;
    @Autowired
    private QrDataFactory qrDataFactory;
    @Autowired
    private QrGenerator qrGenerator;
    @Autowired
    private CodeVerifier verifier;
    @Autowired
    private RedisCache redisCache;
    

    //生成双因子二维码 
    public String setupDevice() throws QrGenerationException {
        // Generate and store the secret
        String totpkey=CacheConstants.Tcode_KEY+SecurityUtils.getUsername();
        String secret = secretGenerator.generate();
        System.out.println(secret);
        QrData data = qrDataFactory.newBuilder()
            .label("345234498@qq.com")
            .secret(secret)
            .algorithm(HashingAlgorithm.SHA1)
            .issuer("AppName")
            .build();
       // System.out.println(data);
        byte[] imagedata=qrGenerator.generate(data);
        String dataUri = Base64.encode(imagedata);
        System.out.println(dataUri);
        redisCache.deleteObject(totpkey);
        redisCache.setCacheObject(totpkey, secret, 2, TimeUnit.MINUTES);
        return dataUri;
    }

    public Boolean secretChcek(String tcode)
    {
       String totpkey=CacheConstants.Tcode_KEY+SecurityUtils.getUsername();
       String redissecret=redisCache.getCacheObject(totpkey);
        System.out.println(redissecret);
        if(!verifier.isValidCode(redissecret, tcode))
         {
            throw new TcodeNotMatchException();
         }
        return true;

    }
  
}
