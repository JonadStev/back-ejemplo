package com.tienda.corebusiness.service;

import com.tienda.corebusiness.model.Banner;
import com.tienda.corebusiness.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class BannerServiceImpl implements BannerService{

    @Autowired
    BannerRepository bannerRepository;

    @Override
    public Banner saveBanner(Banner banner) {
        return bannerRepository.save(banner);
    }

    @Override
    public List<Banner> getBanners() {
        return (List<Banner>) bannerRepository.findAll();
    }

    @Override
    public Banner getBannerById(long id) {
        Optional<Banner> banner = bannerRepository.findById(id);
        return banner.get();
    }

    @Override
    public String deleteBannerById(long id) {
        try {
            bannerRepository.delete(getBannerById(id));
            return "Baner eliminado";
        }catch (Exception e){
            return "El banner no se ha podido guardar";
        }
    }

    @Override
    public byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }

    @Override
    public byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}
