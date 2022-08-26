package com.tienda.corebusiness.repository;

import com.tienda.corebusiness.model.Banner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerRepository extends CrudRepository<Banner,Long> {
}
