package com.tienda.corebusiness.service;

import com.tienda.corebusiness.model.Banner;

import java.util.List;
import java.util.Optional;

public interface BannerService {

    Banner saveBanner(Banner banner);
    List<Banner> getBanners();
    Banner getBannerById(long id);
    String deleteBannerById(long id);
    byte[] compressBytes(byte[] data);
    byte[] decompressBytes(byte[] data);
}
