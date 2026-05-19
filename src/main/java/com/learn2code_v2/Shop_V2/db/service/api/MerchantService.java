package com.learn2code_v2.Shop_V2.db.service.api;

import com.learn2code_v2.Shop_V2.domain.Merchant;
import org.springframework.lang.Nullable;

import java.util.List;

public interface MerchantService {
    List<Merchant> getMerchants();
    @Nullable
    Merchant get(int id);

    Integer add(Merchant merchant);
}
