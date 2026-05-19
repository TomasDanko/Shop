package com.learn2code_v2.Shop_V2.db.service.impl;

import com.learn2code_v2.Shop_V2.db.repository.MerchantRepository;
import com.learn2code_v2.Shop_V2.db.service.api.MerchantService;
import com.learn2code_v2.Shop_V2.domain.Merchant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantServiceImpl implements MerchantService {

    private final MerchantRepository merchantRepository;

    public MerchantServiceImpl(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    @Override
    public List<Merchant> getMerchants() {
        return merchantRepository.getAll();
    }

    @Override
    public Merchant get(int id) {
        return merchantRepository.get(id);
    }

    @Override
    public Integer add(Merchant merchant) {
        return merchantRepository.add(merchant);
    }
}
