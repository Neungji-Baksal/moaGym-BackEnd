package com.project.moagym.sevice;

import com.project.moagym.domain.Img;
import com.project.moagym.domain.Item;
import com.project.moagym.repository.ImgRepository;
import com.project.moagym.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ImgService {
    private final ImgRepository imgRepository;

    @Transactional
    public void saveImg(Img img) {
        imgRepository.save(img);
    }
}
